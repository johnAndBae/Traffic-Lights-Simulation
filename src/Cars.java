import objectdraw.*;
import java.util.Random;
import java.awt.*;

public class Cars extends ActiveObject {
	private static final double CAR_LENGTH = 70;
	private static final double CAR_WIDTH = 40;
	private static final double CAR_ANGLE = 20;
	private static final double FRONT_ANGLE = 90;
	private static final double WINDSHIELD_OFFSET = 40;
	private static final double BACKSHIELD_SIDE_OFFSET = 6;
	private static final double WINDSHIELD_SIDE_OFFSET = 3;
	private static final double WINDSHIELD_LENGTH = 18;
	private static final double WINDSHIELD_WIDTH = 35;
	private static final double WINDSHIELD_ANGLE = 15;
	private static final double BACKSHIELD_LENGTH = 15;
	private static final double BACKSHIELD_WIDTH = 30;
	private static final double BACKSHIELD_ANGLE = 10;
	private static final double BACKSHIELD_CURVE_ANGLE = 20;
	private static final double BACKSHIELD_OFFSET = 10;
	private static final double ROOF_LENGTH = 35;
	private static final double ROOF_WIDTH = 30;
	private static final double ROOF_ANGLE = 20;
	private static final int ROOF_OFFSET = 15;
	private static final int STOP_OFFSET = 15;
	
	//private DrawingCanvas canvas;
	private FilledRoundedRect body, windshield, backWindshield, roof;
	private Lane direction;
	private Signals laneSignal;
	private int signalTime;
	private int linaStDistance = 210;
	private int mainStDistance = 300;
	private int distance;
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
	
	public Cars (double x, double y, Lane whichLane, Signals signal, DrawingCanvas aCanvas) {
		canvas = aCanvas;
		laneSignal = signal;
		direction = whichLane;
		
		int color = random.nextInt(6);

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
			
			distance = linaStDistance;
			
		}
		else {
			body = new FilledRoundedRect(x, y, CAR_WIDTH, CAR_LENGTH, FRONT_ANGLE, CAR_ANGLE, canvas);
			//to fix: the windshields
			if(whichLane == Lane.BL || whichLane == Lane.BM || whichLane == Lane.BR) {
				windshield = new FilledRoundedRect(x + WINDSHIELD_SIDE_OFFSET, y + BACKSHIELD_OFFSET,
						WINDSHIELD_WIDTH, WINDSHIELD_LENGTH, WINDSHIELD_ANGLE, WINDSHIELD_ANGLE, canvas);
				backWindshield = new FilledRoundedRect(x + BACKSHIELD_SIDE_OFFSET, y + WINDSHIELD_OFFSET,
						BACKSHIELD_WIDTH, BACKSHIELD_LENGTH, BACKSHIELD_CURVE_ANGLE, BACKSHIELD_ANGLE, canvas);
				roof = new FilledRoundedRect(x + BACKSHIELD_SIDE_OFFSET, y + ROOF_OFFSET, ROOF_WIDTH, ROOF_LENGTH,
						ROOF_ANGLE, ROOF_ANGLE, canvas);
				windshield.setColor(windshieldColor);
				backWindshield.setColor(windshieldColor);
				
			} else {
				//to fix: the windshields
				backWindshield = new FilledRoundedRect(x + WINDSHIELD_SIDE_OFFSET, y + BACKSHIELD_OFFSET,
						WINDSHIELD_WIDTH, WINDSHIELD_LENGTH, WINDSHIELD_ANGLE, WINDSHIELD_ANGLE, canvas);
				windshield = new FilledRoundedRect(x + BACKSHIELD_SIDE_OFFSET, y + WINDSHIELD_OFFSET,
						BACKSHIELD_WIDTH, BACKSHIELD_LENGTH, BACKSHIELD_CURVE_ANGLE, BACKSHIELD_ANGLE, canvas);
				roof = new FilledRoundedRect(x + BACKSHIELD_SIDE_OFFSET, y + ROOF_OFFSET, ROOF_WIDTH, ROOF_LENGTH,
						ROOF_ANGLE, ROOF_ANGLE, canvas);
				windshield.setColor(windshieldColor);
				backWindshield.setColor(windshieldColor);	
			}
		
			distance = mainStDistance;
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
		roof.removeFromCanvas();
		
	}
	
	public void run() {
		
	/*	while(body.getX() > 0) {
			
			move(0, Y_MOVE);
			pause(DELAY_TIME);
		}
		
		while(laneSignal.getSignal() == Color.RED && body.getHeight() + body.getX() > distance) {
			move(0, Y_MOVE);
			pause(DELAY_TIME);
			
		} */
		
		while( true ) {
		
		//if(laneSignal.getSignal() == Color.GREEN) {
			switch( direction ) {
			
			case TL:
				
				if( body.getY() + CAR_LENGTH < simulationController.beforeStopLineT - STOP_OFFSET ) {
					move( 0, Y_MOVE );
				} else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH > simulationController.beforeStopLineT) {
					move (0, Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, Y_MOVE);
				} else {
					break;
					
				}
				
				break; 
				
			case TM:

				if( body.getY() + CAR_LENGTH < simulationController.beforeStopLineT - STOP_OFFSET ) {
					
					move( 0, Y_MOVE );

				} 
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH > simulationController.beforeStopLineT) {
					move (0, Y_MOVE);
				}  
				else if (laneSignal.getSignal() == Color.GREEN){
					move(0, Y_MOVE);
				} 
				else {
					break;
				}	
				break;
				
			case TR:
				if( body.getY() + CAR_LENGTH < simulationController.beforeStopLineT - STOP_OFFSET ) {
					
					move( 0, Y_MOVE );

				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH > simulationController.beforeStopLineT) {
					move (0, Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, Y_MOVE);
				} else {
					break;
					
				}	
				break;
				
			case BL:
				if( body.getY() + CAR_LENGTH < simulationController.beforeStopLineB - STOP_OFFSET ) {
					move( 0, - Y_MOVE );
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH > simulationController.beforeStopLineB) {
					move (0, - Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, - Y_MOVE);
				} else {
					break;
					
				}	
				
			case BM:
				if( body.getY() + CAR_LENGTH < simulationController.beforeStopLineB - STOP_OFFSET ) {
					move( 0, - Y_MOVE );
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH > simulationController.beforeStopLineB) {
					move (0, - Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, - Y_MOVE);
				} else {
					break;
					
				}	
			case BR:
				if( body.getY() + CAR_LENGTH < simulationController.beforeStopLineB - STOP_OFFSET ) {
					move( 0, - Y_MOVE );
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH > simulationController.beforeStopLineB) {
					move (0, - Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, - Y_MOVE);
				} else {
					break;
					
				}	
				
			case LL:
				if( body.getY() + CAR_WIDTH < simulationController.beforeStopLineL - STOP_OFFSET ) {
					move( Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_WIDTH > simulationController.beforeStopLineL) {
					move (Y_MOVE, 0);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(Y_MOVE, 0);
				} else {
					break;
				}	
				
			case LR:
				if( body.getY() + CAR_WIDTH < simulationController.beforeStopLineL - STOP_OFFSET ) {
					move( Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_WIDTH > simulationController.beforeStopLineL) {
					move (Y_MOVE, 0);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(Y_MOVE, 0);
				} else {
					break;
				}
			case RL:
				if( body.getY() + CAR_WIDTH < simulationController.beforeStopLineR - STOP_OFFSET ) {
					move(- Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_WIDTH > simulationController.beforeStopLineR) {
					move (- Y_MOVE, 0);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(- Y_MOVE, 0);
				} else {
					break;
				}	
				
			case RR:
				if( body.getY() + CAR_WIDTH < simulationController.beforeStopLineR - STOP_OFFSET ) {
					move(- Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_WIDTH > simulationController.beforeStopLineR) {
					move (- Y_MOVE, 0);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(- Y_MOVE, 0);
				} else {
					break;
				}	
				
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
		if( body.contains(point) || windshield.contains(point) || backWindshield.contains(point)) {
			return true;
		}	
		return false;
	}	
	
}