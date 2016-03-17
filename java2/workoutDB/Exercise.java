package workoutDB;

public class Exercise{
	String description, currentGoal, bestResult;
	int weight, repetitions, sets, length, duration;
	
	Exercise(String description, String currentGoal, String bestResult, int weight, int repetitions, int sets, int length, int duration){
		this.description = description;
		this.currentGoal = currentGoal;
		this.bestResult = bestResult;
		
		this.weight = weight;
		this.repetitions = repetitions;
		this.sets = sets;
		this.length = length;
		this.duration = duration;
	}
	
	
}
