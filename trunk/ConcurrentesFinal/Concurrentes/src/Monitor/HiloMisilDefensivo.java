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
		
		//BaseMisil base=monitor.obtenerRecurso(misilAtacante);
		MisilDefensivo misilDef=analizador.generarMisilDefensivo(misilAtacante,new Vector(0,0,0));
		try {
			enviar.put(misilDef);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
	
	public HiloMisilDefensivo(MisilEnemigo misil,Buffer elementosAEnviar){
		misilAtacante=misil;
		//this.monitor=monitor;
		enviar=elementosAEnviar;
	}
	
	
	
}