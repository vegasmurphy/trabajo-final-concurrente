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
		double iteraciones=Math.abs(z/Vz);
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
		vecVelocidad.productoEscalar(10);
		return vecVelocidad;
		
	}
	public MisilDefensivo generarMisilDefensivo(MisilEnemigo misil,Vector posInicial){
		MisilDefensivo misilDef=null;
		Vector interseccion=DeterminarPuntoInterseccion(misil);
		Vector distancia=Vector.restar(misil.getPosicion(), interseccion);
		double iteraciones=Math.abs(distancia.getX()/misil.getVelocidad().getX());
		double z=misil.getPosicion().getZ()+(misil.getVelocidad().getZ()*iteraciones);
		Vector puntoIntermedio=new Vector(posInicial.getX(),posInicial.getY(),100);
		interseccion.setZ(z);
		interseccion.imprimir();
		Vector tray1=CalcularTrayectoria(puntoIntermedio,interseccion);
		Vector tray2=CalcularTrayectoria(interseccion,misil.getPosicion());
		misilDef=new MisilDefensivo(posInicial,new Vector(0,0,10), misil.getID(), tray1, tray2, misil.getPosicion(),interseccion);
		return misilDef;
		
	}
	
}
