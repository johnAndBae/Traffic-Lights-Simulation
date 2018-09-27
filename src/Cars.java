import objectdraw.*;
import java.util.Random;
import java.awt.*;

public class Cars extends ActiveObject {
	private static final double CAR_LENGTH = 70;
	private static final double CAR_WIDTH = 40;
	private static final double CAR_ANGLE = 20;
	private static final double FRONT_ANGLE = 90;
	private static final double WINDSHIELD_OFFSET = CAR_LENGTH - 30;
	private static final double BACKSHIELD_SIDE_OFFSET = 6;
	private static final double WINDSHIELD_SIDE_OFFSET = 3;
	private static final double WINDSHIELD_LENGTH = 18;
	private static final double WINDSHIELD_WIDTH = 35;
	private static final double WINDSHIELD_ANGLE = 15;
	private static final double WINDSHIELD_CURVE_ANGLE = 20;
	private static final double BACKSHIELD_LENGTH = 15;
	private static final double BACKSHIELD_WIDTH = 30;
	private static final double BACKSHIELD_ANGLE = 10;
	private static final double BACKSHIELD_CURVE_ANGLE = 20;
	private static final double BACKSHIELD_OFFSET = CAR_LENGTH - 60;
	private static final double ROOF_LENGTH = 35;
	private static final double ROOF_WIDTH = 30;
	private static final double ROOF_ANGLE = 20;
	private static final int ROOF_OFFSET = 15;
	
	private static int SIGNAL_TIME = 60;
	private static int LINAST_STOP_LENGTH = 210;
	private static int MAINST_STOP_LENGTH = 300;
	private int laneDistance;
	private int laneNumber = 1;
	private int count = 0;
	
	//private DrawingCanvas canvas;
	private FilledRoundedRect body, windshield, backWindshield, roof;
	private Lane direction;
	
	private Color yellowCar = new Color(255, 217, 0);
	private Color blueCar = new Color(7, 47, 122);
	private Color purpleCar = new Color(90, 0, 68);
	private Color redCar = new Color(200, 0, 0);
	private Color cyanCar = new Color(0, 143, 149);
	private Color orangeCar = new Color(255, 142, 0);
	private Color windshieldColor = new Color(25, 25, 25);
	private static int Y_MOVE = 5;
	private static int DELAY_TIME = 30;
	private DrawingCanvas canvas;
	Random random = new Random();
	
	//double x, double y, double width, double height, double arcWidth, double arcHeight, DrawingCanvas canvas
	// = new FilledRoundedRect ( 0, 0 , car_Height, car_Height, car_Height, car_Height, canvas );
	
	/* in parameters, (double x, double y, Lane whichLane, DrawingCanvas acanvas) */
	public Cars (double x, double y, Lane whichLane, DrawingCanvas aCanvas) {
		canvas = aCanvas;
		direction = whichLane;
		int color = random.nextInt(6);
		//windshield = new FilledRoundedRect(x + WINDSHIELD_OFFSET, y + WINDSHIELD_OFFSET);
		

		if( whichLane == Lane.LL || whichLane == Lane.LR || whichLane == Lane.RL || whichLane == Lane.RR ) {
	
			body = new FilledRoundedRect(x, y, CAR_LENGTH, CAR_WIDTH, CAR_ANGLE, FRONT_ANGLE, canvas);
			
			if(whichLane == Lane.LL || whichLane == Lane.LR) {
				windshield = new FilledRoundedRect(x + WINDSHIELD_OFFSET, y + WINDSHIELD_SIDE_OFFSET,
						WINDSHIELD_LENGTH, WINDSHIELD_WIDTH, WINDSHIELD_ANGLE, WINDSHIELD_ANGLE, canvas);
				backWindshield = new FilledRoundedRect(x + BACKSHIELD_OFFSET, y + BACKSHIELD_SIDE_OFFSET,
						BACKSHIELD_LENGTH, BACKSHIELD_WIDTH, BACKSHIELD_CURVE_ANGLE, BACKSHIELD_ANGLE, canvas);
				roof = new FilledRoundedRect(x + ROOF_OFFSET, y + BACKSHIELD_SIDE_OFFSET, ROOF_LENGTH, ROOF_WIDTH,
						ROOF_ANGLE, ROOF_ANGLE, canvas);
				windshield.setColor(windshieldColor);
				backWindshield.setColor(windshieldColor);
			} else {
				windshield = new FilledRoundedRect(x + BACKSHIELD_OFFSET, y + WINDSHIELD_SIDE_OFFSET,
						WINDSHIELD_LENGTH, WINDSHIELD_WIDTH, WINDSHIELD_ANGLE, WINDSHIELD_ANGLE, canvas);
				backWindshield = new FilledRoundedRect(x + WINDSHIELD_OFFSET, y + BACKSHIELD_SIDE_OFFSET,
						BACKSHIELD_LENGTH, BACKSHIELD_WIDTH, BACKSHIELD_CURVE_ANGLE, BACKSHIELD_ANGLE, canvas);
				roof = new FilledRoundedRect(x + ROOF_OFFSET, y + BACKSHIELD_SIDE_OFFSET, ROOF_LENGTH, ROOF_WIDTH,
						ROOF_ANGLE, ROOF_ANGLE, canvas);
				windshield.setColor(windshieldColor);
				backWindshield.setColor(windshieldColor);	
			}
			
			
		}
		else {
			body = new FilledRoundedRect(x, y, CAR_WIDTH, CAR_LENGTH, FRONT_ANGLE, CAR_ANGLE, canvas);
		}
		
		
		if(color == 0) {
			body.setColor(blueCar);
			roof.setColor(blueCar);
		} else if (color == 1) {
			body.setColor(redCar);
			roof.setColor(redCar);
		} else if (color == 2){
			body.setColor(yellowCar);
			roof.setColor(yellowCar);
		} else if (color == 3){
			body.setColor(purpleCar);
			roof.setColor(purpleCar);
		} else if (color == 4){
			body.setColor(cyanCar);
			roof.setColor(cyanCar);
		} else {
			body.setColor(orangeCar);
			roof.setColor(orangeCar);
		}
		
		start();
	}
		//windshield = new FilledRoundedRect();
		//backWindshield = new FilledRoundedRect();
			
	
	// To implement the car's movements.
	public void move ( double x, double y ) {
		
		body.move(x, y);
		windshield.move(x, y);
		backWindshield.move(x, y);
		roof.move(x, y);
	}
	
	public void removeFromCanvas() {
		
		body.removeFromCanvas();
		windshield.removeFromCanvas();
		backWindshield.removeFromCanvas();
		
	}
	
	public void run() {
		
		while( true ) {
					
			switch( direction ) {
			
			case TL:
				move( 0, Y_MOVE );
				break;
				
			case TM:
				move( 0, Y_MOVE );
				break;
				
			case TR:
				move( 0, Y_MOVE );
				break;
				
			case BL:
				move( 0, - Y_MOVE );
				break;
				
			case BM:
				move( 0, - Y_MOVE );
				break;
				
			case BR:
				move( 0, - Y_MOVE );
				break;
				
			case LL:
				move( Y_MOVE, 0 );
				break;
				
			case LR:
				move( Y_MOVE, 0 );
				break;
				
			case RL:
				move( - Y_MOVE, 0 );
				break;
				
			case RR:
				move( - Y_MOVE, 0 );
				break;
				
			}
			
			pause(DELAY_TIME);
			
			if( (body.getX() + body.getWidth() < 0 || body.getX() > canvas.getWidth()) ||
					(body.getY() + body.getHeight() < 0 || body.getY() > canvas.getHeight()) ) {
			
				removeFromCanvas();
				
				break;
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