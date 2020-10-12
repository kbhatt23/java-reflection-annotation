package annotaion_reflection_usage;

public class MethodUtility {
	//@CallThisMethod(intArgument = 108)
	public void utilize(int num) {
		System.out.println("jai shree ram from integer using "+num);
	}
	@CallThisMethod(stringArgument = "jai shree ram")
	public void utilize(String str) {
		System.out.println("jai shree ram from string using "+str);
	}
	
	@CallThisMethod(noArgProperty = true)
	public void utilize() {
		System.out.println("jai shree ram from no arg method");
	}
}
