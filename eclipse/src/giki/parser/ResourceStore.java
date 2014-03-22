package giki.parser;

import java.io.IOException;
import java.util.List;

public interface ResourceStore {
	Resource create(String name) throws IOException;
	boolean exists(String name);
//	InputStream getInputStream(String name) throws IOException;
//	OutputStream getOutputStream(String name) throws IOException;
	Resource getResource(String name);
	String getResourcePath(String name);
	String getName();
	List<Resource> getResources();
//	void rename(String currentName, String newName);
	void remove(String name) throws IOException;
	String ensureUnique(String name);
}
