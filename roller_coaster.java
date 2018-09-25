package montanha_russa;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

public class roller_coaster {
	
	static int capacity, time_travel, boarding = 0, flag = 0;
	
	static void log(String s){
		try {
			semaphores.log.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("(" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + 
		LocalDateTime.now().getSecond() + ") " + s); 
		semaphores.log.release();
	}
	
	public static void main(String[] args) {
		time_travel = 15;
		capacity = 2;
		Wagon w1 = new Wagon(capacity, time_travel);
		semaphores.passenger = new Semaphore(capacity, true);
		Passenger p1 = new Passenger(1, 2, 2);
		Passenger p2 = new Passenger(2, 3, 3);
		Passenger p3 = new Passenger(3, 4, 4);
	}
}
