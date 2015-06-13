package Brazylia;


import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


//Uwaga!!!!! siła silnika powinna modyfikować przyspieszenie (zasada Newtona), a nie bezpośrednio położenie- zmiana koncepcji, żeby było fizycznie!
public class PrintingPlanets extends JPanel implements ActionListener, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int planetsLocationHelper=6;
	public List<Planet> planets = new ArrayList<Planet>();
	public Rocket rocket=new Rocket(1.2, 0);
	boolean ifFuelUsed=false;
	int direction=0;
	BufferedImage imageL;
	BufferedImage imageR;
	BufferedImage imageD;
	BufferedImage imageU;
	int fuel=1000;
	
	
	{	
		for (int ii=1; ii<9; ii+=1){
			Planet p=new Planet((float)3*ii);
			planets.add(p);
		}
	}
	
	List<Planet> getPlanets(){
		return planets;
	}
	
	Planet getPlanet(int i){
		return planets.get(i);
	}
	
	Timer timer;
	
	public PrintingPlanets() {
		
		try {
			imageL = ImageIO.read(new File("rak.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			imageR = ImageIO.read(new File("rakpr.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			imageU = ImageIO.read(new File("rakgr.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	try {
			imageD = ImageIO.read(new File("rakdl.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(Color.BLACK);
		setFocusable(true);
		setRequestFocusEnabled(true);
		addKeyListener(this);
		timer = new Timer(10, this);
	    timer.setInitialDelay(190);
	    timer.start();

	}
	

	CalcModel calculate= new CalcModel();
	
	public void actionPerformed(ActionEvent e) {
	 	for (int i=0; i<planets.size(); i++){
			
			calculate.iterate(planets.get(i),0.01);
		}
	 	calculate.iterateRocket(rocket, 0.01, planets, this.getWidth()/2, this.getHeight()/2, this);
	    repaint();
	  }
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){rocket.ax=3; ifFuelUsed=true; direction=0;fuel--;} 
		if (e.getKeyCode() == KeyEvent.VK_LEFT){rocket.ax=-3; ifFuelUsed=true;direction=1;fuel--;} 
		if (e.getKeyCode() == KeyEvent.VK_UP) {rocket.ay=-3; ifFuelUsed=true;direction=2;fuel--;} 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {rocket.ay=3; ifFuelUsed=true;direction=3;fuel--;} 
		 
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	
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
		g.fillOval(this.getWidth()/2+(int)(this.getWidth()/planetsLocationHelper*(-rocket.x)), this.getHeight()/2+(int)(this.getWidth()/planetsLocationHelper*(-rocket.y)), this.getWidth()/5, this.getWidth()/5);
		for (int j=0; j<planets.size(); j+=1){
			g2.setColor(colors[j]);
			printPlanet(g2, planets.get(j));

		}
		g2.setColor(colors[8]);
		printRocket(g2, rocket);
		
		if(calculate.GameOver==true||fuel==0)
		{
		
			g2.setFont(new Font(null, Font.PLAIN, 18));
			g2.clearRect(0, 0, this.getWidth(), this.getHeight());
			g2.setColor(Color.red);
			
			g2.drawString("GAME OVER", this.getWidth()/2,  this.getHeight()/2);

		
		}
	}
	
	private void printPlanet(Graphics2D g, Planet b){
		g.fillOval((int)(this.getWidth()*(0.5+0.025)+(this.getWidth()/planetsLocationHelper*(b.x-rocket.x))), (int)(this.getHeight()*(0.5+0.025)+(this.getHeight()/planetsLocationHelper*(b.y-rocket.y))), (int)(this.getWidth()/90*Math.sqrt(b.radius)), (int)(this.getWidth()/90*Math.sqrt(b.radius)));
		
	}
	

	
	private void printRocket(Graphics2D g, Rocket r){
		if(direction==0)
		g.drawImage(imageL,(int)(this.getWidth()*(0.5+0.025)) , (int)(this.getHeight()*(0.5+0.025)), this);
		if(direction==1)
		g.drawImage(imageR,(int)(this.getWidth()*(0.5+0.025)) , (int)(this.getHeight()*(0.5+0.025)), this);
		if(direction==2)
		g.drawImage(imageU,(int)(this.getWidth()*(0.5+0.025)) , (int)(this.getHeight()*(0.5+0.025)), this);
        if(direction==3)
        g.drawImage(imageD,(int)(this.getWidth()*(0.5+0.025)) , (int)(this.getHeight()*(0.5+0.025)), this);
	}
	
	public void setFuel(int f)
	{
		fuel = f;
	}

	public int getFuel()
	{
		return fuel;
	}
	
	public void setPlanLocHelper(int f)
	{
		planetsLocationHelper = f;
	}

	public int getPlanLocHelper()
	{
		return planetsLocationHelper;
	}



}
