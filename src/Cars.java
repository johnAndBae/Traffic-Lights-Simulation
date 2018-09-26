import objectdraw.*;
import java.util.Random;
import java.awt.*;

public class Cars extends ActiveObject {
	private static final double CAR_LENGTH = 70;
	private static final double CAR_WIDTH = 40;
	private static final double CAR_ANGLE = 20;
	private static final double FRONT_ANGLE = 90;
	private static final double WINDSHIELD_OFFSET = 40; 
	private static final double WINDSHIELD_LENGTH = 30;
	private static final double WINDSHIELD_WIDTH = 30;

	private static int SIGNAL_TIME = 60;
	private static int LINAST_STOP_LENGTH = 210;
	private static int MAINST_STOP_LENGTH = 300;
	private int laneDistance;
	private int laneNumber = 1;
	private int count = 0;
	
	//private DrawingCanvas canvas;
	private FilledRoundedRect body, windshield, backWindshield;
	
	private Color yellowCar = new Color(121, 30, 35);
	private Color blueCar = new Color(100, 130, 188);
	private Color greenCar = new Color(40, 150, 111);
	private static int Y_MOVE = 5;
	private static int DELAY_TIME = 30;
	private DrawingCanvas canvas;
	Random random = new Random();
	
	//double x, double y, double width, double height, double arcWidth, double arcHeight, DrawingCanvas canvas
	// = new FilledRoundedRect ( 0, 0 , car_Height, car_Height, car_Height, car_Height, canvas );
	
	/* in parameters, (int lane, DrawingCanvas) */
	public Cars (double x, double y, DrawingCanvas aCanvas) {
		canvas = aCanvas;
		int color = random.nextInt(3);
		body = new FilledRoundedRect(x, y, CAR_LENGTH, CAR_WIDTH, CAR_ANGLE, FRONT_ANGLE, canvas);
		windshield = new FilledRoundedRect(x + WINDSHIELD_OFFSET, y + WINDSHIELD_OFFSET);
		
		if(laneNumber == 1) {
			laneDistance = LINAST_STOP_LENGTH;
		} else {
			
		}
		
		if(color == 0) {
			body.setColor(blueCar);
		} else if (color == 1) {
			body.setColor(greenCar);
		} else {
			body.setColor(yellowCar);
		}
		
		start();
	}
		//windshield = new FilledRoundedRect();
		//backWindshield = new FilledRoundedRect();
			
	
	// To implement the car's movements.
	public void move ( double x, double y ) {
		
		body.move(x, y);
		//windshield.move(x, y);
		//backWindshield.move(x, y);
	}
	
	public void run() {
		
		while(body.getY() < canvas.getWidth()) {
			count++;
			
			if(count < SIGNAL_TIME) {
				count++;
				move(Y_MOVE, 0);
				pause(DELAY_TIME);
			}
			
		}
	}
	
	// To create another car when this car is clicked in a lane
	public boolean contains ( Location point ) {
		if( body.contains(point) || windshield.contains(point) || backWindshield.contains(point) ) {
			return true;
		}	
		return false;
	}	
	
}