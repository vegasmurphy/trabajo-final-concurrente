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
		System.out.println(lista.get(0).getX()+" "+lista.get(1).getX());
		
	}

}
