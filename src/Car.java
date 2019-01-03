import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import objectdraw.*;

public class Car extends ActiveObject {

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

	private FilledRoundedRect body, windshield, backWindshield, roof;
	private Lane laneCode;
	private Signals laneSignal;
	private Color yellow = new Color(255, 217, 0);
	private Color blue = new Color(7, 47, 122);
	private Color purple = new Color(90, 0, 68);
	private Color red = new Color(200, 0, 0);
	private Color cyan = new Color(0, 143, 149);
	private Color orange = new Color(255, 142, 0);
	private Color windshieldColor = new Color(25, 25, 25);
	private static int Y_MOVE = 5;
	private static int DELAY_TIME = 30;
	private DrawingCanvas canvas;
	Random random = new Random();
	private double carX;
	private double carY;
	private boolean verticalCar = false;
	private boolean reverseCar = false;
	private final int GAP_SIZE = 15;
	private Location obstacle;
	
	private boolean moving;

	public Car(double x, double y, Lane whichLane, Signals signal, DrawingCanvas aCanvas) {
		canvas = aCanvas;
		laneSignal = signal;
		laneCode = whichLane;
		carX = x;
		carY = y;

		if (whichLane == Lane.LL || whichLane == Lane.LR) {
			constructCar(WINDSHIELD_OFFSET, WINDSHIELD_SIDE_OFFSET, BACKSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET,
					ROOF_OFFSET, BACKSHIELD_SIDE_OFFSET);
		} else if (whichLane == Lane.RL || whichLane == Lane.RR) {
			reverseCar = true;
			//System.out.println(reverseCar);
			constructCar(BACKSHIELD_OFFSET, WINDSHIELD_SIDE_OFFSET, WINDSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET,
					ROOF_OFFSET, BACKSHIELD_SIDE_OFFSET);
		} else if (whichLane == Lane.BL || whichLane == Lane.BR || whichLane == Lane.BM) {
			verticalCar = true;
			reverseCar = true;
			constructCar(WINDSHIELD_SIDE_OFFSET, BACKSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET, WINDSHIELD_OFFSET,
					BACKSHIELD_SIDE_OFFSET, ROOF_OFFSET);
		} else {
			verticalCar = true;
			constructCar(WINDSHIELD_SIDE_OFFSET, BACKSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET, WINDSHIELD_OFFSET,
					BACKSHIELD_SIDE_OFFSET, ROOF_OFFSET);
		}

		setCarColor();
		moving = true;
		start();
	}

	public void constructCar(double windshieldX, double windshieldY, double backshieldX, double backshieldY,
			double roofX, double roofY) {
		double carSizeX, carSizeY;
		double angleX, angleY;

		if (!verticalCar) {
			carSizeX = CAR_LENGTH;
			carSizeY = CAR_WIDTH;
			angleX = CAR_ANGLE;
			angleY = FRONT_ANGLE;
		} else {
			carSizeX = CAR_WIDTH; // 40
			carSizeY = CAR_LENGTH; // 70
			angleX = FRONT_ANGLE;
			angleY = CAR_ANGLE;
		}

		body = new FilledRoundedRect(carX, carY, carSizeX, carSizeY, angleX, angleY, canvas);
		constructWindshield(windshieldX, windshieldY);
		constructBackWindshield(backshieldX, backshieldY);
		constructRoof(roofX, roofY);

	}
	
	public void constructWindshield(double xOffset, double yOffset) {
		double windshieldX = carX + xOffset;
		double windshieldY = carY + yOffset;

		double width, length;

		if (!verticalCar) {
			width = WINDSHIELD_LENGTH;
			length = WINDSHIELD_WIDTH;
		} else {
			width = WINDSHIELD_WIDTH;
			length = WINDSHIELD_LENGTH;
		}
		windshield = new FilledRoundedRect(windshieldX, windshieldY, width, length, WINDSHIELD_ANGLE, WINDSHIELD_ANGLE,
				canvas);

	}

	public void constructBackWindshield(double xOffset, double yOffset) {
		double backwindshieldX = carX + xOffset;
		double backwindshieldY = carY + yOffset;
		double width, length;

		if (verticalCar) {
			width = BACKSHIELD_WIDTH;
			length = BACKSHIELD_LENGTH;
		} else {
			width = BACKSHIELD_LENGTH;
			length = BACKSHIELD_WIDTH;
		}
		backWindshield = new FilledRoundedRect(backwindshieldX, backwindshieldY, width, length, BACKSHIELD_CURVE_ANGLE,
				BACKSHIELD_ANGLE, canvas);

	}

	public void constructRoof(double xOffset, double yOffset) {
		double roofX = carX + xOffset;
		double roofY = carY + yOffset;
		double width, length;

		if (verticalCar) {
			width = ROOF_WIDTH;
			length = ROOF_LENGTH;
		} else {
			width = ROOF_LENGTH;
			length = ROOF_WIDTH;
		}
		roof = new FilledRoundedRect(roofX, roofY, width, length, ROOF_ANGLE, ROOF_ANGLE, canvas);
	}

	public void setCarColor() {
		windshield.setColor(windshieldColor);
		backWindshield.setColor(windshieldColor);

		Color color = null;
		switch (random.nextInt(6)) {
		case 0:
			color = blue;
			break;
		case 1:
			color = red;
			break;
		case 2:
			color = yellow;
			break;
		case 3:
			color = purple;
			break;
		case 4:
			color = cyan;
			break;
		default:
			color = orange;
		}
		
		body.setColor(color);
		roof.setColor(color);
	}

	// To implement the car's movements.
	public void move(double x, double y) {
		body.move(x, y);
		windshield.move(x, y);
		backWindshield.move(x, y);
		roof.move(x, y);
		carX = body.getLocation().getX(); // updates the car positions in the variable
		carY = body.getLocation().getY();
	}

	// Removes the car from the canvas
	public void remove() {
		body.removeFromCanvas();
		windshield.removeFromCanvas();
		backWindshield.removeFromCanvas();
		roof.removeFromCanvas();
	}
	
	public Location getLocation() {
		return new Location(carX, carY);
	}

	public void runCar(int stoplineDistance, int x, int y) {
		LinkedList<Car> lane = SimulationController.lanes.get(laneCode);
		double carPosition;
		double overlap = Double.MAX_VALUE; // maximum value a double can represent
		double boundary;
		
		int index = lane.indexOf(this); // retrieves the lane
		//System.out.println("The laneCode is : " + laneCode + " and the lane index is at : " + index);
		
		if (verticalCar) {
			if( reverseCar ) { carPosition = body.getY(); } 
			else { carPosition = body.getY() + CAR_LENGTH;}
		} else { 
			if( reverseCar ) { carPosition = body.getX();} 
			else {carPosition = body.getX() + CAR_LENGTH;}
		}
		
			
		// if this car overlaps with previous car, don't move 
		if (index > 0) {
			if(!reverseCar) {
				Car previous = lane.get(index - 1);
				overlap = verticalCar ? previous.getLocation().getY() - GAP_SIZE : previous.getLocation().getX() - GAP_SIZE;
				if (carPosition > overlap) {
					return;
				}
			} else {
				Car previous = lane.get(index - 1);
				overlap = verticalCar ? previous.getLocation().getY() + CAR_LENGTH + GAP_SIZE : previous.getLocation().getX() + CAR_LENGTH + GAP_SIZE;
				if (carPosition < overlap) {
					return;
				}
			}
		}

		// For cars traveling toward positive directions (toward South and East)
		if(!reverseCar) {
			boundary = stoplineDistance - (index * CAR_LENGTH + GAP_SIZE);
			//System.out.println("The boundary is " + boundary);
			if (laneSignal.getSignal() == Color.GREEN || carPosition < boundary || carPosition > stoplineDistance) {
				move(x, y);
				if (carPosition > stoplineDistance) {
					removeThisFromLane(lane); // pushes the car out
				}
			}
		} else {		// For cars traveling toward negative directions (toward North and West)
			boundary = stoplineDistance + (index * CAR_LENGTH + GAP_SIZE);
			//System.out.println("The boundary is " + boundary);
			if (laneSignal.getSignal() == Color.GREEN || carPosition > boundary || carPosition < stoplineDistance) {
				move(x, y);
				if (carPosition < stoplineDistance) {
					removeThisFromLane(lane); // pushes the car out
				}
			}
		}
	}
	
	/*************************** Double check the parameters !! Some are not used ! ***************************/
	private void leftTurn(int stoplineDistance, int x, int y) {
		LinkedList<Car> lane = SimulationController.lanes.get(laneCode);

		// When car has passed the stop line
		if ( body.getY() + CAR_LENGTH > SimulationController.beforeStopLineT && 
				body.getY() < y ) {
			
			move(Y_MOVE * 2 / 3, Y_MOVE);
			if ( body.getY() + CAR_LENGTH > SimulationController.beforeStopLineT ) {
				removeThisFromLane(lane);
			}
		} else if( body.getY() >= y ) {
			if( verticalCar ) {
				rotateCar();
			}
			move(Y_MOVE, 0);
		}
	}
	private void rotateCar() {
		
		double temp;
		/*constructCar(WINDSHIELD_OFFSET, WINDSHIELD_SIDE_OFFSET, BACKSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET,
				ROOF_OFFSET, BACKSHIELD_SIDE_OFFSET);*/
		// Resetting body
		temp = body.getHeight();
		body.setHeight(body.getWidth());
		body.setWidth(temp);
		
		temp = body.getArcHeight();
		body.setArcHeight(body.getArcWidth());
		body.setArcWidth(temp);
		
		
		// Resetting windshield
		temp = windshield.getHeight();
		windshield.setHeight(windshield.getWidth());
		windshield.setWidth(temp);
		
		temp = windshield.getArcHeight();
		windshield.setArcHeight(windshield.getArcWidth());
		windshield.setArcWidth(temp);
		windshield.move(WINDSHIELD_OFFSET, - WINDSHIELD_SIDE_OFFSET - 5);
		
		
		// Resetting backWindshield
		temp = backWindshield.getHeight();
		backWindshield.setHeight(backWindshield.getWidth());
		backWindshield.setWidth(temp);
		backWindshield.move(5, 5 - WINDSHIELD_OFFSET);

		temp = backWindshield.getArcHeight();
		backWindshield.setArcHeight(backWindshield.getArcWidth());
		backWindshield.setArcWidth(temp);
		
		
		// Resetting roof
		temp = roof.getHeight();
		roof.setHeight(roof.getWidth());
		roof.setWidth(temp);
		roof.move(ROOF_OFFSET - 2, 5 - ROOF_OFFSET);

		temp = roof.getArcHeight();
		roof.setArcHeight(roof.getArcWidth());
		roof.setArcWidth(temp);
		
		verticalCar = false;
	}

	// Removes the car
	private void removeThisFromLane(LinkedList<Car> queue) {
		if (queue.contains(this)) {
			queue.remove(this);
			//System.out.println("Succesfully removed");
		}
	}

	public void setSignal(Signals newSignal) {
		laneSignal = newSignal;
	}

	// Car moving simulation with association with traffic signals
	public void run() {

		while (moving) {

			switch (laneCode) {

			case TL:
				if( body.getY() + CAR_LENGTH <= SimulationController.beforeStopLineT ) {
					runCar(SimulationController.beforeStopLineT, 0, Y_MOVE);
				} else {
					leftTurn( SimulationController.beforeStopLineT, 0, SimulationController.LEFT_TURN_R );
				}
				break;

			case TM:
				runCar(SimulationController.beforeStopLineT, 0, Y_MOVE);
				break;
			case TR:
				runCar(SimulationController.beforeStopLineT, 0, Y_MOVE);
				break;
			case BL:
				if( body.getY() > SimulationController.beforeStopLineB ) {
					runCar(SimulationController.beforeStopLineB, 0, - Y_MOVE);
				} /*else {
					leftTurn( SimulationController.beforeStopLineB, 0, SimulationController.LEFT_TURN_L );
				}*/
				break;

			case BM:
				runCar(SimulationController.beforeStopLineB, 0, - Y_MOVE);
				break;

			case BR:
				runCar(SimulationController.beforeStopLineB, 0, - Y_MOVE);
				break;

			case LL:
				runCar(SimulationController.beforeStopLineL, Y_MOVE, 0);
				break;

			case LR:
				runCar(SimulationController.beforeStopLineL, Y_MOVE, 0);
				break;

			case RL:
				runCar(SimulationController.beforeStopLineR, - Y_MOVE, 0);
				break;

			case RR:
				runCar(SimulationController.beforeStopLineR, - Y_MOVE, 0);
				break;
			} // End of switch statement

			pause(DELAY_TIME);

			// if car goes outside the screen, it gets removed
			if ((body.getX() + body.getWidth() < 0 || body.getX() > canvas.getWidth())
					|| (body.getY() + body.getHeight() < 0 || body.getY() > canvas.getHeight())) {

				remove();
				break;
			}
		} // End of while
	} // End of run()

	// To create another car when this car is clicked in a lane
	public boolean contains(Location point) {
		if (body.contains(point) || windshield.contains(point) || backWindshield.contains(point)) {
			return true;
		}
		return false;
	}

	public Lane getLane() {
		return laneCode;
	}

}