package giki.parser;

import giki.diagnostics.StopWatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.SwingUtilities;

import org.stringtemplate.v4.ST;

public class FileResourceStore implements ResourceStore {
	public interface Listener {
		void changedDirectory(FileResourceStore store);
	}
	
	private ArrayList<FileResourceStore.Listener> listeners = new ArrayList<FileResourceStore.Listener>();
	private String directory;
	
	public FileResourceStore(String directory) {
		setDirectoryDirect(directory);
	}
	
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	@Override
	public boolean exists(String name) {
		return new File(getResourcePath(name)).exists();
	}

	@Override
	public String getResourcePath(String name) {
		return directory + name + ".giki";
	}
	
	public String getDirectory() {
		return directory;
	}
	
	public void setDirectory(String directory) {
		if(!this.directory.equals(directory)) {
			setDirectoryDirect(directory);
			for(FileResourceStore.Listener listener: listeners)
				listener.changedDirectory(this);
		}
	}
	
	private void setDirectoryDirect(String directory) {
		if(!directory.endsWith(File.separator))
			directory = directory + File.separator;
		this.directory = directory;
	}

	@Override
	public String getName() {
		return new File(directory).getName();
	}

	@Override
	public List<Resource> getResources() {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		
//		StopWatch sw = new StopWatch();
//		sw.start();
		
		for(final File file: new File(directory).listFiles()) {
			if(file.isFile() && file.getName().endsWith(".giki")) {
//				final String name = file.getName().substring(0, file.getName().length() - 5);
				Resource resource = getFileResource(file);
				resources.add(resource);
//				names.add(file.getName().substring(0, file.getName().length() - 5));
			}
		}
		
//		sw.stop();
//		sw.printElapsed(System.out, "get file resources");
		
		return resources;
	}

	@Override
	public Resource create(String name) throws IOException {
		File file = new File(getResourcePath(name));
		if(file.createNewFile())
			return getFileResource(file);
		return null;
	}

	@Override
	public void remove(String name) throws IOException {
		new File(getResourcePath(name)).delete();
	}

	@Override
	public String ensureUnique(String name) {
		HashSet<String> names = new HashSet<String>();
		for(Resource resource: getResources())
			names.add(resource.getName());
		
		int no = 0;
		while(true) {
			String candidateName = name + (no + 1);
			if(!names.contains(candidateName))
				return candidateName;
			no++;
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof FileResourceStore) {
			FileResourceStore otherFileResourceStore = (FileResourceStore)other;
			File thisDirectory = new File(this.directory);
			File otherDirectory = new File(otherFileResourceStore.directory);
			try {
				return thisDirectory.getCanonicalPath().equals(otherDirectory.getCanonicalPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Resource getResource(String name) {
		return getFileResource(new File(getResourcePath(name)));
	}
	
	private Resource getFileResource(final File file) {
		return new Resource() {
			String name;
			
			{
				name = file.getName().substring(0, file.getName().length() - 5);
			}
			
			@Override
			public OutputStream getOutputStream() throws IOException {
				return new FileOutputStream(file);
			}
			
			@Override
			public InputStream getInputStream() throws IOException {
				return new FileInputStream(file);
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getPath() {
				try {
					return file.getCanonicalPath();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}

			@Override
			public void rename(String newName) throws IOException {
//				new File(getResourcePath(name)).renameTo(new File(getResourcePath(newName)));
//				name = newName;
				
				Path source = Paths.get(getResourcePath(name));
				Path target = Paths.get(getResourcePath(newName));
				Files.move(source, target);
			}
		};
	}
}
