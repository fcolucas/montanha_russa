package montanha_russa;

public class roller_coaster {
	
	static int capacity, time_travel, boarding = 0 ;
	
	public static void main(String[] args) {
		time_travel = 15;
		capacity = 2;
		Wagon w1 = new Wagon(capacity, time_travel);
		Passenger p1 = new Passenger(1, 2, 2);
		Passenger p2 = new Passenger(2, 3, 3);
	}
}
