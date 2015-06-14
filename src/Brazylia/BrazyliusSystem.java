package Brazylia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class BrazyliusSystem extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int boxWidth=640;
	public int boxHeight=480;
	GettingLine gettingLine=new GettingLine();
	
	class MapandParameters extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MapandParameters(){
			setBackground(Color.lightGray);
			setLayout(new GridLayout(2,1));
			
		}
		public Dimension getPrafferedSize() {
			return new Dimension(boxWidth/4, boxHeight/2);
		}
		
	}
	
	class Parameters extends JPanel{


		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		float rocketFuel=1000;
		public Parameters(PrintingPlanets a) {
			
			setBorder(BorderFactory.createTitledBorder(null, gettingLine.getLine(), TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.WHITE));
			
			JLabel speed=new JLabel(gettingLine.getLine());
			JTextField Speed=new JTextField("15 km/s");
			TextThread SpeedThread=new TextThread(Speed, a, 1);
			
			Speed.setBackground(Color.white);
			Speed.setSize(100, 20);
			
			JLabel fuel=new JLabel(gettingLine.getLine());
			JTextField Fuel=new JTextField();
			TextThread FuelThread=new TextThread(Fuel, a, 2);
			
			JLabel distanceFromTheTarget=new JLabel(gettingLine.getLine());
			JTextField DistanceFromTheTarget=new JTextField("2.35 J.A.");
			DistanceFromTheTarget.setBackground(Color.white);
			DistanceFromTheTarget.setSize(100, 20);
			TextThread DistanceThread=new TextThread(DistanceFromTheTarget, a, 3);
			
			JLabel mass=new JLabel(gettingLine.getLine());
			JTextField Mass=new JTextField("1000t");
			Mass.setBackground(Color.white);
			Mass.setSize(100, 20);
			TextThread MassThread=new TextThread(Mass, a, 4);
			
			this.setLayout(new GridLayout(8, 1));
			this.add(speed);
			this.add(Speed);
			this.add(fuel);
			this.add(Fuel);
			this.add(distanceFromTheTarget);
			this.add(DistanceFromTheTarget);
			this.add(mass);
			this.add(Mass);
			
			setBackground(Color.gray);
			FuelThread.start();
			MassThread.start();
			DistanceThread.start();
			SpeedThread.start();
		}

	}
	class TextThread extends Thread{
		JTextField text;
		String napis;
		PrintingPlanets prpl;
		int checkField=0;
		int rocketFuel=0;
		int rocketMass=0;
		int rocketDistance=0;
		int rocketSpeed=0;
		

		public TextThread(JTextField g, PrintingPlanets pp,int i){
			text=g;
			prpl = pp;
			checkField=i;
		}
		public void run(){
			while(prpl.getFuel()!=0){
				
				if(checkField==1)
				{
					rocketSpeed = prpl.getSpeed();
					//prpl.setFuel(rocketSpeed);
					napis=Integer.toString((int)rocketSpeed);
					text.setText(napis+"km/s");
				}
				if(checkField==2)
				{
					rocketFuel = prpl.getFuel();
					//prpl.setFuel(rocketFuel);
					napis=Integer.toString((int)rocketFuel);
					text.setText(napis+"t");
				
				}
				if(checkField==3)
				{
					rocketDistance= prpl.getDistance();
					//prpl.setDistance(rocketDistance);
					napis=Integer.toString((int)rocketDistance);
					text.setText(napis+"J.A.");
				}
				if(checkField==4)
				{
					rocketMass = prpl.getMass();
					//prpl.setMass(rocketMass);
					napis=Integer.toString((int)rocketMass);
					text.setText(napis+"t");
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}
	}

	public BrazyliusSystem (){
		setSize(boxWidth,boxHeight);		
		setLayout(new BorderLayout());
		PrintingPlanets PlayingField=new PrintingPlanets();
		PlayingField.setFocusable(true);
		PlayingField.setRequestFocusEnabled(true);
		MapandParameters map=new MapandParameters();
		PlayingField.setBorder(BorderFactory.createTitledBorder(null, gettingLine.getLine(), TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.WHITE));
		this.add(PlayingField,BorderLayout.CENTER);
		this.add(map, BorderLayout.EAST);
		Parameters Param=new Parameters(PlayingField);
		MapLocation MapLocation=new MapLocation(gettingLine.getLine(), gettingLine.getLine(), 16, PlayingField);
		map.add(Param, BorderLayout.NORTH);
	    map.add(MapLocation,BorderLayout.SOUTH);
	    setTitle("Brazylius");
	}
	
	//uwaga! languageChooser przeniesiony do getLine w LanuguageChooser- tam jest teraz tworzony!
	public static void main(String[] args) {
		BrazyliusSystem frame = new BrazyliusSystem(); 
		frame.setVisible(true);
		
	}
	
}
