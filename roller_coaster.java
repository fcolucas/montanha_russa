package montanha_russa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class roller_coaster {
    public static int capacity, time_travel, boarding = 0, flag = 0, id_passenger = 0; 
    public static final int CAP_MAXIMA = 5, FILA_MAXIMA = 10;
    static ArrayList<Passenger> passengers = new ArrayList();

    public static void log(String s){
        try {
            semaphores.log.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("(" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + 
        LocalDateTime.now().getSecond() + ") " + s); 
        semaphores.log.release();
    }
    
    static Wagon create_wagon(int capacity, int time_travel){
        Wagon w = new Wagon(capacity, time_travel);
        w.setWagonAlive(true);
        return w;
    }
    
    static Passenger create_passenger(int time_boarding, int time_landing){
        Passenger p = new Passenger(id_passenger, time_boarding, time_landing);
        p.setWagonAlive(true);
        passengers.add(p);
        id_passenger++;
        return p;
    }
    
    void exclude_wagon(Wagon w){
        w.setWagonAlive(false);
    }
    
    void exclude_passenger(Passenger p){
        p.setWagonAlive(false);
        passengers.remove(p);
    }
    
    public static void main(String[] args) {
        time_travel = 15;
        capacity = 2;
        Wagon w = create_wagon(capacity, time_travel);
        semaphores.passenger = new Semaphore(capacity, true);
        Passenger p1 = create_passenger(2, 2);
        Passenger p2 = create_passenger(3, 3);
        Passenger p3 = create_passenger(4, 4);
    }
}
