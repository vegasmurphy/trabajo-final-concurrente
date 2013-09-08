package Monitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import servidor.AnalizadorTrayectoria;

import Misiles.MisilEnemigo;
import Misiles.Vector;

public class Monitor {
	private AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
	private double[][] matrix ={{-1,-1,-1,-1,1,1,1,1},{1,0,0,0,-1,0,0,0},{0,1,0,0,0,-1,0,0},{0,0,1,0,0,0,-1,0},{0,0,0,1,0,0,0,-1},{-1,0,0,0,1,0,0,0},{0,-1,0,0,0,1,0,0},{0,0,-1,0,0,0,1,0},{0,0,0,-1,0,0,0,1}};
	private double[][] marcados = {{4,0,0,0,0,1,1,1,1}};
	private double [][] res = {{0,0,0,0,0,0,0,0}};
	private double [][] disparoNorOeste = {{1},{0},{0},{0},{0},{0},{0},{0}}, disparoSudOeste = {{0},{1},{0},{0},{0},{0},{0},{0}}, disparoSudEste = {{0},{0},{1},{0},{0},{0},{0},{0}}, disparoNorEste = {{0},{0},{0},{1},{0},{0},{0},{0}};
	private double [][] bases = {{0,0,1,2,3,0,0,0,0}}; // vector que decide que base esta disponible, usado al final. ()
	private Matrix norOeste,sudOeste,sudEste,norEste;
	private Matrix marcadosM;
	private Matrix matriz;
	private Matrix resultado;
	private Matrix base_devol;
	private BaseMisil [] arregloBases;
	private int numero_base;
	final Lock lock = new ReentrantLock();
	final Condition recursoNoDisponible  = lock.newCondition();

	
	public Monitor(){	
		marcadosM = new Matrix(marcados);
		matriz = new Matrix(matrix);
		norOeste = new Matrix(disparoNorOeste);
		sudOeste = new Matrix(disparoSudOeste);
		sudEste = new Matrix(disparoSudEste);
		norEste = new Matrix(disparoNorEste);
		resultado = new Matrix(res);
		base_devol = new Matrix(bases);
		arregloBases = new BaseMisil[4];
		arregloBases[0] = new BaseMisil(new Vector(-10,0,0),0);
		arregloBases[1] = new BaseMisil (new Vector(0,-10,0),1); 
		arregloBases[2] = new BaseMisil (new Vector(10,0,0),2); 
		arregloBases[3] = new BaseMisil (new Vector(0,10,0),3);
	}
	
	public BaseMisil obtenerRecurso(MisilEnemigo misilEnemigo) throws InterruptedException{
	
		BaseMisil base=null; 
		/*Calcular base mas cercana y devolver la posicion de la base disponible de la cual se procedera a lanzar el misil
		 * Se puede utilizar los metodos provistos por AnalizadorTrayectoria para obtener el punto de interseccion con el cilindro
		 */
		//lock ver de buffer, es para ver si hay recursos disponibles o no
		lock.lock();
		try {
		//entro a la seccion critica
			if ((marcadosM.getValor(0,0)) < 1) // si no hay recurso disponible, bloqueo al hilo.
			{
				recursoNoDisponible.await();
			}
	
			Vector interseccion = analizador.DeterminarPuntoInterseccion(misilEnemigo); //pasar misil ,devuelve un vector
			if (interseccion.getX()>0 && interseccion.getY()>0){
				numero_base = 3;
			} 	
			else if (interseccion.getX()>0 && interseccion.getY()<0){
				numero_base = 2;
			}
			else if (interseccion.getX()<0 && interseccion.getY()<0){
				numero_base = 1;
			}	
			else {
				numero_base = 0;
			}
	
			marcadosM.rotarIzquierda(numero_base); 	// esto se rota para que los if se pregunten en siempre el mismo orden
													// en vez de cambiar el orden de los if, se rota el vector
			base_devol.rotarIzquierda(numero_base); 	// por la misma razon que lo anterior, es el numero de base 
												// que devuelve el monitor	
	
			// se altera el mapa !!!!!!!! ojoo
			resultado = (matriz.times(norOeste).plus(marcadosM));
			resultado.show();
			if (!resultado.contieneNeg()){ // si contiene negativos quire decir que esta base esta ocupada
				base = elegirBase((int)base_devol.getValor(0,1));
				desrotar (numero_base);
				return base; 
			}
			else {
				resultado = (matriz.times(sudOeste).plus(marcadosM));
				resultado.show();
				if (!resultado.contieneNeg()){
					base = elegirBase((int)base_devol.getValor(0,2));
					desrotar (numero_base);
					return base; 
				}
				else {
					resultado = (matriz.times(sudEste).plus(marcadosM));
					resultado.show();
					if (!resultado.contieneNeg()){
						base = elegirBase((int)base_devol.getValor(0,3));
						desrotar (numero_base);
						return base; 
					}
					else {
						base = elegirBase((int)base_devol.getValor(0,4));
						desrotar (numero_base);
						return base;
					}
				}
			}	
		}
		finally {
			lock.unlock();
		}
	}
	
	
	// hacer un metodo devolver recurso
	public void devolverRecurso(int n_base) throws InterruptedException{
		double [][] devolverNorOeste = {{1},{0},{0},{0},{0},{0},{0},{0}}, devolverSudOeste = {{0},{1},{0},{0},{0},{0},{0},{0}},
					devolverSudEste = {{0},{0},{1},{0},{0},{0},{0},{0}}, devolverNorEste = {{0},{0},{0},{1},{0},{0},{0},{0}};
		Matrix devNorOeste = new Matrix(devolverNorOeste);
		Matrix devSudOeste = new Matrix(devolverSudOeste);
		Matrix devSudEste = new Matrix(devolverSudEste);
		Matrix devNorEste = new Matrix(devolverNorEste);
		
		lock.lock();
		
		if(n_base == 0)	
			resultado = matriz.times(devNorOeste).plus(marcadosM);
		else if (n_base == 1)
			resultado = matriz.times(devSudOeste).plus(marcadosM);
		else if (n_base == 2)
			resultado = matriz.times(devSudEste).plus(marcadosM);
		else resultado = matriz.times(devNorEste).plus(marcadosM);
		
		try {
			recursoNoDisponible.signal(); // se devuelve el recurso utilizado.
		}
		finally {
			lock.unlock();
		}
	}
	
	private void desrotar(int numero_base){
		marcadosM.rotarDerecha(numero_base);
		base_devol.rotarDerecha(numero_base);	
	}
	
	private BaseMisil elegirBase(int num_base)
	{
		return arregloBases[num_base];
	}
}
