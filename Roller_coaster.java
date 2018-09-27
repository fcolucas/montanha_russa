package russa_animado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Roller_coaster extends Application {
    public static int capacity, time_travel, boarding = 0, flag = 0, id_passenger = 0; 
    public static final int CAP_MAXIMA = 5, FILA_MAXIMA = 10;
    static ArrayList<Passenger> passengers = new ArrayList();

    public static void log(String s){
        try {
            Semaphores.log.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("(" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + 
        LocalDateTime.now().getSecond() + ") " + s); 
        Semaphores.log.release();
    }
    
    static Wagon create_wagon(int capacity, int time_travel){
        Wagon w = new Wagon(capacity, time_travel);
        w.setWagonAlive(true);
        return w;
    }
    
    static Passenger create_passenger(int time_boarding, int time_landing){
        Passenger p = new Passenger(id_passenger, time_boarding, time_landing);
        p.setPassAlive(true);
        passengers.add(p);
        id_passenger++;
        return p;
    }
    
    static void exclude_wagon(Wagon w){
        w.setWagonAlive(false);
    }
    
    static void exclude_passenger(Passenger p){
        p.setPassAlive(false);
        passengers.remove(p);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        
        time_travel = 15;
        capacity = 2;
        Wagon w = create_wagon(capacity, time_travel);
        Semaphores.passenger = new Semaphore(capacity, true);
        Passenger p1 = create_passenger(2, 5);
        Passenger p2 = create_passenger(3, 4);
        Passenger p3 = create_passenger(4, 7);
        //launch(args);
    }
}
