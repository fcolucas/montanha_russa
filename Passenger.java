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
		while(System.currentTimeMillis() != time + (time_boarding * 1000)){ }
		
		/*Calendar date = Calendar.getInstance();
		int min = date.get(Calendar.MINUTE);
		int seg = date.get(Calendar.SECOND);
		int aux;
		if(seg + this.time_boarding > 60) {
			aux = this.time_boarding - (60-seg);
			while(true){
				date = Calendar.getInstance();
				if(date.get(Calendar.MINUTE) == min+1 && date.get(Calendar.SECOND) >= aux) {
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
	
	public void landing() {
		
		long time = System.currentTimeMillis();
		while(System.currentTimeMillis() != time + (time_landing * 1000)){ }
		
		
		/*Calendar date = Calendar.getInstance();
		int min = date.get(Calendar.MINUTE);
		int seg = date.get(Calendar.SECOND);
		int aux;
		if(seg + this.time_landing > 60) {
			aux = this.time_landing - (60-seg);
			while(true){
				date = Calendar.getInstance();
				if(date.get(Calendar.MINUTE) == min+1 && date.get(Calendar.SECOND) >= aux) {
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
	
	public void watching_landscape() {
		
		long time = System.currentTimeMillis();
		while(System.currentTimeMillis() != time + (roller_coaster.time_travel * 1000)){ }
		
		/*Calendar date = Calendar.getInstance();
		int min = date.get(Calendar.MINUTE);
		int seg = date.get(Calendar.SECOND);
		int aux;
		if(seg + roller_coaster.time_travel > 60) {
			aux = roller_coaster.time_travel - (60-seg);
			while(true){
				date = Calendar.getInstance();
				if(date.get(Calendar.MINUTE) == min+1 && date.get(Calendar.SECOND) >= aux) {
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
	
	public void passenger() throws InterruptedException{
		while(true) {
			semaphores.passenger.acquire();
			semaphores.mutex.acquire();
			roller_coaster.boarding ++;
			semaphores.log.acquire(); 
			System.out.println("Passageiro "+ id_pass + " embarcando!"); 
			semaphores.log.release();
			boarding();
			semaphores.log.acquire(); 
			System.out.println("Passageiro "+ id_pass + " embarcou!"); 
			semaphores.log.release();
			if(roller_coaster.boarding == roller_coaster.capacity) {
				semaphores.mutex.release();
				semaphores.log.acquire(); 
				System.out.println("Começa a viagem"); 
				semaphores.log.release();
				semaphores.wagon.release(roller_coaster.capacity);
			}
			else {
				semaphores.mutex.release();
				semaphores.log.acquire(); 
				System.out.println("Passageiro "+ id_pass +" dormindo no vagão!"); 
				semaphores.log.release();
				semaphores.wagon.acquire();	
			}
			semaphores.log.acquire(); 
			System.out.println("Passageiro "+ id_pass + " observando paisagem!"); 
			semaphores.log.release();
			watching_landscape();
			semaphores.log.acquire(); 
			System.out.println("Passageiro "+ id_pass + ": fim da observação"); 
			semaphores.log.release();
			semaphores.mutex.acquire();
			semaphores.log.acquire(); 
			System.out.println("Passageiro "+ id_pass + " desembarcando!"); 
			semaphores.log.release();
			landing();
			semaphores.log.acquire(); 
			System.out.println("Passageiro "+ id_pass + " desembarcou!"); 
			semaphores.log.release();
			semaphores.passenger.release();
			roller_coaster.boarding --;
			semaphores.mutex.release();		
			}
	}
	
	public void run() {
		while(true) {
			try {
				semaphores.log.acquire();
				System.out.println("Passageiro "+ id_pass +" iniciando!"); 
				semaphores.log.release();
				passenger();
			} catch (InterruptedException e) {
				System.out.println("Erro na thread passenger");
			}
		}
	}
}
