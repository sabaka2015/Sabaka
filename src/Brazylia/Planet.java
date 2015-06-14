package Brazylia;

/**
 * class Planet- there are values of initial parameters of planets in constructor.
 * GM=1
 * y=0 for all planets 
 * mass for planets: m=(r/0,5)^3*0,0001*M
 *
 *
 */  
public class Planet {
	double radius;
	double vx;
	double vy;
	double x;
	double y;
	double dt=0.01;
	double mass;
	double ax, ay;
	public Planet (double radius){
		this.radius=radius;
		this.vx=0.0;
		this.vy=Math.sqrt(1/radius);
		this.x=radius;
		this.y=0.0;
		this.mass=Math.pow((Math.sqrt(radius)/0.5), 3)*0.0002;
	}

}

class Rocket {
	double vx;
	double vy;
	double x; 
	double y;
	double dt=0.01;
	double mass;
	double ax, ay;
	
	public Rocket (double x, double y){
		this.x=x;
		this.y=y;
		this.vx=0.0;
		this.vy=0.91;
		this.mass=0.0000001;
	}
		
	
}