package Comunicacion;

import java.net.*;

import java.io.*;

import Misiles.Misil;

public class Receptor extends Thread {
	private ObjectInputStream ois;
	private InputStream is;
	private Socket socketCliente;
	private Buffer buffer;
	
	
	
	public void run(){
		while(true){
			Misil elemento = null;
		try {
			elemento = (Misil) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			buffer.put(elemento);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	
	
	
	
	
	
	
	public Receptor(Buffer buffer,Socket socketCliente){
		this.buffer=buffer;
		this.setSocketCliente(socketCliente);
		
		try {
			is = socketCliente.getInputStream();
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}
	
	

	public Buffer getBuffer() {
		return buffer;
	}
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}



	public Socket getSocketCliente() {
		return socketCliente;
	}



	public void setSocketCliente(Socket socketCliente) {
		this.socketCliente = socketCliente;
	}
	
	
	
}

