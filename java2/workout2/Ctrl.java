package workout2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Ctrl {

	// JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://localhost/workout";

	 //  Database credentials
	 static final String USER = "root";
	 static final String PASS = "";
	 
	 Connection conn = null;
	 Statement stmt = null;
	 
	 public ArrayList<HashMap<String, String>> query(){
		 try{
		    //STEP 2: Register JDBC driver
		    Class.forName("com.mysql.jdbc.Driver");
	
		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
		    //STEP 4: Execute a query
		    System.out.println("Creating statement...");
		    stmt = conn.createStatement();
		    String sql;
		    sql = "SELECT * from exercise";
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    // STEP 5: Extract and save to list
		    ArrayList<HashMap<String, String>> list = new ArrayList();
		    list = extract(rs);
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		    return (list); // return resultSet
		    
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
		 return (null);
	 }
	 private ArrayList<HashMap<String,String>> extract(ResultSet rs) throws SQLException{
		 
		    // create list for hashmaps
		    ArrayList<HashMap<String, String>> list = new ArrayList();
		    
		    
		    return list;
	 }

}
