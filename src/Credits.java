import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Credits extends BasicGameState
{	
	public String present = "Presented by:";
	public String ant = "Anthony Ruffin";
	public String sean = "Shawn Dutill";
	public String shawn = "Sean Fitzpatrick";
	public String alex = "Alex Johnson";
	static Input input;
	java.awt.Font UIFont1;
	org.newdawn.slick.UnicodeFont unifont;
	
	public Credits(int state)
	{
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
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
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			sbg.enterState(0);
		}
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		unifont.drawString(150,100,present, Color.white);
		unifont.drawString(150,150,ant, Color.white);
		unifont.drawString(150,200,sean, Color.white);
		unifont.drawString(150,250,shawn, Color.white);
		unifont.drawString(150,300,alex, Color.white);
		unifont.drawString(150, 350, "Hope you enjoy the test ;]");
	}
	public int getID()
	{
		return 2;
	}
}
