package util;

import com.iocframework.bean.SimpleBean;

public class Applicationrunner {
public static void main(String[] args) {
	ApplicationContextLoader context=  new ApplicationContextLoader();
	SimpleBean findBeanFromCache = context.findBeanFromCache(SimpleBean.class);
	System.out.println(findBeanFromCache);
	System.out.println(findBeanFromCache.getAnotherBean());
}
}
