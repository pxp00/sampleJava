/**
 * 
 */
package tst.tmp;

import java.util.Random;

//final var could be change only  at init
//static var could be init once only
public class FinalTst {
	Random random = new Random();
	private final int a = random.nextInt(10);

	private static String s1 = "123";
	public static final String s2 = "123"; // all cls could use, once copy, couldn't be modified as MACRO of C language (PI = 3.14)

	public static void main(String[] args) {
		// Q, does constant have one copy only ? A: yes, store at mtd_district belong to cls
		String s3 = "123";
		final String s4 = "123";
		System.out.println(s1 == s2);
		System.out.println(s3 == s4);
		System.out.println(s1 == s3);

		// Q, final array: could change ctx of element or not ?
		int[] a = new int[5];
		final int[] b = { 1, 2, 3, 4, 5 }; // as const of C_language, ref could not change, ref point ctx could change
		b[0] = 6;
		// b = a; //X, could change ref
		System.out.println(b[0]);
		// int *px, *py, x= 3;
		// int* const pa = px;
		// pa = py; //X
		// int const *pa = &x;
		// pa = 4; //X

		// Q, final var: Does it have one copy or not ? A: multi-copy; belong to obj, store on heap
		FinalTst finalTst1 = new FinalTst();
		System.out.println(finalTst1.a);

		FinalTst finalTst2 = new FinalTst();
		System.out.println(finalTst2.a);

		System.out.println(finalTst1.a);

	}

}
