package com.learning.staticproxypattern;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DBReaderPRoxy implements IDBReader{
	
	private IDBReader dbReader = new DBReaderImpl();
	
	private static List<String> allData ; 
	
	private static List<String> filteredData ; 
	
	
	//addoing cache and time feature all together 
	@Override
	public List<String> readAllData() {
		LocalDateTime startTime = LocalDateTime.now();
		
		if(allData == null) {
			allData = new ArrayList<String>(dbReader.readAllData());
		}
		
		LocalDateTime endtime = LocalDateTime.now();
		
		System.out.println("Total time taken to read data "+ Duration.between(startTime, endtime).getNano());
		return allData;
	}

	//this method also intercept for same reason
	@Override
	public List<String> readFilteredData() {
LocalDateTime startTime = LocalDateTime.now();
		
		if(filteredData == null) {
			filteredData = new ArrayList<String>(dbReader.readFilteredData());
		}
		
		LocalDateTime endtime = LocalDateTime.now();
		
		System.out.println("Total time taken to read data "+ Duration.between(startTime, endtime).getNano());
		return filteredData;
	}

}
