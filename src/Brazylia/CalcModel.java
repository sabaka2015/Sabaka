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
	public void iterateRacket (Racket racket, double dt, List<Planet> list, int starPositionX, int starPositionY){
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
		if (PrintingPlanets.ifFuelUsed==false){
			racket.ax=0;
			racket.ay=0;
		}
		else {
			PrintingPlanets.ifFuelUsed=false;
		}
		double distance=Math.sqrt(Math.pow(racket.x, 2)+Math.pow(racket.y, 2));
		racket.ax+=(-racket.x)/Math.pow(distance, 3);
		racket.ay+=(-racket.y)/Math.pow(distance, 3);
		for (int ii=0; ii<8; ii++){
			distance=Math.sqrt(Math.pow(racket.x-list.get(ii).x, 2)+Math.pow(racket.y-list.get(ii).y, 2));
			racket.ax+=(-racket.x+list.get(ii).x)/Math.pow(distance, 3)*list.get(ii).mass;
			racket.ay+=(-racket.y+list.get(ii).y)/Math.pow(distance, 3)*list.get(ii).mass;
			 
		}
		racket.vx+=(dt)*racket.ax;
		racket.vy+=(dt)*racket.ay;
		racket.x+=dt*racket.vx;
		racket.y+=dt*racket.vy;
		

	 
		
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
		
	}
	
	
	}


