package montanha_russa;

import java.util.concurrent.Semaphore;

public class semaphores {
	static Semaphore wagon = new Semaphore(0);
	static Semaphore mutex = new Semaphore(1);
	static Semaphore passenger = new Semaphore(2);
	static Semaphore log = new Semaphore(1);
}
