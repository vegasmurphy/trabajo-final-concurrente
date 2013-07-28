package radar;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import Misiles.*;
import Comunicacion.*;
import GUI.GUI;

public class Radar extends Thread
{
	private final double distanciaMaxima = 12;
	private final int frecuencia = 5000;
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
	
		Socket clienteTx = null;
		try {
			clienteTx = new Socket("localhost",9000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Socket clienteRx = null;
		try {
			clienteRx = new Socket("localhost", 9000);
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
			int i;
			
			/* Identifica y registra todos los misiles defensivos lanzados*/
			while(radarBufferRx.isEmpty() == false)
			{
				try {
					listaMisilesDefensivos.add((MisilDefensivo) radarBufferRx.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}	
			
			/* Envia todos los misiles detectados al Servidor */
			for(i = cantidadMisilesEnemigos; i <= listaMisilesEnemigos.size(); i++)
			{
				try {
					radarBufferTx.put(listaMisilesEnemigos.get(i));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cantidadMisilesEnemigos++;
			}
			
			/* Hace avanzar a cada misil enemigo y defensivo*/
			EfectuarAvance();
			
			/* Compara si hubo colision entre los misiles */
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
		/* Efectua el avance de los misiles enemigos */
		Iterator<MisilEnemigo> iteradorMisilesEnemigos = listaMisilesEnemigos.iterator();
		while(iteradorMisilesEnemigos.hasNext())
			iteradorMisilesEnemigos.next().avanzar();
		
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
		for(i = 0; i <= listaMisilesEnemigos.size(); i++)
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
			if( i < listaMisilesEnemigos.size())
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
			
			/* Se compara la posicion del misil anterior (misil1), con la de los misiles atacantes */
			for(j = 0; j <= listaMisilesDefensivos.size(); j++)
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
			
		/* Compara si dos misiles defensivos chocaron entre si */
		for(i = 0; i < listaMisilesDefensivos.size(); i++)
		{
			misil1 = listaMisilesDefensivos.get(i);
			for(j = i+1; j < listaMisilesDefensivos.size(); j++)
			{
				misil2 = listaMisilesDefensivos.get(j);
				
				/* Si las posiciones de ambos misiles se encuentran a una distancia igual o menor a la 'distanciaMinima', se eliminan 
				 * y se sigue la comparacion con los demas misiles */
				if(misil1.getPosicion().compararVector(misil2.getPosicion(), this.distanciaMaxima))
				{
					System.out.println("Chocaron los misiles: Defensivo ---> " + misil1.getID() + " y Defensivo ---> "+  misil2.getID());
					
					/* En caso de haberse producido una colision entre dos misiles defensivos, se debe informar tal situacion al Servidor */
					ComunicarColision(misil1.getID(), misil2.getID());
					
					listaMisilesDefensivos.remove(i);
					listaMisilesDefensivos.remove(j);
					i--;
					break;
				}	
			}
		}
	}
	
	private void ComunicarColision(int ID1, int ID2)
	{
		int indice;
		for(indice = 0; indice <= listaMisilesEnemigos.size(); indice++)
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
