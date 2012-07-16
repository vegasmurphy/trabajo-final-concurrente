/*esta clase se encarga de mantener una comunicacion constante con el radar
 * y pasarle los datos recibidos al calculador de trayectoria para que los procese
 */



package servidor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ComunicacionRadar extends Thread {
private ServerSocket comunicacion;
private Socket comRadar;
private boolean isAlive=true;	
private BufferedReader inputString;
private double pos[],vec[];
private CalculadorTrayectoriaME procesador;
/*La clase comunicacionRadar debe recibir cuando es creado el calculador de trayectoria ya creado 
 * al cual le debe ir mandando los datos recibidos por el radar para que este lo procese 
 * */
public ComunicacionRadar(CalculadorTrayectoriaME proc){
	procesador=proc;
	
}



public void run(){
		pos=new double[3];//vector posicion del misil enemigo
		vec=new double[3];//vector velocidad del misil enemigo
		try {
			//establezco comunicacion con radar por socket
			comunicacion=new ServerSocket(10000);
			comRadar=comunicacion.accept();
			inputString=new BufferedReader(new InputStreamReader(comRadar.getInputStream()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(isAlive){
			try {
				//recibo en cada linea un double que me representa un atributo del misil enemigo a procesar
				for(int i=0;i<3;i++){
				
				pos[i]=new Double(inputString.readLine()).doubleValue();
				}
				for(int i=0;i<3;i++){
					
					vec[i]=new Double(inputString.readLine()).doubleValue();
					}
				
				procesador.procesar(pos,vec);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
	
	
	
	
}
