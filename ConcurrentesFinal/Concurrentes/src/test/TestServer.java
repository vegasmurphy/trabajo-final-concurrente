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

	//MisilEnemigo misil2=new MisilEnemigo(new Vector (0,0,0),new Vector (1,1,1),1);
	//MisilEnemigo misil1=new MisilEnemigo(new Vector (0,0,0),new Vector (1,1,1),2);
	//colaEnviar.put(misil2);
	//colaEnviar.put(misil1);
	trans.start();
	rec.start();
	while(true){
		misil=(MisilEnemigo)colaRec.take();
		System.out.println("se agrego a Server misil enemigo: " + misil.getID());
		
	}


}catch(Exception e){System.out.println(e);}  
}
} 

