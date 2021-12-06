package tst.tmp;
// Q: override: mtd/field

public class Override {
    public static void main(String[] args) {
        B1 b = new B1();
        b.b1();
    }
}

class A1 {
    String a = "a";
    A1 () {
        System.out.println("A1,con, this.a: " + this.a); //** a
        System.out.println("A1,constructor, this.getClass(): " + this.getClass()); // B1
    }

    void visit() {
        System.out.println("A1,this.getClass(): " + this.getClass()); // B1
        System.out.println("A1,visit, this.a = " + this.a);
        mtdA(); // B1
        this.mtdA(); //B1
    }

    //retrieve(try my best): private(curCls) -> default(pCls) -> protected(childCls) -> public()
    void mtdA() {
        System.out.println("A1.mtda --> ");
    }
}

class B1 extends A1 {
    String a = "b";

    B1() {
        System.out.println("b super.a: " + super.a); //** a
        System.out.println("b this.a: " + this.a); // b
    }

    void mtdA() {
        System.out.println("B1.mtda --> ");
    }

    void b1() {
        visit();
        super.mtdA(); //** A1
    }
}


