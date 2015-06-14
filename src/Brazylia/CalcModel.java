package Brazylia;

import java.util.List;

/**
 * 
 * Calculation models of planets and the rocket by Euler method
 * Gravity forces between: the star and the planets, the star and the rocket
 * and the planets and the rocket.
 * Forces between planets are omitted.
 * 
 * Game over after collision with a planet or the star. 
 * Game over when the fuel is gone.
 *
 */
public class CalcModel {
	boolean GameOver=false;
	double distanceToStar=0;
	double speed=0;
	public void iterate(Planet p,double dt){

	        p.ax=(-p.x)/Math.pow(p.radius, 3);
	        p.ay=(-p.y)/Math.pow(p.radius,  3);
	        p.vx+=(dt)*p.ax;
	        p.vy+=(dt)*p.ay;
	        p.x+=(dt*p.vx);
	        p.y+=(dt*p.vy);
	  
	    }


	public void iterateRocket (Rocket rocket, double dt, List<Planet> list,
			int starPositionX, int starPositionY, PrintingPlanets p) {
		
		PrintingPlanets PrintPlHelp=p;
		List <Planet> Planets=PrintPlHelp.planets;
		if (p.ifFuelUsed==false){
			rocket.ax=0;
			rocket.ay=0;
		}
		else {
			p.ifFuelUsed=false;
		}
		speed=Math.sqrt(Math.pow(rocket.vx, 2)+Math.pow(rocket.vy, 2));
	    distanceToStar=Math.sqrt(Math.pow(rocket.x, 2)+Math.pow(rocket.y, 2));
		double distance=Math.sqrt(Math.pow(rocket.x, 2)+Math.pow(rocket.y, 2));
		rocket.ax+=(-rocket.x)/Math.pow(distance, 3);
		rocket.ay+=(-rocket.y)/Math.pow(distance, 3);
		for (int ii=0; ii<8; ii++){
			distance=Math.sqrt(Math.pow(rocket.x-list.get(ii).x, 2)+Math.pow(+
					rocket.y-list.get(ii).y, 2));
			rocket.ax+=(-rocket.x+list.get(ii).x)/Math.pow(distance, 3)*list.get(ii).mass;
			rocket.ay+=(-rocket.y+list.get(ii).y)/Math.pow(distance, 3)*list.get(ii).mass;
			 
		}
		rocket.vx+=(dt)*rocket.ax;
		rocket.vy+=(dt)*rocket.ay;
		rocket.x+=dt*rocket.vx;
		rocket.y+=dt*rocket.vy;
		
		/**
		 * Author: Mateusz
		 * Support collisions with planets and the star:
		 * algorithm is counting time to next collision for all orbs.
		 * if the time < dt, collision is detected, game is over.
		 */
		
		for(int i=0;i<Planets.size();i++){
			double x=Planets.get(i).x;
			double y=Planets.get(i).y;
			double vx=Planets.get(i).vx;
			double vy=Planets.get(i).vy;
			double deltaX=rocket.x-x;
			double deltaY=rocket.y-y;
			double deltaVX=rocket.vx-vx;
			double deltaVY=rocket.vy-vy;
			double dist=0.15; 
			double b=2*(deltaX*deltaVX+deltaY*deltaVY);
			double a=deltaVX*deltaVX+deltaVY*deltaVY;
			double c=deltaX*deltaX+deltaY*deltaY-4*dist*dist;
			double delta=Math.pow(b, 2)-4*a*c;
		    double t=rocket.dt*2;
			if (delta>=0){
				t=Math.min((-b-Math.sqrt(delta))/2/a,(-b+Math.sqrt(delta))/2/a) ;
				if (t<0) t=Math.max((-b-Math.sqrt(delta))/2/a,(-b+Math.sqrt(delta))/2/a) ;
				if (t<0) t=rocket.dt*2;
			}
		
			if (t<=rocket.dt) {
				
			     GameOver=true;
				
			}
			if (Math.sqrt(rocket.x*rocket.x+rocket.y*rocket.y)<0.6) GameOver=true;
		}
		
	}
	
	
}

		


