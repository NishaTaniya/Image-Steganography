package MiddleLayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import login.Login;

public class StegoClient {
	public static Socket s;
	public static DataInputStream dis;
	public static DataOutputStream dos;
	
	public static void main(String args[]) throws IOException  {
		
		try {
			s=new Socket("127.0.0.1",9090);
			dis=new DataInputStream(s.getInputStream());
			dos=new DataOutputStream(s.getOutputStream());
			Login.loginUser(null);
			
		}
		
		catch(Exception e) {
			//e.printStackTrace();
			
			dis.close();
			dos.close();
			s.close();
		}	
	}
		
}
