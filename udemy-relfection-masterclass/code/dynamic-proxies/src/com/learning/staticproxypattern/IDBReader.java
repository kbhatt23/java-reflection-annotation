package com.learning.staticproxypattern;

import java.util.List;
//as of now mimicing only one meethod
public interface IDBReader {

	public List<String> readAllData();
	
	static IDBReader getInstance() {
		return new DBReaderPRoxy();
	}
	
	public List<String> readFilteredData();
	
}
