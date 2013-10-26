package radar;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import Misiles.*;
import Comunicacion.*;
import GUI.GUI;

public class Radar extends Thread
{
	private final double distanciaMaxima = 50; // tolerancia
	private final int frecuencia = 5000; //cada uanto se genera un misil enemigo
	private CopyOnWriteArrayList<MisilEnemigo> listaMisilesEnemigos;
	private CopyOnWriteArrayList<MisilDefensivo> listaMisilesDefensivos;
	private Transmisor radarTx;
	private Receptor radarRx;
	private Buffer radarBufferRx;
	private Buffer radarBufferTx;
	private GeneradorDeMisiles generador;
	private int cantidadMisilesEnemigos;
	
	public Radar()
	{	
	
		Socket clienteRx = null;
		try {
			clienteRx = new Socket("localhost",9000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Socket clienteTx = null;
		try {
			clienteTx = new Socket("localhost", 9000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		listaMisilesEnemigos = new CopyOnWriteArrayList<MisilEnemigo>();
		listaMisilesDefensivos = new CopyOnWriteArrayList<MisilDefensivo>();
		radarBufferRx = new Buffer();
		radarRx = new Receptor(radarBufferRx, clienteRx);
		radarBufferTx = new Buffer();
		radarTx = new Transmisor(radarBufferTx, clienteTx);
		
		generador = new GeneradorDeMisiles(listaMisilesEnemigos, this.frecuencia);
		cantidadMisilesEnemigos = 0; 
		new GUI(listaMisilesEnemigos,listaMisilesDefensivos);
	}
	
	public void run()
	{	
		/* Lanza los hilos generador, transmisor y receptor del radar*/
		generador.start();
		radarTx.start();
		radarRx.start();
		
		while(true)
		{
			
			/* Identifica y registra todos los misiles defensivos lanzados*/
			while(!radarBufferRx.isEmpty())
			{
				try {
					listaMisilesDefensivos.add((MisilDefensivo) radarBufferRx.take());
					System.out.println("Agrego misil defensivo: " + listaMisilesDefensivos.get(listaMisilesDefensivos.size()-1).getID()+"\n");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			
			/* Envia todos los misiles enemigos detectados recientemente, al Servidor 
			 * Se compara el ID del ultimo misil enemigo identificado con la cantidad de misiles. Si este ultimo valor es mayor al primero, significa 
			 * que hay nuevos misiles que comunicar*/
		//	if(this.cantidadMisilesEnemigos < listaMisilesEnemigos.get(listaMisilesEnemigos.size()).getID());
			ComunicarMisilesEnemigos();
			
			/* Hace avanzar a cada misil enemigo y defensivo*/
			EfectuarAvance();
			
			/* Compara si hubo colision entre los misiles */
			if(listaMisilesEnemigos.size() > 1)
				VerificarImpacto();
			
			/* Espera por el proximo ciclo */
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void EfectuarAvance()
	{
		Misil misil;
		/* Efectua el avance de los misiles enemigos */
		Iterator<MisilEnemigo> iteradorMisilesEnemigos = listaMisilesEnemigos.iterator();
		while(iteradorMisilesEnemigos.hasNext())
		{
			misil = (MisilEnemigo) iteradorMisilesEnemigos.next();
			misil.avanzar();
		//	System.out.println("Misil: "+ misil.getID() + "---> Posicion actual "); 
		//	misil.getPosicion().imprimir();
		//	System.out.println("\n");
		}
		/* Efectua el avance de los misiles defensivos */
		Iterator<MisilDefensivo> iteradorMisilesDefensivos = listaMisilesDefensivos.iterator();
		while(iteradorMisilesDefensivos.hasNext())
			iteradorMisilesDefensivos.next().avanzar();
	}
	
	private void VerificarImpacto()
	{
		int i,j;
		Misil misil1, misil2;
		
		/* Compara las posiciones de un determinado misil con todos los demas misiles enemigos */
		for(i = 0; i < listaMisilesEnemigos.size(); i++)
		{
			misil1 = listaMisilesEnemigos.get(i);
			
			/* comprueba si el misil impacto en el suelo */
			if(misil1.getPosicion().getZ() <= 0)
			{
				/* Comprueba si el misil impacto dentro de la zona protegida */
				if(misil1.getPosicion().compararVector(new Vector(0,0,0), 10000))
				{
					System.out.println("El misil: " + misil1.getID() + " impacto en la zona protegida\n");
				}
				else
				{
					System.out.println("El misil: " + misil1.getID() + " impacto fuera de la zona protegida\n");
				}
				listaMisilesEnemigos.remove(i);
				cantidadMisilesEnemigos--;
				i--;
				break;
			}
			
			/* Se compara la posicion de un determinado misil enemigo con los demas misiles enemigos */
			if( i < listaMisilesEnemigos.size()-1)
			{
				for(j = i+1; j < listaMisilesEnemigos.size(); j++ )
				{
					misil2 = listaMisilesEnemigos.get(j);
				
					/* Si las posiciones de ambos misiles se encuentran a una distancia igual o menor a la 'distanciaMinima', se eliminan 
					 * y se sigue la comparacion con los demas misiles */
					if(misil1.getPosicion().compararVector(misil2.getPosicion(), this.distanciaMaxima))
					{
						System.out.println("Chocaron los misiles: Enemigo ---> " + misil1.getID() + " y Enemigo ---> "+  misil2.getID());
						listaMisilesEnemigos.remove(i);
						listaMisilesEnemigos.remove(j);
						cantidadMisilesEnemigos--;
						i--;
						break;
					}	
				}
			}	
			
			/* Se compara la posicion del misil anterior (misil1), con la de los demas misiles enemigos, en caso de haber mas de uno */
			if(listaMisilesDefensivos.size() > 0)
			{
				for(j = 0; j < listaMisilesDefensivos.size(); j++)
				{
					misil2 = listaMisilesDefensivos.get(j);
				
					/* Si las posiciones de ambos misiles se encuentran a una distancia igual o menor a la 'distanciaMinima', se eliminan 
					 * y se sigue la comparacion con los demas misiles */
					if(misil1.getPosicion().compararVector(misil2.getPosicion(), this.distanciaMaxima))
					{
						System.out.println("Chocaron los misiles:  Enemigo ---> " + misil1.getID() + " y Defensivo ---> "+  misil2.getID());
						listaMisilesEnemigos.remove(i);
						listaMisilesDefensivos.remove(j);
						cantidadMisilesEnemigos--;
						i--;
						break;
					}	
				}
			}
		}
			
		/* Compara, en caso de existir mas de dos misiles defensivos, si dos de estos chocaron entre si 
		if(listaMisilesDefensivos.size() > 1)
		{
			for(i = 0; i < listaMisilesDefensivos.size(); i++)
			{
				misil1 = listaMisilesDefensivos.get(i);
				for(j = i+1; j < listaMisilesDefensivos.size(); j++)
				{
					misil2 = listaMisilesDefensivos.get(j);
				
					/* Si las posiciones de ambos misiles se encuentran a una distancia igual o menor a la 'distanciaMinima', se eliminan 
					 * y se sigue la comparacion con los demas misiles 
					if(misil1.getPosicion().compararVector(misil2.getPosicion(), this.distanciaMaxima))
					{
						System.out.println("Chocaron los misiles: Defensivo ---> " + misil1.getID() + " y Defensivo ---> "+  misil2.getID());
					
						/* En caso de haberse producido una colision entre dos misiles defensivos, se debe informar tal situacion al Servidor 
						ComunicarColision(misil1.getID(), misil2.getID());
					
						listaMisilesDefensivos.remove(i);
						listaMisilesDefensivos.remove(j);
						i--;
						break;
					}	
				}
			}
		}*/
	}
	
	private void ComunicarMisilesEnemigos()
	{
		ListIterator<MisilEnemigo> iteradorMisilesEnemigos = listaMisilesEnemigos.listIterator(this.cantidadMisilesEnemigos);
		while(iteradorMisilesEnemigos.hasNext())
		{
			try {
				radarBufferTx.put(iteradorMisilesEnemigos.next());
				this.cantidadMisilesEnemigos++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Comunico misil\n");
		}
	}
	
	private void ComunicarColision(int ID1, int ID2)
	{
		int indice;
		for(indice = 0; indice < listaMisilesEnemigos.size(); indice++)
		{
			if(listaMisilesEnemigos.get(indice).getID() == ID1 || listaMisilesEnemigos.get(indice).getID() == ID2)
			{
				try {
					radarBufferTx.put(listaMisilesEnemigos.get(indice));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
