package workoutDB;
import java.sql.*;

public abstract class Controller {

	 // JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://localhost/workout";

	 //  Database credentials
	 static final String USER = "root";
	 static final String PASS = "";
	 
	 
	 // connection variables
	 static Connection conn = null;
	 static Statement stmt = null;
	 ResultSet rs;
	 
	 
	 
	 
	 public void connect(){

		 try{
		    //STEP 2: Register JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");
	
		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);

		 }catch(SQLException se){
		    //Handle errors for JDBC
		    se.printStackTrace();
		 }catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		 }finally{
		    //finally block used to close resources
		    try{
		       if(stmt!=null)
		          stmt.close();
		    }catch(SQLException se2){
		    }// nothing we can do
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }//end finally try
		 }//end try
		 System.out.println("Connected...");
		}
	 
	 public ResultSet get(String query) throws SQLException{
		//Execute a query
		 connect();
		 try{
		    System.out.println("Creating statement...");
		    stmt = conn.createStatement();
		    String sql;
		    sql = query;
		    ResultSet rs = stmt.executeQuery(sql);
		    System.out.println(rs);
		    return (rs);
		    
		 }catch(SQLException se){
			    //Handle errors for JDBC
			    se.printStackTrace();
			 }catch(Exception e){
			    //Handle errors for Class.forName
			    e.printStackTrace();
			 }
		 finally{
			    //finally block used to close resources
			    try{
			       if(stmt!=null)
			          stmt.close();
			       		return null;
			    }
			    catch(SQLException se2){
			    	}// nothing we can do
			    try{
			       if(conn!=null)
			          conn.close();
			       return null;
			    }catch(SQLException se){
			       se.printStackTrace();
			    }
			 }
			 return null;
		}
	 public void disconnect(){
		
		// Clean up environment
		try {
			rs.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }

}
