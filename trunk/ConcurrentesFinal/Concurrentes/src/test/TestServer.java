package test;
import java.net.*;

import servidor.AnalizadorTrayectoria;
//import java.io.*;
import Comunicacion.*;
import Misiles.MisilDefensivo;
import Misiles.MisilEnemigo;
import Misiles.Vector;

public class TestServer {
	


public static void main(String[] args){
Buffer colaEnviar=new Buffer();
Buffer colaRec=new Buffer();
Transmisor trans;
Receptor rec;
//MisilEnemigo misil;
	
try {  
ServerSocket ss = new ServerSocket(9000);  
Socket s = ss.accept();  
Socket s1 =ss.accept();
System.out.println("connected");
trans=new Transmisor(colaEnviar,s);
rec=new Receptor(colaRec,s1);

MisilEnemigo misil=new MisilEnemigo(new Vector(50000,50000,100),new Vector(-10,-10,0),1);
MisilEnemigo misil1=new MisilEnemigo(new Vector(-50000,50000,100),new Vector(10,-10,0),1);
MisilEnemigo misil2=new MisilEnemigo(new Vector(-50000,-50000,100),new Vector(10,10,0),1);
MisilEnemigo misil3=new MisilEnemigo(new Vector(50000,-50000,100),new Vector(-10,10,0),1);
AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
MisilDefensivo misildef=analizador.generarMisilDefensivo(misil, new Vector(10000,0,0));
MisilDefensivo misildef1=analizador.generarMisilDefensivo(misil1, new Vector(0,10000,0));
MisilDefensivo misildef2=analizador.generarMisilDefensivo(misil2, new Vector(0,-10000,0));
MisilDefensivo misildef3=analizador.generarMisilDefensivo(misil3, new Vector(-10000,0,0));
	//colaEnviar.put(misildef2);
	colaEnviar.put(misildef1);
	trans.start();
	rec.start();
	while(true){
		misil=(MisilEnemigo)colaRec.take();
		System.out.println(misil.getID());
		
	}


}catch(Exception e){System.out.println(e);}  
}
} 

