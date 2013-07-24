
public class MisilEnemigo extends Misil {

	public MisilEnemigo(Vector posicion, Vector velocidad) {
		super(posicion, velocidad);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void avanzar() {
		Vector posNueva = this.getPosicion();
		posNueva.sumarVector(this.getVelocidad());	
		this.setPosicion(posNueva);
	}

}
