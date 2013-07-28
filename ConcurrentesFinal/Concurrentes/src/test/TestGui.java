package test;
import GUI.*;
import Misiles.*;
import servidor.AnalizadorTrayectoria;
public class TestGui {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MisilEnemigo misil=new MisilEnemigo(new Vector(50000,50000,100),new Vector(-10,-10,0),1);
		AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
		MisilDefensivo misildef=analizador.generarMisilDefensivo(misil, new Vector(0,0,0));
		Misil[] misiles=new Misil[2];
		misiles[0]=misil;
		misiles[1]=misildef;
		new GUI(misiles);
		while(!Vector.compararVector(misil.getPosicion(), misildef.getPosicion(), 20)){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			misil.avanzar();
			misildef.avanzar();
		}
	}

}
