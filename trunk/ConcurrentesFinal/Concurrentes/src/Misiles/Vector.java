package Misiles;

public class Vector {
double x;
double y;
double z;


//devuelve verdadero si se encuentra en una distancia determinada del otro punto 
public boolean compararVector(Vector b,double dist){
	double sum;
	sum=0;
	sum+=(x-b.getX())*(x-b.getX());
	sum+=(y-b.getY())*(y-b.getY());
	sum+=(z-b.getZ())*(z-b.getZ());
	sum=java.lang.Math.sqrt(sum);
	if(sum<dist){return true;}
	else{return false;}
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

}
