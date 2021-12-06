package tst.tmp;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws Exception {
        Object p = new Person("Xiao Ming");
        Class<?> c = p.getClass();
        Field f = c.getDeclaredField("name");
        f.setAccessible(true);
        Object value = f.get(p); // f.get(objX);
        System.out.println(value); // "Xiao Ming"
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
