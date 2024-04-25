package MiddleLayer;

import java.io.IOException;
import java.net.ServerSocket;


public class StegoServer {
ServerSocket ss;
	
	public StegoServer(int port) throws IOException {
		ss=new ServerSocket(port);
		
		System.out.println("Server is listening at port : "+port);
		while(true) {
			new StegoServerThread(ss.accept()).start();
		}
		
	}

	public static void main(String[] args) throws IOException {
		StegoServer css=new StegoServer(9090);
	}
}
