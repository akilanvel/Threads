// Name: Akilan Gnanavel
// NET ID: AXG180113
// Professor: Dr. Salazar
// Course: CS 4348.003

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Waiter extends Thread {
	private Table table; // Table that the waiter is assigned to
	private int id; // ID to label the waiter
	Semaphore semaphore; // The semaphore that customers can access
	private Kitchen kitchen; // Kitchen that the waiter delivers orders to
	private Door door; // Door that is used to leave/enter the restaurant

	// Constructor
	public Waiter(Table t, int i, Kitchen k, Door d) {
		this.table = t;
		this.id = i;
		this.semaphore = new Semaphore(1);
		this.kitchen = k;
		this.door = d;
	}

	// Execution method for the thread
	public void run() {
		System.out.println("Waiter " + id + " is assigned to Table " + table);
	}

	// Access method that allows a customer to start interacting with the waiter
	public void access(Customer customer) {
		try {
			semaphore.acquire();
			this.perform(customer);
		} catch (InterruptedException e) {

		}
	}

	// Release method for when the waiter is done helping a customer
	public void release() {
		semaphore.release();
	}

	// This method performs the waiter's duties to each customer
	public void perform(Customer customer) {
		// Waiter takes customer's order
		System.out.println("Waiter " + id + " goes to Table " + table);

		// Waiter goes to kitchen to deliver customer's order
		System.out
				.println("Waiter " + id + " goes to the kitchen to deliver Customer " + customer.getID() + "'s order");
		kitchen.access();

		// Generate a random wait time (according to instructions)
		Random rand = new Random();
		int waitTime = rand.nextInt(400) + 101;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {

		}

		// Waiter leaves kitchen to wait for order
		kitchen.release();
		System.out.println("Waiter " + id + " exits the kitchen and waits");

		// Generate a random wait time (according to instructions)
		waitTime = rand.nextInt(700) + 301;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {

		}

		// Waiter re-enters kitchen to get customer's order
		kitchen.access();
		System.out.println("Waiter " + id + " enters the kitchen to pick up Customer " + customer.getID() + "'s food");

		// Generate a random wait time (according to instructions)
		waitTime = rand.nextInt(400) + 101;
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {

		}

		// Waiter delivers the food to customer
		kitchen.release();
		System.out.println("Waiter " + id + " exits the kitchen with Customer " + customer.getID() + "'s food");
		System.out.println("Waiter " + id + " brings food to Customer " + customer.getID());
	}

	// This function makes the waiter exit the restaurant once all customers leave
	public void clean() {
		door.access();
		System.out.println("Waiter " + id + " cleans Table " + table + " and leaves the restaurant");
		door.release();
	}
}
