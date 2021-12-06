package tst.exception;

//1. extends cls_exception
class DefaultException extends Exception { // custom exception

	// sub_cls must extends sup_cls constructor(init function)
	public DefaultException(String msg) {
		super(msg);
	}

}

class TestMethodException {
	public void testMethodThrowException(int a, int b) throws Exception { // throws exception to mtd_sup
		int c;
		c = a / b; // runtime exception
		System.out.println(a + "/" + b + "=" + c);
	}
}

public class CustomException {

	public static void main(String[] args) {

		// alt + shift + z
		try {
			// 2.meet condition, throw obj_exceptiona
			throw new DefaultException("自定义异常！ "); // throw obj_exception
		} catch (Exception e) {
			System.out.println(e);// output: cn.hugo.DefaultException: 自定义异常！// package + class

			String str = e.getMessage(); // str
			System.out.println(str);
		}

		TestMethodException excpt = new TestMethodException();
		try {
			excpt.testMethodThrowException(1, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
