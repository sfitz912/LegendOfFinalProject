import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame
{
	public static final String gamename = "Legend Of Final Project";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int HEIGHT = 480;
	public static final int WIDTH = 640;
	public static final int TILE_SIZE = 16;
	
	/**
	 * This constructor takes the name of the game, passes it to the superclass constructor,
	 * and adds the menu and play states to the game
	 * 
	 * @param gamename The name of the game as a String
	 */
	public Game(String gamename)
	{
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
	}
	
	/**
	 * The initStatesList method accepts a GameContainer object and 
	 * initializes the menu and play states, and then enters the menu
	 * state to start the game.  
	 * @param gc The GameContainer object
	 */
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		AppGameContainer appgc;
		
		try
		{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setMinimumLogicUpdateInterval(20);			// Prevents weird issues on fast computers
			appgc.setTargetFrameRate(60);						// Keeps it from having ridiculously high frame rates and wasting cpu time
			appgc.start();
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}

}
