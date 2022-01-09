package lab7;

import java.util.concurrent.Semaphore;
//TODO: Auto-generated Javadoc

/**
* The Class DiningPhilosopher.
*/


public class DiningPhilosopher {
	
	/**
	 * Description: Lab7
	 * Create a Java project that shows a solution to theDining philosophers problem.
	 * The solution must be implemented as one or more Java classes that utilises only semaphores. 
	 *
	 * @author 		Chi Ieong Ng C00223421
	 * @version 		1.0
	 * @param args the arguments
	 * @since 		2021-11-29
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * Main Driver
		 * there has 5 Philosophers and 5 forks and will testing to eat in 20 rounds 
		 */
		
		Think think = new Think();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 5; j++) {
				get_forks(j, think);
				eat(j);
				put_forks(j, think);
			}
			
			
		}
	}
	
	static void get_forks(int j, Think think) {
		/**
		 * give request to semaphores (footman and fork) to wait
		 * 
		 */
		try {
			int left = j;
			int right = j+1;
			if (j >= 4) right = 0;
			think.footman.acquire();
			think.fork[left].acquire();
			think.fork[right].acquire();	
			System.out.println("Philosopher(" + j + ") pick up fork(" + left + ") & fork(" + right + ")!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	static void put_forks(int j, Think think) {
		/**
		 * give request to semaphores (footman and fork) to signal
		 * 
		 */
		try {
			int left = j;
			int right = j+1;
			if (j >= 4) right = 0;
			think.fork[left].release();
			think.fork[right].release();	
			think.footman.release();
			System.out.println("Philosopher(" + j + ") put down fork(" + left + ") & fork(" + right + ")!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	static void eat(int j) {
		/**
		 * print out who going to eat
		 * 
		 */
		System.out.println("Philosopher(" + j + ") eat!");
	}
	
}

class Think {
	/**
	 * manages all the semaphores
	 * 
	 */
	
	Semaphore footman = new Semaphore(4);
	Semaphore[] fork = new Semaphore[] {new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1)};

	Think() {	
	}
}

