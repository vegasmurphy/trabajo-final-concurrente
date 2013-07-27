package Monitor;

import java.io.BufferedReader;
import java.io.FileReader;

import Misiles.MisilEnemigo;
import Misiles.Vector;

public class Monitor {

	private double[][] matrix=new double[9][8];
	
	public Monitor(){
		cargarMatrixPetri(matrix);
		Matrix matriz=new Matrix(matrix);
		matriz.show();
	}
	
	public Vector obtenerRecurso(MisilEnemigo misilEnemigo){
		Vector posicionBase=null;
		/*Calcular base mas cercana y devolver la posicion de la base disponible de la cual se procedera a lanzar el misil
		 * Se puede utilizar los metodos provistos por AnalizadorTrayectoria para obtener el punto de interseccion con el cilindro
		 */
		
		return posicionBase;
		
	}

void cargarMatrixPetri(double[][] matrix){
	int i=0; 
	int j=0;
	try{

		    BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\vegas\\workspace\\Concurrentes\\bin\\test\\textfile.txt"));
		    String strLine;
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		      // Print the content on the console
		    	//System.out.println(strLine);
		    	try{
		    		j=0;
		    	    String noInStringArr[] = strLine.split(" ");
		    	    for (String num:noInStringArr){
		    	    	matrix[i][j]=Double.parseDouble(num);
		    	    	j++;
		    	    	
		    	    }
		    	    i++;}
		    	    //then you can parse it to Int as above
		    	    catch(NumberFormatException npe){
		    	    //do something
		    	    }
		    }
		   // }
		    //Close the input stream
		    br.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }finally{
		    }
	
}


}