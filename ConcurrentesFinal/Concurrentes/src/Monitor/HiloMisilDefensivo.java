package Monitor;
import servidor.AnalizadorTrayectoria;
import Comunicacion.Buffer;
import Misiles.*;
public class HiloMisilDefensivo extends Thread {
	private MisilEnemigo misilAtacante;
	private Monitor monitor;
	private AnalizadorTrayectoria analizador;
	private Buffer enviar;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Vector posicion=monitor.obtenerRecurso(misilAtacante);
		MisilDefensivo misilDef=analizador.generarMisilDefensivo(misilAtacante,posicion);
		try {
			enviar.put(misilDef);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
	
	public HiloMisilDefensivo(MisilEnemigo misil,Monitor monitor,Buffer elementosAEnviar){
		misilAtacante=misil;
		this.monitor=monitor;
		enviar=elementosAEnviar;
	}
	
	
	
}
