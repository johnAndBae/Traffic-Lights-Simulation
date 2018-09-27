import objectdraw.*;

public class Signals {

	private static final int SIGNAL_OFFSET = 20;
	private static final int SIGNAL_BODY_WIDTH = 40;
	private static final int SIGNAL_BODY_HEIGHT = 120;
	private static final int TOP_ANGLE = 90;
	private static final int BOTTOM_ANGLE = 20;
	private static final int LIGHT = 30;

	
	public Signals( DrawingCanvas canvas ) {
	
		// For top Main Street
		new FilledRoundedRect( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET, 
				0, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		
		
		// For bottom Main Street
		new FilledRoundedRect( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		
		new FilledRoundedRect( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
	}
}
