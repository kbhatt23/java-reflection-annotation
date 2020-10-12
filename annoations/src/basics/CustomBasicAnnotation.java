package basics;
//meta data for annotion

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//if documented is added : It will add java doc when the user of this annotation is documented using javaodc command
/**
 * @author kbhatt23
 *
 */
@Documented
//the child of user class of this annotation will also inherit it
@Inherited
//applicable for both method and class level
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface CustomBasicAnnotation {

	//assuming everyone's favourite footballer is messi
	String favouriteFootballer() default "messi";
	
	//assuming everyone's favourite god is narayan
	String favouriteIncarnation() default "narayan";
}
