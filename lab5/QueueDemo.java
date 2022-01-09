package lab5;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: Lab5
 * Create a Java project that shows a solution to the Leaders-Followers problem.
 * he solution must be implemented as a Java(s) class that utilises only semaphores.
 * @author			Chi Ieong Ng C00223421
 * @version			1.0
 * @since			2021-11-15
 *
 */


public class QueueDemo {
	
	/**
	 * This is main driver for managing all the threads and pool.
	 * @param	args	default
	 */
	public static void main(String[] args) {
		/**
		 * Create a queues and threads as 4 followers and 4 leaders
		 * Create a pool and execute all the threads
		 */
		
		Queues q = new Queues();
		Runnable p1 = new Leaders("John",q);
		Runnable p2 = new Leaders("Tommy",q);
		Runnable p3 = new Leaders("Chris",q);
		Runnable p4 = new Leaders("Robert",q);
		Runnable p5 = new Followers("Annie",q);
		Runnable p6 = new Followers("Mary",q);
		Runnable p7 = new Followers("Susan",q);
		Runnable p8 = new Followers("Michelle",q);
		ExecutorService pool = Executors.newFixedThreadPool(8);
		pool.execute(p1);
		pool.execute(p2);
		pool.execute(p3);
		pool.execute(p4);
		pool.execute(p5);
		pool.execute(p6);
		pool.execute(p7);
		pool.execute(p8);
        pool.shutdown(); 
	}

}
