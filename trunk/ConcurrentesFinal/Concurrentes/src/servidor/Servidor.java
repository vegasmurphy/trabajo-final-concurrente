package servidor;
import java.io.IOException;
import java.net.*;

import radar.Radar;
import Comunicacion.*;
import Misiles.*;
import Monitor.HiloMisilDefensivo;
import Monitor.Monitor;


public class Servidor {

private static Buffer Rx=new Buffer();
private static Buffer Tx=new Buffer();
private static Transmisor transmisor;
private static Receptor receptor;
private static int port=9000;
private static ServerSocket ss;
private static Monitor monitor;
/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//monitor=new Monitor();
		initServer();
		MisilEnemigo misilNuevo = null;
		while(true){
			try {
				//System.out.println("llego1");
				//if(!Rx.isEmpty()){
				misilNuevo=(MisilEnemigo)Rx.take();
				
				procesarMisil(misilNuevo);
				//System.out.println("llego2");
			//}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	private static void procesarMisil(MisilEnemigo misilNuevo) {
	// TODO Auto-generated method stub
	//analizar el impacto del misil
	//crear hiloMisilDefensivo
	//
		//HiloMisilDefensivo hiloDefensivo=new HiloMisilDefensivo(misilNuevo,Tx);
		AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
		Vector v=analizador.DeterminarPuntoImpacto(misilNuevo);
		//v.imprimir();
		if(v.getX()<10000&&v.getX()>-10000){
			if(v.getY()<10000&&v.getY()>-10000){
			MisilDefensivo misildef=analizador.generarMisilDefensivo(misilNuevo, new Vector(0,-10000,0));
		
			
			try {
				if(misildef!=null){
				Tx.put(misildef);}
				//Tx.put(misildef1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	}}
	
	}

	private static void initServer(){
		Socket s = null,s1 = null;
		
		try {
			ss=new ServerSocket(port);
			s=ss.accept();
			s1=ss.accept();
			transmisor=new Transmisor(Tx,s);
			receptor=new Receptor(Rx,s1);
			transmisor.start();
			receptor.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
