package lab5;

/**
 * Class Followers
 * @author			Chi Ieong Ng C00223421
 * @version			1.0
 * @since			2021-11-15
 */

public class Followers implements Runnable{
	
	/**
	 * Followers queue
	 */
	
	private String name;
	private Queues queue;

	Followers(String name, Queues queue) {
		this.name = name;
		this.queue = queue;
	}
	
	/**
	 * getter
	 * @return	Follower name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter
	 * @param	name	follower's name
	 */
	public void setName(String name) {

		this.name = name;
	}
	
	
	/**
	 * follower dance 
	 */
	public void dance() {
		
		System.out.println("Follower " + this.name + " dances!");
	}
	
	@Override
	public void run() {
		try{
			/**
			 * run
			 */
			queue.mutex.acquire();
			if (queue.getLeaders() > 0) {
				queue.setLeaders(queue.getLeaders() - 1);
				queue.leadersQ.release();	
			}
			else {
				queue.setFollowers(queue.getFollowers() + 1);
				queue.mutex.release();
				queue.followersQ.acquire();
				
			}
			dance();
			Thread.sleep(1000);
			queue.rendezvous.release();
	    }
		catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	
}
