import objectdraw.*;
import java.util.*;
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
	private final int linaStDistance = 210;
	private final int mainStDistance = 300;
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
	private boolean firstTime = true;
	Random random = new Random();
	private double carX;
	private double carY;
	private boolean verticalCar = false;
	private boolean removed = false;
	private final int GAP_SIZE = 15;
	private static boolean firstCar = false;
	private static boolean secondCar = false;
	private static boolean thirdCar = false;
	
	private Iterator iterator;
	
	public Cars (double x, double y, Lane whichLane, Signals signal, DrawingCanvas aCanvas) {
		canvas = aCanvas;
		laneSignal = signal;
		direction = whichLane;
		carX = x;
		carY = y;
		
		if(whichLane == Lane.LL || whichLane == Lane.LR) {
			constructCar(WINDSHIELD_OFFSET, WINDSHIELD_SIDE_OFFSET, BACKSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET,
						ROOF_OFFSET, BACKSHIELD_SIDE_OFFSET);
		} else if (whichLane == Lane.RL || whichLane == Lane.RR) {
				constructCar(BACKSHIELD_OFFSET, WINDSHIELD_SIDE_OFFSET, WINDSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET,
						ROOF_OFFSET, BACKSHIELD_SIDE_OFFSET);
		} else if (whichLane == Lane.BL || whichLane == Lane.BR || whichLane == Lane.BM){
			verticalCar = true;
			constructCar(WINDSHIELD_SIDE_OFFSET, BACKSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET, WINDSHIELD_OFFSET,
					BACKSHIELD_SIDE_OFFSET, ROOF_OFFSET);	
		} else {
			verticalCar = true;
			constructCar(WINDSHIELD_SIDE_OFFSET, BACKSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET, WINDSHIELD_OFFSET, BACKSHIELD_SIDE_OFFSET, ROOF_OFFSET);
		}
		
		setCarColor();

		start();
	}	
	
	public void constructCar(double windshieldX, double windshieldY, double backshieldX, double backshieldY, double roofX, double roofY) {
		double carSizeX, carSizeY;
		double angleX, angleY;
		
		if(!verticalCar) {
			carSizeX = CAR_LENGTH;
			carSizeY = CAR_WIDTH;
			angleX = CAR_ANGLE;
			angleY = FRONT_ANGLE;
		} else {
			carSizeX = CAR_WIDTH; //40
			carSizeY = CAR_LENGTH; //70
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
		
		if(!verticalCar) {
			width = WINDSHIELD_LENGTH;
			length = WINDSHIELD_WIDTH;
		} else {
			width = WINDSHIELD_WIDTH;
			length = WINDSHIELD_LENGTH;
		}
		windshield = new FilledRoundedRect(windshieldX, windshieldY, width, length, WINDSHIELD_ANGLE, WINDSHIELD_ANGLE, canvas);
		
	}

	public void constructBackWindshield(double xOffset, double yOffset) {
		double backwindshieldX = carX + xOffset;
		double backwindshieldY = carY + yOffset;
		double width, length;
		
		if(verticalCar) {
			width = BACKSHIELD_WIDTH;
			length = BACKSHIELD_LENGTH;
		} else {
			width = BACKSHIELD_LENGTH;
			length = BACKSHIELD_WIDTH;
		}
		backWindshield = new FilledRoundedRect(backwindshieldX, backwindshieldY, width, length, BACKSHIELD_CURVE_ANGLE, BACKSHIELD_ANGLE, canvas);

	}
	
	public void constructRoof(double xOffset, double yOffset) {
		double roofX = carX + xOffset;
		double roofY = carY + yOffset;
		double width, length;
		
		if(verticalCar) {
			width = ROOF_WIDTH;
			length = ROOF_LENGTH;
		} else {
			width = ROOF_LENGTH;
			length = ROOF_WIDTH;
		}
		roof = new FilledRoundedRect(roofX, roofY, width, length, ROOF_ANGLE, ROOF_ANGLE, canvas);	
	}
	
	/*************************************************************************** 
	 * setCarColor(int color)
	 * Sets the color of the car.
	 **************************************************************************/
	
	public void setCarColor() {
		
		int color = random.nextInt(6);
		
		windshield.setColor(windshieldColor);
		backWindshield.setColor(windshieldColor);
		
		switch(color) {
		case 0:
			body.setColor(blueCar);
			roof.setColor(blueCar);
			break;
		case 1:
			body.setColor(redCar);
			roof.setColor(redCar);
			break;
		case 2:
			body.setColor(yellowCar);
			roof.setColor(yellowCar);
			break;
		case 3:
			body.setColor(purpleCar);
			roof.setColor(purpleCar);
			break;
		case 4:
			body.setColor(cyanCar);
			roof.setColor(cyanCar);
			break;
		default:
			body.setColor(orangeCar);
			roof.setColor(orangeCar);
			break;
		}
		
	}
	
	// To implement the car's movements.
	public void move ( double x, double y ) {
		body.move(x, y);
		windshield.move(x, y);
		backWindshield.move(x, y);
		roof.move(x, y);
	}
	
	// Removes the car from the canvas
	public void remove() {
		body.removeFromCanvas();
		windshield.removeFromCanvas();
		backWindshield.removeFromCanvas();
		roof.removeFromCanvas();
	}
	
	// Removes the car from the list
	public void popCar(LinkedList<Cars> laneList) {
		if(firstTime) {
			firstTime = false;
			if( laneList.isEmpty() == false && laneSignal.getSignal() == Color.GREEN) {
				System.out.println("Car is popped !");
				laneList.removeFirst();
				removed = true;
			}
		}
	}
	
	public void setLaneQueue(LinkedList<Cars> laneList, int stoplineDistance) {
		
	}
	
	public void runCarForward(LinkedList<Cars> laneList, int stoplineDistance, int x, int y) {
		
	
		double carPosition;
		boolean secondPosition;
		boolean thirdPosition;
		//int laneListSize = laneList.size();
		//Other concerns: if you click TOO fast, then the car will be produced
		// of course, the second car will stop FIRST before the first car, which will cause
		// an overlap ISSUE
		// the problem with working with junhee's hardcoded is that it will ALWAYS stop there
		// before continuing.
		// I need a DELAY between the cars, how can i achieve a delay?
		if(verticalCar) {
			carPosition = body.getY() + CAR_LENGTH;
		} else {
			carPosition = body.getX() + CAR_LENGTH;
		}
		
		if(laneSignal.getSignal() == Color.GREEN) {
			move(x,y);
		}
		
		if(carPosition <= stoplineDistance) {
			move(x, y);
		}
		
		// When car has passed the stop line, it will continue moving
		if ( carPosition > stoplineDistance ) {
			move( x, y );
			popCar(laneList);
	
		// Only the car that is at the very front of the lane is allowed to go when it's green
		} else if (body.getY() + CAR_LENGTH >= stoplineDistance - STOP_OFFSET && 
				laneSignal.getSignal() == Color.GREEN){
			move( x, y );
		}
		
	//	if(laneSignal.getSignal() == Color.RED && carPosition <= stoplineDistance) {	
			if( ! laneList.isEmpty() && laneList.get(0).equals(this) && 
					body.getY() + CAR_LENGTH <= stoplineDistance - STOP_OFFSET ) {
				move( x, y); //* 2
 				// the default of this will mean that the car will stop once it reaches the distance
			} else {
				firstCar = true;
			}
			
			if(laneList.size() > 1 && laneList.get(1).equals(this) && 
					carPosition <= stoplineDistance - CAR_LENGTH - STOP_OFFSET * 2) {
				move( x, y );
			}
			
			if( laneList.size() > 2 && laneList.get(2).equals(this) &&
				carPosition <= stoplineDistance - CAR_LENGTH * 2 - STOP_OFFSET * 3) {
				move( x, y );
			}
			else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH >= simulationController.beforeStopLineT) {
				move (0, Y_MOVE);
			} else if (laneSignal.getSignal() == Color.GREEN){
				move(0, Y_MOVE);
			} 
	
			
			/* IF MORE THAN 3 CARS IN THE LIST, it will iterate through each element,
			 * and give a delay so it will time itself so it won't overlap
			 * note: if the user clicks TOO fast, then it might throw an issue
			 * ^^ can be resolved by limiting the user clicking.
			 */
			
			if(laneList.size() > 3) {
				iterator = laneList.listIterator(3);
				for(int i = 2; i < laneList.size() - 1; i++) {
					while(iterator.hasNext() && laneList.get(i).equals(this)) {
						pause(200 * i);
					} // end of while loop
				} // end of for loop
		//	} // end of if statement

		}
			
		
	}
	
	public void runCarLeft(LinkedList<Cars> laneList, int stoplineDistance, int x, int y) {
		
		
	}
	
	public double distanceTraveled() {
		if(verticalCar) {
			return (distance - body.getY());
		} 
		
		return (distance - body.getX());
	}
	
	// Check distance between the car and the next car
	private boolean checkCarGap(Cars car, int gap) {
		return distanceTraveled() < gap + body.getHeight();
	}
	
	// Forces the car to delay
	private void waitCar(Cars car) {
		while (checkCarGap(car, GAP_SIZE)){
			pause(GAP_SIZE);
		}
	}
	
	public void setSignal( Signals newSignal ) {		
		laneSignal = newSignal;
	}

	public void run() {
		
		while( true ) {
			
			switch( direction ) {
			
			case TL:

				if( ! simulationController.carListTL.isEmpty() && simulationController.carListTL.get(0).equals(this) && 
						body.getY() + CAR_LENGTH <= simulationController.beforeStopLineT - STOP_OFFSET ) {
					move( 0, Y_MOVE );
				} 
				if( simulationController.carListTL.size() > 1 && simulationController.carListTL.get(1).equals(this) && 
						body.getY() + CAR_LENGTH <= simulationController.beforeStopLineT - CAR_LENGTH - STOP_OFFSET * 2) {
					move( 0, Y_MOVE );
				} 
				if( simulationController.carListTL.size() > 2 && simulationController.carListTL.get(2).equals(this) &&
						body.getY() + CAR_LENGTH <= simulationController.beforeStopLineT - CAR_LENGTH * 2 - STOP_OFFSET * 3) {
					move( 0, Y_MOVE );
				}

				// When car has passed the stop line
				if ( body.getY() + CAR_LENGTH > simulationController.beforeStopLineT ) {
					
					/*double temp = body.getWidth();
					
					
					// 	GRASS_Y(150) + LANE_WIDTH(60) * 2 + LINE_OFFSET(3) + CAR_OFFSET(7)
					if( body.getY() >= 280 ) {
						body.setWidth(body.getHeight());
						
						body.setHeight(temp);
						move( Y_MOVE, 0 );
					}
					else {
						move(Y_MOVE * 2 / 3, Y_MOVE);
					}
					*/
					
					move(Y_MOVE * 2 / 3, Y_MOVE);

					
					if(firstTime) {
						firstTime = false;
						popCar(simulationController.carListTL);
					}
				} 
				
				// Only the car that is at the very front of the lane is allowed to go when it's green
				else if (body.getY() + CAR_LENGTH >= simulationController.beforeStopLineT - STOP_OFFSET && 
						laneSignal.getSignal() == Color.GREEN){
					move(0, Y_MOVE);

					if(firstTime) {
						firstTime = false;
						popCar(simulationController.carListTL);					
					}
					
				} else if (laneSignal.getSignal() == Color.RED && body.getY() + CAR_LENGTH >= simulationController.beforeStopLineT) {
					move (0, Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, Y_MOVE);
				} else {
					break;
					
				}
			
				
				break; 
				
			case TM:
				
				runCarForward(simulationController.carListTM, simulationController.beforeStopLineT, 0, Y_MOVE);
				
				break;
				
			case TR:
				
				runCarForward(simulationController.carListTR, simulationController.beforeStopLineT, 0, Y_MOVE);
				
				break;
				//test
			case BL:

				if( ! simulationController.carListBL.isEmpty() && simulationController.carListBL.get(0).equals(this) && 
						body.getY() >= simulationController.beforeStopLineT + STOP_OFFSET ) {
					move( 0, - Y_MOVE );
				} 
				if( simulationController.carListBL.size() > 1 && simulationController.carListBL.get(1).equals(this) && 
						body.getY() >= simulationController.beforeStopLineT + CAR_LENGTH + STOP_OFFSET * 2) {
					move( 0, - Y_MOVE );
				} 
				if( simulationController.carListBL.size() > 2 && simulationController.carListBL.get(2).equals(this) &&
						body.getY() >= simulationController.beforeStopLineT + CAR_LENGTH * 2 + STOP_OFFSET * 3) {
					move( 0, - Y_MOVE );
				} 

				// When car has passed the stop line
				if ( body.getY() < simulationController.beforeStopLineB ) {
					move(0, - Y_MOVE);
					
					if(firstTime) {
						firstTime = false;
						popCar(simulationController.carListBL);
					}
				} 
				
				// Only the car that is at the very front of the lane is allowed to go when it's green
				else if (body.getY() <= simulationController.beforeStopLineB + STOP_OFFSET && 
						laneSignal.getSignal() == Color.GREEN){
					move(0, - Y_MOVE);
				} 
				
				break; 
				
				/* START HERE */
			case BM:
				if( body.getY() > simulationController.beforeStopLineB + STOP_OFFSET ) {
					move( 0, - Y_MOVE );
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() <= simulationController.beforeStopLineB) {
					move (0, - Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, - Y_MOVE);
				} else {
					break;
				}
				
				break;
				
			case BR:
				if( body.getY() > simulationController.beforeStopLineB + STOP_OFFSET ) {
					move( 0, - Y_MOVE );
				}
				else if (laneSignal.getSignal() == Color.RED && body.getY() <= simulationController.beforeStopLineB) {
					move (0, - Y_MOVE);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(0, - Y_MOVE);
				} else {
					break;
				}
				
				break;
				
			case LL:
				if( body.getX() + CAR_LENGTH < simulationController.beforeStopLineL - STOP_OFFSET ) {
					move( Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getX() + CAR_LENGTH >= simulationController.beforeStopLineL) {
					move (Y_MOVE, 0);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(Y_MOVE, 0);
				} else {
					break;
				}
				
				
				break;
				
			case LR:
				if( body.getX() + CAR_LENGTH < simulationController.beforeStopLineL - STOP_OFFSET ) {
					move( Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getX() + CAR_WIDTH >= simulationController.beforeStopLineL) {
					move (Y_MOVE, 0);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(Y_MOVE, 0);
				} else {
					break;
				}
				
				break;
				
			case RL:
				if( body.getX() > simulationController.beforeStopLineR + STOP_OFFSET ) {
					move(- Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getX() <= simulationController.beforeStopLineR) {
					move (- Y_MOVE, 0);
				}  else if (laneSignal.getSignal() == Color.GREEN){
					move(- Y_MOVE, 0);
				} else {
					break;
				}
				
				break;
				
			case RR:
				if( body.getX() > simulationController.beforeStopLineR + STOP_OFFSET ) {
					move(- Y_MOVE, 0);
				}
				else if (laneSignal.getSignal() == Color.RED && body.getX() <= simulationController.beforeStopLineR) {
					move (- Y_MOVE, 0);
				}  
				else if (laneSignal.getSignal() == Color.GREEN){
					move(- Y_MOVE, 0);
				} else {
					break;
				}
					
				break;
			} // End of switch statement
	
			pause(DELAY_TIME);
			
			if( (body.getX() + body.getWidth() < 0 || body.getX() > canvas.getWidth()) ||
					(body.getY() + body.getHeight() < 0 || body.getY() > canvas.getHeight()) ) {
			
				remove();
				
				break;
			}
		} // End of while
		
	} // End of run()
	
	// To create another car when this car is clicked in a lane
	public boolean contains ( Location point ) {
		if( body.contains(point) || windshield.contains(point) || backWindshield.contains(point)) {
			return true;
		}	
		return false;
	}	
	
}