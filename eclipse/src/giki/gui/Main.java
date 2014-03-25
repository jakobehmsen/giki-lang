package giki.gui;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import giki.parser.FileResourceStore;
import giki.parser.ResourceStore;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class Main {
	public static void main(String[] args) throws IOException {
		final java.util.Properties config = new Properties();
		
		final File configFile = new File("config.props");
		if(!configFile.exists()) {
			configFile.createNewFile();
			FileWriter fileWriter = new FileWriter(configFile);
			config.store(fileWriter, null);
			fileWriter.close();
		}
		
		FileReader fileReader = new FileReader(configFile);
		config.load(fileReader);
		fileReader.close();

		final FileResourceStore initialFileResourceStore;
		String fileResourceStoreDirectory = config.getProperty("FileResourceStore.Directory");
		if(fileResourceStoreDirectory == null) {
//			fileResourceStoreDirectory = System.getProperty("user.dir");
//			config.setProperty("FileResourceStore.Directory", fileResourceStoreDirectory);
//			config.setProperty("FileResourceStore.PopularDirectories", fileResourceStoreDirectory);
//			
//			FileWriter fileWriter = new FileWriter(configFile);
//			config.store(fileWriter, null);
//			fileWriter.close();
//			
//			initialFileResourceStore = new FileResourceStore(fileResourceStoreDirectory);
			initialFileResourceStore = null;
		} else {
			initialFileResourceStore = new FileResourceStore(fileResourceStoreDirectory);
		}

		String popularDirectoriesAsString = config.getProperty("FileResourceStore.PopularDirectories");
		final ArrayList<ResourceStore> popularResourceStores = new ArrayList<ResourceStore>();
		if(popularDirectoriesAsString == null) {
//			fileResourceStoreDirectory = System.getProperty("user.dir");
//			config.setProperty("FileResourceStore.PopularDirectories", fileResourceStoreDirectory);
//			popularResourceStores.add(new FileResourceStore(fileResourceStoreDirectory));
//			
//			FileWriter fileWriter = new FileWriter(configFile);
//			config.store(fileWriter, null);
//			fileWriter.close();
		} else {
			String[] popularDirectoriesAsStringArray = popularDirectoriesAsString.split(";");
			for(String popularDirectory: popularDirectoriesAsStringArray)
				popularResourceStores.add(new FileResourceStore(popularDirectory));
		}
		
		ResourceStoreProvider resourceStoreProvider = new ResourceStoreProvider() {
			private int maxPopularResourceStoreCount = 3;
			private ArrayList<ResourceStoreProvider.Listener> listeners = new ArrayList<ResourceStoreProvider.Listener>();
			
			private ResourceStore selectedStore;
			
			{
				selectedStore = initialFileResourceStore;
			}

			@Override
			public ResourceStore getSelectedResourceStore() {
				return selectedStore;
			}

			@Override
			public void selectResourceStore(ResourceStore resourceStore) {
				this.selectedStore = resourceStore;
				
				config.setProperty("FileResourceStore.Directory", ((FileResourceStore)resourceStore).getDirectory());

				popularResourceStores.remove(resourceStore);
				popularResourceStores.add(0, resourceStore);
				
				while(popularResourceStores.size() > maxPopularResourceStoreCount)
					popularResourceStores.remove(popularResourceStores.size() - 1);
				
				StringBuilder popularResourceStoresBuilder = new StringBuilder();
				for(int i = 0; i < popularResourceStores.size(); i++) {
					if(i > 0)
						popularResourceStoresBuilder.append(";");
					popularResourceStoresBuilder.append(((FileResourceStore)popularResourceStores.get(i)).getDirectory());
				}
				config.setProperty("FileResourceStore.PopularDirectories", popularResourceStoresBuilder.toString());
				
				try {
					FileWriter fileWriter = new FileWriter(configFile);
					config.store(fileWriter, null);
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				for(ResourceStoreProvider.Listener listener: listeners)
					listener.selectedResourceStoreChanged(this);
				
				for(ResourceStoreProvider.Listener listener: listeners)
					listener.popularResourcesChanged(this);
			}

			@Override
			public void addListener(Listener listener) {
				listeners.add(listener);
			}

			@Override
			public void removeListener(Listener listener) {
				listeners.remove(listener);
			}

			@Override
			public List<ResourceStore> getPopularResourceStores() {
				return popularResourceStores;
			}
		};
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
//		UIManager.put("Button.disabledText", Color.YELLOW);
		UIManager.put("ToolTip.font", MainFrame.DESCRIPTION_FONT);
//		UIManager.getDefaults().put("Table.font", MainFrame.DESCRIPTION_FONT);
		
		UIManager.put("ToolTip.background", new ColorUIResource(204, 223, 240));
//		Border border = BorderFactory.createLineBorder(new Color(76,79,83)); // The color is #4c4f53.
//		UIManager.put("ToolTip.border", border);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		JFrame frame = new MainFrame(resourceStoreProvider);
		
//		frame.setSize(1024, 768);
		frame.setSize(640, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
