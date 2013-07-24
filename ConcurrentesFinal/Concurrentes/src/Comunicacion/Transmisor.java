package Comunicacion;

import java.net.*;

import java.io.*;

import Misiles.Misil;

public class Transmisor extends Thread {
	private ObjectOutputStream oos;
	private OutputStream os;
	private Socket socketCliente;
	private Buffer buffer;
	
	
	
	public void run(){
		while(true){
		Misil elemento = null;
		try {
			
			elemento=(Misil) buffer.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		send(elemento);
		}
	}
	
	
	
	private void send(Object elemento){
		try {
			oos.writeObject(elemento);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public Transmisor(Buffer buffer,Socket socketCliente){
		this.buffer=buffer;
		this.setSocketCliente(socketCliente);
		
		try {
			os = socketCliente.getOutputStream();
			oos = new ObjectOutputStream(os);
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

