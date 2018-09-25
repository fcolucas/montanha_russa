package montanha_russa;

public class Wagon extends Thread {

    private int number_chairs;
    private int time_travel;
    private boolean wagonAlive;

    public Wagon(int chairs, int time) {
        this.number_chairs = chairs;
        this.time_travel = time;
        this.start();
    }

    public boolean getWagonAlive(){
        return wagonAlive;
    }
    
    public void setWagonAlive(boolean alive){
        this.wagonAlive = alive;
    }
    
    public void traveling(){
        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() <= time + (this.time_travel * 1000)){ }
    }

    @Override	
    public void run() {
        roller_coaster.log("Vagão iniciando!"); 
        while (wagonAlive){
            try {
                semaphores.wagon.acquire(); 
                roller_coaster.log("Vagao iniciando viagem!"); 
                semaphores.mutex.acquire();
                roller_coaster.flag++;
                traveling();
                roller_coaster.flag--;
                semaphores.mutex.release();
                roller_coaster.log("Fim da viagem do vagao!");
            } catch(InterruptedException e) {
                System.out.println("Erro na thread wagon!");
            }
        }
        roller_coaster.log("Vagão excluido!");
    }
}
