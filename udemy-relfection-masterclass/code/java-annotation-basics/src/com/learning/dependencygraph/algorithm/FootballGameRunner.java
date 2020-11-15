package com.learning.dependencygraph.algorithm;

public class FootballGameRunner {
	public static void main(String[] args) {
		try {
			GameAlgoRunner.runAlgorithmLogically(new FootballGameAlgorithm("defender"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
