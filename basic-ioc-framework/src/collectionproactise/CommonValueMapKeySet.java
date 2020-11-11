package collectionproactise;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class CommonValueMapKeySet {
public static void main(String[] args) {
	Map<Integer, String> map1 = new HashMap<Integer, String>();
	map1.put(1, "raghav");
	map1.put(4, "ksrihna");
	map1.put(5, "madhav");
	map1.put(122, "nagesham");
	map1.put(12, "badrinath");
	
	Map<Integer, String> map2 = new HashMap<Integer, String>();
	map2.put(11, "raghav");
	map2.put(43, "krishna");
	map2.put(53, "mahesh");
	map2.put(12222, "nath");
	map2.put(1222, "badrinath");
	Set<Integer> map2SetWithValuesSameAsMap1 = new HashSet<Integer>();
	Collection<String> map1ValCollection = map1.values();
	for(Entry<Integer, String> entrySet : map2.entrySet()) {
		if(map1ValCollection.contains(entrySet.getValue())) {
			map2SetWithValuesSameAsMap1.add(entrySet.getKey());
		}
	}
	System.out.println(map2SetWithValuesSameAsMap1);
	System.out.println("========using streams============");
	
	Set<Integer> map2SetWithValuesSameAsMap1Stream = map2.entrySet()
		.stream()
		.filter(entry -> map1ValCollection.contains(entry.getValue()))
		.map(Entry::getKey)
		.collect(Collectors.toSet());
	System.out.println(map2SetWithValuesSameAsMap1Stream);
	
	
	System.out.println("==========using O(n) complexity=]]]]]]]]]]]]]]");
	HashSet<String> hashSetMap1Values = new HashSet<>(map1ValCollection);//
	//hashSetMap1Values containes to hashset is o(1) better than collection contains which is O(n)
	
	
	Set<Integer> map2SetWithValuesSameAsMap1Stream1 = map2.entrySet()
			.stream()
			.filter(entry -> hashSetMap1Values.contains(entry.getValue()))
			.map(Entry::getKey)
			.collect(Collectors.toSet());
		System.out.println(map2SetWithValuesSameAsMap1Stream1);
		
}
}
