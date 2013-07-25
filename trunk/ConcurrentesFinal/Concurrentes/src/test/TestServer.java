package test;
import java.net.*;
//import java.io.*;
import Comunicacion.*;
import Misiles.MisilEnemigo;
import Misiles.Vector;

public class TestServer {
	


public static void main(String[] args){
Buffer colaEnviar=new Buffer();
Buffer colaRec=new Buffer();
Transmisor trans;
Receptor rec;
MisilEnemigo misil;
	
try {  
ServerSocket ss = new ServerSocket(9000);  
Socket s = ss.accept();  
Socket s1 =ss.accept();
System.out.println("connected");
trans=new Transmisor(colaEnviar,s);
rec=new Receptor(colaRec,s1);

	misil=new MisilEnemigo(new Vector (0,0,0),new Vector (1,1,1),1);
	colaEnviar.put(misil);
	trans.start();
	rec.start();
	while(true){
		misil=(MisilEnemigo)colaRec.take();
		System.out.println(misil.getPosicion().getX());
		
	}


}catch(Exception e){System.out.println(e);}  
}
} 

