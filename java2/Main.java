package workout2;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
 
	public static void main(String[] args) throws SQLException{
		ExerciseCtrl ex = new ExerciseCtrl();
		WorkoutCtrl work = new WorkoutCtrl();
		
		System.out.println("### FOR TESTING PURPOSES ###");
		System.out.println(ex.query("SELECT * from exercise"));
		System.out.println(work.query("SELECT * from workout"));
		
		// Commands:
		ArrayList<String> commands = new ArrayList();
		String show = "show";
		String add = "add";
		String newCommand = "new";
		String edit = "edit";
		String exit = "exit";
		String help = "help";
		commands.add(show);
		commands.add(add);
		commands.add(newCommand);
		commands.add(edit);
		commands.add(exit);
		commands.add(help);
		
		
		
		//  Variables and objects for user interaction:
		String input, command, request;
		Scanner in = new Scanner(System.in);
		
		// Hash tables
		
		Map<String, String> em = new HashMap<String, String>();
	    
		   // adds all fields to hashmap
		   em.put("name", "");
		   em.put("description", "");
		   em.put("currentGoal", "");
		   em.put("bestResult", "");
		   em.put("weight",  "");
		   em.put("sets","");
		   em.put("length",  "");
		   em.put("duration", "");
		
		Map<String, String> wm = new HashMap<String, String>();
		    
		   // adds all fields to hashmap
		   // NB: all are strings, need to be converted.. 
		   
		   Date date = new Date(0);
		   String strDate = date.toString();
		   
	       wm.put("name", "");
	       wm.put("istemplate", "");
	       //wm.put("workouttime", "");
	       wm.put("duration",  "");
	       wm.put("shape",  "");
	       wm.put("performance", "");
	       wm.put("workoutnote",  "");
	       wm.put("weatherconditions", "");
	       wm.put("airconditions", "");
	       wm.put("numberofspectators", "");
		
		
		
		System.out.println("#### MAIN PROGAM ####");
		System.out.println("Welcome!");
		System.out.println("- type \"help\" to see commands");
		
		// Main loop
		
		while (true){
			
			System.out.print(">");
			input = in.nextLine().toLowerCase();
			command = input.split(" ")[0];
			request = input.replace(command+" ", "");
			
			
			if (command.equals(show)){
				if (request.contains("exercise")){
					System.out.println(ex.toString());
				}
				else if(request.contains("workout")){
					System.out.println(work.getWorkouts());
				}
				else{
					System.out.println("# You can show: exercise, workout. Eg. type: > show exercise");
				}
			}
			else if (command.equals(newCommand)){
				if (request.contains("exercise")){
					Map<String, String> tempEm;
					tempEm = userToMap(em, in);
					
				    System.out.println(em);
				    ex.add(tempEm); // sending to database
				    tempEm.clear();
				}
				else if (request.equals("workout")){
					Map<String, String> tempWm;
					tempWm = userToMap(wm, in);
					
				    System.out.println(tempWm);
				    work.add(tempWm); // sending to database
				    tempWm.clear();
				}
			}
			else if (command.contains(add)){
				if (request.contains("exercise")){
					System.out.println("# Choose workout:");
					System.out.println(work.getWorkouts());
					System.out.print(">");
					String workout = in.nextLine();
					
					System.out.println("Choose exercise:");
					System.out.println(ex.getExercises());
					System.out.print(">");
					String exercise = in.nextLine();
					
					work.addExercise(exercise, workout);
				}
			}
			else if (command.equals(edit)){ // Wait with implementing (not important)
				
				}
			else if (command.equals(help)){
				System.out.println(commands);
			}
			else if (command.equals(exit)){
				break;
			}
			else{
				System.out.println("Invalid command");
			}
			// for testing:
			//exit = true;
			
		}

		
		
		// Testing:
		
		/*
		
		Map<String, String> em = new HashMap<String, String>();
	    
		   // adds all fields to hashmap
	       em.put("name", "moro");
	       em.put("description", "blabla");
	       em.put("currentGoal", "goal 120");
	       em.put("bestResult", "best 100");
	       em.put("weight",  "100");
	       em.put("sets","3");
	       em.put("length",  "200");
	       em.put("duration", "100");

	       ex.add(em);
	    
		
	    System.out.println(ex.query("SELECT * from exercise"));
	    
		
    

	   Map<String, String> hm = new HashMap<String, String>();
    
	   // adds all fields to hashmap
	   // NB: all are strings, need to be converted.. 
	   
	   Date date = new Date(0);
	   String strDate = date.toString();
	   
       hm.put("name", "strength");
       hm.put("isTemplate", "false");
       hm.put("workoutTime", strDate);
       hm.put("duration",  "120");
       //hm.put("shape",  "shape");
       //hm.put("performance", rs.getString("performance"));
       //hm.put("workoutNote",  rs.getString("workoutNote"));
       //hm.put("weatherConditions", rs.getString("weatherConditions"));
       //hm.put("airconditions", "null");
       //hm.put("numberOfSpectators", rs.getString("numberofspectators"));
		
		work.add(hm);
		work.addExercise("moro", "strength");
		
		System.out.println("Showing..");
		work.show();
		
		
		
		// cleanup (test)
		System.out.println("##### TEST CLEANUP ###" );
		work.delete("strength");
		work.show();
		ex.delete("moro");
		ex.show();
		*/
	}
	
	
	// Help functions:
	
	// Fill into map - remember to clean() map after!
	private static Map<String, String> userToMap(Map<String, String> map, Scanner in){
		System.out.println("# Fill inn:");
		String input;
		//add values to hashmap:
		Iterator it = map.entrySet().iterator();
	    while (it.hasNext()) {
	    	String column = it.next().toString();
	        System.out.println(column);
	        input = in.nextLine().toString();
	        System.out.println();
	        map.put(column.replace("=", ""), input);
	        
	        
	    }
		return map;
	}
}