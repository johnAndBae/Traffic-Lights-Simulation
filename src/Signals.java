import java.awt.Color;

import objectdraw.*;

public class Signals extends ActiveObject {

	private static final int SIGNAL_BODY_WIDTH = 40;
	private static final int SIGNAL_BODY_HEIGHT = 120;
	private static final int TOP_ANGLE = 90;
	private static final int BOTTOM_ANGLE = 20;
	private static final int LIGHT_SIZE = 30;
	private static final int LIGHT_OFFSET = (SIGNAL_BODY_WIDTH - LIGHT_SIZE) / 2;
	
	private FilledOval redLight, yellowLight, greenLight;

	private static Color defaultLightColor = Color.DARK_GRAY;
	private static Color red = Color.RED;
	private static Color yellow = Color.YELLOW;
	private static Color green = Color.GREEN;
	
	public Signals( double x, double y, boolean isLeftTurn, DrawingCanvas canvas ) {
	
		// Create the body of signal
		new FilledRoundedRect( x, y, SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
	


		// Need to draw arrows for left-turn signals
		if ( isLeftTurn ) {
			redLight = new FilledOval( x + LIGHT_OFFSET, y + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
			yellowLight = new FilledOval( x + LIGHT_OFFSET, y + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
			greenLight = new FilledOval( x + LIGHT_OFFSET, y + LIGHT_SIZE * 2 + LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
			
			redLight.setColor(red);
			yellowLight.setColor(defaultLightColor);
			greenLight.setColor(defaultLightColor);
		}
		
		else {
			redLight = new FilledOval( x + LIGHT_OFFSET, y + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
			yellowLight = new FilledOval( x + LIGHT_OFFSET, y + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
			greenLight = new FilledOval( x + LIGHT_OFFSET, y + LIGHT_SIZE * 2 + LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
			
			redLight.setColor(defaultLightColor);
			yellowLight.setColor(defaultLightColor);
			greenLight.setColor(green);
		}


	}
	
	public Color getSignal() {
		
		if( redLight.getColor() == red ) {
			return red;
		}
		else
			return green;
	}
	
	public void turnGreen() {
		/*redLight.setColor(defaultLightColor);
		yellowLight.setColor(yellow);
		
		pause(1000);
		
		redLight.setColor(defaultLightColor);
		
		pause(1000);
		yellowLight.setColor(yellow);
		
		pause(1000);
		
		yellowLight.setColor(defaultLightColor);
		greenLight.setColor(green);*/
	}

	public void turnRed() {
		
		greenLight.setColor(defaultLightColor);
		yellowLight.setColor(yellow);
		
		pause(1000);
		
		yellowLight.setColor(defaultLightColor);
		redLight.setColor(red);
	}
}
