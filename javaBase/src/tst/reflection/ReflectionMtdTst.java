package tst.reflection;

import tst.util.Utils;

import java.lang.reflect.Method;

public class ReflectionMtdTst {
    public static void main(String[] args) throws Exception {
        mtd();
        Utils.seperator(32);
    }

    static void mtd() throws NoSuchMethodException {
        Class<?> stdClass = Student1.class;
        // 获取public方法getScore，参数为String:
        System.out.println(stdClass.getMethod("getScore", String.class));
        // 获取继承的public方法getName，无参数:
        System.out.println(stdClass.getMethod("getName"));
        Method[] mtds = stdClass.getMethods();
        for(int i =0; i < mtds.length; i ++)
            System.out.println("mtd" + i + " = " + mtds[i].getName());
        // 获取private方法getGrade，参数为int:
        System.out.println(stdClass.getDeclaredMethod("getGrade", int.class));
    }

    static void mtd1(){

    }
}



class Student2 extends Person2 {
    public String getName() {
        return "";
    }
    public int getScore(String type) {
        return 99;
    }
    private int getGrade(int year) {
        return 1;
    }
}

class Person2 {
    public String getName(String str) {
        return "Person";
    }
}
