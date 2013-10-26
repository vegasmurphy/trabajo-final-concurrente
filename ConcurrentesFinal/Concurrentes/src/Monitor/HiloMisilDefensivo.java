package Monitor;
import servidor.AnalizadorTrayectoria;
import Comunicacion.Buffer;
import Misiles.*;

public class HiloMisilDefensivo extends Thread {
	private MisilEnemigo misilAtacante;
	private Monitor monitor;
	private AnalizadorTrayectoria analizador=new AnalizadorTrayectoria();
	private Buffer enviar;
	private BaseMisil base=null;
	@Override
	
	public void run() {
		// TODO Auto-generated method stub
		
	
		MisilEnemigo misilLocal=new MisilEnemigo(misilAtacante.getPosicion(), misilAtacante.getVelocidad(), misilAtacante.getID());
			try {
				base = monitor.obtenerRecurso(misilLocal);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(base!=null){
		MisilDefensivo misilDef=analizador.generarMisilDefensivo(misilLocal,base.getPosicion());
		//MisilDefensivo misilDef=analizador.generarMisilDefensivo(misilAtacante,new Vector(0,10000,0));

			try {
				enviar.put(misilDef);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
				try {
					sleep(10000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			try {
				monitor.devolverRecurso(base.getNumeroBase());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		
	}
	
	public HiloMisilDefensivo(MisilEnemigo misil,Buffer elementosAEnviar, Monitor monitor){
		this.misilAtacante = misil;
		this.monitor = monitor;
		//this.monitor=monitor;
		this.enviar = elementosAEnviar;
	}
		
}
