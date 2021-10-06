package com.nil;

import java.util.ArrayList;
import java.util.List;

public class Tst {
    class A {
    }

    class B extends A{
    }

    /**
     * 父类
     */
     class Superclass {
        public void method(ArrayList arrayList) {
            System.out.println("父类方法执行了！");
        }

        public A get(B b) {
            System.out.println("父类方法执行了！");
            return new B();
        }
    }

    // fun(int, ) // funName, parameters return
    // subCls replace superCls

    /**
     * 子类
     */
     class Son extends Superclass {
        // 重载了父类的method，并且方法入参比父类的入参范围更广。
        public void method(List list) {
            System.out.println("子类方法执行了！");
        }

        public B get(B b) {
            System.out.println("父类方法执行了！");
            return new B();
        }
    }


    public static void main(String[] args) {

    }
}
