package test;
import GUI.*;
import Misiles.*;
public class TestGui {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MisilEnemigo misil=new MisilEnemigo(new Vector(50000,50000,100),new Vector(-100,-100,0),1);
		Misil[] misiles=new Misil[1];
		misiles[0]=misil;
		new GUI(misiles);
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			misil.avanzar();
			
		}
	}

}
