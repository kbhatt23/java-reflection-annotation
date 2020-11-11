package exam;

import java.lang.reflect.Field;

import customjacksonAPI.BasicJSONBean;
import customjacksonAPI.CompleJSONBean;

public class ObjectSizeCalculator {

	private static final long HEADER_SIZE = 12;
	private static final long REFERENCE_SIZE = 4;

	public static void main(String[] args) {
		CompleJSONBean input = new CompleJSONBean("kannu", 23, 100, new BasicJSONBean("inner kannu", 32, 7));
		System.out.println(new ObjectSizeCalculator().sizeOfObject(input));
		//System.out.println(new ObjectSizeCalculator().sizeOfObject(new BasicJSONBean("kannu", 23, 100)));
	}

	public long sizeOfObject(Object input) {
		Class<?> clazz = input.getClass();
		long size = 0;
		Field[] declaredFields = clazz.getDeclaredFields();
		for(Field field: declaredFields) {
			Class<?> fieldClass = field.getType();
			field.setAccessible(true);
			if(fieldClass.isPrimitive()) {
				//primitves
				size +=sizeOfPrimitiveType(fieldClass);
				
			}else if(fieldClass.equals(String.class)) {
				try {
					size +=sizeOfString((String)field.get(input));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}else {
				//object
				try {
					size+=sizeOfObject(field.get(input));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return size;
	}
	
	/*************** Helper methods ********************************/    
    private long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }
    
    private long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }
}
