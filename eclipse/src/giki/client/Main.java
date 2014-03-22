package giki.client;

import giki.diagnostics.StopWatch;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
	public static void main(String[] args) throws UnknownHostException, IOException {
		if(args.length < 1) {
			System.out.println(
				"Please supply a single argument representing the path of the file to request for evaluation. " + 
				"Optionally, a second argument can be provided to indicate the host, which, otherwise, will be localhost.");
			return;
		}
		
		//String filePath = "./examples/application.giki second third /t";//args[0];
		StopWatch requestStopWatch = new StopWatch();
		requestStopWatch.start();
		
		String filePath = args[0];
//		String filePath = "C:/Users/Jakob/Dropbox/Eclipse/workspace/Giki 0.0.2/examples/application.giki";
		
		String hostName = args.length > 1 ? args[1] : "localhost";
		int portNumber = 8888;
		StopWatch socketCreationStopWatch = new StopWatch();
		socketCreationStopWatch.start();
		Socket socket = new Socket(hostName, portNumber);
		socketCreationStopWatch.stop();
		socketCreationStopWatch.printElapsed(System.out, "Socket Creation");

		StopWatch communicationStopWatch = new StopWatch();
		communicationStopWatch.start();
		PrintWriter out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
	    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    
	    System.out.println("Requesting " + hostName + " for evaluation of \"" + filePath + "\"...");
	    out.println(filePath);
	    out.flush();

	    StringBuilder responseBuilder = new StringBuilder();
	    char[] cbuf = new char[1024]; 
	    while(true) { 
	    	int readChars = in.read(cbuf, 0, cbuf.length);
	    	if(readChars == -1)
	    		break;
	    	responseBuilder.append(cbuf, 0, readChars);
	    }
//	    String responseLine;
//	    while((responseLine = in.readLine()) != null) {
////		    System.out.println(response);
//		    responseBuilder.append(responseLine + "\n");
//	    }
	    String response = responseBuilder.toString();
		communicationStopWatch.stop();
		communicationStopWatch.printElapsed(System.out, "Communication");
	    System.out.println(response);
	    
//	    out.close();
//	    in.close();
	    socket.close();
	    requestStopWatch.stop();
	    requestStopWatch.printElapsed(System.out, "Request");
	}
}
