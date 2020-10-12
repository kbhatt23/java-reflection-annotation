package basics;
@CustomBasicAnnotation(favouriteFootballer = "lionel messi", favouriteIncarnation = "lakshmi narayan")
public class Utility {
		//can be accesed at mehod level
	//@CustomBasicAnnotation(favouriteFootballer = "lionel messi", favouriteIncarnation = "lakshmi narayan")
	//any anotation having target as Elementtype.Type_use -> can be used in constructor/method argument
	public void usage1(/* @CustomBasicAnnotation */ @CustomBasicAnnotationJAVA8 String argumnent1) {
		System.out.println("jai shree ram while using 1st algo");
	}
}
