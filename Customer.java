// Name: Akilan Gnanavel
// NET ID: AXG180113
// Professor: Dr. Salazar
// Course: CS 4348.003

import java.util.HashMap;
import java.util.Random;

public class Customer extends Thread {
	private Table primaryTable; // Table of primary choice
	private Table secondaryTable; // Table of second choice
	private Register register; // Cash register to pay at
	private int id; // ID of the customer
	public Waiter waiter; // The waiter of the table that the customer sits at
	private HashMap<String, Waiter> map;
	private static int waiterCalls = 0;
	private Door door; // Door to use to enter/exit

	// Constructor
	public Customer(Register r, Table t1, Table t2, int i, Waiter waiterA, Waiter waiterB, Waiter waiterC, Door d) {
		this.register = r;
		this.primaryTable = t1;
		this.secondaryTable = t2;
		this.id = i;
		map = new HashMap<>();
		map.put("A", waiterA);
		map.put("B", waiterB);
		map.put("C", waiterC);
		this.door = d;
	}

	// Execution method of the thread
	public void run() {
		boolean secondary = false;
		Random rand = new Random();

		// Customer's preliminary information
		System.out.println("Customer " + id + " wants to eat at Table " + primaryTable);
		if (hasSecondChoice()) {
			System.out.println("Customer " + id + " has second choice: Table " + secondaryTable);
		} else {
			System.out.println("Customer " + id + " does not have a second choice");
		}

		// Customer enters the restaurant
		System.out.println("Customer " + id + " wants to enter the restaurant");
		door.access();
		System.out.println("Customer " + id + " enters the restaurant");
		door.release();

		// Customer decides which table/line to join
		System.out.println("Customer " + id + " goes to Table " + primaryTable);
		if (primaryTable.getLine() > 6) {
			System.out.println("Line is too long, customer " + id + " checks second choice to see if it is open");
			if (hasSecondChoice() && secondaryTable.getLine() > 6) {
				System.out.println("Customer " + id + " decides to just stand in line for Table " + primaryTable);
			} else if (hasSecondChoice() && secondaryTable.getLine() <= 6) {
				System.out.println("Customer " + id + " stands in line for Table " + secondaryTable);
				secondary = true;
			} else if (!hasSecondChoice()) {
				System.out.println("Customer " + id + " does not have a second choice, so he stands in line for Table "
						+ primaryTable);
			}
		} else {
			System.out.println("Customer " + id + " stands in line for Table " + primaryTable);
		}

		// Change the actions slightly based on whether we are at the first choice table
		if (!secondary) {
			waiter = map.get(primaryTable.toString());
			primaryTable.access();
			System.out.println("Customer " + id + " sits at Table " + primaryTable);

			// Customer interacts with waiter and then eats
			System.out.println("Customer " + id + " calls for the waiter");
			waiter.access(this);
			waiter.release();
			waiterCalls++;
			System.out.println("Customer " + id + " eats");
			int waitTime = rand.nextInt(800) + 201;
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Customer " + id + " is done eating");
			primaryTable.release();
		} else {
			waiter = map.get(secondaryTable.toString());
			secondaryTable.access();
			System.out.println("Customer " + id + " sits at Table " + secondaryTable);

			// Customer interacts with waiter and then eats
			System.out.println("Customer " + id + " calls for the waiter");
			waiter.access(this);
			waiter.release();
			waiterCalls++;
			int waitTime = rand.nextInt(800) + 201;
			System.out.println("Customer " + id + " eats");
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Customer " + id + " is done eating");
			secondaryTable.release();
		}

		// Customer goes to pay for meal
		System.out.println("Customer " + id + " goes to pay for meal");
		register.access();
		System.out.println("Customer " + id + " pays for the meal");
		register.release();

		// Customer leaves the restaurant
		door.access();
		System.out.println("Customer " + id + " leaves the restaurant");
		door.release();
		if (waiterCalls >= 40) {
			map.get("A").clean();
			map.get("B").clean();
			map.get("C").clean();
		}
	}

	public Table getFirstChoice() {
		return primaryTable;
	}

	public boolean hasSecondChoice() {
		return secondaryTable != null;
	}

	public int getID() {
		return id;
	}
}
