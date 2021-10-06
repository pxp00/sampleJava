/**
 * 
 */
package Tst;

import java.util.Random;

//final var could be change only at init, store on heap, multi-copy, belong to obj;s  
//static var could be init once only, one copy only;
public class StaticTst2 {

	// private: obj_curCls use only, cls_child couldn't use
	// final: constant, change at init only, multi-copy
	// static: init once only, once copy only on mtd_district(heap)
	private final static float PI = 3.14f;

	static Random random = new Random();
	private static int a = random.nextInt(10);

	public static void main(String[] args) {

		// init once only,
		StaticTst2 finalTst1 = new StaticTst2();
		System.out.println(finalTst1.a);

		StaticTst2 finalTst2 = new StaticTst2();
		System.out.println(finalTst2.a);
		System.err.println(StaticTst2.a);

		// one-copy, belong to cls, store on mtd_district (heap)
		finalTst2.a = 10;
		System.out.println(finalTst2.a);
		System.out.println(finalTst1.a);
		System.err.println(StaticTst2.a);
	}

}
