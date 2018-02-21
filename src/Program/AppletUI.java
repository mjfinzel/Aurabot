package Program;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class AppletUI extends JFrame{
	private static final long serialVersionUID = -6215774992938009947L;
	public static int windowWidth=980;
	public static int windowHeight=580;
	public static Point location = new Point(1920,0);
	public static int GAME_FPS = 2;
	public static final long milisecInNanosec = 1000000L;
	public static final long secInNanosec = 1000000000L;
	private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
	String[] titles = {"Mario is a cow",
			"Mario smells bad", 
			"Mario is awesome", 
			"Only Mario can prevent forest fires", 
			"Mario loves trees",
			"Silly mario. Trix are for kids",
			"Portal Waster 3000",
			"Door? What door?",
			"You didn't want that portal anyways"};
	Controller ctrl;
	public static void main(String[] args) throws IOException{

		AppletUI f = new AppletUI ();
		f.setSize(windowWidth,windowHeight);
		f.setVisible(true);
		
		if(args.length==3) {
			GamePanel.maxFollowDistance=Integer.valueOf(args[0]);
			GamePanel.followXOffset=Integer.valueOf(args[1]);
			GamePanel.followYOffset=Integer.valueOf(args[2]);
			System.out.println("Custom Values Used:");
			System.out.println("args[0] (Maximum distance from leader) = "+GamePanel.maxFollowDistance);
			System.out.println("args[1] (X offset from leader) = "+GamePanel.followXOffset);
			System.out.println("args[2] (Y offset from leader) = "+GamePanel.followYOffset);
		}
		else if(args.length>0) {
			System.out.println("Invalid input. Correct input is as follows:");
			System.out.println("	java -jar Aurabot.jar A B C");
			System.out.println("where:");
			System.out.println("A = Maximum distance from leader before continuing to follow.");
			System.out.println("B = Number of pixels to the right of the top left corner of the leading player's HP to click when following.");
			System.out.println("C = Number of pixels below the top left corner of the leading player's HP to click when following.");
			System.exit(1);
		}
		else {
			System.out.println("Default Values Used:");
			System.out.println("args[0] (Maximum distance from leader) = "+GamePanel.maxFollowDistance);
			System.out.println("args[1] (X offset from leader) = "+GamePanel.followXOffset);
			System.out.println("args[2] (Y offset from leader) = "+GamePanel.followYOffset);
		}
		activate.makeActive(GamePanel.windowNames[GamePanel.currentWindow]);
	}
	public AppletUI() {

		setSize(windowWidth,windowHeight);
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		JPanel drawPanel = new GamePanel();
		setTitle(titles[GamePanel.randomNumber(0, titles.length-1)]);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		drawPanel.setBackground(new Color(200,10,10));
		ctrl = new Controller();
		this.addKeyListener(ctrl);
		ctrl.setGamePanel(drawPanel);
		//this.setFocusable(true);
		pane.add(drawPanel);
		
		setLocation(location.x,location.y);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	activate.makeActive(GamePanel.windowNames[GamePanel.currentWindow]);
		    	GamePanel.insult();
		    	System.exit(0);
		    }
		});
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);  
		//this.setUndecorated(true);  
		//We start game in new thread.
		Thread gameThread = new Thread() {			
			public void run(){
				gameLoop();
			}
		};
		//setVisible(true);
		gameThread.start();
	}
	public void gameLoop(){
		long beginTime, timeTaken, timeLeft;
		while(true){
			windowWidth = this.getWidth();
			windowHeight = this.getHeight();
			
			
			beginTime = System.nanoTime();
			
			repaint();
			
			timeTaken = System.nanoTime() - beginTime;
			timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
			// If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
			if (timeLeft < 10){ 
				timeLeft = 10; //set a minimum
			}
			try {
				//Provides the necessary delay and also yields control so that other thread can do work.
				Thread.sleep(timeLeft);
			} catch (InterruptedException ex) { }
		}
	}
}
