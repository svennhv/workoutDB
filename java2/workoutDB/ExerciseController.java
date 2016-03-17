package workoutDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseController extends Controller{
	// function 
		protected ArrayList<Exercise> getAll(){
			String sql = "GET * FROM EXERCISE";
			connect();
			ResultSet rs = null;
			try {
				System.out.println("trying");
				rs = get(sql);  //stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("made list");   //debugging	
			ArrayList<Exercise> list =  new ArrayList<>();
			
			if ( rs != null){
				System.out.println(rs);
			try{
				while(rs.next()){
					list.add(new Exercise(rs.getString("description"),
							rs.getString("currentgoal"), rs.getString("bestresult"),
							rs.getInt("weight"), rs.getInt("repetitions"),
							rs.getInt("sets"), rs.getInt("length"),
							rs.getInt("duration")));
					}
				}
			catch (SQLException e){
				e.printStackTrace();
				}
			}
			disconnect();
			
			return list;
		}
}
