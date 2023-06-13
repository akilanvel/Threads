// Name: Akilan Gnanavel
// NET ID: AXG180113
// Professor: Dr. Salazar
// Course: CS 4348.003

import java.util.concurrent.Semaphore;

public class Register {
	Semaphore semaphore; // The semaphore that allows a thread to use

	// Constructor
	public Register(int num) {
		this.semaphore = new Semaphore(num);
	}

	// Access method that allows a thread to use the register
	public void access() {
		try {
			semaphore.acquire();
		} catch (Exception e) {
		}
	}

	// Release method that allows a thread to release the register
	public void release() {
		try {
			semaphore.release();
		} catch (Exception e) {

		}
	}
}
