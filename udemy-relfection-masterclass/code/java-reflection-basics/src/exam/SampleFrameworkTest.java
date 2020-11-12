package exam;

public class SampleFrameworkTest {

	public static void beforeClass() {
		System.out.println("before whole class with instance ");
	}
	
	public static void afterClass() {
		System.out.println("after whole class with instance ");
	}
	
	public void setupTest() {
		System.out.println("Setting up for test with instance "+this.hashCode());
	}
	
	public void testFirst() {
		System.out.println("Running first test with instance "+this.hashCode());
	}
	
	public void testSecond() {
		System.out.println("runnign second test with instance "+this.hashCode());
	}
}
