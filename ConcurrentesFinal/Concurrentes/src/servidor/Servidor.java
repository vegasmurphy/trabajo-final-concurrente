package servidor;
import java.io.IOException;
import java.net.*;
import Comunicacion.*;
import Misiles.*;
import Monitor.HiloMisilDefensivo;
import Monitor.Monitor;


public class Servidor {

private static Buffer Rx;
private static Buffer Tx;
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
		monitor=new Monitor();
		initServer();
		MisilEnemigo misilNuevo = null;
		while(true){
			try {
				misilNuevo=(MisilEnemigo) Rx.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			procesarMisil(misilNuevo);
			
		}
	}

	private static void procesarMisil(MisilEnemigo misilNuevo) {
	// TODO Auto-generated method stub
	//analizar el impacto del misil
	//crear hiloMisilDefensivo
	//
		HiloMisilDefensivo hiloDefensivo=new HiloMisilDefensivo(misilNuevo,monitor,Tx);
		hiloDefensivo.start();
}

	private static void initServer(){
		Socket s = null,s1 = null;
		
		try {
			ss=new ServerSocket(port);
			s=ss.accept();
			s1=ss.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transmisor=new Transmisor(Tx,s);
		receptor=new Receptor(Rx,s1);
		transmisor.start();
		receptor.start();
	}
	
	
	
}
