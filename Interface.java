/**
 * @author 	Adham Khalifa
 *			Alejandro Garibo
 * 			Gabe Crane 
 	COM212 Final Project
 * Submitted on 05/13/2020
 * This project consists of :
 * -This file "interface": it includes the main user options.
 * -MySocialProfile class: it reads, writes, scans for files.
 * -ArrayQueue: it includes the implementation of the priority queue.
 * -SinglyLinkedList: it includes the implementation of the SLinked List.
 * -Event: creating the Event class.
 */


 	import java.io.IOException;
 	import java.util.Scanner;

 	public class Interface {

	static Scanner myObj = new Scanner(System.in);				//scanning user input in myObj
	
	
	  /**
	   * 
	   * 
	   * This is the main program method.
	   */

	  public static void program() throws IOException {

	  	System.out.println();


		/*
		 * From the main menu prompt, the user can either:
		1. Create a new account/profile,
		2. Load an existing profile (if one exists in the file), or
		3. Exit the entire program.
		 * 
		 */

		MySocialProfile profile = new MySocialProfile(myObj);

		System.out.println("Welcome to your social profile!" + "\n" + "Press the number of your choice." + "\n" + "Options:"
			+ "\n" + "1. Create a new account/profile." + "\n" + "2. Load an existing profile."
			+ "\n" + "3. Exit the entire program." + "\n");
		System.out.print("Choice: ");

		int choice =Integer.parseInt( myObj.nextLine() );

		switch(choice) {
			case 1:
			profile.createAccount();
			profile.secondary();
			break;
			case 2:
			profile.loadExisting();	
			profile.secondary();
			break;
			case 3:
			profile.exit();
			break;
		}
	} 


	public static void main(String[] args) throws IOException {

		while(true)
		{
			program();
		}
	}

}
