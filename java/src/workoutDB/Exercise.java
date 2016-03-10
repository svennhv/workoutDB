package workoutDB;

public class Exercise {
	
	protected static void init(){
		  //STEP 3: Open a connection
		  System.out.println("Connecting to database...");
		  conn = DriverManager.getConnection(DB_URL,USER,PASS);

		  //STEP 4: Execute a query
		  System.out.println("Exercise: Creating statement...");
		  stmt = conn.createStatement();
	}
	
	protected static void terminate(){
		  stmt.close();
		  conn.close();
	}
	
	protected static String getAll(){
		
	}
	
	
	// only use in class:
	
	private 
}
