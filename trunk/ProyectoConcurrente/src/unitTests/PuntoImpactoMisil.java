package unitTests;
import junit.framework.Assert;
import servidor.CalculadorTrayectoriaME;
import static org.junit.Assert.*;

import org.junit.Test;

public class PuntoImpactoMisil {

	@Test
	public void PuntoImpacto() {
		CalculadorTrayectoriaME calculador=new CalculadorTrayectoriaME();
		double pos[]=new double[3];
		pos[0]=0;
		pos[1]=0;
		pos[2]=1000;
		double vec[]=new double[3];
		vec[0]=10;
		vec[1]=10;
		vec[2]=10;
		double resultado[]=new double[2];
		resultado[0]=1000;
		resultado[1]=1000;
		double[] pto= calculador.procesar(pos,vec);
		Assert.assertEquals(resultado[0], pto[0]);
		Assert.assertEquals(resultado[1], pto[1]);
		
	}
	@Test
	public void PuntoImpacto1() {
		CalculadorTrayectoriaME calculador=new CalculadorTrayectoriaME();
		double pos[]=new double[3];
		pos[0]=100;
		pos[1]=100;
		pos[2]=1000;
		double vec[]=new double[3];
		vec[0]=10;
		vec[1]=10;
		vec[2]=10;
		double resultado[]=new double[2];
		resultado[0]=1100;
		resultado[1]=1100;
		double[] pto= calculador.procesar(pos,vec);
		Assert.assertEquals(resultado[0], pto[0]);
		Assert.assertEquals(resultado[1], pto[1]);
		
	}
	
}
