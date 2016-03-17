package workout2;

//STEP 1. Import required packages
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
 
	public static void main(String[] args) throws SQLException{
		ExerciseCtrl ex = new ExerciseCtrl();
		System.out.println(ex.query("SELECT * from exercise"));
		
		//  Variables and objects for user interaction:
		Boolean exit = false;	
		String input, command, request;
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("Welcome!");
		
		// Main loop
		while (!exit){
			
			/* For real program:
			System.out.println(">");
			input = in.nextLine();
			*/
			// testing:
			input = "show exercises";
			
			
			command = input.split(" ")[0];
			request = input.replace(command+" ", "");
			
			if (command.equals("show")){
				if (request.equals("exercise")){
					ex.show();
				}
			}
			else if (command.equals("new")){
				if (request.equals("exercise")){
					
				}
			}
			else if (command.equals("edit")){ // Wait with implementing (not important)
				
				}
			else if (command.equals("exit")){
				exit = true;
			}
			else{
				System.out.println("Invalid command");
			}
			
			// for testing:
			exit = true;
			
		}
		
		/*
		// test map:
		Map<String, String> hm = new HashMap<String, String>();
	    
		   // adds all fields to hashmap
	       hm.put("name", "moro");
	       hm.put("description", "blabla");
	       hm.put("currentGoal", "goal 120");
	       hm.put("bestResult", "best 100");
	       hm.put("weight",  "100");
	       hm.put("sets","3");
	       hm.put("length",  "200");
	       hm.put("duration", "100");

	       ex.add(hm);
	    
		ex.delete("moro");
	    System.out.println(ex.query("SELECT * from exercise"));
	    */
	}
}