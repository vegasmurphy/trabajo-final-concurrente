package test;
import java.net.*;
//import java.io.*;
import Comunicacion.*;
import Misiles.MisilEnemigo;
import Misiles.Vector;

public class TestClient {
	
//private Buffer colaEnviar;

public static void main(String[] args){
Buffer colaRecibidos=new Buffer();
Buffer colaEnviar=new Buffer();
Receptor receptor;
MisilEnemigo misil;
Transmisor transmisor;
	
	try {    
Socket s = new Socket("localhost",9000);  
Socket s1= new Socket("localhost",9000);
System.out.println("connected client");
receptor=new Receptor(colaRecibidos,s);
transmisor=new Transmisor(colaEnviar,s1);
receptor.start();
colaEnviar.put(new MisilEnemigo(new Vector(1,1,1),new Vector(1,2,3)));
transmisor.start();
while(true){
	misil=(MisilEnemigo)colaRecibidos.take();
	System.out.println(misil.getPosicion().getX());
	
}
}catch(Exception e){System.out.println(e);}  
}
} 
