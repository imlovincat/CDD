package lab5;
import java.util.concurrent.Semaphore;

/**
 * Class Queues
 * @author			Chi Ieong Ng C00223421
 * 
 */

public class Queues{
	
	/**
	 * Semaphore controller
	 * 
	 */
	
	Semaphore leadersQ = new Semaphore(0);
	Semaphore followersQ = new Semaphore(0);
	Semaphore mutex = new Semaphore(1);
	Semaphore rendezvous = new Semaphore(0);
	private int leaders = 0;
	private int followers = 0;
	
	/**
	 * queues instance
	 */
	public Queues() {
    }
	
	/**
	 * getter
	 * @return	leaders	number of leaders
	 */
	int getLeaders() {
		return leaders;
	}
	
	/**
	 * setter
	 * @param	leaders	set number of leader
	 */
	void setLeaders(int leaders) {
		this.leaders = leaders;
	}
	
	/**
	 * getter
	 * @return	followers	number of followers
	 */
	int getFollowers() {
		return followers;
	}
	
	/**
	 * setter
	 * @param	followers	set number of followers
	 */
	void setFollowers(int followers) {
		this.followers = followers;
	}

}
