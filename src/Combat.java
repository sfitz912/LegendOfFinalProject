import org.newdawn.slick.*;
import java.util.concurrent.*;
import java.io.*;

/**
 * The Combat class has methods for detecting if the Player comes into contact with a Monster, 
 * and determining if the player or monster is damaged
 * 
 *
 */

public class Combat 
{
	private static long lastContact;					// Holds the time the player last came into contact with a monster
	private static boolean activeContact = false;		// Whether or not there is a fight whose outcome is yet to be determined
	final static long TIME_TO_ATTACK = 500;				// The amount of time the player has to swing their sword to damage the monster, instead of getting injured
	
	/**
	 * The checkContact method judges all fights between the player and monsters
	 * @param gc			The GameContainer object
	 * @param p				The Player object
	 * @param monsters		The list of monsters on CurrentScreen
	 * @return				True if a new contact between Player and a Monster has been detected
	 */
	
	public static boolean checkContact(GameContainer gc, Player p, CopyOnWriteArrayList<SubMonster> monsters)
	{
		// For each monster on the screen
		for (SubMonster m : monsters)
		{
			// If there is already a fight which hasn't been resolved
			if (activeContact)
			{
				// Store the time
				long time = System.currentTimeMillis();
				// If enough time has passed since the fight started to determine a winner...
				if ( (time - lastContact ) > TIME_TO_ATTACK)
				{
					// If the player has swung their sword in time
					if ( (time - p.lastSwing ) < TIME_TO_ATTACK)
					{
						// Print message to the console
						System.out.printf("Time: %d Last Contact: %d Last Swing: %d \nEnemy damaged\n", time, lastContact, p.lastSwing);
						activeContact = false;	// We can set activeContact to false, this fight has been resolved
						monsters.remove(m);		// Right now monsters don't have health, we simply remove vanquished monsters from the game
					}
					// If the player didn't swing in time, they take damage
					else
					{
						if (activeContact)
						{
							// Although we didn't implement a health system yet, so it just displays a message in the console
							System.out.printf("Time: %d Last Contact: %d Last Swing: %d \nPlayer damaged\n", time, lastContact, p.lastSwing);
						}
					}
					activeContact = false;
				}
			}
			
			// If there wasn't already an unresolved fight, check if player is colliding with the monster
			if (p.hitbox.intersects(m.hitbox) && !activeContact)
			{
				// And if so, update lastContact, and activeContact, and return true
				lastContact = System.currentTimeMillis();
				activeContact = true;
				System.out.printf("Contact at: %d\n", lastContact);
				return true;
			}
		}
		return false;
	}
	
}
