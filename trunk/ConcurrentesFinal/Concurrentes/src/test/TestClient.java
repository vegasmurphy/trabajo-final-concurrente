package test;
import java.net.*;
//import java.io.*;
import Comunicacion.*;
import Misiles.MisilEnemigo;

public class TestClient {
	
//private Buffer colaEnviar;

public static void main(String[] args){
Buffer colaRecibidos=new Buffer();
Receptor receptor;
MisilEnemigo misil;

	
	try {    
Socket s = new Socket("localhost",9000);  
System.out.println("connected client");
receptor=new Receptor(colaRecibidos,s);
receptor.start();

while(true){
	misil=(MisilEnemigo)colaRecibidos.take();
	System.out.println(misil.getPosicion().getX());
	
}
}catch(Exception e){System.out.println(e);}  
}
} 
