package tst.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)  //package, class(type), constructor, mtd, field, parameter
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {
    public enum GenderType {
        Male("male"),
        Female("Female"),
        Other("other");

        private String strGender;
        private GenderType(String strGender){
            this.strGender = strGender;
        }
        @Override
        public String toString() {
            return strGender;
        }
    }
    GenderType gender() default GenderType.Male;
}
