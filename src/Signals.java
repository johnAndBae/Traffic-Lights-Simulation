import java.awt.Color;

import objectdraw.*;

public class Signals {

	private static final int SIGNAL_OFFSET = 20;
	private static final int SIGNAL_BODY_WIDTH = 40;
	private static final int SIGNAL_BODY_HEIGHT = 120;
	private static final int TOP_ANGLE = 90;
	private static final int BOTTOM_ANGLE = 20;
	private static final int LIGHT_SIZE = 30;
	private static final int LIGHT_OFFSET = (SIGNAL_BODY_WIDTH - LIGHT_SIZE) / 2;
	
	private FilledOval lightTLG, lightTLY, lightTLR, lightTSG, lightTSY, lightTSR, lightBLG,
	lightBLY, lightBLR, lightBSR, lightBSY, lightBSG, lightLLG, lightLLY, lightLLR, lightLSR,
	lightLSY, lightLSG, lightRLG, lightRLY, lightRLR, lightRSG, lightRSY, lightRSR;

	private Color defaultLightColor = Color.DARK_GRAY;
	private Color redLight = Color.RED;
	private Color yellowLight = Color.YELLOW;
	private Color greenLight = Color.GREEN;
	
	public Signals( DrawingCanvas canvas ) {
	
		// For the top of Main Street
		new FilledRoundedRect( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		// (For left-turn signals)
		new FilledRoundedRect( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );

		
		// For the bottom of Main Street
		new FilledRoundedRect( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		
		// (For left-turn signals)
		new FilledRoundedRect( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		
		
		
		// For the left side of Lina Street
		new FilledRoundedRect( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		
		// (For left-turn signals)
		new FilledRoundedRect( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		
		// For the right side of Lina Street
		new FilledRoundedRect( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
	
		// (For left-turn signals)
		new FilledRoundedRect( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, 
				SIGNAL_BODY_WIDTH, SIGNAL_BODY_HEIGHT, TOP_ANGLE, BOTTOM_ANGLE, canvas );
		
		// Creating Top Left-turn Red light
		lightTLR = new FilledOval( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2 + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		// Creating Top Left-turn Yellow light
		lightTLY = new FilledOval( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2 + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		// Creating Top Left-turn Green light
		lightTLG = new FilledOval( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2 + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE * 2 + LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		
		lightTSR = new FilledOval( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightTSY = new FilledOval( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightTSG = new FilledOval( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE * 2+ LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		
		lightRSR = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightRSY = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightRSG = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE * 2+ LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		
		lightRLR = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET  + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightRLY = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightRLG = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET, 
				simulationController.GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET + LIGHT_SIZE * 2+ LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		
		lightBLR = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET  + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightBLY = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightBLG = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE * 2+ LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		
		lightBSR = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH  + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightBSY = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightBSG = new FilledOval( simulationController.GRASS_X + simulationController.MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE * 2+ LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );

		
		lightLSR = new FilledOval( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH  + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightLSY = new FilledOval( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightLSG = new FilledOval( simulationController.GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE * 2+ LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightLLR = new FilledOval( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2  + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_OFFSET * 2, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightLLY = new FilledOval( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2 + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE + LIGHT_OFFSET * 3, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		lightLLG = new FilledOval( simulationController.GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2 + LIGHT_OFFSET, 
				simulationController.GRASS_Y + simulationController.LINA_ST_WIDTH + SIGNAL_OFFSET + LIGHT_SIZE * 2+ LIGHT_OFFSET * 4, LIGHT_SIZE, LIGHT_SIZE, canvas );
		
		// Top
		lightTLR.setColor(redLight);
		lightTLY.setColor(defaultLightColor);
		lightTLG.setColor(defaultLightColor);

		lightTSR.setColor(defaultLightColor);
		lightTSY.setColor(defaultLightColor);
		lightTSG.setColor(greenLight);

		// Bottom
		lightBLR.setColor(redLight);
		lightBLY.setColor(defaultLightColor);
		lightBLG.setColor(defaultLightColor);
		
		lightBSR.setColor(defaultLightColor);
		lightBSY.setColor(defaultLightColor);
		lightBSG.setColor(greenLight);
		
		// Left
		lightLSR.setColor(redLight);
		lightLSY.setColor(defaultLightColor);
		lightLSG.setColor(defaultLightColor);
		
		lightLLR.setColor(redLight);
		lightLLY.setColor(defaultLightColor);
		lightLLG.setColor(defaultLightColor);
		
		// Right
		lightRSR.setColor(redLight);
		lightRSY.setColor(defaultLightColor);
		lightRSG.setColor(defaultLightColor);

		lightRLR.setColor(redLight);
		lightRLY.setColor(defaultLightColor);
		lightRLG.setColor(defaultLightColor);

	}

}
