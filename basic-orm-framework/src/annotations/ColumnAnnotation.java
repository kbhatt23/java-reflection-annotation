package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//need not want the child of user to inherit it automatically
@Documented
//can only be used at instnce variable  level of class
@Target(ElementType.FIELD)
//while app is runnig we still want annotation to be present
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnAnnotation {
	//making filed tableNAme as mandatory
	String columnName() default "defaultColumn";
}
