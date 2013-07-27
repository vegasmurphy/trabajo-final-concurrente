package test;
import servidor.*;
import Misiles.*;

public class TestTrayectoriaMisiles {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		MisilDefensivo misilDef;
//		MisilEnemigo misilEnemigo;
//		Vector interseccion,tray1,tray2;
//		misilEnemigo=new MisilEnemigo(new Vector (50000,0,0), new Vector(-100,0,0), 0);
//		AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
//		interseccion=analizador.DeterminarPuntoInterseccion(misilEnemigo);
//		interseccion.imprimir();
//		//interseccion.setZ(100);
//		//interseccion.imprimir();
//		tray1=analizador.CalcularTrayectoria(new Vector(0,0,100), interseccion);
//		tray1.imprimir();
//		tray2=analizador.CalcularTrayectoria(interseccion, misilEnemigo.getPosicion());
//		tray2.imprimir();
//		interseccion.imprimir();
//		misilDef=new MisilDefensivo(new Vector(0,0,0),new Vector(0,0,100), 1, tray1, tray2, misilEnemigo.getPosicion(),interseccion );
//		int i=0;
//		for(i=0;i<105;i++){
//			
//			System.out.println("Posicion:"+misilDef.getPosicion().getX()+","+misilDef.getPosicion().getY()+","+misilDef.getPosicion().getZ());
//			System.out.println("Velocidad:"+misilDef.getVelocidad().getX()+","+misilDef.getVelocidad().getY()+","+misilDef.getVelocidad().getZ());
//			misilDef.avanzar();
//		}
		AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
		MisilDefensivo misilDef;
		MisilEnemigo misilEnemigo=new MisilEnemigo(new Vector(50000,50000,5000),new Vector(-10,-10,-10),2);
		misilDef=analizador.generarMisilDefensivo(misilEnemigo, new Vector(0,0,0));
		analizador.DeterminarPuntoImpacto(misilEnemigo).imprimir();
		misilEnemigo.getPosicion().imprimir();
		misilDef.getPosicion().imprimir();
		while(!Vector.compararVector(misilEnemigo.getPosicion(),misilDef.getPosicion(),10)){
		imprimirMisil(misilDef);
		misilDef.avanzar();
		misilEnemigo.avanzar();
		}
		imprimirMisil(misilEnemigo);
		imprimirMisil(misilDef);
	}


private static void imprimirMisil(Misil misilDef){
	System.out.println("Posicion:"+misilDef.getPosicion().getX()+","+misilDef.getPosicion().getY()+","+misilDef.getPosicion().getZ());
	System.out.println("Velocidad:"+misilDef.getVelocidad().getX()+","+misilDef.getVelocidad().getY()+","+misilDef.getVelocidad().getZ());

	
}
}
