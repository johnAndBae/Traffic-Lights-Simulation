import objectdraw.*;
import java.awt.*;

public class Cars {
	private static final double CAR_HEIGHT = 60;
	private static final double CAR_WIDTH = 45;
	private static final double CAR_ANGLE = 20;

	//private DrawingCanvas canvas;
	private FilledRoundedRect body, windshield, backWindshield;

	
	//double x, double y, double width, double height, double arcWidth, double arcHeight, DrawingCanvas canvas
	// = new FilledRoundedRect ( 0, 0 , car_Height, car_Height, car_Height, car_Height, canvas );
	//private Color
	
	
	//include Mouse Listener position
	public Cars (int lane, DrawingCanvas canvas) {
		body = new FilledRoundedRect(0, 0, CAR_HEIGHT, CAR_WIDTH, CAR_ANGLE, CAR_ANGLE, canvas);
		windshield = new FilledRoundedRect();
		backWindshield = new FilledRoundedRect();
			
		
	}
	
	// To implement the car's movements.
	public void move ( double x, double y ) {
		
		body.move(x, y);
		windshield.move(x, y);
		backWindshield.move(x, y);
	}
	
	
	// To create another car when this car is clicked in a lane
	public boolean contains ( Location point ) {
		
		if( body.contains(point) || windshield.contains(point) || backWindshield.contains(point) ) {
		
			return true;
		}
		return false;
	}	
}