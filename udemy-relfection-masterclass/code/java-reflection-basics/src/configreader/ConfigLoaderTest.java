package configreader;

public class ConfigLoaderTest {
public static void main(String[] args) {
	try {
		ConfigBean loadConfig = ConfigLoader.loadConfig("C:/Kanishk/learning/java-reflection-annotation/code/java-reflection-annotation/udemy-relfection-masterclass/code/java-reflection-basics/config.properties", ConfigBean.class);
		System.out.println(loadConfig);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
