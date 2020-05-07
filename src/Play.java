import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.*;
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
//import org.lwjgl.input.Mouse;

public class Play extends BasicGameState
{
	boolean quit = false;
	
	boolean drawBox = false;		// option to show hitboxes for debugging purposes
	
	// Start in upper left screen in 2d screens array
	int row = 0;
	int col = 0;
	
	Player player = new Player();		// Create an instance of Player
	//SubMonster monster = new SubMonster(300, 400, 50, 40, 100, 100);
	
	Screen[][] screens = new Screen[2][2];	// Create a 2d array of Screen objects
	Screen currentScreen;
	TiledMap map;
	
	static Input input;
	
	CopyOnWriteArrayList<SubMonster> monsterList;		// CopyOnWriteArrayList to hold monsters on current screen
	Rectangle[] worldCollisions;						// holds collision rectangles for current screen
	
	static long screenLoadTime;							// holds the time current screen was loaded
	
	/**
	 * This no-arg constructor is required by Slick2d, but we don't actually need to do anything
	 * with it.  It accepts an integer for the number representing this game state
	 * @param state
	 */
	public Play(int state)
	{
		
	}
	
	/**
	 * The init method runs once to initialize the Play state
	 * @param gc The GameContainer
	 * @param sbg The StateBasedGame
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{	
		try
		{
			screens[0][0] = new Screen("res/map_02/screen_0_0.tmx", 
										"res/map_02/col_0_0.txt",
										"res/map_02/monsters_0_0.txt");
			screens[1][0] = new Screen("res/map_02/screen_1_0.tmx", 
										"res/map_02/col_1_0.txt",
										"res/map_02/monsters_1_0.txt");
			screens[0][1] = new Screen("res/map_02/screen_0_1.tmx", 
										"res/map_02/col_0_1.txt",
										"res/map_02/monsters_0_1.txt");
			screens[1][1] = new Screen("res/map_02/screen_1_1.tmx", 
										"res/map_02/col_1_1.txt",
										"res/map_02/monsters_1_1.txt");	
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		map = screens[row][col].getMap();						// Set map to the one for this screen
		currentScreen = screens[row][col];						// Set current screen
		worldCollisions = currentScreen.getCollisions();		// Get list of collision objects for this screen
		monsterList = currentScreen.getMonsters();				// Get list of monsters for this screen
		
		player.init();		// run init method for player
		
		// initialize monsters for first screen
		for (SubMonster m : monsterList)
		{
			m.init();
		}
	}
	
	/**
	 * the render method runs every time the screen is refreshed, and draws everything that 
	 * needs to be drawn
	 * @param gc	The GameContainer
	 * @param sbg	The StateBasedGame
	 * @param g		The graphics object
	 * @throws		SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		map.render(0, 0);								// draw the map first, so everything else is on top
		monsterList = currentScreen.getMonsters();
		// Draw all monsters on current screen
		for (SubMonster m : monsterList)
		{
			m.monsterAnim.draw(m.getX(), m.getY() );
		}
		
		// draw the player
		player.playerAnim.draw(player.getX(), player.getY());
		
		// draw debugging info
		/*
		g.drawString("Player X: " + player.getX() + "\nPlayer Y: " + player.getY(), 100, 50);
		g.drawString("Mouse X: " + Controls.getMouseX(gc) + " Mouse Y: " + Controls.getMouseY(gc), 300, 50);
		*/
		if (drawBox)
		{
			g.draw(player.getBox() );
			//g.draw(monster.hitbox);
			for (int i = 0; i < worldCollisions.length; i++)
			{
				g.draw(worldCollisions[i]);
			}
			
			for (SubMonster m : monsterList)
			{
				g.draw(m.hitbox );
			}
		}
	}
	
	/**
	 * The update method updates everything in the Play state
	 * @param gc	The GameContainer
	 * @param sbg	The StateBasedGame
	 * @param delta	The time since last update
	 * @throws		SlickException
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{		
		// Run the Players update method
		player.update(delta, gc, worldCollisions);
		
		input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			gc.pause();
			sbg.enterState(4);
		}
		
		// Check if the player is at the edge of the screen
		if (player.getY() > 450 && row < 1)							// If at the bottom of currentScreen
		{
			row++;													// Increment row, so they move down one screen
			currentScreen = screens[row][col];						// Set current screen to the one below
			map = currentScreen.getMap();							// Retrieve the map for the new screen
			worldCollisions = currentScreen.getCollisions();		// Get the list of impassible objects for new screen
			monsterList = currentScreen.getMonsters();				// Get the list of monsters for the new screen
			
			// initialize monsters for this screen, I initially forgot this and it took me 3 hours to figure out what I did wrong
			for (SubMonster m : monsterList)
			{
				m.init();
			}
			
			// move the player to top of the new screen
			player.setY(Game.HEIGHT - player.getY() + 30);
		}
		
		// Same things for other directions
		if (player.getY() < 30 && row > 0)
		{
			row--;
			currentScreen = screens[row][col];
			map = screens[row][col].getMap();
			worldCollisions = currentScreen.getCollisions();
			System.out.println(monsterList + "\n" + System.currentTimeMillis() );
			monsterList = currentScreen.getMonsters();
			// initialize monsters for this screen
			for (SubMonster m : monsterList)
			{
				m.init();
			}
			System.out.println(monsterList + "\n" + System.currentTimeMillis() );
			player.setY(Game.HEIGHT - player.getY() - 30 );
		}
		if (player.getX() > 610 && col < 1)
		{
			col++;
			currentScreen = screens[row][col];
			map = screens[row][col].getMap();
			worldCollisions = currentScreen.getCollisions();
			System.out.println(monsterList + "\n" + System.currentTimeMillis() );
			monsterList = currentScreen.getMonsters();
			// initialize monsters for this screen
			for (SubMonster m : monsterList)
			{
				m.init();
			}
			System.out.println(monsterList + "\n" + System.currentTimeMillis() );
			player.setX(Game.WIDTH  - player.getX() + 30);
		}
		if (player.getX() < 30 && col > 0)
		{
			col--;
			currentScreen = screens[row][col];
			map = currentScreen.getMap();
			worldCollisions = currentScreen.getCollisions();
			monsterList = currentScreen.getMonsters();
			// initialize monsters for this screen
			for (SubMonster m : monsterList)
			{
				m.init();
			}
			player.setX(Game.WIDTH  - player.getX() - 30);
		}
		// Check for fighting
		Combat.checkContact(gc, player, currentScreen.getMonsters() );
	}
	
	public int getID()
	{
		return 1;
	}
	

}