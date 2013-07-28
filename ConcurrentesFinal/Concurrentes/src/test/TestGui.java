package test;
import java.util.ArrayList;

import GUI.*;
import Misiles.*;
import servidor.AnalizadorTrayectoria;
public class TestGui {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Misil>misiles=new ArrayList<Misil>();
		MisilEnemigo misil=new MisilEnemigo(new Vector(50000,50000,100),new Vector(-10,-10,0),1);
		MisilEnemigo misil1=new MisilEnemigo(new Vector(-50000,50000,100),new Vector(10,-10,0),1);
		MisilEnemigo misil2=new MisilEnemigo(new Vector(-50000,-50000,100),new Vector(10,10,0),1);
		MisilEnemigo misil3=new MisilEnemigo(new Vector(50000,-50000,100),new Vector(-10,10,0),1);
		AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
		MisilDefensivo misildef=analizador.generarMisilDefensivo(misil, new Vector(10000,0,0));
		MisilDefensivo misildef1=analizador.generarMisilDefensivo(misil1, new Vector(0,10000,0));
		MisilDefensivo misildef2=analizador.generarMisilDefensivo(misil2, new Vector(0,-10000,0));
		MisilDefensivo misildef3=analizador.generarMisilDefensivo(misil3, new Vector(-10000,0,0));
		
		//Misil[] misiles=new Misil[2];
		misiles.add(misil);
		misiles.add(misildef);
		misiles.add(misil1);
		misiles.add(misildef1);
		misiles.add(misildef2);
		misiles.add(misil2);
		misiles.add(misildef3);
		misiles.add(misil3);
		new GUI(misiles);
		while(!Vector.compararVector(misil.getPosicion(), misildef.getPosicion(), 20)){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			misil.avanzar();
			misildef.avanzar();
			misil1.avanzar();
			misildef1.avanzar();
			misil2.avanzar();
			misildef2.avanzar();
			misil3.avanzar();
			misildef3.avanzar();
		}
	}

}
