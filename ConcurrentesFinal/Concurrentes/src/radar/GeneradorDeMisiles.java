package radar;


import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import Misiles.Vector;
import Misiles.MisilEnemigo;

public class GeneradorDeMisiles extends Thread
{
	private CopyOnWriteArrayList<MisilEnemigo> misiles;
	private Random rand;
	private long frecuencia;
	private int ID;
	/** 
	 * Constructor. Inicia la lista de objetos y el generador de valores aleatorios
	 * @param misiles es una lista que contiene objetos de tipo MisilEnemigo.
	 * */
	public GeneradorDeMisiles(CopyOnWriteArrayList<MisilEnemigo> misiles, long frecuencia)
	{
		this.misiles = misiles;
		this.frecuencia = frecuencia;
		rand = new Random();
		ID = 0;
	}
	
	/* El metodo run crea un objeto MisilEnemigo, con su posicion inicial y velocidad, y lo almacena en la lista 'misiles'
	 * Duerme un tiempo especificado por el valor del campo 'frecuencia'*/
	public void run()
	{
		while(true)
		{
			Vector posicion = new Vector();
			Vector velocidad = new Vector();
			setPosicionInicial(posicion);
			setVelocidad(velocidad, posicion);
			MisilEnemigo misil = new MisilEnemigo(Vector.Ceil(posicion), Vector.Truncar(velocidad), ID);
			misiles.add(misil);
			misil.getPosicion().imprimir();
			
			ID++;
			try {
				sleep(this.frecuencia);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Establece la posicion inicial del Misil Enemigo
	 * @param posicion Objeto de tipo Vector que almacena los valores del vector posicion
	 * */
	private void setPosicionInicial(Vector posicion)
	{	
		double coordX=0, coordY=0;
		
		/* Genera una valor para la coordenada X entre -50000 y 50000*/ 
		coordX = 100000*rand.nextDouble()-50000;
		posicion.setX(coordX);
		
		/* Se calcula el valor para la coordenada Y teniendo en cuenta el treorema de Pitagoras 
		 * Como el valor siempre dara positivo, se hace una correcion para conseguir valores negativos*/
		coordY = Math.sqrt(Math.pow(50000,2)-Math.pow(coordX,2));
		/* Para corregirlo, se calcula un valor booleano, que arroja 'true' or 'false' con probabilidades similares. 
		 * En caso de que sea 'true', se cambia el signo de la coordenada Y*/
		if(rand.nextBoolean()==true)
				coordY = -coordY;
		posicion.setY(coordY);
		
		/* Se establece una altura entre 500 y 5000 metros*/
		posicion.setZ(500+4500*rand.nextDouble());
		
	}
	
	/** Establece la velocidad del Misil Enemigo
	 * @param velocidad Objeto de tipo Vector que almacena los valores del vector velocidad
	 * @param posicion Objeto de tipo Vector que almacena los valores del vector posicion
	 *  */
	private void setVelocidad(Vector velocidad, Vector posicion)
	{
		double impactoX = 0, impactoY = 0, modulo = 0, velocX=0, velocY=0, velocZ=0;
		
		/* Para calcular el vector velocidad, calculo el punto de impacto. 
		 * El punto calculado caera dentro de un area de 15km de radio */
		impactoX = 30000*rand.nextDouble()-15000;
		/* Para calcular el valor de Y, hacemos uso del teorema de Pitagoras*/
		impactoY = Math.sqrt(Math.pow(15000,2) - Math.pow(impactoX,2)) * rand.nextDouble();
		/* Se corrige el valor anterior para que pueda tomar valores negativos */
		if(rand.nextBoolean()==true)
			impactoY = -impactoY;
		
		System.out.println("\n punto de impacto en ---> " + impactoX + "  " + impactoY + "  " + "0\n");
		
		/* Se calculan los valores para el vector velocidad y se ajusta el modulo a un valor entre 80 y 120*/
		velocX = impactoX - posicion.getX();
		velocY = impactoY - posicion.getY();
		velocZ = - posicion.getZ();
		
		modulo = (Math.sqrt(Math.pow(velocX,2) + Math.pow(velocY,2) + Math.pow(velocZ,2))) / (8 + 4 * rand.nextDouble());
		
		velocidad.setX( velocX / modulo );
		velocidad.setY( velocY / modulo );
		velocidad.setZ( velocZ / modulo );
		
	}
	
	public int getID()
	{
		return ID;
	}
}
