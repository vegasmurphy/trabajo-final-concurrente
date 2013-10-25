package Monitor;
import servidor.AnalizadorTrayectoria;
import Comunicacion.Buffer;
import Misiles.*;

public class HiloMisilDefensivo extends Thread {
	private MisilEnemigo misilAtacante;
	private Monitor monitor;
	private AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
	private Buffer enviar;
	@Override
	
	public void run() {
		// TODO Auto-generated method stub
		
		BaseMisil base = null;
		try {
			base = monitor.obtenerRecurso(misilAtacante);
			if(base!=null){
		//MisilDefensivo misilDef=analizador.generarMisilDefensivo(misilAtacante,base.getPosicion());
		MisilDefensivo misilDef=analizador.generarMisilDefensivo(misilAtacante,new Vector(10000,0,0));

			enviar.put(misilDef);
	
	
			monitor.devolverRecurso(base.getNumeroBase());}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HiloMisilDefensivo(MisilEnemigo misil,Buffer elementosAEnviar, Monitor monitor){
		misilAtacante = misil;
		this.monitor = monitor;
		//this.monitor=monitor;
		enviar = elementosAEnviar;
	}
		
}
