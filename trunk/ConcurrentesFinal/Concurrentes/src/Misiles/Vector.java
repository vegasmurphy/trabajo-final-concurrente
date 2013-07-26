package Misiles;

import java.io.Serializable;

public class Vector implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
double x;
double y;
double z;

public void restarVector(Vector b){
	x-=b.getX();
	y-=b.getY();
	z-=b.getZ();
	
	
	
}

public void imprimir(){
	System.out.println("Vector:"+x+","+y+","+z);
	
}
public double distancia(Vector b){
	double sum;
	sum=0;
	sum+=(x-b.getX())*(x-b.getX());
	sum+=(y-b.getY())*(y-b.getY());
	sum+=(z-b.getZ())*(z-b.getZ());
	sum=java.lang.Math.sqrt(sum);
	return sum;
}
public void productoEscalar(double escalar){
	x=x*escalar;
	y=y*escalar;
	z=z*escalar;
	
}
public void divisionEscalar(double escalar){
	x=x/escalar;
	y=y/escalar;
	z=z/escalar;
	
}
public double Modulo(){
	double sum=0;
	sum=(x*x)+(y*y)+(z*z);
	sum=java.lang.Math.sqrt(sum);
	return sum;
	
	
}
//devuelve verdadero si se encuentra en una distancia determinada del otro punto 
public boolean compararVector(Vector b,double dist){
	double sum;
	sum=0;
	sum+=(x-b.getX())*(x-b.getX());
	sum+=(y-b.getY())*(y-b.getY());
	sum+=(z-b.getZ())*(z-b.getZ());
	sum=java.lang.Math.sqrt(sum);
	if(sum<dist){return true;}
	else{imprimir();
		return false;}
}
public static Vector restar(Vector a,Vector b){
	Vector vector;
	vector=new Vector(a.getX()-b.getX(),a.getY()-b.getY(),a.getZ()-b.getZ());
	return vector;
}


//compara si dos vectores se encuentran en una distancia entre si
public boolean compararVector(Vector a,Vector b,double distancia){
	return a.compararVector(b, distancia);
	
}
public void sumarVector(Vector b){//suma el vector actual con el vector pasado como parametro
	x+=b.getX();
	y+=b.getY();
	z+=b.getZ();
}
public static Vector sumarVector(Vector a,Vector b){
	Vector vector=new Vector(a.getX()+b.getX(),a.getY()+b.getY(),a.getZ()+b.getZ());
	return vector;
}
public double getX() {
	return x;
}
public void setX(double x) {
	this.x = x;
}
public double getY() {
	return y;
}
public void setY(double y) {
	this.y = y;
}
public double getZ() {
	return z;
}
public void setZ(double z) {
	this.z = z;
}
public Vector(double x, double y, double z) {
	super();
	this.x = x;
	this.y = y;
	this.z = z;
}
public Vector() {
	// TODO Auto-generated constructor stub
}

}
