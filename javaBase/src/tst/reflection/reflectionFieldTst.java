package tst.reflection;


import tst.util.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class reflectionFieldTst {
    public static void main(String[] args) throws Exception {
        mtd();

        Utils.seperator(32);
        mtd1();

        Utils.seperator(32);
        mtd2();
    }

    /*
     1. getFields: only public Fields of superCls and curCls
     2. getDeclaredFields: all fields of curCls (exclude Fields of extends superCls)
    */
    static void mtd() throws NoSuchFieldException {
        Class<?> stdClass = Student1.class;

        // only public fields of supCls and curCls
        Field[] fields = stdClass.getFields();
        for(Field field: fields)
            System.out.println("fieldName --> " + field.getName());

        Utils.seperator(32);

        // all fields of curCls (exclude Fields of extends superCls)
        fields =stdClass.getDeclaredFields();
        for(Field field: fields)
            System.out.println("fieldName --> " + field.getName());

        //  ====================================================
        System.out.println(stdClass.getField("score"));

        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("supName"));

        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));
    }

    static void  mtd1()  {
        try {
            // getDeclaredField, fields of curCls (exclude fields extends supCls)
            Field f = Student1.class.getDeclaredField("score");
            System.out.println("field = " + f);

            Class<?> clzz = f.getType();
            System.out.println("getType = " + clzz);

            String name = f.getName();
            System.out.println("name = " + name);

            Object value = f.get(Student1.class);  // static filed: clzzX
            System.out.println("value = " + value);

            Utils.seperator(16);

            // getModifiers
            int m = f.getModifiers();
            boolean isFinal= Modifier.isFinal(m);
            boolean isPublic =  Modifier.isPublic(m);
            boolean isProtected = Modifier.isProtected(m);
            boolean isPrivate = Modifier.isPrivate(m);
            boolean isStatic = Modifier.isStatic(m);

            System.out.println("isFinal= " + isFinal + "\n" +
                    "isPublic = "+ isPublic +"\n" +
                    "isProtected = " + isProtected + "\n" +
                    "isPrivate = "+isPrivate + "\n" +
                    "isStatic = " + isStatic);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void mtd2() throws NoSuchFieldException, IllegalAccessException {
        Student1 student = new Student1();
        Class<?> studentClass = student.getClass();
        Field f = studentClass.getDeclaredField("score1");

        f.setAccessible(true);  // private field need setAccessible(true)

        // get: fX.get(objX) // objX.fX
        Object value = f.get(student);
        System.out.println("score1 = " + value);

        // set: fX.set(objX, val)
        f.set(student, "0");
        System.out.println("score1(after set)" + student.getScore());
    }
}

class Student1 extends Person1 {
    public  static  String score = "100";
    public  String score1 = "100";

    public String getScore(){
        return score1;
    }

    public String name;
    private int grade;
}

class Person1 {
    public String supName;
    private int supGrade;
}

