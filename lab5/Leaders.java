package lab5;

/**
 * Class Leaders
 * @author			Chi Ieong Ng C00223421
 * 
 */

public class Leaders implements Runnable{
	
	/**
	 * Leaders queue
	 * 
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
		//name++;
	}
	
	@Override
	public void run() {
		try {
			queue.mutex.acquire();
			
			//System.out.println("Leader(" + this.name + ")'s Leaders: " + queue.getLeaders());
			//System.out.println("Leader(" + this.name + ")'s Followers: " + queue.getFollowers());

			if (queue.getFollowers() > 0) {
				//System.out.println("Leaders(" + this.name + ") if");
				
				queue.setFollowers(queue.getFollowers() - 1);
				queue.followersQ.release();
				
			}
			
			else {
				//System.out.println("Leaders(" + this.name + ") else");
				
				queue.setLeaders(queue.getLeaders() + 1);
				queue.mutex.release();
				queue.leadersQ.acquire();
				
			}
			
			dance();
			Thread.sleep(1000);
			
			//System.out.println("Final Leader(" + this.name + ")'s Leaders: " + queue.getLeaders());
			//System.out.println("Final Leader(" + this.name + ")'s Followers: " + queue.getFollowers());
			
			
			queue.rendezvous.acquire();
			queue.mutex.release();
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
