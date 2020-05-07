/**
 * The Controls class has methods that return true if the user is providing
 * various kinds of input.  For example, a moveUp method can check if either
 * the w or up arrow are pressed, so a player can use either the arrow keys
 * or WASD to move.  Might be able to add controller support later...
 * 
 */


import org.newdawn.slick.*;
//import org.newdawn.slick.state.*;		// Not yet sure if this is needed...
import org.lwjgl.input.Mouse;

public class Controls 
{

	static Input input;			// This is the Input object these methods use

	
	/**
	 * The moveUp method accepts a GameContainer object and returns true if the player
	 * is performing any of the actions that should cause their character to move up
	 * @param gc The GameContainer object
	 * @return true if the character is trying to move up, otherwise false
	 */
	public static boolean moveUp(GameContainer gc)
	{
		input = gc.getInput();
		if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * The moveDown method accepts a GameContainer object and returns true if the player
	 * is performing any of the actions that should cause their character to move down
	 * @param gc The GameContainer object
	 * @return true if the character is trying to move down, otherwise false
	 */
	public static boolean moveDown(GameContainer gc)
	{
		input = gc.getInput();
		if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * The moveLeft method accepts a GameContainer object and returns true if the player
	 * is performing any of the actions that should cause their character to move left
	 * @param gc The GameContainer object
	 * @return true if the character is trying to move left, otherwise false
	 */
	public static boolean moveLeft(GameContainer gc)
	{
		input = gc.getInput();
		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * The moveRight method accepts a GameContainer object and returns true if the player
	 * is performing any of the actions that should cause their character to move to the right
	 * @param gc The GameContainer object
	 * @return true if the character is trying to move right, otherwise false
	 */
	public static boolean moveRight(GameContainer gc)
	{
		input = gc.getInput();
		if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * The attack method accepts a GameContainer object and returns true if the player 
	 * is pressing the attack key(z)
	 * @param gc
	 * @return true if character attacking, otherwise false
	 */
	
	public static boolean attack(GameContainer gc)
	{
		input = gc.getInput();
		if (input.isKeyDown(Input.KEY_Z) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * The toggleDebugBoxes method accepts a GameContainer object and returns true if
	 * the player is pressing the (b) key
	 * @param gc
	 * @return true if player pressing b, false otherwise
	 * 
	 */
	public static boolean toggleDebugBoxes(GameContainer gc)
	{
		input = gc.getInput();
		if (input.isKeyDown(Input.KEY_B))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean noMovement(GameContainer gc)
	{
		input = gc.getInput();
		if (!moveRight(gc)
				&& !moveLeft(gc)
				&& !moveUp(gc)
				&& !moveDown(gc)
				&& !attack(gc))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * The getMouseX method returns the x position of the mouse.
	 * @return The x position of the mouse
	 */
	public static int getMouseX(GameContainer gc)
	{
		return Mouse.getX();
	}
	
	/**
	 * The getMouseY method returns the y position of the mouse with
	 * an origin of the top of the screen, because that's what slick uses,
	 * while the lwjgl uses the bottom of the screen for the y axis.
	 * @param gc The GameContainer object
	 * @return The y position of the mouse
	 */
	public static int getMouseY(GameContainer gc)
	{
		int y = Mouse.getY();
		y = gc.getHeight() - y;
		return y;
	}
	
	/**
	 * The leftMouseDown method returns true if the left mouse button is down,
	 * false otherwise
	 * @param gc The GameController
	 * @return true if left mouse button is down, otherwise false
	 */
	public static boolean leftMouseDown(GameContainer gc)
	{
		if (Mouse.isButtonDown(0) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}