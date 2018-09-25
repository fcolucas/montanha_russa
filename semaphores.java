package montanha_russa;

import java.util.concurrent.Semaphore;

public class semaphores {
	static Semaphore wagon = new Semaphore(0, true);
	static Semaphore mutex = new Semaphore(1, true);
	static Semaphore passenger; //capacidade maxima
	static Semaphore log = new Semaphore(1, true);
}
