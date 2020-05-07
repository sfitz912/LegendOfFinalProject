import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState
{
	TrueTypeFont font;
	Image background;
	Image resume;
	Image menu;
	static Input input;
	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont unifont;
	public Pause(int state)
	
	{
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		background = new Image("res/sprites/Pause.PNG");
		resume = new Image("res/sprites/start.png");		// 168 x 70 px
		menu = new Image ("res/sprites/Menu.png");
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
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		input = gc.getInput();
		int xpos = Controls.getMouseX(gc);
		int ypos = Controls.getMouseY(gc);
		if( (xpos > 250 && xpos < 368) && (ypos > 200 && ypos < 270) && (Controls.leftMouseDown(gc) ) )
		{
			gc.resume();
			sbg.enterState(1);
		}
		if( (xpos > 250 && xpos < 368) && (ypos > 100 && ypos < 170) && (Controls.leftMouseDown(gc) ) )
		{
			sbg.enterState(0);
		}
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		background.draw(25,0);
		resume.draw(250,200);
		menu.draw(250,100);
		unifont.drawString(250,50,"Need a break?",Color.black);
	}
	public int getID()
	{
		return 4;
	}
}
