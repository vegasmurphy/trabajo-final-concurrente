package test;

import java.util.List;

import Misiles.Vector;
import servidor.CircleLine;

public class testInterseccion {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Vector>lista=CircleLine.getCircleLineIntersectionPoint(new Vector(50000,50000,0),  new Vector(0,0,0), new Vector(0,0,0), 10000);
		System.out.println("Punto 1:"+lista.get(0).getX()+","+lista.get(0).getY()+"Punto 2: "+lista.get(1).getX()+","+lista.get(1).getY());
		List<Vector>lista1=CircleLine.getCircleLineIntersectionPoint(new Vector(50000,0,0),  new Vector(0,0,0), new Vector(0,0,0), 10000);
		System.out.println("Punto 1:"+lista1.get(0).getX()+","+lista1.get(0).getY()+"Punto 2: "+lista1.get(1).getX()+","+lista1.get(1).getY());
		List<Vector>lista11=CircleLine.getCircleLineIntersectionPoint(new Vector(0,50000,0),  new Vector(0,0,0), new Vector(0,0,0), 10000);
		System.out.println("Punto 1:"+lista11.get(0).getX()+","+lista11.get(0).getY()+"Punto 2: "+lista11.get(1).getX()+","+lista11.get(1).getY());
	}

}
