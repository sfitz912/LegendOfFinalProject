import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.*;
//import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState
{
	Image background;
	Image playNow;
	Image exitGame;
	Image credits;
	boolean up = false;
	boolean down = false;
	boolean left = false;
	boolean right = false;
	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont unifont;
	
	
	public String mouse = "No input yet...";
	public Menu(int state)
	{
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		playNow = new Image("res/sprites/start.png");		// 168 x 70 px
		exitGame = new Image("res/sprites/exit.png");		// 168 x 70 px
		credits = new Image("res/sprites/Credits.png");
		background = new Image("res/sprites/Capture.PNG");
		
		try 
		{
			UIFont1= java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					org.newdawn.slick.util.ResourceLoader.getResourceAsStream("res/manaspc.ttf"));
			UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 24);
			
			unifont = new org.newdawn.slick.UnicodeFont(UIFont1);
			unifont.addAsciiGlyphs();
			unifont.getEffects().add(new ColorEffect(java.awt.Color.white));
			unifont.addAsciiGlyphs();
			unifont.loadGlyphs();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString(mouse, 50, 550);
		background.draw(0,0);
		playNow.draw(250, 150);
		exitGame.draw(250, 250);
		credits.draw(250, 350);
		unifont.drawString(0,50,"Welcome to 'The Legend of Final Project'!",Color.black );
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		int xpos = Controls.getMouseX(gc);
		int ypos = Controls.getMouseY(gc);
		mouse = "Current Mouse Position: X: " + xpos + " Y: " + ypos;
		
		if ( (xpos > 250 && xpos < 368) && (ypos > 150 && ypos < 220) && (Controls.leftMouseDown(gc) ) )
		{
			sbg.enterState(1);
		}
		
		if ( (xpos > 250 && xpos < 368) && (ypos > 250 && ypos < 320) && (Controls.leftMouseDown(gc) ) )
		{
			System.exit(0);
		}
		if ( (xpos > 250 && xpos < 368) && (ypos > 350 && ypos < 420) && (Controls.leftMouseDown(gc) ) )
		{
			sbg.enterState(2);
		}
		if ( (xpos > 250 && xpos < 368) && (ypos > 450 && ypos < 520) && (Controls.leftMouseDown(gc) ) )
		{
			sbg.enterState(3);
		}
		
		
	}
	
	public int getID()
	{
		return 0;
	}
	

}
