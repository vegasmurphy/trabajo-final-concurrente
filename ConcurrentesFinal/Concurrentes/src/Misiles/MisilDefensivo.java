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
	System.out.println("interseccion:");
	interseccion.imprimir();
	System.out.println("tray2");
	trayectoria2.imprimir();
	System.out.println("tray3");
	trayectoria3.imprimir();
	// TODO Auto-generated constructor stub
}



	@Override
	public void avanzar() {
		// TODO Auto-generated method stub
		if(etapaTray==0){
			if(getPosicion().getZ()<100){
				posicion.sumarVector(getVelocidad());}
			else{
				posicion.setZ(100);
				etapaTray=1;
				setVelocidad(trayectoria2);
				System.out.println("Termino etapa 0");
			}
			}
		else{
			if (etapaTray==1){
				//posicion.imprimir();
				//trayectoria2.imprimir();
				//interseccion.imprimir();
				if(posicion.compararVector(interseccion, 100)){
					etapaTray=2;
					setPosicion(interseccion);
					setVelocidad(trayectoria3);
					System.out.println("termino etapa 1");
				}
				else{
					posicion.sumarVector(getVelocidad());
					
					
				}
				
				
			}
			else{
				posicion.sumarVector(getVelocidad());
				
				
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
