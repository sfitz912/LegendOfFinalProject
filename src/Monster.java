import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * This is the Monster class. A super class that sets and gets the 
 * monster's position, hit box, width, and height with get and set 
 * methods.
 * */

public class Monster 
{
	
			
			protected int x;				//For the monster's x position.
			protected int y;				//For the monster's y position.
			protected float hitBoxX;		//For the monster's x hit box.
			protected float hitBoxY;		//For the monster's y hit box.
			protected int width;			//For the width. 
			protected int height;			//For the height.
		   
		    
		    
			/**
	    	A no parameter Monster constructor. 
			 */
		    public Monster()
		    {
		   
		    }
		    
		    /**
	     	A Monster constructor with parameter
	     	@param x To set the monster's x position.
	     	@param y To set the monster's y position.
	     	@param w To set the monster's width.
	     	@param h To set the monster's height.
	     	
		     */
		    public Monster(int x, int y, int w, int h)
		    {
		    	this.x = x;
		    	this.y = y;
		    	width = w;
		    	height = h;
		    }
		    
		    /**
	     	This setX method is for the monster's x position.
	     	@param x To set the monster's x position.
		     */
		    public void setX(int x)
		    {
		    	this.x = x;
		    }
		   
		    /**
	        This setY method is for the monster's x position.
	     	@param y To set the monster's y position.
		     */
		    public void setY(int y)
		    {
		    	this.y = y;
		    }
		    
		    /**
	     	This setWidth method sets the monter's width.
	     	@param w To hold the monster's width.
		     */
		    public void setWidth(int w)
		    {
		    	width= w;
		    }
		    
		    /**
	      	This setHeight method sets the monter's height.
	     	@param h To hold the monster's height.
		     */
		    public void setHeight(int h)
		    {
		    	height = h;
		    }
		    
		    /**
     		This getX method is for getting the monster's x position.
     		@return x returns the monster's x position.
		     */
		    public float getX()
		    {
		    	return this.x;
		    }
		    
		    /**
	     	This getY method is for getting the monster's y position.
	     	@return y returns the monster's y position.
		     */
		    public float getY()
		    {
		    	return this.y;
		    }
		    
		    
		    /**
	     	This getWidth method gets the monter's width.
	     	@return width returns the monster's width.
		     */
		    public int getWidth()
		    {
		    	return width;
		    }
		    
		    /**
	     	This getHeight method gets the monter's height.
	     	@return height returns the monster's height.
		     */
		    public int getHeight()
		    {
		    	return height;
		    }
}