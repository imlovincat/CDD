package lab5;

/**
 * Class Leaders
 * @author			Chi Ieong Ng C00223421
 * @version			1.0
 * @since			2021-11-15
 */

public class Leaders implements Runnable{
	
	/**
	 * Leaders queue
	 */
	
	private String name;
	private Queues queue;
	
	Leaders(String name, Queues queue) { 
		this.name = name;
		this.queue = queue;
	}
	
	/**
	 * getter
	 * @return	Leader name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * setter
	 * @param	name	leader's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * leader dance
	 * 
	 */
	public void dance() {
		System.out.println("Leader " + this.getName() + " dances!");
	}
	
	@Override
	public void run() {
		/**
		 * run
		 */
		try {
			queue.mutex.acquire();
			if (queue.getFollowers() > 0) {	
				queue.setFollowers(queue.getFollowers() - 1);
				queue.followersQ.release();	
			}
			
			else {
				queue.setLeaders(queue.getLeaders() + 1);
				queue.mutex.release();
				queue.leadersQ.acquire();
				
			}
			dance();
			Thread.sleep(1000);
			queue.rendezvous.acquire();
			queue.mutex.release();	
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
