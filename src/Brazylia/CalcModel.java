package Brazylia;

import java.util.List;


public class CalcModel {
	public void iterate(Planet p,double dt){

	        p.ax=(-p.x)/Math.pow(p.radius, 3);
	        p.ay=(-p.y)/Math.pow(p.radius,  3);
	        p.vx+=(dt)*p.ax;
	        p.vy+=(dt)*p.ay;
	        p.x+=(dt*p.vx);
	        p.y+=(dt*p.vy);
	  
	    }
	//na razie obsługuje tylko siły grawitacji. Wymnożenie przez masę- proporcja w stosunku do siły grawitacji gwiazdy.
	/**
	 * @param rocket
	 * @param dt
	 * @param list
	 * @param starPositionX
	 * @param starPositionY
	 * @param p
	 */
	public void iterateRocket (Rocket rocket, double dt, List<Planet> list, int starPositionX, int starPositionY, PrintingPlanets p){
		/*double distance=Math.sqrt(Math.pow(PrintingPlanets.shiftX, 2)+Math.pow(PrintingPlanets.shiftY, 2));
		racket.ax=(-PrintingPlanets.shiftX)/Math.pow(distance, 3);
		racket.ay=(-PrintingPlanets.shiftY)/Math.pow(distance, 3);
		for (int ii=0; ii<8; ii++){
			distance=Math.sqrt(Math.pow(PrintingPlanets.shiftX-list.get(ii).x, 2)+Math.pow(PrintingPlanets.shiftY-list.get(ii).y, 2));
			racket.ax+=(-PrintingPlanets.shiftX+list.get(ii).x)/Math.pow(distance, 3)*list.get(ii).mass;
			racket.ay+=(-PrintingPlanets.shiftY+list.get(ii).y)/Math.pow(distance, 3)*list.get(ii).mass;
		}
		racket.vx+=(dt)*racket.ax;
		racket.vy+=(dt)*racket.ay;
		racket.x+=dt*racket.vx;
		racket.y+=dt*racket.vy;
		PrintingPlanets.shiftX+=dt*racket.vx;
		PrintingPlanets.shiftY+=dt*racket.vy;*/
		//sprawdzam czy klawisze- strzałki użyte:
		PrintingPlanets PrintPlHelp=p;
		List <Planet> Planets=PrintPlHelp.planets;
		if (p.ifFuelUsed==false){
			rocket.ax=0;
			rocket.ay=0;
		}
		else {
			p.ifFuelUsed=false;
		}
		double distance=Math.sqrt(Math.pow(rocket.x, 2)+Math.pow(rocket.y, 2));
		rocket.ax+=(-rocket.x)/Math.pow(distance, 3);
		rocket.ay+=(-rocket.y)/Math.pow(distance, 3);
		for (int ii=0; ii<8; ii++){
			distance=Math.sqrt(Math.pow(rocket.x-list.get(ii).x, 2)+Math.pow(rocket.y-list.get(ii).y, 2));
			rocket.ax+=(-rocket.x+list.get(ii).x)/Math.pow(distance, 3)*list.get(ii).mass;
			rocket.ay+=(-rocket.y+list.get(ii).y)/Math.pow(distance, 3)*list.get(ii).mass;
			 
		}
		rocket.vx+=(dt)*rocket.ax;
		rocket.vy+=(dt)*rocket.ay;
		rocket.x+=dt*rocket.vx;
		rocket.y+=dt*rocket.vy;
		

	 
		/*
		for(int i=0;i<PrintingPlanets.planets.size();i++){
		//if((int)racket.x<=((int)(PrintingPlanets.planets.get(i).x)+(int)1/2*((PrintingPlanets.planets.get(i).radius)))&&(int)racket.x>=((int)(PrintingPlanets.planets.get(i).x)-(int)1/2*PrintingPlanets.planets.get(i).radius)&&(int)racket.y<=((int)(PrintingPlanets.planets.get(i).y)+(int)1/2*PrintingPlanets.planets.get(i).radius)&&(int)racket.y>=((int)(PrintingPlanets.planets.get(i).y)-1/2*(int)PrintingPlanets.planets.get(i).radius))
		double x=PrintingPlanets.planets.get(i).x;
		double y=PrintingPlanets.planets.get(i).y;
		double radius=PrintingPlanets.planets.get(i).radius;
		if (((float)racket.x<=(float)(x+0.3)&&(float)racket.x>=(float)(x-0.3))&&((float)racket.y<=(int)(y+0.3)&&(float)racket.y>=(float)(y-0.3)))
		{
		 System.out.print("Game Over");
		 System.exit(0);
		}
		}
		*/
		//nowy model obliczania zderzeń- kule bilardowe, strona AGH
		for(int i=0;i<Planets.size();i++){
			double x=Planets.get(i).x;
			double y=Planets.get(i).y;
			double vx=Planets.get(i).vx;
			double vy=Planets.get(i).vy;
			double radius=Planets.get(i).radius;
			double deltaX=rocket.x-x;
			double deltaY=rocket.y-y;
			double deltaVX=rocket.vx-vx;
			double deltaVY=rocket.vy-vy;
			double dist=0.15; //1/(double)PrintPlHelp.getPlanLocHelper()*radius;
			double b=2*(deltaX*deltaVX+deltaY*deltaVY);
			double a=deltaVX*deltaVX+deltaVY*deltaVY;
			double c=deltaX*deltaX+deltaY*deltaY-4*dist*dist;
			double delta=Math.pow(b, 2)-4*a*c;
			double t=rocket.dt*2;
			if (delta>=0){
				t=Math.min((-b-Math.sqrt(delta))/2/a,(-b+Math.sqrt(delta))/2/a) ;
				if (t<0) t=Math.max((-b-Math.sqrt(delta))/2/a,(-b+Math.sqrt(delta))/2/a) ;
				if (t<0) t=rocket.dt*2;
				System.out.println(t);
			}
			if (t<=rocket.dt) {
				 System.out.println("Game Over");
				 System.out.print(t);
				 System.exit(0);
				}
			
		}
	}
	
	
	}


