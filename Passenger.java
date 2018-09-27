package russa_animado;

public class Passenger extends Thread{
    private int id_pass;
    private int time_boarding;
    private int time_landing;
    private boolean passAlive;

    public Passenger(int pass,int boarding, int landing) {
        this.id_pass = pass;
        this.time_boarding = boarding;
        this.time_landing = landing;
        this.start();
    }
    
    public boolean getPassengerAlive(){
        return passAlive;
    }
    
    public void setWagonAlive(boolean alive){
        this.passAlive = alive;
    }
    public void boarding() {
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() <= time + (time_boarding * 1000)){ }
    }

    public void landing() {
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() <= time + (time_landing * 1000)){ }
    }

    public void watching_landscape() {
        while(Roller_coaster.flag > 0){ }
    }

    public void passenger() throws InterruptedException{
        while(true) {
            Semaphores.passenger.acquire();
            Semaphores.mutex.acquire();
            Roller_coaster.boarding ++;
            Roller_coaster.log("Passageiro "+ id_pass + " embarcando!");
            boarding();
            Roller_coaster.log("Passageiro "+ id_pass + " embarcou!"); 
            if(Roller_coaster.boarding == Roller_coaster.capacity) {
                Semaphores.mutex.release();
                Semaphores.wagon.release(Roller_coaster.capacity);
            }
            else {
                Semaphores.mutex.release();
                Roller_coaster.log("Passageiro "+ id_pass +" dormindo no vagão!"); 
                Semaphores.wagon.acquire();	
            }
            Roller_coaster.log("Passageiro "+ id_pass + " observando paisagem!"); 
            Semaphores.mutex.acquire();
            watching_landscape();
            Semaphores.mutex.release();
            Roller_coaster.log("Passageiro "+ id_pass + ": fim da observação"); 
            Semaphores.mutex.acquire();
            Roller_coaster.log("Passageiro "+ id_pass + " desembarcando!"); 
            landing();
            Roller_coaster.log("Passageiro "+ id_pass + " desembarcou!"); 
            Semaphores.passenger.release();
            Roller_coaster.boarding --;
            Semaphores.mutex.release();		
        }
    }
    @Override
    public void run() {
        while(passAlive == true) {
            try {
                Roller_coaster.log("Passageiro "+ id_pass +" iniciando!"); 
                passenger();
            } catch (InterruptedException e) {
                System.out.println("Erro na thread passenger");
            }
        }
    }
}