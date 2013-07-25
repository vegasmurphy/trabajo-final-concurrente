package Misiles;

public class MisilDefensivo extends Misil {
/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
private Vector destinoFinal,interseccion;
private int etapaTray;
private Vector trayectoria2,trayectoria3;	


	public MisilDefensivo(Vector posicion, Vector velocidad,int ID,Vector tray2,Vector tray3,Vector dest,Vector interseccion) {
	super(posicion, velocidad, ID);
	this.destinoFinal=dest;
	this.interseccion=interseccion;
	trayectoria2=tray2;
	trayectoria3=tray3;
	// TODO Auto-generated constructor stub
}



	@Override
	public void avanzar() {
		// TODO Auto-generated method stub
		if(etapaTray==0){
			if(getPosicion().getX()<100){
				posicion.sumarVector(getVelocidad());}
			else{
				posicion.setX(100);
				setEtapaTray(1);
			}
			}
		else{
			if (etapaTray==1){
				if(posicion.compararVector(interseccion, 1)){
					etapaTray=2;
					setPosicion(interseccion);
					
				}
				else{
					posicion.sumarVector(trayectoria2);
					
					
				}
				
				
			}
			else{
				posicion.sumarVector(trayectoria3);
				
				
			}
			
			
			
			
		}
			
		}
		
		
	





	public int getEtapaTray() {
		return etapaTray;
	}



	public void setEtapaTray(int etapaTray) {
		this.etapaTray = etapaTray;
	}



	public Vector getDestinoFinal() {
		return destinoFinal;
	}



	public void setDestinoFinal(Vector destinoFinal) {
		this.destinoFinal = destinoFinal;
	}



	public Vector getInterseccion() {
		return interseccion;
	}



	public void setInterseccion(Vector interseccion) {
		this.interseccion = interseccion;
	}

}
