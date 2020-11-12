package methodusage;

public class CallThisclassMethods {

	public void method() {
		System.out.println("No arg method called");
	}
	public void method(Integer number) {
		System.out.println("number method called with number "+number);
	}
	public void method(String string) {
		System.out.println("string method called with number "+string);
	}
	
	public void method(Integer number,String str) {
		System.out.println("complex method called with number "+number+" and string "+str);
	}
}
