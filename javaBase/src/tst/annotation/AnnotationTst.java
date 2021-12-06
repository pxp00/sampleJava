package tst.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class AnnotationTst {
    public static void getInfo(Class<?> clzz){
        String name = "";
        Field[] fields = clzz.getDeclaredFields();

        for(Field field: fields){
            if(field.isAnnotationPresent(Name.class)){
                Name annObj = field.getAnnotation(Name.class); // get annotation obj(instance)
                name = name + annObj.value();
                System.out.println("name = "+ name);

                Annotation[] annObjs = field.getAnnotations();  //
                for(Annotation annotation: annObjs)
                    System.out.println("annotaion = " + annObj);
            }


            if(field.isAnnotationPresent(Gender.class)){
                Gender annObj = field.getAnnotation(Gender.class); // get annotation obj(instance)
                name = "";
                name = name + annObj.gender();
                System.out.println("name = "+ name);
            }

            if(field.isAnnotationPresent(Profile.class)){
                Profile annObj = field.getAnnotation(Profile.class); // get annotation obj(instance)
                name = "";
                name = name + annObj.id();
                name = name +"_"+ annObj.height();
                name = name + "_" + annObj.nativePlace();

                System.out.println("name = "+ name);
            }
        }
    }

    public static void main(String[] args) {
        AnnotationTst.getInfo(Person.class);
    }
}


class Person  {
    @Name("hugo_t0Iget_abgn_Sd_Qghxx")  //one parameter only, use "value", needn't parameterName
    @Gender(gender = Gender.GenderType.Female)
    private String name;

    @Name(value = "hugo_t0Iget_abgn_Sd_Qgh11")  //one parameter only, use "value", needn't parameterName
    private String name1;

    @Gender(gender = Gender.GenderType.Male)
     String gender;

    @Profile(id = 19, height = 173, nativePlace = "hunan")  // ctrl + p
    protected String profile;
}


@Target(ElementType.FIELD)  //package, class(type), constructor, mtd, field, parameter, local_variable
@Retention(RetentionPolicy.RUNTIME)
@Documented
  @interface Name {  // must be public or default(curPackageCls)
    public String value() default "";  //one parameter only, use "value", needn't parameterName
}