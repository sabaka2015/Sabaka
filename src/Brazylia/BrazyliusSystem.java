package Brazylia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
//zmiana próbna
//zmiana na stronie int.
public class BrazyliusSystem extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int boxWidth=640;
	public int boxHeight=480;

	
	GettingLine gettingLine=new GettingLine();
	
	class MapandParameters extends JPanel {

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

		private static final long serialVersionUID = 1L;
		float rocketFuel=100;
		//JTextField Fuel=new JTextField();
		public Parameters(PrintingPlanets a) {
			JTextField Fuel=new JTextField();
			Guzik guzik=new Guzik(Fuel, a);
			//add(Fuel);
			//guzik.start();
			
			
			setBorder(BorderFactory.createTitledBorder(null, gettingLine.getLine(), TitledBorder.LEFT, TitledBorder.TOP, new Font("times new roman",Font.PLAIN,12), Color.WHITE));
			
			JLabel speed=new JLabel(gettingLine.getLine());
			JTextField Speed=new JTextField("15 km/s");
			Speed.setBackground(Color.white);
			Speed.setSize(100, 20);
			
			JLabel fuel=new JLabel(gettingLine.getLine());
			
			JLabel distanceFromTheTarget=new JLabel(gettingLine.getLine());
			JTextField DistanceFromTheTarget=new JTextField("2.35 J.A.");
			DistanceFromTheTarget.setBackground(Color.white);
			DistanceFromTheTarget.setSize(100, 20);
			
			JLabel mass=new JLabel(gettingLine.getLine());
			JTextField Mass=new JTextField("1000t");
			Mass.setBackground(Color.white);
			Mass.setSize(100, 20);
			
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
			//rocketFuel=p.fuel;
			guzik.start();
		}

	}
	class Guzik extends Thread{
		//JButton guzik;
		JTextField guzik;
		String napis;
		PrintingPlanets prpl;
		
		int rocketFuel=100;
		public Guzik(JTextField g, PrintingPlanets pp){
//		public Guzik(JTextField g){
			guzik=g;
			prpl = pp;
			
		}
		public void run(){
//		public void run(PrintingPlanets p){
			while(true){
				//guzik.doClick();
				//rocketFuel+="1";
				//rocketFuel=PrintingPlanets.fuel;
				rocketFuel = prpl.getFuel();
				rocketFuel++;
				prpl.setFuel(rocketFuel);
				napis=Integer.toString((int)rocketFuel);
				System.out.print(napis);
				
				guzik.setText(napis);
				//j.setText(f);
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
		MapLocation MapLocation=new MapLocation(gettingLine.getLine(), gettingLine.getLine(), 16);
		map.add(Param, BorderLayout.NORTH);
	    map.add(MapLocation,BorderLayout.SOUTH);
	    setTitle("Brazylius");
	}
	
	
	public static void main(String[] args) {
		LanguageChooser languageChooser=new LanguageChooser();
		BrazyliusSystem frame = new BrazyliusSystem(); 
		frame.setVisible(true);
		
	}
	
}
