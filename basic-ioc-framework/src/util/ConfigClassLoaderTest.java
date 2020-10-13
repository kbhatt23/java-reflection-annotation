package util;

import com.iocframework.bean.AnotherBean;
import com.iocframework.bean.SimpleBean;

import otherbeans.OtherBean;

public class ConfigClassLoaderTest {
public static void main(String[] args) {
	ApplicationContextLoader context=  new ApplicationContextLoader();
	OtherBean findBeanFromCache = context.findBeanFromCache(OtherBean.class);
	System.out.println("other bean loaded "+findBeanFromCache);
	
	System.out.println("=============");
	AnotherBean findBeanFromCache2 = context.findBeanFromCache(AnotherBean.class);
	System.out.println("another bean loaded "+findBeanFromCache2);
	
	SimpleBean findBeanFromCache3 = context.findBeanFromCache(SimpleBean.class);
	System.out.println("Simple bena loaded "+findBeanFromCache3);
	System.out.println("anpother bean in side simple bean "+findBeanFromCache3.getAnotherBean());
	System.out.println("final last bean "+findBeanFromCache3.getAnotherBean().getLastBean());
}
}
