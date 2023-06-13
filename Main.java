// Name: Akilan Gnanavel
// NET ID: AXG180113
// Professor: Dr. Salazar
// Course: CS 4348.003

import java.util.HashMap;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		// Create the table objects (resources)
		Table tableA = new Table("A", 4);
		Table tableB = new Table("B", 4);
		Table tableC = new Table("C", 4);

		// Create register, kitchen, and door objects (resources)
		Register cashRegister = new Register(1);
		Kitchen kitchen = new Kitchen(1);
		Door door = new Door(2);
		HashMap<Integer, Table> map = new HashMap<Integer, Table>();
		map.put(0, tableA);
		map.put(1, tableB);
		map.put(2, tableC);
		map.put(-1, null);

		// Create the waiter objects and assign them to a table
		Waiter waiterA = new Waiter(tableA, 0, kitchen, door);
		Waiter waiterB = new Waiter(tableB, 1, kitchen, door);
		Waiter waiterC = new Waiter(tableC, 2, kitchen, door);

		// Start the three waiter threads
		waiterA.start();
		waiterB.start();
		waiterC.start();

		// Create a 40-customer array and create each customer with table preferences
		Customer customers[] = new Customer[40];
		for (int i = 0; i < customers.length; i++) {
			Random rand = new Random();
			int num = rand.nextInt(3);
			int secondNum = rand.nextInt(100);
			if (secondNum > 95) {
				do {
					secondNum = rand.nextInt(3);
				} while (secondNum == num);
			} else {
				secondNum = -1;
			}
			customers[i] = new Customer(cashRegister, map.get(num), map.get(secondNum), i, waiterA, waiterB, waiterC,
					door);
		}

		// Start every single customer thread
		for (int i = 0; i < customers.length; i++) {
			customers[i].start();
		}
	}
}
