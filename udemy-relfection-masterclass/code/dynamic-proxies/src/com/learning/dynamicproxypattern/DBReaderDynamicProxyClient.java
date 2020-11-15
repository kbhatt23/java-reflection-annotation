package com.learning.dynamicproxypattern;

import com.learning.staticproxypattern.DBReaderClient;
import com.learning.staticproxypattern.DBReaderImpl;
import com.learning.staticproxypattern.IDBReader;

public class DBReaderDynamicProxyClient {
	public static void main(String[] args) {
		// IDBReader dbReader = new DBReaderImpl();
		IDBReader dbReaderProxy = null;
		try {
			dbReaderProxy = (IDBReader) ProxyCreator.createProxy(DBReaderImpl.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dbReaderProxy != null) {
			DBReaderClient.readAllDAta(dbReaderProxy);
			DBReaderClient.readFiltereDAta(dbReaderProxy);

			DBReaderClient.readAllDAta(dbReaderProxy);
			DBReaderClient.readFiltereDAta(dbReaderProxy);
		}
	}
}
