
/**
 @author 	Adham Khalifa
 *			Alejandro Garibo
 * 			Gabe Crane
 * 5/13/20
 * COM212
 * Final Project - MySocialProfile Class

 -Does the IO files operations.
 */

 import java.util.Scanner;


 import java.io.IOException;
 import java.time.LocalDateTime;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.util.Arrays;


	/**
	MySocialProfile class
	It does the File IO operations and scans from the user.
 */
	public class MySocialProfile {

		static String info[] = new String[3];
		public static int year;
		Scanner myObj = new Scanner(System.in); 							//Scanning the user input into myObj
		
		
		SinglyLinkedList<String> friends = new SinglyLinkedList<String>();	//Friends SLL
		
		SinglyLinkedList<String> posts = new SinglyLinkedList<String>();	//Posts SLL
		
		ArrayQueue events = new ArrayQueue(100000);							

		public MySocialProfile(Scanner myObj2) {
			myObj = myObj2;
		}
		
		/**
		Updates information and appends it to the mysocialprofile.txt
	 	*/
		public void updateFile() throws IOException
		{
			FileWriter fileWriter = new FileWriter("mysocialprofile.txt"); 
			fileWriter.append(info[0] + "\n"); 				//name
			fileWriter.append(info[1] + "\n");				//user
			fileWriter.append(info[2] + "\n");				//pass
			fileWriter.append(year + "\n");					//year
			

			//iterating over events and adding them
			for(int i=1; i <= events.size(); i++) {
				fileWriter.append('"' + events.getAll()[i].getString() + '"' + ",");
			} 
			fileWriter.append("\n");
			
			//iterating over posts and apeending them to the file
			Object[] arr_post = posts.toArray();
			for(int i=0;i<posts.size();i++) {
				fileWriter.append('"' + arr_post[i].toString() + '"' + ",");
			}
			fileWriter.append("\n");
			
			//doing the same for friends
			Object[] arr_friend = friends.toArray();
			for(int i=0;i<friends.size();i++) {
				fileWriter.append('"' + arr_friend[i].toString() + '"' + ",");
			}
			fileWriter.append("\n");
			
			
			fileWriter.close();
		}

	/**
	Allows the user to create a new account
	 */
	public void createAccount() throws IOException{
		
	/*
	 *If the user chooses to create a new account, they will be prompted for their info and a
	new MySocialProfile will be created for them.
	*/		



	System.out.print("Name: ");
	info[0] = 	myObj.nextLine();
	System.out.print("Username/Email: ");
	info[1] = 	myObj.nextLine(); 
	System.out.print("Password: ");
	info[2] = 	myObj.nextLine(); 
	System.out.print("Year: ");
	year =		myObj.nextInt();
	myObj.nextLine();


		//Events part

	while(true) {
		System.out.print("Events (mm dd year hh mm title) (Write N to stop adding) : ");
		String event = myObj.nextLine();
		if(event.equals("N")) {
			break;
		}
		Event ev = Event.parse(event, false);

		if(ev.Time.isAfter(LocalDateTime.now())) {
			events.insert(ev);
		}
	}



		//Posts part
	
	while(true) {
		System.out.print("Posts (Write N to stop adding) : ");
		String post = myObj.nextLine();
		if(post.equals("N")) {
			break;
		}
		posts.insertAtTail(post);


	}





		//Friendspart

	while(true) {
		System.out.print("Friends (Write N to stop adding) : ");
		String friend = myObj.nextLine();
		if(friend.equals("N")) {
			break;
		}
		friends.insertAtTail(friend);


	}


	   	 loadExisting();					//Allows the user to load an existing user information then.

	   	}
	   	
	   	public void loadExisting() {

		/*
		 * If they are an existing user, then their profile details should be loaded from the
mysocialprofile.txt file.
		 */


FileReader fr=null;
try {
	fr = new FileReader("mysocialprofile.txt");
} catch (FileNotFoundException e) {
	e.printStackTrace();
}

        Scanner inFile = new Scanner(fr); 						//scanning into File
        
        String line;
        
        //as long as it has a next line
        if(inFile.hasNext())									
        {
        	//append information into it

        	line = inFile.nextLine();
        	info[0] = 	line;
        	line = inFile.nextLine();
        	info[1] = 	line;
        	line = inFile.nextLine();
        	info[2] = 	line;
        	year = Integer.parseInt(inFile.nextLine());
        	
        	
        	String[] events_Str = inFile.nextLine().split(",");
        	for(int i=0; i < events_Str.length; i++)
        	{
        		if(events_Str[i].length() > 2){
        			Event ev = Event.parse(events_Str[i], true);
        			if(ev.Time.isAfter(LocalDateTime.now())) {
        				events.insert(ev);
        			}
        		}
        	}
        	
        	String[] posts_Str = inFile.nextLine().split(",");
        	for(int i=0; i < posts_Str.length; i++)
        	{
        		if(posts_Str[i].length() > 2)
        			posts.insertAtTail(posts_Str[i].substring(1, posts_Str[i].length()-1));
        	}
        	
        	String[] friends_Str = inFile.nextLine().split(",");
        	for(int i=0; i < friends_Str.length; i++)
        	{
        		if(friends_Str[i].length() > 2)
        			friends.insertAtTail(friends_Str[i].substring(1, friends_Str[i].length()-1));
        	}
        	
        }

        inFile.close();


		//Display the information

        System.out.println("Welcome, " + info[0] + " to your homepage" + "\n" 
        	+ "=====================================");

        System.out.println("Your next event: ");

        System.out.println(events.getAll()[1].getString()+ "\n =====================================");

        System.out.println("Recent posts: ");
        if(posts.size()>0)
        	System.out.println(posts.toArray()[posts.size()-1]);
        if(posts.size()>1)
        	System.out.println(posts.toArray()[posts.size()-2]);
        if(posts.size()>2)
        	System.out.println(posts.toArray()[posts.size()-3]);
        System.out.println("\n=====================================");

        System.out.println("All events: ");
        for(int i=1; i <= events.size(); i++) {
        	System.out.println(events.getAll()[i].getString()+", ");
        } 
        System.out.println("=======================================");



    }

		/**
	Gives the user the option to do other secondary options: posting, adding events, adding/removing/listing friends, logging out.

 */
	
	public void secondary() throws IOException {
		
		System.out.println("Your options: ");
		System.out.println("1. Posting to your timeline.");
		System.out.println("2. Adding an event.");
		System.out.println("3. List your friends.");
		System.out.println("4. Add/Remove a friend.");
		System.out.println("5. Logout.");
		
		int choice = Integer.parseInt(myObj.nextLine());
		
		switch(choice) {
			case 1:
			System.out.println("Add to Your timeline: ");
			String new_post = myObj.nextLine();
			posts.insertAtTail(new_post);
			break;
			case 2:
			System.out.println("Add an event: ");
			String new_event=myObj.nextLine();
			events.insert(Event.parse(new_event, true));
			break;
			case 3:
			System.out.println("Your friends list: ");
			friends.traverse();
			break;
			case 4:
			System.out.println("Add/Remove friends: ");
			String friend_ID=myObj.nextLine();
			int idx = friends.getIndexOf(friend_ID);
			if(idx == -1)
			{
				friends.insertAtTail(friend_ID);
			}
			else
			{
				friends.deleteFromPosition(idx);
			}
			break;
			case 5:
			updateFile();	
			return;
		}								//updates the information into the file.

	}
	
	//to exit the program.
	public void exit() throws IOException {
		updateFile();
		myObj.close();
		System.exit(0);
	}
}