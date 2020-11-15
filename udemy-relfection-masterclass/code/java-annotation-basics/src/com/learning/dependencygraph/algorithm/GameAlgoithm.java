package com.learning.dependencygraph.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.learning.dependencygraph.annotations.AlgorithmRunnerClass;
import com.learning.dependencygraph.annotations.DependsOn;
import com.learning.dependencygraph.annotations.FinalResult;
import com.learning.dependencygraph.annotations.Operation;

@AlgorithmRunnerClass
public class GameAlgoithm {

	@Operation(name = "all-names")
	public List<String> findAllNames(){
		return Arrays.asList("sita-ram","radha-krishna","uma-shankar","radha-krishna");
	}
	@Operation(name = "valid-names")
	public List<String> createValidNames(@DependsOn(operationName = "all-names") List<String> names){
		return names.stream()
					.map(a -> a.replace("-", ""))
					.map(String::toUpperCase)
					.collect(Collectors.toList());
	}
	@Operation(name = "unique-names")
	public Set<String> uniqueNames(@DependsOn(operationName = "valid-names") List<String> names){
		return names.stream()
					.collect(Collectors.toSet());
	}
	
	@FinalResult
	public void compareItems(@DependsOn(operationName = "all-names") List<String> originalNames, @DependsOn(operationName = "unique-names") Set<String> validNames) {
		System.out.println("Original names "+originalNames);
		System.out.println("valid names "+validNames);
	}
}
