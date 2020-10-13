package util;

import java.lang.reflect.InvocationTargetException;

import com.iocframework.bean.SimpleBean;

public class SimpleComponentRunner {
public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	SimpleBean findBean = ContextLoader.findBean(SimpleBean.class);
	
	
	
	System.out.println(findBean);
	System.out.println(findBean.getAnotherBean());
}
}
