// Name: Akilan Gnanavel
// NET ID: AXG180113
// Professor: Dr. Salazar
// Course: CS 4348.003

import java.util.concurrent.Semaphore;

public class Table {
	private String label; // The letter describing the table
	Semaphore semaphore; // The semaphore that allows a thread to use
	private int line; // The length of the queue for the semaphore

	// Constructor
	public Table(String type, int num) {
		this.label = type;
		this.semaphore = new Semaphore(num);
		this.line = 4;
	}

	// Access method that assigns a semaphore to a thread and keeps track of queue
	public void access() {
		try {
			if (semaphore.availablePermits() == 0) {
				line++;
			} else {
				line--;
			}
			semaphore.acquire();
		} catch (Exception e) {

		}
	}

	// Release method that releases the semaphore
	public void release() {
		try {
			semaphore.release();
		} catch (Exception e) {

		}
	}

	@Override
	public String toString() {
		return this.label;
	}

	public int getLine() {
		return line;
	}

	public void addToLine() {
		line++;
	}

	public void removeFromLine() {
		line--;
	}
}
