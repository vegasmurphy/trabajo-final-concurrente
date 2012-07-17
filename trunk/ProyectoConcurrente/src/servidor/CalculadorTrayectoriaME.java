package servidor;

public class CalculadorTrayectoriaME {
/*debe analizar si un misil es amenaza o no
 * si es amenaza lo debe pasar a la clase control para que lo destruya
 */
	public double[] procesar(double pos[],double vec[])
	{
		double cant=pos[2]/vec[2];
		double pto[]=new double[2];
		pto[0]=vec[0]*cant+pos[0];
		pto[1]=vec[1]*cant+pos[1];
		return pto;
	};
	
	
	
	
	
	
}
