package sxt.thread_9;

// interface
interface IInterest { // interface_x
	int lambda(int a, int b); // mtd_x
}

public class LambdaTst {

	interface TstLambda {
		void mtd(int x, int y);
	}

	public static void main(String[] args) {
		// a, implements interface, ret ref_interface
		// useless: interfaceName, funcName, paramType
		// use: paramName, operation steps, return values
		TstLambda refL = (x, y) -> {
			x = y;
		};

		refL.mtd(1, 2); // b. use mtd

		// 0.
		IInterest interest = new IInterest() { // a. define anonymous cls
			public int lambda(int x, int y) { // override paramName could different
				System.out.println("i like lambda -->" + (x + y));
				return 0;
			}
		};
		interest.lambda(100, 200); // b. use mtd

		// 1.
		interest = (int a, int c) -> { // a. define mtd
			System.out.println("i like lambda -->" + (a + c));
			return a + c;
		};
		interest.lambda(100, 200); // b. use mtd

		// 2.
		interest = (a, c) -> {
			System.out.println("i like lambda -->" + (a + c));
			return a + c;
		};
		interest.lambda(20, 200);

		// 3.
		interest = (a, c) -> {
			return a + c;
		};
		System.out.println(interest.lambda(100, 20));

		// 4.
		interest = (a, c) -> a + c;
		System.out.println(interest.lambda(10, 50));

		// 5.
		// a. implement interface_x (define mtd): parameters & retVal
		interest = (a, c) -> 100; // ref_x = parameters, ret, handle step
		// b. call mtd_x
		System.out.println(interest.lambda(10, 20));

		interest = (a, b) -> 100;
	}
}

// 外部类
class Interest implements IInterest {

	@Override
	public int lambda(int a, int c) {
		System.out.println("i like  -->" + (a + c));
		return a + c;
	}

}