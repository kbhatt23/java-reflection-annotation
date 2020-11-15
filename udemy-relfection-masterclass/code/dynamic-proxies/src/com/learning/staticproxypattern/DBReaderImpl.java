package com.learning.staticproxypattern;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.learning.dynamicproxypattern.annotations.EnableCache;

public class DBReaderImpl implements IDBReader{
	@EnableCache
	@Override
	public List<String> readAllData() {
		//mimicking for demonstrationas D.B call is time consuming
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Arrays.asList("sita-ram","radhe-krishna","uma-shankar");
	}
	@EnableCache
	@Override
	public List<String> readFilteredData() {
		return readAllData().stream()
						    .filter(str -> str.contains("radhe"))
						    .collect(Collectors.toList());
	}

}
