package servidor;
import java.util.List;

import Misiles.*;
public class AnalizadorTrayectoria {
	
	public Vector DeterminarPuntoImpacto(MisilEnemigo misil){
		Vector puntoImpacto = null;
		/*Calcular a partir del misil enemigo el punto de impacto con el suelo aqui
		 * 
		 * 
		 */
		double z=misil.getPosicion().getZ();
		double Vz=misil.getVelocidad().getZ();
		double iteraciones=z/Vz;
		puntoImpacto=new Vector(misil.getPosicion().getX()+misil.getVelocidad().getX()*iteraciones,misil.getPosicion().getY()+misil.getVelocidad().getY()*iteraciones,0);
		return puntoImpacto;
		
	}
	public Vector DeterminarPuntoInterseccion(MisilEnemigo misil){
		
		Vector puntoB=Vector.sumarVector(misil.getPosicion(),misil.getVelocidad());
		//puntoB.imprimir();
		//misil.getPosicion().imprimir();
		List<Vector> puntos = CircleLine.getCircleLineIntersectionPoint(misil.getPosicion(), puntoB, new Vector(0,0,0), 10000);
		Vector intA,intB;
		
		
		intA=puntos.get(0);
		intB=puntos.get(1);
		//intA.imprimir();
		//intB.imprimir();
		if(intA.distancia(puntoB)<intB.distancia(puntoB)){
			//intA.imprimir();
			return intA;
			
		}
		else{
			//intB.imprimir();
			return intB;
			
		}
		
	}
	
	public Vector CalcularTrayectoria(Vector posInicial,Vector posFinal){
		Vector vecVelocidad=null;
		/*En base a un punto inicial y final se calculo el vector velocidad necesario para llegar al destino
		 * Con una velocidad de 100m/s
		 * 
		 */
		vecVelocidad=Vector.restar(posFinal,posInicial);
		//vecVelocidad.restarVector(posInicial);
		vecVelocidad.divisionEscalar(vecVelocidad.Modulo());
		vecVelocidad.productoEscalar(100);
		return vecVelocidad;
		
	}
	
	
}