package tst.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    curCls, curPackage, subCls
    private default(curpackage), protected(curPackage, childCls)
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Profile {
    int id() default -1;
    int height() default 0;
    String nativePlace() default   "";
}
