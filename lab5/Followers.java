package lab5;

/**
 * Class Followers
 * @author			Chi Ieong Ng C00223421
 * 
 */

public class Followers implements Runnable{
	
	/**
	 * Followers queue
	 * 
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
			queue.mutex.acquire();
			
			//System.out.println("Follower(" + this.name + ")'s Leaders: " + queue.getLeaders());
			//System.out.println("Follower(" + this.name + ")'s Followers: " + queue.getFollowers());
			
			
			if (queue.getLeaders() > 0) {
				
				//System.out.println("Followers(" + this.name + ") if ");
				
				queue.setLeaders(queue.getLeaders() - 1);
				queue.leadersQ.release();
				
			}
			else {
				
				//System.out.println("Followers(" + this.name + ") else");
				
				queue.setFollowers(queue.getFollowers() + 1);
				queue.mutex.release();
				queue.followersQ.acquire();
				
			}
			
			dance();
			Thread.sleep(1000);
			
			//System.out.println("Final Follower(" + this.name + ")'s Leaders: " + queue.getLeaders());
			//System.out.println("Final Follower(" + this.name + ")'s Followers: " + queue.getFollowers());
			
			
			queue.rendezvous.release();
			

	    }
		catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	
}
