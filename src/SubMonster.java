import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * This SubMonster class creates the type of monsters 
 * that are in the game.
 * */

public class SubMonster extends Monster
{	
		public int[] duration = {200, 200, 200, 200};		// Duration for each frame of animation in milliseconds
		private int maxHealth;
		private int attackPoints;
		public Animation monsterAnim, movingUp, movingDown, movingLeft, movingRight;
		public Rectangle hitbox;
		
		/**
		 * An abstract method init (initialize) that has no parameters and throws a
		 * Slick2d exception. This method initialize the monster's movements.
		 * And create the monster's animations.
		 * @throws SlickException To indicate a internal error.
		 * */
		
		public void init() throws SlickException
		{
			
			  Image[] monsterUp = 
				{
					new Image("res/Enemy/walkup1.png"),
					new Image("res/Enemy/walkup2.png"),
					new Image("res/Enemy/walkup3.png"),
					new Image("res/Enemy/walkup4.png")
				};
			
			Image[] monsterDown = 
				{
					new Image("res/Enemy/walkdown1.png"),
					new Image("res/Enemy/walkdown2.png"),
					new Image("res/Enemy/walkdown3.png"),
					new Image("res/Enemy/walkdown4.png")
				};
			
			Image[] monsterLeft = 
				{
					new Image("res/Enemy/walkleft1.png"),
					new Image("res/Enemy/walkleft2.png"),
					new Image("res/Enemy/walkleft3.png"),
					new Image("res/Enemy/walkleft4.png")
				};
			
			Image[] monsterRight = 
				{
					new Image("res/Enemy/walkright1.png"),
					new Image("res/Enemy/walkright2.png"),
					new Image("res/Enemy/walkright3.png"),
					new Image("res/Enemy/walkright4.png")
				};
				
			/*
			setHeight(50);
			setWidth(40);
			setX(300);
			setY(400);
			setHitBoxX(300);
			setHitBoxY(400);
		*/
			
			hitbox = new Rectangle(x, y, width, height);

			movingRight = new Animation(monsterRight, duration, true);
			movingRight.setLooping(true);
			
			
			monsterAnim = movingRight;
			
			
		}
		

		/*
		 
		 **/
		public SubMonster() 
		{	
			
		}
		
		/*
		 
		 **/
		public SubMonster(int x, int y, int w, int h, int hea, int att) 
		{	
			  super(x, y, w, h);
			  maxHealth = hea;
			  attackPoints = att;
			  
		}
		
		/*
		 
		**/
		public void setMaxHealth(int hea)
		{
			maxHealth = hea;
		}
		
		/*
		 
		**/
		public void setAttackPoints(int att)
		{
			attackPoints = att;
		}
		
		/*
		 
		**/
		public int getMaxHealth()
		{
			return maxHealth;
		}
		
		/*
		 
		**/
		public int getAttackPoints()
		{
			return attackPoints;
		}
		 
		/*
		  
		 **/
		@Override
		public String toString()
		{
			String output = String.format("x: %d y: %d\n", x, y);
			return output;
		}

}