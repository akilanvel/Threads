Name: Akilan Gnanavel
NETID: AXG180113
CS 4348.003 Project 02
Professor: Dr. Elmer Salazar


Files contained and their purpose:

-Main.java is the main class of the program, which creates the Customer and Waiter threads and gets the restaurant simulation started
-Table.java is the Java file for the Table resource, which Customers use to sit at
-Register.java is the Java file for the Cash Register resource, which Customers use to pay for their meal
-Kitchen.java is the Java file for the Kitchen resource, which Employees use to deliver the order and pick it up
-Door.java is the Java file for the Door resource, which Customers and Employees use to enter/leave the restaurant
-Customer.java is the Java file for Customer threads
-Waiter.java is the Java file for Waiter threads


How to compile and run the project:

In the UTD CS machine on giant.utdallas.edu, after inserting the files into the same directory, I performed these commands in order to get the program running successfully:
1. javac Main.java
2. javac Table.java
3. javac Register.java
4. javac Kitchen.java
5. javac Door.java
6. javac Customer.java
7. javac Waiter.java
8. java Main


Notes:

As the professor mentioned, the output does not follow the order in which the threads started, so the output order for Customers is not linear. However, there are no errors in the order of logic. For example, a waiter will not give an order to a customer before the customer has even sat down at the table.
As the professor mentioned, I used probability to determine whether the customer has a second-choice table. There is about a 95% chance that the customer does not have a second choice.


Thank you very much for your time and have a wonderful day!