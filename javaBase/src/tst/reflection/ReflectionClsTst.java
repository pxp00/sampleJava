package tst.reflection;

import tst.util.Utils;

public class ReflectionClsTst {
    public static void main(String[] args) {
        mtd();
        Utils.seperator(32);
        mtd1();
    }

    static void printClassInfo(Class<?> clzz) {
        System.out.println("Class name: " + clzz.getName());
        System.out.println("Simple name: " + clzz.getSimpleName());
        if (clzz.getPackage() != null) {
            System.out.println("Package name: " + clzz.getPackage().getName());
        }

        System.out.println("is interface: " + clzz.isInterface());
        System.out.println("is enum: " + clzz.isEnum());
        System.out.println("is array: " + clzz.isArray());
        System.out.println("is primitive: " + clzz.isPrimitive());
        Utils.seperator(16);
    }

    static void mtd(){
        printClassInfo("".getClass());
        printClassInfo(String[].class);
        printClassInfo(int.class);

        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
    }

    static void mtd1(){
        Integer n = new Integer(123);

        boolean b1 = n instanceof Integer; // true，因为n是Integer类型
        boolean b2 = n instanceof Number; // true，因为n是Number类型的子类

        boolean b3 = n.getClass() == Integer.class; // true，因为n.getClass()返回Integer.class

//        boolean b4 = n.getClass() == Number.class; // false，因为Integer.class!=Number.class
        Boolean[] booleans = {b1,b2,b3};
        for(int i = 0; i < booleans.length; i ++)
            System.out.println("b" + i + " = " + booleans[i]);
    }
}
 /*
    Class name: [Ljava.lang.String;
    Simple name: String[]
    is interface: false
    is enum: false
    is array: true
    is primitive: false
    seperator================================

    Class name: int
    Simple name: int
    is interface: false
    is enum: false
    is array: false
    is primitive: true
    seperator================================
*/
