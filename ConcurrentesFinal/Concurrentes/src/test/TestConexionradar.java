package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestConexionradar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss;
		try {
			ss = new ServerSocket(9000);
			Socket s=ss.accept();
			Socket s1=ss.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){}
	}

}
