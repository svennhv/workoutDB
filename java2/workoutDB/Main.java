package workoutDB;

//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;

public class Main {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/EMP";
	
	//  Database credentials
	static final String USER = "root";
	static final String PASS = "";
	
	

	
	
	
	// MAIN METHOD:
	
	public static void main(String[] args) throws SQLException {
	
		Boolean exit = false;	
		String input, command, request;
		Scanner in = new Scanner(System.in);
		
		// Controllers
		ExerciseController exCtrl = new ExerciseController();
		
		
		
		System.out.println("Welcome!");
		
		// for testing:

		
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
				show(request, exCtrl);
			}
			else if (command.equals("new")){
				create(request);
			}
			else if (command.equals("edit")){ // Wait with implementing (not important)
				edit(request);
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
	}
	
	private static void edit(String request){
		// user wants to edit something
	}
	private static void create(String request){
		// this method is used whenever the user wants to create something new
	}
	private static void show(String request, ExerciseController exCtrl) throws SQLException{
		// this method is used if the user wants to show something
		if ( request.equals("exercises") ){
			System.out.println("Showing all exercises:");
			System.out.println( exCtrl.getAll() );
		}
	}
	
	private static String parse(String str){
		return str;
	}
}
