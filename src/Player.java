

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.geom.*;

public class Player 
{
	private int x;
	private int y;
	private float tempX;
	private float tempY;
	private int width;
	private int height;
	public Animation playerAnim, movingUp, movingDown, movingLeft, movingRight,
					swingUp, swingDown, swingLeft, swingRight;
	public int[] duration = {200, 200, 200, 200};
	public int[] swingDuration = {100, 100, 100, 100};
	public final long SWING_TIME = 400;

	public Rectangle hitbox;
	public Rectangle tempHitbox;
	public Rectangle attackBox;
	
	public enum Direction { UP, DOWN, LEFT, RIGHT};
	Direction facing;
	public boolean swinging;
	public long lastSwing;		// Holds timestamp of start of most recent sword swing
	
	// Holds the timestamp of the last time the game detected contact between player and a monster
	public long lastContact;		

	public Player()
	{
		// Start player in middle of first screen
		x = Game.WIDTH / 2;
		y = Game.HEIGHT / 2;
		tempX = x;
		tempY = y;
	}
	
	public void init() throws SlickException
	{
		facing = Direction.DOWN;
		swinging = false;
		lastSwing = 0;
		lastContact = 0;
		
		// Attack Animations
		Image[] attackUp = 
			{
				new Image("res/sprites/swordup1.png"),
				new Image("res/sprites/swordup2.png"),
				new Image("res/sprites/swordup3.png"),
				new Image("res/sprites/swordup4.png")
			};
		Image[] attackDown = 
			{
				new Image("res/sprites/sworddown1.png"),
				new Image("res/sprites/sworddown2.png"),
				new Image("res/sprites/sworddown3.png"),
				new Image("res/sprites/sworddown4.png")
			};
		Image[] attackLeft= 
			{
				new Image("res/sprites/swordleft1.png"),
				new Image("res/sprites/swordleft2.png"),
				new Image("res/sprites/swordleft3.png"),
				new Image("res/sprites/swordleft4.png")
			};
		Image[] attackRight = 
			{
				new Image("res/sprites/swordright1.png"),
				new Image("res/sprites/swordright2.png"),
				new Image("res/sprites/swordright3.png"),
				new Image("res/sprites/swordright4.png")
			};
		
		// Walking Animations
		Image[] walkUp = 
			{
				new Image("res/sprites/walkup1.png"),
				new Image("res/sprites/walkup2.png"),
				new Image("res/sprites/walkup3.png"),
				new Image("res/sprites/walkup4.png")
			};
		
		Image[] walkDown = 
			{
				new Image("res/sprites/walkdown1.png"),
				new Image("res/sprites/walkdown2.png"),
				new Image("res/sprites/walkdown3.png"),
				new Image("res/sprites/walkdown4.png")
			};
		
		Image[] walkLeft = 
			{
				new Image("res/sprites/walkleft1.png"),
				new Image("res/sprites/walkleft2.png"),
				new Image("res/sprites/walkleft3.png"),
				new Image("res/sprites/walkleft4.png")
			};
		
		Image[] walkRight = 
			{
				new Image("res/sprites/walkright1.png"),
				new Image("res/sprites/walkright2.png"),
				new Image("res/sprites/walkright3.png"),
				new Image("res/sprites/walkright4.png")
			};

		
		width = 16;
		height = 32;
		
		// Hit-Boxes
		hitbox = new Rectangle(x, y, width, height);
		tempHitbox = new Rectangle(tempX, tempY, width, height);
		
		// Attack Animations
		swingUp = new Animation(attackUp, swingDuration, true);
		swingUp.setLooping(true);
		
		swingDown = new Animation(attackDown, swingDuration, true);
		swingDown.setLooping(true);
		
		swingLeft = new Animation(attackLeft, swingDuration, true);
		swingLeft.setLooping(true);
		
		swingRight = new Animation(attackRight, swingDuration, true);
		swingRight.setLooping(true);
		
		// Walking Animations
		movingUp = new Animation(walkUp, duration, true);
		movingUp.setLooping(true);
		
		movingDown = new Animation(walkDown, duration, true);
		movingDown.setLooping(true);
		
		movingLeft = new Animation(walkLeft, duration, true);
		movingLeft.setLooping(true);
		
		movingRight = new Animation(walkRight, duration, true);
		movingRight.setLooping(true);
		
		playerAnim = movingDown;
	}
	
	public void update(int delta, GameContainer gc, Rectangle[] worldCollisions) throws SlickException
	{
		// Movement Controls
		if(Controls.moveUp(gc) )
		{
			playerAnim = movingUp;
			tempY = y - (delta * .2f);
			hitbox.setY(tempY);
			playerAnim.start();
			if ( !checkWorldCollisions(gc, worldCollisions) )
			{
				y = (int)tempY;
			}
			else
			{
				hitbox.setY(y);
			}
			facing = Direction.UP;
		}
		
		if(Controls.moveDown(gc) )
		{
			playerAnim = movingDown;
			tempY = y + (delta * .2f);
			hitbox.setY(tempY);
			playerAnim.start();
			if ( !checkWorldCollisions(gc, worldCollisions) )
			{
				y = (int)tempY;
			}
			else
			{
				hitbox.setY(y);
			}
			
			facing= Direction.DOWN;
		}
		
		if(Controls.moveLeft(gc) )
		{
			playerAnim = movingLeft;
			tempX = x - (delta * .2f);
			hitbox.setX(tempX);
			playerAnim.start();
			if ( !checkWorldCollisions(gc, worldCollisions) )
			{
				x = (int)tempX;
			}
			else
			{
				hitbox.setX(x);
			}
			facing= Direction.LEFT;

		}
		if(Controls.moveRight(gc) )
		{
			playerAnim = movingRight;
			tempX = x + (delta * .2f);
			hitbox.setX(tempX);
			playerAnim.start();
			if ( !checkWorldCollisions(gc, worldCollisions) )
			{
				x = (int)tempX;
			}
			else
			{
				hitbox.setX(x);
			}
			facing= Direction.RIGHT;

		}
		if (Controls.noMovement(gc))
		{
			playerAnim.stop();
		}
		
		// Swing check and sets player direction after swing stops
		if(System.currentTimeMillis() - lastSwing >=SWING_TIME) {
			swinging = false;
			
			switch(facing) {
			case UP:
				playerAnim = movingUp;
			break;
			case DOWN:
				playerAnim = movingDown;
			break;
			case LEFT:
				playerAnim = movingLeft;
			break;
			case RIGHT:
				playerAnim = movingRight;
			break;
			}
		}
		
		// Attack Controls
		if(Controls.attack(gc))
		{
			// Checks if direction and if swinging
			if(facing == Direction.UP && swinging == false){ 	// Add && player isn't already swinging
				playerAnim = swingUp;
				playerAnim.start();
				swinging = true;
				lastSwing = System.currentTimeMillis();
			}
			// Checks if direction and if swinging
			if(facing == Direction.DOWN && swinging == false){ 	// Add && player isn't already swinging
				playerAnim = swingDown;
				playerAnim.start();
				swinging = true;
				lastSwing = System.currentTimeMillis();
			}
			// Checks if direction and if swinging
			if(facing == Direction.LEFT && swinging == false){ 	// Add && player isn't already swinging
				playerAnim = swingLeft;
				playerAnim.start();
				swinging = true;
				lastSwing = System.currentTimeMillis();
			}
			// Checks if direction and if swinging
			if(facing == Direction.RIGHT && swinging == false){ 	// Add && player isn't already swinging
				playerAnim = swingRight;
				playerAnim.start();
				swinging = true;
				lastSwing = System.currentTimeMillis();
			}
			
		}
		
		// check current time, last swing start time, & if we stop the hit box
		// 
		
		
		// Update the location of players hit-box on each frame
		hitbox.setX(tempX);
		hitbox.setY(tempY);
				
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public Rectangle getBox()
	{
		return hitbox;
	}
	
	public boolean checkWorldCollisions(GameContainer gc, Rectangle[] worldCollisions)
	{
		for(int i = 0; i < worldCollisions.length; i++)
		{
			if (hitbox.intersects(worldCollisions[i]))
			{
				return true;
			}
		}
		return false;
	}
}