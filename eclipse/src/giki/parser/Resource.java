package giki.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Resource {
	InputStream getInputStream() throws IOException;
	OutputStream getOutputStream() throws IOException;
	String getName();
	String getPath();
	void rename(String newName) throws IOException;
}
