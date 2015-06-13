package Brazylia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

public class MapLocation extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	int planetsLocationHelper2=40;
	Timer timer;

	public MapLocation(String lineA, String lineB, int helper){
		
		setBorder(BorderFactory.createTitledBorder(null, lineA, TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.WHITE));
		setBackground(Color.blue);
		JPanel Title= new JPanel();
		JLabel title= new JLabel(lineB);
		Title.setBackground(Color.LIGHT_GRAY);
		Title.add(title);
		this.add(Title);
		setBackground(Color.BLUE);
		setFocusable(true);
		setRequestFocusEnabled(true);
		timer = new Timer(10, this);
	    timer.setInitialDelay(190);
	    timer.start();

		
		
	}
CalcModel calculate= new CalcModel();
	
	public void actionPerformed(ActionEvent e) {
	 	for (int i=0; i<PrintingPlanets.planets.size(); i++){
			
			calculate.iterate(PrintingPlanets.planets.get(i),0.01);
		}
	 	calculate.iterateRocket(PrintingPlanets.rocket, 0.01, PrintingPlanets.planets, this.getWidth()/2, this.getHeight()/2);
	    repaint();
	  }
	
	//Planets colors
	Color colors[]=new Color[9];
	{
	colors[0]=Color.YELLOW;
	colors[1]=Color.GREEN;
	colors[2]=Color.PINK;
	colors[3]=Color.magenta;
	colors[4]=Color.RED;
	colors[5]=Color.ORANGE;
	colors[6]=Color.RED;
	colors[7]=Color.GREEN;
	colors[8]=Color.WHITE;
	}
	
	public void paint(Graphics g){	
		
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		g.setColor(Color.yellow);
		g.fillOval(this.getWidth()/2, this.getHeight()/2, this.getWidth()/15, this.getWidth()/15);
		for (int j=0; j<PrintingPlanets.planets.size(); j+=1){
			g2.setColor(colors[j]);
			printPlanet(g2, PrintingPlanets.planets.get(j));

		}
		g2.setColor(colors[8]);
		printRocket(g2, PrintingPlanets.rocket);
	}
	
	private void printPlanet(Graphics2D g, Planet b){
		g.fillOval((int)(this.getWidth()*(0.5+0.025)+(this.getWidth()/planetsLocationHelper2*b.x)), (int)(this.getHeight()*(0.5+0.025)+(this.getHeight()/planetsLocationHelper2*b.y)), (int)(this.getWidth()/90*Math.sqrt(b.radius)), (int)(this.getWidth()/90*Math.sqrt(b.radius)));
	}
	
	private void printRocket(Graphics2D g, Rocket r){
		//dodatkowy warunek- jeśli wyjdzie za mapkę
		
		if((1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.x)>-0.5-0.025&&(1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.x))<0.5-0.025&&((1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.y))==-0.5||(1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.y))==0.5)))
		{
			
		}
		
		int a=0;
		if ((1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.x))>0.5-0.025) a+=1;
		if ((1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.x))<-0.5-0.025) a+=2;
		if ((1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.y))>0.5-0.025) a+=10;
		if ((1/(double)planetsLocationHelper2*(PrintingPlanets.rocket.y))<-0.5-0.025) a+=20;
		switch (a){
		case 0: g.fillRect((int)(this.getWidth()*(0.5+0.025)+(this.getWidth()/planetsLocationHelper2*(PrintingPlanets.rocket.x))), (int)(this.getHeight()*(0.5+0.025)+(this.getHeight()/planetsLocationHelper2*(PrintingPlanets.rocket.y))), 5, 5); break;
		case 1: g.fillRect((int)(this.getWidth())-5, (int)(this.getHeight()*(0.5+0.025)+(this.getHeight()/planetsLocationHelper2*(PrintingPlanets.rocket.y))), 5, 5); break;
		case 2:	g.fillRect(0, (int)(this.getHeight()*(0.5+0.025)+(this.getHeight()/planetsLocationHelper2*(PrintingPlanets.rocket.y))), 5, 5); break;
		case 10: g.fillRect((int)(this.getWidth()*(0.5+0.025)+(this.getWidth()/planetsLocationHelper2*(PrintingPlanets.rocket.x))), (int)(this.getHeight())-5, 5, 5); break;
		case 20: g.fillRect((int)(this.getWidth()*(0.5+0.025)+(this.getWidth()/planetsLocationHelper2*(PrintingPlanets.rocket.x))), 0, 5, 5); break;
		case 11: g.fillRect((int)(this.getWidth())-5, (int)(this.getHeight())-5, 5, 5); break;
		case 12: g.fillRect(0, (int)(this.getHeight()*(0.5+0.025)+(this.getHeight()/planetsLocationHelper2*(PrintingPlanets.rocket.y))), 5, 5); break;
		case 21: g.fillRect((int)(this.getWidth())-5, 0, 5, 5); break;
		case 22: g.fillRect(0, 0, 5, 5); break;

		}
		//g.fillRect((int)(this.getWidth()*(0.5+0.025)+(this.getWidth()/planetsLocationHelper2*(PrintingPlanets.racket.x))), (int)(this.getHeight()*(0.5+0.025)+(this.getHeight()/planetsLocationHelper2*(PrintingPlanets.racket.y))), 5, 5);
	}


}

