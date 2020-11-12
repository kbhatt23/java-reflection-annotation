package exam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PAginationUSingStream {

	public static void main(String[] args) {
		List<Integer> allItems = findAllItems();
		//System.out.println(allItems);
		
		System.out.println(paginate(allItems, 10, 11));
		
	}
	
	public static List<Integer> findAllItems(){
		IntStream rangeClosed = IntStream.rangeClosed(1, 100);
		return rangeClosed.collect(ArrayList::new, List::add, List::addAll);
	}
	
	//generic
	public static <T> List<T> paginate(List<T> items ,int pageNumber, int sizePerPAge){
		int skipNumber = pageNumber*sizePerPAge;
		return items.stream()
			 .skip(skipNumber)
			 .limit(sizePerPAge)
			 .collect(Collectors.toList());
			 
	}
}
