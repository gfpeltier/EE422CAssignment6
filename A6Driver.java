
import java.applet.Applet; 
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class A6Driver extends Applet {
	public final static int INITIAL_CAR_X = 100;
	public final static int INITIAL_CAR_Y = 100;
	public final static int CAR_Y_OFF = 50;
	
	public ArrayList<RaceCar> race;
	StringBuffer buffer;
	public boolean stoploop;
	Graphics locG;
	MouseSpy listener;
	JPanel panel;

    public void init() {
    	listener = new MouseSpy();
    	panel = new JPanel();
		addMouseListener(listener);
		race = new ArrayList<RaceCar>();
        buffer = new StringBuffer();
        addItem("initializing... ");
    }

    public void start() {
        addItem("starting... ");
    }

    public void destroy() {
        addItem("preparing for unloading...");
    }
    
//    public void update(Graphics g){
//    	
//    }
    
    public void reset(){
    	race = initRace(locG);
    }

    private void addItem(String newWord) {
        System.out.println(newWord);
        buffer.append(newWord);
        repaint();
    }
	
	public void paint(Graphics g){
		locG = g;
		race = initRace(locG);
		StopWatch clock = new StopWatch();
		boolean notDone = true;
		RaceCar winner = new RaceCar();
		Random rand = new Random();
		clock.start();
		while(notDone){
			if(clock.getElapsedTime()%250 == 0){
			int car = rand.nextInt(5);
			race.get(car).clearCar(g);
			repaint();
				if(race.get(car).moveCar() != 0){
					winner = race.get(car);
					notDone = false;
				}
			}
			raceUpdate(g, race);
		}
		Graphics2D g2 = (Graphics2D)g;
		Font win = new Font("Helvetica",Font.BOLD, 32);
		g2.setFont(win);
		g2.drawString("The winner is car number " + winner.getNum(), 150, 400);
		stoploop = false;
		while(!stoploop){
		}
	}
	
	public void raceUpdate(Graphics g, ArrayList<RaceCar> cars){
		Iterator<RaceCar> i = cars.iterator();
		Graphics2D g2 = (Graphics2D)g;
		while(i.hasNext()){
			i.next().drawCar(g2);
		}
		drawFinish(g);
	}
	
	public ArrayList<RaceCar> initRace(Graphics g){
		Color burntOrange = new Color(196,98,16);
		ArrayList<RaceCar> race1 = new ArrayList<RaceCar>();
		Graphics2D g2 = (Graphics2D)g;
		RaceCar car1 = new RaceCar(1, INITIAL_CAR_X, INITIAL_CAR_Y, Color.green);
		RaceCar car2 = new RaceCar(2, INITIAL_CAR_X, INITIAL_CAR_Y+CAR_Y_OFF, Color.yellow);
		RaceCar car3 = new RaceCar(3, INITIAL_CAR_X, INITIAL_CAR_Y+(2*CAR_Y_OFF), Color.cyan);
		RaceCar car4 = new RaceCar(4, INITIAL_CAR_X, INITIAL_CAR_Y+(3*CAR_Y_OFF), burntOrange);
		RaceCar car5 = new RaceCar(5, INITIAL_CAR_X, INITIAL_CAR_Y+(4*CAR_Y_OFF), Color.magenta);
		car1.drawCar(g2);
		car2.drawCar(g2);
		car3.drawCar(g2);
		car4.drawCar(g2);
		car5.drawCar(g2);
		drawFinish(g2);
		race1.add(car1);
		race1.add(car2);
		race1.add(car3);
		race1.add(car4);
		race1.add(car5);
		return race1;
	}
	
	public static void drawFinish(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		Rectangle finish = new Rectangle(360, 75, 10, 275);
		g2.setColor(Color.red);
		g2.draw(finish);
		g2.fill(finish);
	}
	
	public void drawClick(int x, int y){
		Graphics2D g2 = (Graphics2D)locG;
		Font click = new Font("Helvetica",Font.BOLD, 20);
		g2.setFont(click);
		g2.drawString("Mouse clicked at x= " + x + " y= " + y, 100, 100);
	}
	
	public class MouseSpy implements MouseListener {
		public void mouseClicked(MouseEvent e){
			reset();
			stoploop = true;
			drawClick(e.getX(), e.getY()); 
		}
		
		public void mouseEntered(MouseEvent e){
			
		}
		public void mouseExited(MouseEvent e){
			
		}
		public void mouseReleased(MouseEvent e){
			
		}
		public void mousePressed(MouseEvent e){
			
		}
	}
}
