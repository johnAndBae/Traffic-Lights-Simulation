import java.awt.Color;
import java.awt.Polygon;

import objectdraw.*;

public class Signals extends ActiveObject {

	private static final int SIGNAL_BODY_WIDTH = 40;
	private static final int SIGNAL_BODY_HEIGHT = 120;
	private static final int TOP_ANGLE = 90;
	private static final int BOTTOM_ANGLE = 20;
	private static final int LIGHT_SIZE = 30;
	private static final int LIGHT_OFFSET = (SIGNAL_BODY_WIDTH - LIGHT_SIZE) / 2;
	private static boolean leftTurn = false;

	private FilledRoundedRect signalBody;
	private FilledOval redLight, yellowLight, greenLight;

	private static Color defaultLightColor = Color.DARK_GRAY;
	private static Color red = Color.RED;
	private static Color yellow = Color.YELLOW;
	private static Color green = Color.GREEN;
	private boolean defaultSignal;
	
	public Signals(double x, double y, boolean isLeftTurn, boolean isDefault, Color currentSignal, DrawingCanvas canvas) {

		// Create the body of signal
		signalBody = new FilledRoundedRect(x, y, SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE,
				canvas);
		leftTurn = isLeftTurn;
		defaultSignal = isDefault;

		// Need to draw arrows for left-turn signals
		if (isLeftTurn) {

			redLight = new FilledOval(x + LIGHT_OFFSET, y + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas);
			yellowLight = new FilledOval(x + LIGHT_OFFSET, y + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE,
					canvas);
			greenLight = new FilledOval(x + LIGHT_OFFSET, y + LIGHT_SIZE * 2 + LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE,
					canvas);
		}

		else {

			redLight = new FilledOval(x + LIGHT_OFFSET, y + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas);
			yellowLight = new FilledOval(x + LIGHT_OFFSET, y + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE,
					canvas);
			greenLight = new FilledOval(x + LIGHT_OFFSET, y + LIGHT_SIZE * 2 + LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE,
					canvas);
		}

		if (currentSignal == red) {
			turnRed();
		} else {
			turnGreen();
		}

		start();

	}

	public double getStopLinePosition() {
		return signalBody.getX() + SIGNAL_BODY_HEIGHT;
	}

	public boolean contains(Location point) {

		if (signalBody.contains(point) || redLight.contains(point) || yellowLight.contains(point)
				|| greenLight.contains(point)) {
			return true;
		}

		return false;
	}

	public void remove() {
		signalBody.removeFromCanvas();
		redLight.removeFromCanvas();
		yellowLight.removeFromCanvas();
		greenLight.removeFromCanvas();
	}

	public Color getSignal() {

		if (redLight.getColor() == Color.RED) {
			return red;
		} else if (yellowLight.getColor() == Color.YELLOW) {
			return yellow;
		} else
			return green;
	}

	public void turnGreen() {
		redLight.setColor(defaultLightColor);
		yellowLight.setColor(defaultLightColor);
		greenLight.setColor(Color.GREEN);
	}

	public void turnYellow() {
		redLight.setColor(defaultLightColor);
		greenLight.setColor(defaultLightColor);
		yellowLight.setColor(Color.YELLOW);
	}

	public void turnRed() {
		greenLight.setColor(defaultLightColor);
		yellowLight.setColor(defaultLightColor);
		redLight.setColor(Color.RED);
	}
	
	public void change() {
		if (getSignal() == Color.GREEN) {
			turnYellow();
		} else if (getSignal() == Color.YELLOW) {
			turnRed();
		} else {
			turnGreen();
		}
	}

	public void runSignal() {
		if (getSignal() == Color.GREEN) {
			turnYellow();
			pause(1000);
		} else if (getSignal() == Color.YELLOW) {
			turnRed();
			pause(4500);
		} else {
			pause(1300);
			turnGreen();
			pause(4500);
		}
	}
	
	public void runSignal2() {
		if(getSignal() == Color.RED) {
			pause(1300);
			turnGreen();
			pause(4500);
			turnYellow();
			pause(1000);
		} else if (getSignal() == Color.YELLOW) {
			turnRed();
			pause(1000);
		}
	} 
	
	public void run() {
	
		while (true) {
			
			if( defaultSignal && SimulationController.lanes.get(Lane.LR).isEmpty() && 
					SimulationController.lanes.get(Lane.RR).isEmpty() && SimulationController.signalRS != null && 
					SimulationController.signalRS.getSignal() == Color.RED ) {
				pause(1000);
				turnGreen();
			} else if (defaultSignal && (!SimulationController.lanes.get(Lane.LR).isEmpty() ||
					!SimulationController.lanes.get(Lane.RR).isEmpty() && SimulationController.signalRS != null && 
					SimulationController.signalRS.getSignal() == Color.RED)) {
				runSignal(); // create another function that will time and operate the signals to change color
			} else if (!defaultSignal && (!SimulationController.lanes.get(Lane.LR).isEmpty() || 
					!SimulationController.lanes.get(Lane.RR).isEmpty() && SimulationController.signalTS != null && 
					SimulationController.signalTS.getSignal() == Color.RED)) {
				runSignal2();
				pause(1000);
			} else {
				turnRed();
				pause(10);
			}
		}
	}
}

