import java.awt.*;
import objectdraw.*;

public class simulationController extends WindowController {

	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 600;
	private FilledRect background, mainSt, linaSt, yellowLineT, yellowLineB, 
	yellowLineL, yellowLineR, stopLineB, stopLineT, stopLineL, stopLineR,
	leftTurnLineL, leftTurnLineR, leftTurnLineT, leftTurnLineB, 
	brokenLineTL1, brokenLineTL2, brokenLineTL3,
	brokenLineTR1, brokenLineTR2, brokenLineTR3,
	brokenLineBR1, brokenLineBR2, brokenLineBR3,
	brokenLineBL1, brokenLineBL2, brokenLineBL3,
	brokenLineLL, brokenLineLR, brokenLineLT, brokenLineLB;
	
	
	private Color streetColor = Color.GRAY;
	private Color yellowLine = Color.YELLOW;
	private Color regularLine = Color.WHITE;
	private Color grass = new Color( 0, 200, 0 );
	
	private static final int LANE_WIDTH = 60;
	
	/* Lane Size * Number of Lanes */
	private static final int MAIN_ST_WIDTH = LANE_WIDTH * 5;
	private static final int LINA_ST_WIDTH = LANE_WIDTH * 3;
	
	private static final int GRASS_X = (FRAME_WIDTH - MAIN_ST_WIDTH) / 2;
	private static final int GRASS_Y = (FRAME_HEIGHT - LINA_ST_WIDTH) / 2;
	
	private static final int BROKEN_LINE_LENGTH = (GRASS_X / 8) - 10;
	private static final int BROKEN_LINE_OFFSET = (GRASS_X / 8) + 10;
	private static final int BROKEN_LINE_DISTANCE = BROKEN_LINE_LENGTH + BROKEN_LINE_OFFSET;
	
	private static final int LEFT_TURN_LINE_LENGTH = GRASS_Y / 2 + (GRASS_Y / 2);
	
	private static final int LINE_WIDTH = 6;
	private static final int LINE_OFFSET = LINE_WIDTH / 2;
	
	public void begin() {
		// Draw streets and lines
		background = new FilledRect( 0, 0, FRAME_WIDTH, FRAME_HEIGHT, canvas );		
		mainSt = new FilledRect( GRASS_X, 0, MAIN_ST_WIDTH, FRAME_HEIGHT, canvas );
		linaSt = new FilledRect( 0, GRASS_Y, FRAME_WIDTH, LINA_ST_WIDTH, canvas );
		
		
		yellowLineT = new FilledRect( GRASS_X + LANE_WIDTH * 3 - LINE_OFFSET, 0, LINE_WIDTH, GRASS_Y, canvas);
		yellowLineB = new FilledRect( GRASS_X + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH, LINE_WIDTH, GRASS_Y, canvas);
		yellowLineL = new FilledRect( 0, GRASS_Y + LANE_WIDTH - LINE_OFFSET, GRASS_X, LINE_WIDTH, canvas);
		yellowLineR = new FilledRect( GRASS_X + MAIN_ST_WIDTH, GRASS_Y + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_X, LINE_WIDTH, canvas);
		
		stopLineT = new FilledRect( GRASS_X, GRASS_Y - LINE_WIDTH * 2, LANE_WIDTH * 3 - LINE_OFFSET, LINE_WIDTH * 2, canvas );
		stopLineB = new FilledRect( GRASS_X + LANE_WIDTH * 2 + LINE_OFFSET, 
				GRASS_Y + LINA_ST_WIDTH, LANE_WIDTH * 3 - LINE_OFFSET, LINE_WIDTH * 2, canvas );
		stopLineL = new FilledRect( GRASS_X - LINE_WIDTH * 2, GRASS_Y + LANE_WIDTH + LINE_OFFSET,
				LINE_WIDTH * 2, LANE_WIDTH * 2 - LINE_OFFSET, canvas );
		stopLineR = new FilledRect( GRASS_X + MAIN_ST_WIDTH, GRASS_Y, LINE_WIDTH * 2, LANE_WIDTH * 2 - LINE_OFFSET, canvas );

		leftTurnLineT = new FilledRect( GRASS_X + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_Y / 2, LINE_WIDTH, GRASS_Y / 2, canvas );
		leftTurnLineB = new FilledRect( GRASS_X + LANE_WIDTH * 3 - LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH, LINE_WIDTH, GRASS_Y / 2, canvas );
		leftTurnLineL = new FilledRect( GRASS_X / 2, GRASS_Y + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_X / 2, LINE_WIDTH, canvas );
		leftTurnLineR = new FilledRect( GRASS_X + MAIN_ST_WIDTH, GRASS_Y + LANE_WIDTH - LINE_OFFSET, LEFT_TURN_LINE_LENGTH, LINE_WIDTH, canvas );

		/* White Broken Lines at the Top */
		
		/* At the very Top */
		brokenLineTL1 = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				0, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineTL2 = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				BROKEN_LINE_DISTANCE, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineTL3 = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				BROKEN_LINE_DISTANCE * 2, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		
		brokenLineTR1 = new FilledRect( GRASS_X + (LANE_WIDTH * 4) - LINE_OFFSET,
				0, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineTR2 = new FilledRect( GRASS_X + (LANE_WIDTH * 4) - LINE_OFFSET,
				BROKEN_LINE_DISTANCE, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas);
		brokenLineTR3 = new FilledRect( GRASS_X + (LANE_WIDTH * 4) + LINE_OFFSET,
				BROKEN_LINE_DISTANCE * 2, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		
		/* White Broken Lines at the Bottom of the Street */

		brokenLineBL1 = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				FRAME_HEIGHT - (BROKEN_LINE_DISTANCE * 2) - BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineBL2 = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				FRAME_HEIGHT - BROKEN_LINE_DISTANCE - BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineBL3 = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				FRAME_HEIGHT - BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		
		brokenLineBR1 = new FilledRect( GRASS_X + LANE_WIDTH * 4 - LINE_OFFSET, 
				FRAME_HEIGHT - (BROKEN_LINE_DISTANCE * 2) - BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineBR2 = new FilledRect( GRASS_X + LANE_WIDTH * 4 - LINE_OFFSET, 
				FRAME_HEIGHT - BROKEN_LINE_DISTANCE - BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineBR3 = new FilledRect( GRASS_X + LANE_WIDTH * 4 - LINE_OFFSET,
				FRAME_HEIGHT - BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		
		
		brokenLineLT = new FilledRect( GRASS_X + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_Y / 2, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineLB = new FilledRect( GRASS_X + LANE_WIDTH * 3 - LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineLR = new FilledRect( GRASS_X / 2 - BROKEN_LINE_DISTANCE, GRASS_Y + LANE_WIDTH * 2 - LINE_OFFSET, BROKEN_LINE_LENGTH, LINE_WIDTH, canvas );
		brokenLineLL = new FilledRect( GRASS_X + MAIN_ST_WIDTH, GRASS_Y + LANE_WIDTH - LINE_OFFSET, BROKEN_LINE_LENGTH, LINE_WIDTH, canvas );
		
		// Set colors
		background.setColor( grass );
		mainSt.setColor( streetColor );
		linaSt.setColor( streetColor );
		
		yellowLineT.setColor( yellowLine );
		yellowLineB.setColor( yellowLine );
		yellowLineL.setColor( yellowLine );
		yellowLineR.setColor( yellowLine );
		
		stopLineT.setColor( regularLine );
		stopLineB.setColor( regularLine );
		stopLineL.setColor( regularLine );
		stopLineR.setColor( regularLine );

		leftTurnLineT.setColor( regularLine );
		leftTurnLineB.setColor( regularLine );
		leftTurnLineL.setColor( regularLine );
		leftTurnLineR.setColor( regularLine );

		brokenLineTR1.setColor( regularLine );
		brokenLineTR2.setColor( regularLine );
		brokenLineTR3.setColor( regularLine );
		
		brokenLineTL1.setColor( regularLine );
		brokenLineTL2.setColor( regularLine );
		brokenLineTL3.setColor( regularLine );
		
		brokenLineBR1.setColor( regularLine );
		brokenLineBR2.setColor( regularLine );
		brokenLineBR3.setColor( regularLine );
	
		brokenLineBL1.setColor( regularLine );
		brokenLineBL2.setColor( regularLine );
		brokenLineBL3.setColor( regularLine );
		
		brokenLineLT.setColor( regularLine );
		brokenLineLB.setColor( regularLine );
		brokenLineLL.setColor( regularLine );
		brokenLineLR.setColor( regularLine );
		
	}
	
	
	public void onMouseClick (Location point) {
		//new createCars( );
	}
	
	public static void main(String[] args) {
		new Acme.MainFrame (new simulationController(), args, FRAME_WIDTH, FRAME_HEIGHT);
	}

}
