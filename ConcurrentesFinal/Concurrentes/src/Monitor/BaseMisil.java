package Monitor;

import Misiles.Vector;

public class BaseMisil {
	Vector posicion;
	int numeroBase;
	boolean disponible;
	public BaseMisil(Vector posicion, int numeroBase) {
		super();
		disponible=true;
		this.posicion = posicion;
		this.numeroBase = numeroBase;
	}

	public boolean isDisponible() {
		return disponible;
	}
	
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Vector getPosicion() {
		return posicion;
	}

	public void setPosicion(Vector posicion) {
	this.posicion = posicion;
	}

	public int getNumeroBase() {
		return numeroBase;	
	}

	public void setNumeroBase(int numeroBase) {
		this.numeroBase = numeroBase;
	}
}
