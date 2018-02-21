package Program;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;


public class GamePanel extends JPanel{
	public static int currentWindow = 0;
	public static String baseDirectory = "";

	static String [] windowNames = {"Path of Exile"};
	public static boolean paused = false;

	static int[] loadingImageTries = new int[windowNames.length];
	static Point mousePos = new Point(-1,-1);

	static Robot robot;
	static boolean alreadyPressed=false;
	static boolean alreadyReleased=true;
	static boolean debug = false;

	static boolean isAuraBuild=true;

	long delay = 0;
	long leaderLastSeen = 0;
	long lastDoorEntryTime = 0;
	static long soonestAvailableKeyPress = 0;

	static int lifeFlaskThreshold = 50;
	boolean []flaskInUse = new boolean[5];
	boolean []flaskAvailable = new boolean[5];

	Color[] gemPattern = {
			//new Color(210,161,122),
			new Color(182,135,101),
			new Color(150,114,85),
			new Color(134,98,72),
			new Color(134,95,67),
			new Color(134,95,67),
			new Color(128,90,67),
			new Color(126,88,67),
			new Color(126,88,67),
			new Color(126,85,60),
			new Color(126,85,60),
			new Color(126,85,60),
			new Color(126,88,60),
			new Color(126,88,60),
			new Color(126,88,65),
			new Color(126,88,67),
			new Color(126,88,67),
			new Color(120,85,61),
			new Color(120,85,61),
			new Color(120,85,61),
			new Color(126,88,67),
			new Color(126,88,67),
			new Color(126,88,65),
			new Color(126,88,61),
			new Color(126,88,61),
			new Color(121,85,61),
			new Color(119,85,61),
			new Color(119,85,61),
			new Color(126,92,67),
			new Color(126,92,67),
			new Color(128,93,68),
			new Color(140,99,71),
			new Color(140,99,71),
			new Color(148,110,79),
			new Color(106,82,59),
	};

	Color[] ressurectPattern = {
			new Color(3,5,8),
			new Color(22,17,17),
			new Color(16,12,16),
			new Color(40,34,40),
			new Color(17,12,16),
			new Color(14,11,11),
			new Color(18,14,17),
			new Color(50,46,50),
			new Color(114,91,87),
			new Color(16,12,16),
	};
	int[] inventoryPattern = {
			-16250872,
			-16250872,
			-16250872,
			-16250872,
			-16250872,
			-16250872,
			-16251128,
			-16251128,
			-16251128,
			-16251384,
	};
	Color[] scrollPattern = {
			new Color(223,208,187),
			new Color(199,163,120),
			new Color(111,83,55),
			new Color(74,50,32),
	};
	public static int maxFollowDistance = 120;
	public static int followXOffset = 30;
	public static int followYOffset = 130;
	public GamePanel(){

		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	static int randomNumber(int min, int max){
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	public static void click(int x, int y){
		//System.out.println("attempting click at: "+x+","+y);
		mousePos.x = x;
		mousePos.y = y;

		alreadyReleased=true;
		alreadyPressed=false;
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		robot.mouseMove(x,y);
		sleep(20, 50);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		sleep(120, 150);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		sleep(50, 90);
	}
	public static void useKey(int k, boolean typing) {
		if(System.currentTimeMillis()>soonestAvailableKeyPress||typing) {
			robot.keyPress(k);
			if(!typing) sleep(70, 152);
			else sleep(18, 32);
			robot.keyRelease(k);
			if(typing) sleep(18,32);
			soonestAvailableKeyPress = System.currentTimeMillis()+randomNumber(193,257);
		}
	}
	public static void sleep(int min, int max){
		try {
			Thread.sleep(randomNumber(min, max));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Draw(g);
	}
	public int getScreenState(BufferedImage img){
		return -1;
	}
	public boolean colorMatchesES(Color current) {
		if(current.getRed()>=53&&current.getRed()<=132) {
			if(current.getGreen()>=150&&current.getGreen()<=170) {
				if(current.getBlue()>=85&&current.getBlue()<=175) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean colorMatchesHP(Color current) {
		if(current.getRed()>=24&&current.getRed()<=33) {
			if(current.getGreen()>=102&&current.getGreen()<=167) {
				if(current.getBlue()>=37&&current.getBlue()<=55) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean sameColor(Color a, Color b) {
		double rMax = Math.max(a.getRed(), b.getRed());
		double gMax = Math.max(a.getGreen(), b.getGreen());
		double bMax = Math.max(a.getBlue(), b.getBlue());
		double rMin = Math.min(a.getRed(), b.getRed());
		double gMin = Math.min(a.getGreen(), b.getGreen());
		double bMin = Math.min(a.getBlue(), b.getBlue());
		if(rMax-rMin<=50&&rMin/rMax>=.90 && gMin/gMax>=.90 && bMin/bMax>=.90) {
			return true;
		}
		return false;
	}
	public static void addToClipboard(String s) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(s);
		clipboard.setContents(strSel, null);
	}
	public static void insult() {
		String[] insults = {"Fuckstick", "Gutter Slut", "Cockmuppet", "Cousin Fucker", "Assclown", "Douchemonger", "Twat", "Mouth-breather", "Cockshiner", "Cheesedick", "Fuckface", "Knuckle-dragger", "Shitstick", "Tool", "Shitbag", "Carpet-cleaner", "Asshat", "Slutbag", "Numbnuts", "Dicknose", "Cum dumpster", "Sleezebag", "Buttmunch", "Twatwaffle", "Tard", "Cunt rag", "Cuntmuscle", "Shitstain", "Dickbreath", "Jizztissue", "Cockgobbler", "Cuntkicker", "Douchenozzle", "Pigfucker", "Butknuckler", "Clitsplitter", "Shitshaker", "Douche canoe", "Fuckrag", "Rumpranger", "Cock-juggling thundercunt", "Ass fiddler", "Butt monkey", "Fat lard", "Inbreeder", "Boogerface", "Ballsack", "Cumwad", "Poo-poo head", "Village idiot", "Bozo", "Wanker", "Weirdo", "Porker", "Fatso", "Geezer", "Wuss", "Fucktard"};
		String[] adverbs = {"antiquated", "rambunctious", "mundane", "misshapen", "dreary", "dopey", "clammy", "brazen", "indiscreet", "imbecilic", "dysfunctional", "drunken", "dismal", "deficient", "daft", "asinine", "morbid", "repugnant", "unkempt", "decrepit", "impertinent", "grotesque"};
		addToClipboard("&You're a "+insults[randomNumber(0,insults.length-1)]+" mario.");
		useKey(KeyEvent.VK_ENTER,true);
		robot.keyPress(KeyEvent.VK_CONTROL);
		sleep(25, 45);
		useKey(KeyEvent.VK_V,true);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		useKey(KeyEvent.VK_ENTER,true);
		
	}
	public Point getLeaderPos(BufferedImage screen) {

		for(int i = 1; i<screen.getWidth()-10;i+=3) {
			for(int j = 0; j<screen.getHeight()-5; j+=1) {
				Color current = new Color(screen.getRGB(i, j));
				//if(!colorMatchesES(new Color(screen.getRGB(i-1, j)))) {
				if(colorMatchesHP(current)&&colorMatchesHP(new Color(screen.getRGB(i+3, j)))&&colorMatchesHP(new Color(screen.getRGB(i+6, j)))) {
					return new Point(followXOffset+(i*2), followYOffset+(j*2));
				}
				//}
			}
		}
		return null;
	}
	public BufferedImage resizeImage(BufferedImage img) {
		BufferedImage result = new BufferedImage(img.getWidth()/2, img.getHeight()/2, BufferedImage.TYPE_INT_ARGB);
		Graphics g = result.getGraphics();
		g.drawImage(img, 0, 0, img.getWidth()/2, img.getHeight()/2,null);
		g.dispose();
		return result;
	}
	public static double distanceBetween(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(((double)x1-x2),2)+Math.pow(((double)y1-y2),2));
	}

	public void delay(int a, int b) {
		delay = System.currentTimeMillis()+randomNumber(a,b);
	}

	public void clickDoor(ArrayList<Rectangle> doors) {
		if(doors.size()==0) return;
		if(System.currentTimeMillis()-lastDoorEntryTime>8000) {
			//determine which doors are actually usable
			Rectangle closest = doors.get(0);
			double closestDistance = -1;
			for(int i = 0; i<doors.size();i++) {
				double x = doors.get(i).x+(doors.get(i).width/2);
				double y = doors.get(i).y+(doors.get(i).height/2);
				double distance = Math.sqrt(Math.pow((x-480),2)+Math.pow((y-260),2));
				if(i==0) {
					closestDistance=distance;
					closest=doors.get(i);
				}
				else if(distance<closestDistance) {
					closestDistance=distance;
					closest=doors.get(i);
				}
			}
			int x = randomNumber(closest.x, closest.x+closest.width);
			int y = randomNumber(closest.y, closest.y+closest.height);
			click((x*2), (y*2));
			System.out.println("   Clicked the closest door!");
		}
		else {
			Rectangle random = doors.get(randomNumber(0, doors.size()-1));
			int x = randomNumber(random.x, random.x+random.width);
			int y = randomNumber(random.y, random.y+random.height);
			click((x*2), (y*2));
			System.out.println("   Clicked a random door!");
		}
		delay(200,300);

	}
	public void checkForLevelableGem() {
		//Screenshot of potential levelable gems
		BufferedImage gems = robot.createScreenCapture(new Rectangle(1910, 289, 1, 33));

		boolean matchFound=true;
		for(int i = 0; i<gems.getHeight(); i+=2) {
			Color current = new Color(gems.getRGB(0, i));
			if(!current.equals(gemPattern[i])) {
				matchFound=false;
				break;
			}
		}
		if(matchFound) {
			int x = randomNumber(1843,1868);
			int y = randomNumber(293,318);
			click(x,y);
			System.out.println("   Clicking on a gem to level it up!");
		}
	}
	public void useQuickSilver() {
		boolean alreadyInEffect=false;
		for(int i = 2; i<5;i++) {
			if(flaskInUse[i]==true) alreadyInEffect = true;
		}
		if(alreadyInEffect==false) {
			int roll = randomNumber(0,2);
			if(flaskAvailable[2+roll]) {
				System.out.println("   Using a quicksilver flask");
				useKey(KeyEvent.VK_3+roll, false);
			}
		}

	}
	public void resurrect(BufferedImage screen) {

		//release the mouse
		if(!alreadyReleased) {
			alreadyReleased=true;
			alreadyPressed=false;
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}

		boolean found = true;
		for(int i = 0; i<ressurectPattern.length;i++) {
			Color c = new Color(screen.getRGB(405+i, 150));
			if(!ressurectPattern[i].equals(c)) {
				found = false;
				break;
			}
		}
		if(found) {
			System.out.println("Resurrecting");
			click(randomNumber(840, 1078), randomNumber(305, 333));
			System.out.println("Sleeping for 3.8-5.1 seconds");
			sleep(3820,5104);
			System.out.println("Finished Resurrecting.");
			castAuras();
		}

	}
	public void castAuras() {
		System.out.println("Casting auras");
		BufferedImage skills = robot.createScreenCapture(new Rectangle(1415, 952, 271, 118));
		Point[] positions = {
				new Point(110,2),
				new Point(165,2),
				new Point(220,2),
				new Point(0,70),
				new Point(55,70),
				new Point(111,70),
				new Point(166,70),
				new Point(222,70),
		};
		System.out.println("----");
		for(int i = 1; i<positions.length;i++) {
			Color current = new Color(skills.getRGB(positions[i].x, positions[i].y));
			System.out.println(i+" - "+current);
			//if this aura is off
			if(current.getRed()!=113&&current.getRed()!=204&&current.getRed()!=203) {
				System.out.println("Aura at position "+i+" is off. Casting it now.");
				if(i==1) {
					useKey(KeyEvent.VK_6, false);
					sleep(2203,2508);
				}
				if(i==2) {
					useKey(KeyEvent.VK_7, false);
					sleep(2203,2508);
				}
				else if(i==3) {
					useKey(KeyEvent.VK_Q, false);
					sleep(2203,2508);
				}
				else if(i==4) {
					useKey(KeyEvent.VK_W, false);
					sleep(2203,2508);
				}
				else if(i==5) {
					useKey(KeyEvent.VK_E, false);
					sleep(2203,2508);
				}
				else if(i==6) {
					useKey(KeyEvent.VK_R, false);
					sleep(2203,2508);
				}
				else if(i==7) {
					useKey(KeyEvent.VK_T, false);
					sleep(2203,2508);
				}
			}

		}
		System.out.println("Finished casting auras");
	}
	public ArrayList<Rectangle> findDoors(BufferedImage screen){
		ArrayList<Rectangle> doors = new ArrayList<Rectangle>();
		Color textColor = new Color(200,200,220);
		for(int i = 0; i<screen.getWidth(); i++) {
			for(int j = 1; j<screen.getHeight()-1;j++) {
				Color current = new Color(screen.getRGB(i, j));
				if(sameColor(current, textColor)) {
					closestDoor(i,j,doors);
				}

			}
		}

		ArrayList<Rectangle> usableDoors = new ArrayList<Rectangle>();
		//determine which doors are actually usable
		for(int i = 0; i<doors.size();i++) {
			Rectangle random = doors.get(i);
			if(random.getWidth()/random.getHeight()>3&&random.getHeight()>=4&&random.getWidth()<120&&random.getHeight()<=9) {
				usableDoors.add(random);
			}	
		}
		return usableDoors;
	}
	public void doFlaskStuff(Graphics g) {
		//Take a screenshot of the flasks (311, 980, 220, 94)
		BufferedImage flasks = robot.createScreenCapture(new Rectangle(0, 870, 531, 210));
		//determine which flasks are in use
		int w = 46;
		for(int i = 0; i<5; i++) {
			Color current = new Color(flasks.getRGB(311+(i*w), 201));
			Color charges = new Color(flasks.getRGB(322+(i*w), 180));

			if(current.equals(new Color(249,215,153))) {
				flaskInUse[i] = true;
			}
			else {
				flaskInUse[i] = false;
			}
			if(charges.getRed()<40&&charges.getGreen()<40&&charges.getBlue()<40) {
				flaskAvailable[i]=false;
			}
			else {
				flaskAvailable[i]=true;
			}
		}

		//determine %ES
		int percentES = -1;
		for(int i = 0; i<50; i++) {
			for(int j = 0; j<5; j++) {
				int y = (int)(double)((double)((double)flasks.getHeight()/50.0)*(double)i);
				Color current = new Color(flasks.getRGB(115+(20*j), y));
				if((double)current.getBlue()/(double)current.getRed()>1.4 && current.getBlue()>70) {
					percentES=(50-i)*2;
					break;
				}
			}
			if(percentES!=-1)break;
		}
		percentES++;
		g.setColor(Color.white);
		g.drawString("Energy Shield: "+percentES + "%", 10, 90);

		//determine %life
		int percentLife = -1;
		for(int i = 0; i<50; i++) {
			for(int j = 0; j<5; j++) {
				int y = (int)(double)((double)((double)flasks.getHeight()/50.0)*(double)i);
				Color current = new Color(flasks.getRGB(110+(3*j), y));
				if((double)current.getRed()/(double)current.getBlue()>2.5 && (double)current.getRed()/(double)current.getGreen()>2.5 && current.getRed()>40) {
					percentLife=(50-i)*2;
					break;
				}
			}
			if(percentLife!=-1)break;
		}
		percentLife++;
		g.setColor(Color.white);
		g.drawString("Life: "+percentLife + "%", 10, 106);

		//life flask
		if(isAuraBuild) {
			if(percentLife<=lifeFlaskThreshold&&flaskAvailable[0]) {
				useKey(KeyEvent.VK_1, false);
				lifeFlaskThreshold = randomNumber(55, 72);
			}
		}
		else {
			//use silver flask
			if(flaskAvailable[0]) {
				System.out.println("   Using silver flask");
				useKey(KeyEvent.VK_1, false);
			}
		}

		//quicksilver flask
		useQuickSilver();

		//doedre's elixir
		if(percentES>=90&&percentLife>5&&flaskAvailable[1]) {
			System.out.println("   Using doedre's elixer. Current Life is: "+percentLife+"%"+", Current ES is: "+percentES+"%");
			useKey(KeyEvent.VK_2, false);
		}
	}
	public void closestDoor(int x, int y, ArrayList<Rectangle> doors) {
		boolean found = false;
		int dist = 10;
		for(int i = 0; i<doors.size();i++) {
			Rectangle current = doors.get(i);
			//left
			if(x>=current.x-dist&&x<current.x&&y>=current.y-dist&&y<=current.y+current.height+dist) {
				current.width+=current.x-x;
				current.x=x;
				found = true;
			}
			//right
			if(x>current.x+current.width&&x<=current.x+current.width+dist&&y>=current.y-dist&&y<=current.y+current.height+dist) {
				current.width = x-current.x;
				found = true;
			}

			//top
			if(y>=current.y-dist&&y<current.y&&x>=current.x-dist&&y<=current.x+current.width+dist) {
				current.height+=current.y-y;
				current.y=y;
				found = true;
			}
			//bottom
			if(y>current.y+current.height&&y<=current.y+current.height+dist&&x>=current.x-dist&&x<=current.x+current.width+dist) {
				current.height=y-current.y;
				found = true;
			}
			if(found) {
				for(int j = 0; j<doors.size(); j++) {
					Rectangle other = doors.get(j);
					if(other!=current&&other.intersects(current)) {
						int newX = Math.min(current.x, doors.get(j).x);
						int newY = Math.min(current.y, doors.get(j).y);
						int newWidth = Math.max(current.x+current.width, other.x+other.width)-newX;
						int newHeight = Math.max(current.y+current.height, other.y+other.height)-newY;
						current.x=newX;
						current.y=newY;
						current.width = newWidth;
						current.height = newHeight;
						doors.remove(j);
					}
				}
				return;
			}
		}
		doors.add(new Rectangle(x,y,1,1));
	}
	public void destroyItem() {
		robot.keyRelease(KeyEvent.VK_SHIFT);
		//click on item
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		sleep(20, 50);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		sleep(17, 30);
		useKey(KeyEvent.VK_ENTER, true);
		sleep(27, 39);
		useKey(KeyEvent.VK_SLASH, true);
		useKey(KeyEvent.VK_D, true);
		useKey(KeyEvent.VK_E, true);
		useKey(KeyEvent.VK_S, true);
		useKey(KeyEvent.VK_T, true);
		useKey(KeyEvent.VK_R, true);
		useKey(KeyEvent.VK_O, true);
		useKey(KeyEvent.VK_Y, true);
		useKey(KeyEvent.VK_ENTER, true);
	}
	public void drawString(Graphics g, String s, int x, int y) {
		g.setColor(Color.white);
		Font font = new Font("Iwona Heavy",Font.BOLD,16);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics();
		g.drawString(s, x, y);
	}
	public void Draw(Graphics g){

		mousePos = MouseInfo.getPointerInfo().getLocation();
		if(!paused) {

			//Take a screenshot of the window
			//BufferedImage screen = resizeImage(robot.createScreenCapture(new Rectangle(200, 100, 1520, 800)));


			if(debug==false) {
				BufferedImage screen = resizeImage(robot.createScreenCapture(new Rectangle(0, 0, 1920, 1080)));
				//block out the chat box
				Graphics tmpg = screen.getGraphics();
				tmpg.setColor(Color.red);
				tmpg.fillRect(0, 0, 120, 540);
				tmpg.fillRect(820, 0, 140, 140);
				tmpg.fillRect(0, 460, 290, 80);
				tmpg.fillRect(680, 460, 290, 80);
				tmpg.fillRect(120, 270, 55, 150);
				tmpg.dispose();
				if(System.currentTimeMillis()>delay) {

					//resurrect if dead
					resurrect(screen);

					Point leaderPos = getLeaderPos(screen);
					boolean following = false;

					//if the leader is on the screen
					if(leaderPos!=null) {
						System.out.println("Found the leader at: " + leaderPos);
						drawString(g,"L Found",5,30);
						double distance = distanceBetween(leaderPos.x, leaderPos.y, 960, 520);

						//if the leading character is more than 150 pixels from the bot
						if(distance>maxFollowDistance) {
							System.out.println("The leading character is more than "+maxFollowDistance+" pixels away from the bot.");
							drawString(g,"L Far",5,30);

							double angleToLeader = Math.atan2(((double)leaderPos.x-960),(((double)leaderPos.y-520)));
							//move the mouse so that it is over the leading character
							//robot.mouseMove(leaderPos.x, leaderPos.y);
							double x = leaderPos.x;//Math.cos(angleToLeader)*(distance/2);
							double y = leaderPos.y;//Math.sin(angleToLeader)*(distance/2);
							robot.mouseMove((int)x,(int)y);
							System.out.println("   Moved the mouse to follow the leader. New Position is: "+x+","+ y);
							//press the mouse if it's not already pressed
							if(!alreadyPressed) {
								System.out.println("   Pressing the mouse");
								alreadyPressed=true;
								alreadyReleased=false;
								GamePanel.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
							}
							else {
								System.out.println("The mouse was already pressed, nothing changed");
							}


							//use flasks
							doFlaskStuff(g);
						}
						else {
							drawString(g,"L Close",5,30);
							System.out.println("The leading character is less than "+maxFollowDistance+" pixels away from the bot.");
							//check if any gems can be leveled up and click them if so
							checkForLevelableGem();

						}
						leaderLastSeen=System.currentTimeMillis();
					}
					else {
						g.drawImage(screen,0,0,null);
						System.out.println("Unable to locate the leader");
						//release the mouse
						if(!alreadyReleased) {
							System.out.println("   Released the mouse");
							alreadyReleased=true;
							alreadyPressed=false;
							robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						}

						drawString(g,"L Missing",5,30);

						//if the leader hasn't been seen for at least 1 second and it's been at least 5 seconds since the last door was used
						if(System.currentTimeMillis()-leaderLastSeen>1000&&(System.currentTimeMillis()-lastDoorEntryTime>5000)) {							

							//click on a door
							clickDoor(findDoors(screen));
							lastDoorEntryTime = System.currentTimeMillis();
						}

						if(!alreadyReleased) {
							System.out.println("   Released the mouse");
							alreadyReleased=true;
							alreadyPressed=false;
							robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						}

					}

				}
				else {
					drawString(g,"Delayed",5,30);
					System.out.println("Waiting for a delay");
				}

			}
			else {
				long tmp = System.currentTimeMillis();
				BufferedImage inventoryOpen = robot.createScreenCapture(new Rectangle(1880, 30, 10,1));
				boolean found=true;
				//System.out.println("---");
				for(int i = 0; i<10;i++) {
					int c = inventoryOpen.getRGB(i, 0);
					//System.out.println(c+",");
					if(c!=inventoryPattern[i]) found = false;
				}
				Point mouse = MouseInfo.getPointerInfo().getLocation();
				if(found&&mouse.x>1200&&mouse.y>450&&mouse.x<1920) {
					BufferedImage invContents = robot.createScreenCapture(new Rectangle(mouse.x-200,mouse.y-200,400,400));
					boolean scrollInUse=false;
					for(int i = 0; i<invContents.getWidth();i++) {
						for(int j = 0; j<invContents.getHeight();j++) {
							for(int k = 0; k<4;k++) {
								Color current = new Color(invContents.getRGB(i+k, j));
								if(!current.equals(scrollPattern[k])) break;
								if(k==3) scrollInUse=true;
							}
						}
					}
					//System.out.println(scrollInUse);
					if(!scrollInUse) {

						//ItemInfo.clearClipboard();
						//press ctrl-c
						robot.keyPress(KeyEvent.VK_CONTROL);
						sleep(25, 45);
						useKey(KeyEvent.VK_C, true);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						sleep(15, 25);

						//debug stuff here
						ItemInfo.Draw(g);

						if(ItemInfo.item!=null&&(ItemInfo.item.rarity==ItemInfo.RARE)&&ItemInfo.item.identified) {
							int totalResist = ItemInfo.item.totalResist();

							//one handed melee only weapon
							if((ItemInfo.item.baseType==ItemInfo.CLAW||ItemInfo.item.baseType==ItemInfo.THRUSTING_SWORD||ItemInfo.item.baseType==ItemInfo.ONE_HAND_AXE||ItemInfo.item.baseType==ItemInfo.ONE_HAND_MACE||ItemInfo.item.baseType==ItemInfo.ONE_HAND_SWORD)) {
								//if the item has crappy dps
								if(!(ItemInfo.item.dps>=430||ItemInfo.item.pdps>=280||ItemInfo.item.edps>=330)) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							//two handed melee only weapon
							else if((ItemInfo.item.baseType==ItemInfo.TWO_HAND_AXE||ItemInfo.item.baseType==ItemInfo.TWO_HAND_MACE||ItemInfo.item.baseType==ItemInfo.TWO_HAND_SWORD)) {
								//if the item has crappy dps
								if(!(ItemInfo.item.dps>=540||ItemInfo.item.pdps>=380||ItemInfo.item.edps>=400)) {
									//if the item has less than 5 links and has less than 6 sockets
									if(ItemInfo.item.sockets<6&&ItemInfo.item.links<5) {
										destroyItem();
										ItemInfo.clearClipboard();
									}
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.BOW) {
								//if the item has crappy dps and doesn't have +3 level of gems
								if(!(ItemInfo.item.dps>=390||ItemInfo.item.pdps>=330||ItemInfo.item.edps>=280)&&(ItemInfo.item.hasPrefix("Socketed Bow")==null||ItemInfo.item.hasPrefix("Socketed Gems")==null)) {
									//if the item has less than 5 links and has less than 6 sockets
									if(ItemInfo.item.sockets<6&&ItemInfo.item.links<5) {
										destroyItem();
										ItemInfo.clearClipboard();
									}
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.BOOTS) {
								if(!(totalResist>120||ItemInfo.item.energyShield>190||(totalResist>80&&ItemInfo.item.movespeed>=25)||(totalResist>50&&ItemInfo.item.movespeed>=25&&(ItemInfo.item.life>70||ItemInfo.item.energyShield>=100)))) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.GLOVES) {
								if(!((ItemInfo.item.life>60||ItemInfo.item.energyShield>80)&&(ItemInfo.item.hasAffix("Adds")!=null||ItemInfo.item.hasAffix("Speed")!=null))) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.HELMET) {
								if(!((totalResist>80&&ItemInfo.item.life>70)||ItemInfo.item.energyShield>200||ItemInfo.item.increasedRarity>=40)) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.BODY_ARMOUR) {
								if(!(totalResist>80&&ItemInfo.item.life>80)||ItemInfo.item.energyShield<450) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.BELT) {
								if(!(totalResist>=85&&ItemInfo.item.life>80)) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.SHIELD) {
								if(!(totalResist>100&&ItemInfo.item.life>80)&&ItemInfo.item.energyShield<260&&ItemInfo.item.spellDmg<60) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.WAND||ItemInfo.item.baseType==ItemInfo.DAGGER||ItemInfo.item.baseType==ItemInfo.SCEPTRE) {
								//if the item has crappy dps
								if(!(ItemInfo.item.dps>=270||ItemInfo.item.pdps>=200||ItemInfo.item.edps>=180||ItemInfo.item.spellDmg>=80)) {
									destroyItem();
									ItemInfo.clearClipboard();
								}
							}
							else if(ItemInfo.item.baseType==ItemInfo.STAFF) {
								if(ItemInfo.item.sockets<6&&ItemInfo.item.links<5) {
									if((ItemInfo.item.hasPrefix("Socketed Fire")==null&&ItemInfo.item.hasPrefix("Socketed Cold")==null&&ItemInfo.item.hasPrefix("Socketed Lightning")==null)||ItemInfo.item.hasPrefix("Socketed Gems")==null) {
										destroyItem();
										ItemInfo.clearClipboard();
									}
								}
							}
						}
					}
				}
			}

			//g.setColor(Color.red);
			//if(leaderPos!=null)
			//g.drawLine((leaderPos.x+30)/2, (leaderPos.y+100)/2, (screen.getWidth()/2), (screen.getHeight()/2));
		}
		else {
			System.out.println("paused");
		}

		g.setColor(Color.white);
		Font font = new Font("Iwona Heavy",Font.BOLD,26);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics();

	}

}
