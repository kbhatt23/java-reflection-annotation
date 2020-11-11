package exam;
import java.lang.reflect.*;
public class ArrayReader {
    
    public Object getArrayElement(Object array, int index) {

    	if(index < 0) {
    		int size = Array.getLength(array);
    		return Array.get(array,size+index);
    	}else {
    		return Array.get(array, index);
    	}
    	
    }
}