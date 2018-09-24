package montanha_russa;

import java.util.Calendar;

public class Wagon extends Thread {
	
	private int number_chairs;
	private int time_travel;
	
	public Wagon(int chairs, int time) {
		this.number_chairs = chairs;
		this.time_travel = time;
		this.start();
	}
	
	public void traveling(){
		
		long time = System.currentTimeMillis();
		while(System.currentTimeMillis() != time + (this.time_travel * 1000)){ }
		
		/*Calendar date = Calendar.getInstance();
		int min = date.get(Calendar.MINUTE);
		int seg = date.get(Calendar.SECOND);
		int aux;
		if(seg + this.time_travel > 60) {
			aux = this.time_travel - (60-seg);
			while(true){
				date = Calendar.getInstance();
				if(date.get(Calendar.MINUTE) == min+1 && date.get(Calendar.SECOND) >= aux) {
					System.out.println("Fim da Viagem");
					break;
				}
			}
		}
		else {
			while(true) {
				date = Calendar.getInstance();
				if(date.get(Calendar.SECOND) >= seg || date.get(Calendar.MINUTE) > min) {
					break;
				}
			}
		}*/
	}
	
	public void wagon() throws InterruptedException{
		while(true) {
			semaphores.wagon.acquire();
			semaphores.log.acquire(); 
			System.out.println("Vagão iniciando viagem!"); 
			semaphores.log.release();
			traveling();
			semaphores.log.acquire(); 
			System.out.println("Fim da viagem do vagão!"); 
			semaphores.log.release();
		}
	}	
	
	public void run() {
		while (true) {
			try {
				semaphores.log.acquire(); 
				System.out.println("Vagão iniciando!"); 
				semaphores.log.release();
				
				wagon();
			} catch(InterruptedException e) {
				System.out.println("Erro na thread wagon!");
			}
		}
	}
}
