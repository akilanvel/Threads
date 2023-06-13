// Name: Akilan Gnanavel
// NET ID: AXG180113
// Professor: Dr. Salazar
// Course: CS 4348.003

import java.util.concurrent.Semaphore;

public class Kitchen {
	Semaphore semaphore; // The semaphore that allows a thread to use

	// Constructor
	public Kitchen(int num) {
		this.semaphore = new Semaphore(num);
	}

	// Access method that allows a thread to use the kitchen
	public void access() {
		try {
			semaphore.acquire();
		} catch (Exception e) {
		}
	}

	// Release method for when the thread is done with the kitchen
	public void release() {
		try {
			semaphore.release();
		} catch (Exception e) {

		}
	}
}
