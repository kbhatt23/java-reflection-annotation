package annotaion_reflection_usage;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
//inherited not needed -> child class of user of this annotatin need not to inherit this annoataion
//method only -> as it is supposed to be ued by methods only
@Target(ElementType.METHOD)
//since at runtime we need this otherwise it wont work
@Retention(RetentionPolicy.RUNTIME)
public @interface CallThisMethod {
	//field name to be used only by int argument method
	int intArgument() default 0;
	
	//field name to be used only by string argument method
	String stringArgument() default "";
	
	boolean noArgProperty() default false;
}
