package workout2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/*
 * Create one of these for each kind of output for database..
 *  use extends, for things that are 100% similar
 */


public class WorkoutCtrl{
	// JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://localhost/workout";

	 //  Database credentials
	 static final String USER = "root";
	 static final String PASS = "";
	 
	 Connection conn = null;
	 Statement stmt = null;
	 
	 // PUBLIC functions
	 
	 public void delete(String name){
		 String sql = "DELETE FROM workout WHERE name = \""+ name + "\"";
		 query(sql);
	 }
	 
	 public void add(Map<String,String> hm){
		 
		 String sql = "INSERT INTO workout";
		 String columns = "";
		 String values = "";
		 Iterator it = hm.entrySet().iterator();
		 while(it.hasNext()){
			 List<String> list = Arrays.asList(it.next().toString().split("="));
			 columns += list.get(0).toLowerCase() + "," ;
			 String val = "";
			 if (list.get(1) == null){
				 val = "NULL";
			 }
			 else if (isInteger(list.get(1))){
				 val = list.get(1);
			 }
			 else if (list.get(1).toLowerCase().equals("false") || list.get(1).toLowerCase().equals("true")){
				 val = list.get(1).toLowerCase();
			 }
			 else{ // is string
				 val = "\"" + list.get(1)+"\"";
			 }
			 values += val + "," ;
			 
		 }
		 // removing last comma:
		 columns = columns.substring(0, columns.length()-1);
		 values = values.substring(0, values.length()-1);
		 
		 // creating complete sql statement:
		 sql += "(" + columns + ")" + " VALUES (" + values + ");";
		 System.out.println(sql);

		 
		 query(sql);

	 }
	 
	 public void addExercise(String exercise, String workout){
		 ArrayList id = query("SELECT id FROM workout WHERE name= \"" + workout +"\"");
		 Map<String,String> map = new HashMap<String,String>();
		 map =  (Map) id.get(0); // .get("id")
		 String val = map.get("id");
		 val.replaceAll(".*?(\\d+).*", "");
		 System.out.println(val);
		 String sql = "INSERT INTO exerciseinworkout (exercisename, workoutid)"
		 		+ " VALUES (\"" + exercise + "\" , \""+ val + "\")";
		 query(sql);
	 }
	 
	 public String getWorkouts(){
		 String sql = "SELECT name FROM workout";
		 String output = query(sql).toString();
		 output.replace("name","");
		 return output;
	 }
	 public String getExercises(String workout){
		 String sql = "SELECT name FROM exerciseinworkout WHERE workoutid = \"" + workout + "\"";
		 return query(sql).toString();
	 }
	 
	 public String getExcersises(String workout){
		 return "";
	 }
	 
	 public void show(){
		 String sql = "SELECT * from workout";
		 System.out.println(query(sql));
	 }
	 
	 
	 
	 // PRIVATE: Underling functions (connectivity):
	 
	 public ArrayList<Map<String, String>> query(String query){
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
		    
		    ArrayList<Map<String, String>> list = new ArrayList();
		    
		    sql = query.toUpperCase();
		    if (query.contains("INSERT") || query.contains("DELETE")){
		    	System.out.println("updating database... statement: " + query);
		    	stmt.executeUpdate(sql);
		    }
		    else if (query.contains("SELECT name FROM workout")){ 
				ResultSet rs = stmt.executeQuery(sql);
				list = extractName(rs); // needs separate function if you only need one column!
			    System.out.println("returning nameList");
		    	return list;
			    
		    }
		    else if(query.contains("SELECT id FROM workout WHERE name")){
				ResultSet rs = stmt.executeQuery(sql);
				list = extractId(rs); // needs separate function if you only need one column!
			    System.out.println("returning nameList");
		    	return list;
		    }
		    else{
			    ResultSet rs = stmt.executeQuery(sql);
			    
			    // STEP 5: Extract and save to list
			    list = extract(rs);
			    
			    rs.close();
		    }
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

	 private ArrayList<Map<String,String>> extract(ResultSet rs) throws SQLException{
		 
		    // create list for hashmaps
		    ArrayList<Map<String, String>> list = new ArrayList();
		    
		    //STEP 5: Extract data from result set
		    while(rs.next()){
			   // save to hashmap:
			   Map<String, String> hm = new HashMap<String, String>();
		    
			   // adds all fields to hashmap
			   // NB: all are strings, need to be converted..
			   try{ hm.put("name", rs.getString("name"));}
			   finally{}
			   try{	hm.put("istemplate", rs.getString("istemplate"));}
			   finally{}
		       try{ hm.put("workouttime", rs.getString("workouttime"));}
			   finally{}
		       try{ hm.put("duration",  rs.getString("duration"));}
			   finally{}
		       try{ hm.put("shape",  rs.getString("shape"));}
			   finally{}
		       try{ hm.put("performance", rs.getString("performance"));}
			   finally{}
		       try{ hm.put("workoutnote",  rs.getString("workoutnote"));}
			   finally{}
		       try{ hm.put("weatherconditions", rs.getString("weatherconditions"));}
			   finally{}
		       try{ hm.put("airconditions", rs.getString("airconditions"));}
			   finally{}
		       try{ hm.put("numberofspectators", rs.getString("numberofspectators"));}
			   finally{}
		       
		       list.add(hm);
		    }
		    
		    return list;
	 }
	 private ArrayList<Map<String,String>> extractName(ResultSet rs) throws SQLException{
		 

	    	// create list for hashmaps
		    ArrayList<Map<String, String>> nameList = new ArrayList();
		    //STEP 5: Extract data from result set
		    while(rs.next()){
			   // save to hashmap:
			   Map<String, String> hm = new HashMap<String, String>();
			   hm.put("name", rs.getString("name"));
			   nameList.add(hm);
		    } 
		    return nameList;
	 }
	 private ArrayList<Map<String,String>> extractId(ResultSet rs) throws SQLException{
		    ArrayList<Map<String, String>> nameList = new ArrayList();
		    while(rs.next()){
			   Map<String, String> hm = new HashMap<String, String>();
			   hm.put("id", rs.getString("id"));
			   nameList.add(hm);
		    } 
		    return nameList;
	 }
	 
	 
	 // Helper functions:
	 
	 private static boolean isInteger(String s) {
		    return isInteger(s,10);
		}

	 private static boolean isInteger(String s, int radix) {
		    if(s.isEmpty()) return false;
		    for(int i = 0; i < s.length(); i++) {
		        if(i == 0 && s.charAt(i) == '-') {
		            if(s.length() == 1) return false;
		            else continue;
		        }
		        if(Character.digit(s.charAt(i),radix) < 0) return false;
		    }
		    return true;
		}

}
