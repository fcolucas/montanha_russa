package russa_animado;

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
        Roller_coaster.log("Vagão iniciado!"); 
        while (wagonAlive){
            try {
                Semaphores.wagon.acquire(); 
                Roller_coaster.log("Vagão iniciando viagem!"); 
                Semaphores.mutex.acquire();
                Roller_coaster.flag++;
                Semaphores.mutex.release();
                traveling();
                Semaphores.mutex.acquire();
                Roller_coaster.flag--;
                Semaphores.mutex.release();
                Roller_coaster.log("Fim da viagem do vagao!");
            } catch(InterruptedException e) {
                System.out.println("Erro na thread wagon!");
            }
        }
        Roller_coaster.log("Vagão excluido!");
    }
}