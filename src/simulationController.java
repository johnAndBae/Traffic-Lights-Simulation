import java.awt.*;
import objectdraw.*;

public class simulationController extends WindowController {

	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 600;
	private FilledRect background, mainSt, linaSt, yellowLineT, yellowLineB, 
	yellowLineL, yellowLineR, stopLineB, stopLineT, stopLineL, stopLineR,
	leftTurnLineL, leftTurnLineR, leftTurnLineT, leftTurnLineB, 
	brokenLineTT, brokenLineTB, brokenLineBT, brokenLineBB;
	
	
	private Color streetColor = Color.GRAY;
	private Color yellowLine = Color.YELLOW;
	private Color regularLine = Color.WHITE;
	private Color grass = new Color( 0, 200, 0 );
	
	
	private static final int MAIN_ST_WIDTH = 500;
	private static final int LINA_ST_WIDTH = 300;
	
	private static final int GRASS_X = 200;
	private static final int GRASS_Y = 150;
	
	private static final int LANE_WIDTH = 100;
	
	private static final int BROKEN_LINE_LENGTH = 60;
	
	private static final int LINE_WIDTH = 10;
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

		leftTurnLineT = new FilledRect( GRASS_X + LANE_WIDTH * 2 - LINE_OFFSET, 0, LINE_WIDTH, GRASS_Y, canvas );
		leftTurnLineB = new FilledRect( GRASS_X + LANE_WIDTH * 3 - LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH, LINE_WIDTH, GRASS_Y, canvas );
		leftTurnLineL = new FilledRect( 0, GRASS_Y + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_X, LINE_WIDTH, canvas );
		leftTurnLineR = new FilledRect( GRASS_X + MAIN_ST_WIDTH, GRASS_Y + LANE_WIDTH - LINE_OFFSET, GRASS_X, LINE_WIDTH, canvas );

		brokenLineTT = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineTB = new FilledRect( GRASS_X + LANE_WIDTH - LINE_OFFSET, 
				0, LINE_WIDTH, BROKEN_LINE_LENGTH / 2, canvas );
		brokenLineBT = new FilledRect( GRASS_X + LANE_WIDTH * 4 - LINE_OFFSET, 
				GRASS_Y + LINA_ST_WIDTH + BROKEN_LINE_LENGTH / 2, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineBB = new FilledRect( GRASS_X + LANE_WIDTH * 4 - LINE_OFFSET, 
				GRASS_Y + LINA_ST_WIDTH + BROKEN_LINE_LENGTH * 2, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );


		
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

		brokenLineTT.setColor( regularLine );
		brokenLineTB.setColor( regularLine );
		brokenLineBT.setColor( regularLine );
		brokenLineBB.setColor( regularLine );

		
	}
	
	
	public void onMouseClick (Location point) {
		
		//new createCars( );
	}
	
	public static void main(String[] args) {
		new Acme.MainFrame (new simulationController(), args, FRAME_WIDTH, FRAME_HEIGHT);
	}

}
