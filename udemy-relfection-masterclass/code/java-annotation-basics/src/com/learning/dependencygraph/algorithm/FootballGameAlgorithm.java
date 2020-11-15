package com.learning.dependencygraph.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.learning.dependencygraph.annotations.AlgorithmRunnerClass;
import com.learning.dependencygraph.annotations.DependsOn;
import com.learning.dependencygraph.annotations.FinalResult;
import com.learning.dependencygraph.annotations.Operation;

//this class has to be valid algo runner
@AlgorithmRunnerClass
public class FootballGameAlgorithm {
	
	@FieldValue(name="position-value")
	private String position;
	

	public FootballGameAlgorithm(String position) {
		this.position = position;
	}
	
	public FootballGameAlgorithm() {
		
	}
	@Operation(name = "all-footballer")
	public List<Footballer> findAllfootballer(){
		return Arrays.asList(new Footballer("messi", 10, "striker",10)
				, new Footballer("xavi", 6, "midfielder",10)
				, new Footballer("iniesta", 8, "midfielder",9)
				, new Footballer("puyol", 5, "defender",10)
				, new Footballer("pique", 15, "defender",8)
				, new Footballer("suarez", 9, "striker",9)
				, new Footballer("umtiti", 16, "defender",6)
				, new Footballer("vidal", 18, "midfielder",7)
				, new Footballer("griezmann", 17, "striker",6)
				
				);
	}
	@Operation(name = "position")
	public String positionToFind(@FieldParam(property = "position-value") String position) {
		return position;
	}
	
	@Operation(name = "worst-footballer")
	public Footballer worstFootballerByPosition(@DependsOn(operationName = "position") String position, @DependsOn(operationName = "all-footballer") List<Footballer> footballers){
		return footballers.stream()
					.filter(footballer -> footballer.getPosition().equals(position))
					.min(Comparator.comparing(Footballer::getRating))
					.orElse(null)
					;
	}
	
	@Operation(name = "best-footballer")
	public Footballer bestFootballerByPosition(@DependsOn(operationName = "position") String position, @DependsOn(operationName = "all-footballer") List<Footballer> footballers){
		return footballers.stream()
					.filter(footballer -> footballer.getPosition().equals(position))
					.max(Comparator.comparing(Footballer::getRating))
					.orElse(null)
					;
	}
	
	@FinalResult
	public void printDataOfFootballers(@DependsOn(operationName = "best-footballer") Footballer bestFootballer, @DependsOn(operationName = "worst-footballer") Footballer worstFootballer, @DependsOn(operationName = "position") String position) {
		System.out.println(String.format("Best footballer with position %s is %s", position, bestFootballer));
		
		System.out.println(String.format("Worst footballer with position %s is %s", position, worstFootballer));
	}
}
