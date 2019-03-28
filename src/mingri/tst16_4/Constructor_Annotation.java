package mingri.tst16_4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 1. @target ElementType constructor
@Target(ElementType.CONSTRUCTOR)

// 2. @retention runtime load
@Retention(RetentionPolicy.RUNTIME)

public @interface Constructor_Annotation { // @interface key word
	String value() default "默认构造方法"; // field_val have defval
}
