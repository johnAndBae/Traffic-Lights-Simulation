import java.awt.*;
import java.util.*;
import objectdraw.*;

enum Lane {
	TL, TM, TR, BL, BM, BR, LL, LR, RL, RR
}

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
	brokenLineLL, brokenLineLR, brokenLineLT, brokenLineLB,
	laneTL, laneTM, laneTR, laneBL, laneBM, laneBR, laneLL, laneLR, laneRL, laneRR;
	
	private Signals signalTL, signalTS, signalBL, signalBS, signalLL, signalLS, signalRL, signalRS;
	
	private Color streetColor = Color.DARK_GRAY;
	private Color yellowLine = Color.YELLOW;
	private Color regularLine = Color.WHITE;
	private Color grass = new Color( 0, 200, 150 );
	
	private static final int LANE_WIDTH = 60;
	
	// Lane Size * Number of Lanes
	private static final int MAIN_ST_WIDTH = LANE_WIDTH * 5;
	private static final int LINA_ST_WIDTH = LANE_WIDTH * 3;
	
	private static final int GRASS_X = (FRAME_WIDTH - MAIN_ST_WIDTH) / 2;
	private static final int GRASS_Y = (FRAME_HEIGHT - LINA_ST_WIDTH) / 2;
	
	private static final int BROKEN_LINE_LENGTH = (GRASS_X / 8) - 10;
	private static final int BROKEN_LINE_OFFSET = (GRASS_X / 8) + 10;
	private static final int BROKEN_LINE_DISTANCE = BROKEN_LINE_LENGTH + BROKEN_LINE_OFFSET;
	
	
	private static final int LINE_WIDTH = 6;
	private static final int LINE_OFFSET = LINE_WIDTH / 2;
	private static final int STOP_WIDTH = LINE_WIDTH * 2;
	
	private static final int CAR_LENGTH = 70;
	private static final int CAR_OFFSET = 7;
	
	private static final int SIGNAL_OFFSET = 20;
	private static final int SIGNAL_BODY_WIDTH = 40;
	private static final int SIGNAL_BODY_HEIGHT = 120;
	
	public static final int beforeStopLineT = GRASS_Y - STOP_WIDTH;
	public static final int beforeStopLineB = FRAME_HEIGHT - GRASS_Y + STOP_WIDTH;
	public static final int beforeStopLineL = GRASS_X - STOP_WIDTH;
	public static final int beforeStopLineR = FRAME_WIDTH - GRASS_X + STOP_WIDTH;
	
	public static LinkedList<Cars> carList = new LinkedList<Cars>();
	
	public void begin() {
		// Draw streets and lines
		background = new FilledRect( 0, 0, FRAME_WIDTH, FRAME_HEIGHT, canvas );		
		mainSt = new FilledRect( GRASS_X, 0, MAIN_ST_WIDTH, FRAME_HEIGHT, canvas );
		linaSt = new FilledRect( 0, GRASS_Y, FRAME_WIDTH, LINA_ST_WIDTH, canvas );
		
		
		yellowLineT = new FilledRect( GRASS_X + LANE_WIDTH * 3 - LINE_OFFSET, 0, LINE_WIDTH, GRASS_Y, canvas);
		yellowLineB = new FilledRect( GRASS_X + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH, LINE_WIDTH, GRASS_Y, canvas);
		yellowLineL = new FilledRect( 0, GRASS_Y + LANE_WIDTH - LINE_OFFSET, GRASS_X, LINE_WIDTH, canvas);
		yellowLineR = new FilledRect( GRASS_X + MAIN_ST_WIDTH, GRASS_Y + LANE_WIDTH * 2 - LINE_OFFSET, GRASS_X, LINE_WIDTH, canvas);
		
		stopLineT = new FilledRect( GRASS_X, GRASS_Y - STOP_WIDTH, LANE_WIDTH * 3 - LINE_OFFSET, STOP_WIDTH, canvas );
		stopLineB = new FilledRect( GRASS_X + LANE_WIDTH  * 2 + LINE_OFFSET, 
				GRASS_Y + LINA_ST_WIDTH, LANE_WIDTH * 3 - LINE_OFFSET, STOP_WIDTH, canvas );
		stopLineL = new FilledRect( GRASS_X - STOP_WIDTH, GRASS_Y + LANE_WIDTH + LINE_OFFSET,
				STOP_WIDTH, LANE_WIDTH * 2 - LINE_OFFSET, canvas );
		stopLineR = new FilledRect( GRASS_X + MAIN_ST_WIDTH, GRASS_Y, STOP_WIDTH, LANE_WIDTH * 2 - LINE_OFFSET, canvas );

		leftTurnLineT = new FilledRect( GRASS_X + LANE_WIDTH * 2 - LINE_OFFSET,
				BROKEN_LINE_DISTANCE, LINE_WIDTH, GRASS_Y - BROKEN_LINE_DISTANCE, canvas );
		leftTurnLineB = new FilledRect( GRASS_X + LANE_WIDTH * 3 - LINE_OFFSET,
				GRASS_Y + LINA_ST_WIDTH, LINE_WIDTH, GRASS_Y - BROKEN_LINE_DISTANCE, canvas );
		leftTurnLineL = new FilledRect( GRASS_X / 3, GRASS_Y + LANE_WIDTH * 2 - LINE_OFFSET,
				GRASS_X * 2/3, LINE_WIDTH, canvas );
		leftTurnLineR = new FilledRect( GRASS_X + MAIN_ST_WIDTH,
				GRASS_Y + LANE_WIDTH - LINE_OFFSET, GRASS_X * 2/3, LINE_WIDTH, canvas );

		// White Broken Lines at the Top
		
		// At the very Top
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
		brokenLineTR3 = new FilledRect( GRASS_X + (LANE_WIDTH * 4) - LINE_OFFSET,
				BROKEN_LINE_DISTANCE * 2, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		
		// White Broken Lines at the Bottom of the Street
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
		
		
		brokenLineLT = new FilledRect( GRASS_X + LANE_WIDTH * 2 - LINE_OFFSET,
				0, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineLB = new FilledRect( GRASS_X + LANE_WIDTH * 3 - LINE_OFFSET, 
				FRAME_HEIGHT - BROKEN_LINE_LENGTH, LINE_WIDTH, BROKEN_LINE_LENGTH, canvas );
		brokenLineLL = new FilledRect( BROKEN_LINE_LENGTH, GRASS_Y + LANE_WIDTH * 2 - LINE_OFFSET,
				BROKEN_LINE_LENGTH, LINE_WIDTH, canvas );
		brokenLineLR = new FilledRect( FRAME_WIDTH - BROKEN_LINE_LENGTH * 2, 
				GRASS_Y + LANE_WIDTH - LINE_OFFSET, BROKEN_LINE_LENGTH, LINE_WIDTH, canvas );
		
		
		laneTL = new FilledRect( GRASS_X + LANE_WIDTH * 2 + LINE_OFFSET, 0, LANE_WIDTH - LINE_WIDTH, GRASS_Y - STOP_WIDTH, canvas );
		laneTM = new FilledRect( GRASS_X + LANE_WIDTH + LINE_OFFSET, 0, LANE_WIDTH - LINE_WIDTH, GRASS_Y - STOP_WIDTH, canvas );
		laneTR = new FilledRect( GRASS_X, 0, LANE_WIDTH - LINE_OFFSET, GRASS_Y - STOP_WIDTH, canvas );

		laneBL = new FilledRect( GRASS_X + LANE_WIDTH * 2 + LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH + STOP_WIDTH, LANE_WIDTH - LINE_WIDTH, GRASS_Y - STOP_WIDTH, canvas );
		laneBM = new FilledRect( GRASS_X + LANE_WIDTH * 3 + LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH + STOP_WIDTH, LANE_WIDTH - LINE_WIDTH, GRASS_Y - STOP_WIDTH, canvas );
		laneBR = new FilledRect( GRASS_X + LANE_WIDTH * 4 + LINE_OFFSET, GRASS_Y + LINA_ST_WIDTH + STOP_WIDTH, LANE_WIDTH - LINE_OFFSET, GRASS_Y - STOP_WIDTH, canvas );

		laneLL = new FilledRect( 0, GRASS_Y + LANE_WIDTH + LINE_OFFSET, GRASS_X - STOP_WIDTH, LANE_WIDTH - LINE_WIDTH, canvas );
		laneLR = new FilledRect( 0, GRASS_Y + LANE_WIDTH * 2+ LINE_OFFSET, GRASS_X - STOP_WIDTH, LANE_WIDTH - LINE_OFFSET, canvas );

		laneRL = new FilledRect( GRASS_X + MAIN_ST_WIDTH + STOP_WIDTH, GRASS_Y + LANE_WIDTH + LINE_OFFSET, GRASS_X - STOP_WIDTH, LANE_WIDTH - LINE_WIDTH, canvas );
		laneRR = new FilledRect( GRASS_X + MAIN_ST_WIDTH + STOP_WIDTH, GRASS_Y, GRASS_X - STOP_WIDTH, LANE_WIDTH - LINE_OFFSET, canvas );

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
		
		laneTL.setColor( streetColor );
		laneTM.setColor( streetColor );
		laneTR.setColor( streetColor );
		
		laneBL.setColor( streetColor );
		laneBM.setColor( streetColor );
		laneBR.setColor( streetColor );
		
		laneLL.setColor( streetColor );
		laneLR.setColor( streetColor );
		
		laneRL.setColor( streetColor );
		laneRR.setColor( streetColor );

		signalTS = new Signals( GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH, GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, false, canvas );
		signalTL = new Signals( GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2, GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, true, canvas );
		
		signalBS = new Signals( GRASS_X + MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH, GRASS_Y + LINA_ST_WIDTH + SIGNAL_OFFSET, false, canvas );
		signalBL = new Signals( GRASS_X + MAIN_ST_WIDTH + SIGNAL_OFFSET, GRASS_Y + LINA_ST_WIDTH + SIGNAL_OFFSET, true, canvas );
		
		signalLS = new Signals( GRASS_X - SIGNAL_OFFSET - SIGNAL_BODY_WIDTH, GRASS_Y + LINA_ST_WIDTH + SIGNAL_OFFSET, false, canvas );
		signalLL = new Signals( GRASS_X - (SIGNAL_OFFSET + SIGNAL_BODY_WIDTH) * 2, GRASS_Y + LINA_ST_WIDTH + SIGNAL_OFFSET, true, canvas );
		
		signalRS = new Signals( GRASS_X + MAIN_ST_WIDTH + SIGNAL_OFFSET * 2 + SIGNAL_BODY_WIDTH, GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, false, canvas );
		signalRL = new Signals( GRASS_X + MAIN_ST_WIDTH + SIGNAL_OFFSET, GRASS_Y - SIGNAL_BODY_HEIGHT - SIGNAL_OFFSET, true, canvas );

	}
	
	public void onMouseClick (Location point) {

		if( signalTS.contains(point) ) {
			
			if( signalTS.getSignal() == Color.GREEN ) {
				signalTS.turnRed();
			}
			
			else {
				signalTS.turnGreen();
			}
		}

		if( laneTL.contains(point) ) {
			System.out.println("Car is pushed !");
			carList.add(new Cars( laneTL.getX() + CAR_OFFSET, - CAR_LENGTH, Lane.TL, signalTL, canvas));
		}
		
		else if( laneTM.contains(point) ) {
			new Cars( laneTM.getX() + CAR_OFFSET, - CAR_LENGTH, Lane.TM, signalTS, canvas);
		}
		
		else if( laneTR.contains(point) ) {
			new Cars( laneTR.getX() + CAR_OFFSET, - CAR_LENGTH, Lane.TR, signalTS, canvas);
		}
		
		else if( laneBL.contains(point) ) {
			new Cars( laneBL.getX() + CAR_OFFSET, FRAME_HEIGHT, Lane.BL, signalBL, canvas);
		}
		
		else if( laneBM.contains(point) ) {
			new Cars( laneBM.getX() + CAR_OFFSET, FRAME_HEIGHT, Lane.BM, signalBS, canvas);
		}
		
		else if( laneBR.contains(point) ) {
			new Cars( laneBR.getX() + CAR_OFFSET, FRAME_HEIGHT, Lane.BR, signalBS, canvas);
		}
		
		else if( laneLL.contains(point) ) {
			new Cars( - CAR_LENGTH, laneLL.getY() + CAR_OFFSET, Lane.LL, signalLL, canvas);
		}
		
		else if( laneLR.contains(point) ) {
			new Cars( - CAR_LENGTH, laneLR.getY() + CAR_OFFSET, Lane.LR, signalLS, canvas);
		}
		
		else if( laneRL.contains(point) ) {
			new Cars( FRAME_WIDTH, laneRL.getY() + CAR_OFFSET, Lane.RL, signalRL, canvas);
		}
		
		else if( laneRR.contains(point) ) {
			new Cars( FRAME_WIDTH, laneRR.getY() + CAR_OFFSET, Lane.RR, signalRS, canvas);
		}
	}
	
	public static void main(String[] args) {
		new Acme.MainFrame (new simulationController(), args, FRAME_WIDTH, FRAME_HEIGHT);
	}

}
