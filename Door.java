// Name: Akilan Gnanavel
// NET ID: AXG180113
// Professor: Dr. Salazar
// Course: CS 4348.003

import java.util.concurrent.Semaphore;

public class Door {
	Semaphore semaphore; // The semaphore that allows a thread to use

	// Constructor
	public Door(int num) {
		this.semaphore = new Semaphore(num);
	}

	// Access method that allows a thread to use the door
	public void access() {
		try {
			semaphore.acquire();
		} catch (Exception e) {
		}
	}

	// Release method for when the thread is done with the door
	public void release() {
		try {
			semaphore.release();
		} catch (Exception e) {

		}
	}
}
