package configreader;

public class ConfigLoaderTest {
public static void main(String[] args) {
	try {
		ConfigBean loadConfig = ConfigLoader.loadConfig("C:/Kanishk/learning/java-reflection-annotation/code/java-reflection-annotation/udemy-relfection-masterclass/code/java-reflection-basics/config.properties", ConfigBean.class);
		System.out.println(loadConfig);
		
		//to load field variable like spring using @value();
		
		ConfigService instance = new ConfigService();
		ConfigLoader.setConfigToFields( loadConfig, instance);
		
		System.out.println(instance);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
