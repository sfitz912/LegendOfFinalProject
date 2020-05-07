import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.tiled.TiledMap;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.Scanner;

/**
 * The Screen class represents a single game screen.  It has a TiledMap for the game world,
 * a list of collidable objects, and a list of enemies
 * 
 *
 */

public class Screen 
{
	private TiledMap map;
	private Rectangle[] collisionList;
	private File collisions; 
	private Scanner inputFileCollisions;
	
	private File monsters;
	private Scanner inputFileMonsters;
	private CopyOnWriteArrayList<SubMonster> monsterList;
	
	/**
	 * This constructor builds a Screen object using a Tiled map file, a text file containing
	 * a list of impassible rectangular sections of the map, and a text file containing a 
	 * list of monsters
	 * @param mapFile
	 * @param collisionFile
	 * @param monsterFile
	 * @throws SlickException
	 * @throws IOException
	 */
	public Screen(String mapFile, String collisionFile, String monsterFile) throws SlickException, IOException
	{
		System.out.println("Screen constructor. collisionFile: " + collisionFile + " monsterFile: " + monsterFile);
		map = new TiledMap(mapFile);
		collisions = new File(collisionFile);
		inputFileCollisions = new Scanner(collisions);
		buildCollisions();
		
		monsters = new File(monsterFile);
		inputFileMonsters = new Scanner(monsters);
		buildMonsters();
	}
	
	/**
	 * The buildCollisions method reads from the textfile containing collision 
	 * info, and populates an array of Rectangle objects
	 */
	public void buildCollisions ()
	{
		Rectangle[] tempCollisionList = new Rectangle[500];
		int i = 0;
		while (inputFileCollisions.hasNext() )
		{
			int x = inputFileCollisions.nextInt();
			int y = inputFileCollisions.nextInt();
			int width = inputFileCollisions.nextInt();
			int height = inputFileCollisions.nextInt();
			tempCollisionList[i] = new Rectangle(x, y, width, height);
			i++;
		}
		this.collisionList = new Rectangle[i];
		for (int j = 0; j < i; j++)
		{
			collisionList[j] = tempCollisionList[j];
		}
	}
	/**
	 * The buildMonsters method reads from the text file containing monster info, 
	 * and creates an CopyOnWriteArrayList full of SubMonster objects
	 */
	public void buildMonsters ()
	{
		CopyOnWriteArrayList<SubMonster> tempMonsterList = new CopyOnWriteArrayList<SubMonster>();
		
		while (inputFileMonsters.hasNext() )
		{
			int x = inputFileMonsters.nextInt();
			int y = inputFileMonsters.nextInt();
			int width = inputFileMonsters.nextInt();
			int height = inputFileMonsters.nextInt();
			int health = inputFileMonsters.nextInt();
			int attackPoints = inputFileMonsters.nextInt();
			tempMonsterList.add(new SubMonster(x, y, width, height, health, attackPoints) );
		}
		this.monsterList = tempMonsterList;

	}
	
	/**
	 * The getMap method returns the TiledMap object for this game Screen
	 * @return This screens TiledMap object
	 */
	public TiledMap getMap()
	{
		return this.map;
	}
	
	/**
	 * The getCollisions method returns an array populated with the parts of the map
	 * the player can't walk on
	 * @return The array of Rectangles
	 */
	public Rectangle[] getCollisions()
	{
		return this.collisionList;
	}
	
	/**
	 * The getMonsters method returns a CopyOnWriteArrayList containing the starting
	 * positions of all monsters on this screen
	 * @return The list of monsters
	 */
	public CopyOnWriteArrayList<SubMonster> getMonsters()
	{
		return this.monsterList;
	}
	
	

}
