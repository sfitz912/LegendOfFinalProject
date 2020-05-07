import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
//import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState
{
	
	Image playNow;
	Image exitGame;
	
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	
	public String mouse = "No input yet...";
	public Menu(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		playNow = new Image("res/play.png");		// 100 x 50 px
		exitGame = new Image("res/exit.png");		// 100 x 50 px
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouse, 50, 550);
		g.drawString("Welcome to 'The Legend of Final Project'!", 100, 50);
		
		playNow.draw(100, 100);
		exitGame.draw(100, 200);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		int xpos = Controls.getMouseX(gc);
		int ypos = Controls.getMouseY(gc);
		mouse = "Current Mouse Position: X: " + xpos + " Y: " + ypos;
		
		if ( (xpos > 100 && xpos < 200) && (ypos > 100 && ypos < 150) && (Controls.leftMouseDown(gc) ) )
		{
			sbg.enterState(1);
		}
		
		if ( (xpos > 100 && xpos < 200) && (ypos > 200 && ypos < 250) && (Controls.leftMouseDown(gc) ) )
		{
			System.exit(0);
		}
		
	}
	
	public int getID()
	{
		return 0;
	}
	

}
