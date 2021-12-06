import java.util.ArrayList;
import java.util.List;


/*
*   Q1: Unit(eg): combing code & ppt ?  or code only ?
*   A1: ppt & code; combing ppt -> run code and comment the keypoint -> add on ppt;
*   A12: retrieve by code and commit firstly, then ppt & point; eg -> concept&comprehend;
*
*   Q2: save to gitHub use prj or android,java, kotlin ?
*   A2: for study&debug control version only, every change in a timely manner, could save by java, kotlin (multiple prjs);
* */

class A {
}

class B extends A {
}

class C extends B{
}


public class GenericityTst {
    public static void main(String[] args) {

    }
    static void mtd(){
        /*
        1. could as left value; assign right value;
        2. out & in
          List<? extends supCls> list: supCls,extend => out => subCls x = list.get(0) // upperLimit, PE
          List<? super   subCls> list: subCls,super => in => list.add(superCls) // lower limit, in(add), CS, add lower limit subCls only
        * */
        List<?> list =  new ArrayList<>(); // ?  => unknow type, unbound wildcard
        List<A> listA2 = new ArrayList<>();
        List<B> listB2 = new ArrayList<>();
        List<C> listC2 = new ArrayList<>();

        list = listA2;
        list = listB2;
        list = listC2;

        A a2 = new A();
        B b2 = new B();
        C c2 = new C();

//        list.add(a2);
//        list.add(b2);
//        list.add(c2)
    }

    /* why:
        1. List<? extends A>, List<? super C> as left value limit right value;
        2. List<? extends A>: out(get)  A a = list.get(0);
        3. List<? super C>:
     */
    static void mtd1(){
        List<? extends A> list = new ArrayList<>(); // ? extends A => ? < A (supCls, upperLimit, out)
        List<A> listA1 = new ArrayList<>();
        List<B> listB1 = new ArrayList<>();
        List<C> listC1 = new ArrayList<>();

        // 1. List<? extends A> as right value;
        list = listA1;
        list = listB1;
        list = listC1;
//        listB1 = listC1  // err: List<C> isn't subCls of List<B>


        // 2. input output
        A tmp;  // supCls
        A a1 = new A();
        B b1 = new B();
        C c1 = new C();

        listA1.add(a1);
        listB1.add(b1);
        listC1.add(c1);

        // a. in: couldn't in subCls
//        list.add(a1); // err: List<? extends A> listA.add(X); couldn't in
//        list.add(b1);
//        list.add(c1);

        // b. out: could out subCls
        tmp = listA1.get(0);
        tmp = listB1.get(0);
        tmp = listC1.get(0);
    }

    static void mtd2(){
        List<? super C> list =  new ArrayList<B>(); // ? super C => ? > C(subCls, lowerLimit, in);
        List<C> listC = new ArrayList<>();
        List<B> listB = new ArrayList<>();
        List<A> listA = new ArrayList<>();

        // 1. List<? super C> as left value;
        list = listA;
        list = listB;
        list = listC;


        // 2.in & out
        C tmp;
        A a = new A();
        B b = new B();
        C c = new C();

        // a. in
        list.add(c);  // List<? super C> could in the lower limit cls instance only; all ? type are supCls of C
//        list.add(b); // err: couldn't determine which type of add
//        list.add(a);

        // b. out
//        tmp = list.get(0); // List<? super C> couldn't out
    }
}
