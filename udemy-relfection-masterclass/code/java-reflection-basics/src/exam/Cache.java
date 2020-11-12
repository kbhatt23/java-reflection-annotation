package exam;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    private Map<String, Integer> nameToId = new HashMap<>();
 
    public void addEntry(String name, Integer id) {
        nameToId.put(name, id);
    }
 
    public Integer readIdOrThrow(String name) throws Exception {
        if (nameToId.containsKey(name)) {
            return nameToId.get(name);
        }
 
        throw new IllegalArgumentException(String.format("Name: %s is not in the cache", name));
    }
 
    public int getCacheSize() {
        return nameToId.size();
    }
    
    public static void main(String[] args) throws Exception{
    	Cache cache = new Cache();
    	Method addEntryMethod = Cache.class.getDeclaredMethod("addEntry");
    	Object result = addEntryMethod.invoke(cache, "John", 123);
    	System.out.println(result);
	}
}