package tst.genericity;

import java.util.ArrayList;
import java.util.List;

public class GenericityTst1 {
    public static void main(String[] args) {

//        List<Number> list = new ArrayList<Integer>();  // err
        List<? extends Number>  list1  =  new ArrayList<Integer>();

        Pair<Integer> p = new Pair<>(123, 456);
        Pair<Number> p1 = new Pair<>(321, 654);
        int n = add(p);
        int n1 = add(p1);
        System.out.println(n);
        System.out.println(n1);
    }

    static int add(Pair< ? extends Number> p) {  // Pair< ? extends Number> = Pair<Number>, Pair<Integer>
        Number first = p.getFirst();
        Number last = p.getLast();
//        p.setFirst(first.intValue() + 100);  // couldn't set
//        p.setLast(last.intValue() + 100);
        return last.intValue() + first.intValue();
    }
}
class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }
    public void setFirst(T first) {
        this.first = first;
    }
    public void setLast(T last) {
        this.last = last;
    }
}