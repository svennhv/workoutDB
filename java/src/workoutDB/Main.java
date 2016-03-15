package workoutDB;

//STEP 1. Import required packages
import java.sql.*;

public class Main {


public static void main(String[] args) {
	System.out.println(Connector.connect());
	System.out.println("Goodbye!");
	}
}
