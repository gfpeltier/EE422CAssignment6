
import java.applet.Applet; 
import java.awt.*; 
import java.awt.geom.*;

public class RaceCar extends Applet {
	private int carNum;
	private int carX;
	private int carY;
	private Color col;
	
	RaceCar(){
		carNum = 0;
		carX = 100;
		carY = 100;
		col = Color.black;
	}
	
	RaceCar(int num, int x, int y, Color c){
		carNum = num;
		carX = x;
		carY = y;
		col = c;
	}
	
	public void drawCar(Graphics2D g2){
		Font numFont = new Font("Serif",Font.BOLD, 10);
		// create the car body
		Rectangle body = new Rectangle(carX, carY+10, 60, 10);
		// create the car tires
		Ellipse2D.Double frontTire = new Ellipse2D.Double(carX+10, carY+20, 10, 10); 
		Ellipse2D.Double rearTire = new Ellipse2D.Double(carX+40, carY+20, 10, 10);
		// create the 4 points connecting the windshields and roof 
		Point2D.Double r1 = new Point2D.Double(carX+10, carY+10); 
		Point2D.Double r2 = new Point2D.Double(carX+20, carY); 
		Point2D.Double r3 = new Point2D.Double(carX+40, carY); 
		Point2D.Double r4 = new Point2D.Double(carX+50, carY+10);
		// create the windshields and roof of the car
		Line2D.Double frontWindshield = new Line2D.Double(r1, r2); 
		Line2D.Double roofTop = new Line2D.Double(r2, r3); 
		Line2D.Double rearWindshield = new Line2D.Double(r3, r4);
		// draw all of the car parts on the screen
		g2.setColor(col);
		g2.draw(body);
		g2.fill(body);
		g2.draw(roofTop);
		g2.setColor(Color.black);
		g2.draw(frontTire);
		g2.fill(frontTire);
		g2.draw(rearTire);
		g2.fill(rearTire);
		g2.draw(frontWindshield);  
		g2.draw(rearWindshield);
		// draw the label under the car
		g2.setFont(numFont);
		g2.drawString(""+carNum, carX+5, carY+19);
	}
	
	public int getNum(){
		return carNum;
	}
	
	public int getX(){
		return carX;
	}
	
	public void clearCar(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		Rectangle blank = new Rectangle(carX-5, carY, 65, 32);
		g2.setColor(Color.white);
		g2.draw(blank);
		g2.fill(blank);
	}
	
	/**
	 * 
	 * @return 0 if everything is fine
	 * @return 1 if race is over
	 */
	public int moveCar(){
		carX += 5;
		if(carX < 300){
			return 0;
		} else{return 1;}
	}
}
