package lab6;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
// TODO: Auto-generated Javadoc

/**
 * The Class ProducersConsumersDemo.
 */
public class ProducersConsumersDemo {
	
	/**
	 * Description: Lab6
	 * Create a Java project that shows a solution to the Leaders-Followers problem.
	 * he solution must be implemented as a Java(s) class that utilises only semaphores.
	 *
	 * @author 		Chi Ieong Ng C00223421
	 * @version 		1.0
	 * @param args the arguments
	 * @since 		2021-11-15
	 */
	

	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 * Main Driver
		 * Put 5 producers and 7 consumers into the threadpool for testing
		 */
		
		Buffer buffer = new Buffer();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		

		
		Runnable p1 = new Producers(1,buffer);
		Runnable p2 = new Producers(2,buffer);
		Runnable p3 = new Producers(3,buffer);
		Runnable p4 = new Producers(4,buffer);
		Runnable p5 = new Producers(5,buffer);
		Runnable c1 = new Consumers(buffer);
		Runnable c2 = new Consumers(buffer);
		Runnable c3 = new Consumers(buffer);
		Runnable c4 = new Consumers(buffer);
		Runnable c5 = new Consumers(buffer);
		Runnable c6 = new Consumers(buffer);
		Runnable c7 = new Consumers(buffer);
		
		pool.execute(p1);
		pool.execute(c1);
		pool.execute(c2);
		pool.execute(c3);
		pool.execute(p2);
		pool.execute(p3);
		pool.execute(c4);
		pool.execute(c5);
		pool.execute(p4);
		pool.execute(c6);
		pool.execute(c7);
		pool.execute(p5);
        pool.shutdown(); 	
		
	}

}


class Buffer {
	/**
	 * 10 space buffers, manage all semaphores
	 * 
	 */
	
	private int bufferSize = 10;
	private int count = 0;
	private Event[] buffer = new Event[bufferSize];
	Semaphore mutex = new Semaphore(1); //control access to buffer
	Semaphore items = new Semaphore(0); //blocks when buffer is empty
	Semaphore spaces = new Semaphore(buffer.length);
	
	Buffer() {
	}
	
	Buffer(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	
	void add(Event event) {
		if (this.count < this.bufferSize) {
			buffer[this.count] = event;
			this.count++;
		}
		else {
			System.out.println("Buffer full!");
		}
		
	}
	
	Event get() {
		Event event;
		if (this.count > 1) {
			event = buffer[0];
			for (int i = 0; i < count - 1; i++) {
				buffer[i] = buffer[i+1];
			}
			buffer[count - 1] = null;
			this.count--;
			
				
		}
		else if (this.count == 1) {
			
			event = buffer[0];	
			buffer[0] = null;
			this.count--;
		}
		else {
			event = null;
		}
		return event;
	}
	
	int remaining() {
		return this.bufferSize;
	}
	
	int space() {
		return this.bufferSize - this.count;
	}
	
}


class Event {
	/**
	 * Manage events and schedule events to do
	 */
	
	int num = 0;
	
	Event(int num) {
		this.num = num;
		System.out.println("Event(" + this.num + ") is created!");
	}
	
	void process() {
		System.out.println("Event(" + this.num + ") is processed!");
	}
}


class Producers implements Runnable{
	/**
	 * Manage producers and give request semaphores to do
	 */
	
	Buffer buffer;
	int eventNum;
	
	Producers(int eventNum, Buffer buffer) {
		this.eventNum = eventNum;
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		try {
			Event event = new Event(eventNum);
			Thread.sleep(1000);
			buffer.spaces.acquire();
			buffer.mutex.acquire();
			buffer.add(event);
			buffer.mutex.release();
			buffer.items.release();		
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		
		
		
	}
	
	
}

class Consumers implements Runnable{
	/**
	 * Manage producers and give request semaphores to do
	 */
	
	Buffer buffer;
	Event event;
	
	Consumers(Buffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		try {
			buffer.items.acquire();
			buffer.mutex.acquire();
			event = buffer.get();
			buffer.mutex.release();
			buffer.spaces.release();
			event.process();
			Thread.sleep(1000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
