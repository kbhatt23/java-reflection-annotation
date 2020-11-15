package com.learning.staticproxypattern;

public class DBReaderClient {

	public static void main(String[] args) {
		IDBReader dbReader = IDBReader.getInstance();
		
		readAllDAta(dbReader);
		readFiltereDAta(dbReader);
		
		
		readAllDAta(dbReader);
		readFiltereDAta(dbReader);
	}

	public static void readFiltereDAta(IDBReader dbReader) {
		for(int i=0 ; i < 5 ; i++) {
			System.out.println("Calling DBReader to fetch data");
			System.out.println(dbReader.readFilteredData());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void readAllDAta(IDBReader dbReader) {
		for(int i=0 ; i < 5 ; i++) {
			System.out.println("Calling DBReader to fetch data");
			System.out.println(dbReader.readAllData());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
