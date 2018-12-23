/**
 * 
 */
package Tst;

/**
 * @author pengxuanping 2018-09-05
 */
public class FinalTst {
	private static String s1 = "123";
	public static final String s2 = "123"; // all cls could use, once copy, couldn't be modified as MACRO of C language (PI = 3.14)

	public static void main(String[] args) {
		String s3 = "123";
		final String s4 = "123";
		System.out.println(s1 == s2);
		System.out.println(s3 == s4);
		System.out.println(s1 == s3);
	}
}
