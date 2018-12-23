package Tst;

//1. RuntimeException sys deal with/try-catch
//2. checkedException must be try-catch
class TestException {

	static void testThrowsToSys() {
		int a = 1 / 0; // stop all
	}

	public static void test() {
		int a = 2, b = 0;
		try {
			b = a / b;
			System.out.println("remain code in try"); // will not be run
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("arry exception");
		} catch (ArithmeticException e) {
			e.printStackTrace(); // msg & location of exception
			String str = e.getMessage(); // /by zero
			System.out.println("getMessage:" + str);
		} finally { // must be executed
			System.out.println("finally statement1 must be executed");
		}
	}

	public static void testArrException() {
		int[] arr = new int[5];
		try {
			System.out.println(arr[10]);// Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 10
		} catch (Exception e) {
			String str = e.getMessage();
			System.out.println("occurence exception: " + str);
			// e.printStackTrace();
		} finally {
			System.out.println("finally statement2 must be executed");
		}
	}

}

public class ExceptionTst {

	public static void main(String[] args) {
		// TestException.testThrowsToSys();
		TestException.test();
		TestException.testArrException();// output: at cn.hugo.ExceptionDemo.main(ExceptionDemo.java:32)//P + C + M

	}

}
