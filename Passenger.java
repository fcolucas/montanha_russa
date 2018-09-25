package montanha_russa;

public class Passenger extends Thread{

	private int id_pass;
	private int time_boarding;
	private int time_landing;
	
	public Passenger(int pass,int boarding, int landing) {
		this.id_pass = pass;
		this.time_boarding = boarding;
		this.time_landing = landing;
		this.start();
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
		while(roller_coaster.flag > 0){ }
	}
	
	public void passenger() throws InterruptedException{
		while(true) {
			semaphores.passenger.acquire();
			semaphores.mutex.acquire();
			roller_coaster.boarding ++;
			roller_coaster.log("Passageiro "+ id_pass + " embarcando!");
			boarding();
			roller_coaster.log("Passageiro "+ id_pass + " embarcou!"); 
			if(roller_coaster.boarding == roller_coaster.capacity) {
				semaphores.mutex.release();
				roller_coaster.log("Começa a viagem"); 
				semaphores.wagon.release(roller_coaster.capacity);
			}
			else {
				semaphores.mutex.release();
				roller_coaster.log("Passageiro "+ id_pass +" dormindo no vagão!"); 
				semaphores.wagon.acquire();	
			}
			roller_coaster.log("Passageiro "+ id_pass + " observando paisagem!"); 
			semaphores.mutex.acquire();
			watching_landscape();
			semaphores.mutex.release();
			roller_coaster.log("Passageiro "+ id_pass + ": fim da observação"); 
			semaphores.mutex.acquire();
			roller_coaster.log("Passageiro "+ id_pass + " desembarcando!"); 
			landing();
			roller_coaster.log("Passageiro "+ id_pass + " desembarcou!"); 
			semaphores.passenger.release();
			roller_coaster.boarding --;
			semaphores.mutex.release();		
			}
	}
	@Override
	public void run() {
		while(true) {
			try {
				roller_coaster.log("Passageiro "+ id_pass +" iniciando!"); 
				passenger();
			} catch (InterruptedException e) {
				System.out.println("Erro na thread passenger");
			}
		}
	}
}
