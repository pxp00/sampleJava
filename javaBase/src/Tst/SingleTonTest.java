package Tst;

public class SingleTonTest { // public(all class), protected(package itself class add child class),
	// default(package itself class), private(class itself)
	public static void main(String[] args) {
		System.out.println("hello world");
		SingleTon singleTon1 = SingleTon.getSington();
		SingleTon singleTon2 = SingleTon.getSington();
		System.out.println(singleTon1 == singleTon2);

	}
}

// exist one obj_x only
class SingleTon {

	// 1. private constructor
	private SingleTon() {

	}

	// 3. mtd_static use field, so must be f_static
	private static SingleTon s;

	// 2. static get ref_x
	public static SingleTon getSington() {
		if (null == s) {
			synchronized (SingleTon.class) { // double-check, thread safe check once only
				if (null == s) {
					s = new SingleTon();
				}
			}
		}
		return s;
	}
}
