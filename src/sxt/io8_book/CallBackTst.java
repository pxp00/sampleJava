
package sxt.io8_book;

//-------------------------fc analysis ------------------------------------
interface IFc {
	void fc(int x); // private, default, protected, public
}

class A {
	// local_cls
	class C implements IFc {
		public void fc(int x) {
			System.out.println("hello cls_local");
		}

		void mtdC() {
		}
	}

	void f1(IFc fc) { // ** parameter is ref
		fc.fc(1);// ** ef.callback
	}

	// 1. cls_anomynous
	void mtd1() {
		f1(new IFc() {
			public void fc(int x) {
				System.out.println("hello cls_anonymous");
			}
		});
	}

	// 2. cls_ex
	void mtd2() {
		B b = new B();
		f1(b);
	}

	// 3. cls_local
	void mtd3() {
		C c = new C();
		f1(c);
	}
}

class B implements IFc {
	public void fc(int x) {
		System.out.println("hello cls_ex");
	}

	void mtdB() {
	}
}
// ----------------------------------------------------------------

// f0(cls_A) -> f1(cls_B) -> callback(cls_A)
public class CallBackTst {
	public static void main(String[] args) {
		Gao gao = new Gao();
		Liu liu = new Liu(gao);
		// 小刘问问题
		liu.askQuestion("学习Java选哪家机构呢？");
	}
}

// ** interface delivery mtd_x by ref_interface
interface CallBack {
	public void answer(String result);
}

class Liu implements CallBack {
	private Gao gao;

	public Liu(Gao gao) { // initial
		this.gao = gao;
	}

	public void askQuestion(String question) {
		gao.execute(this, question); // invoke mtd_gao
	}

	// ** callback
	@Override
	public void answer(String result) {
		System.out.println("小高告诉小刘的答案是：" + result);
	}
}

class Gao {
	// ** f(fc); delivery mtd_cb by ref_interface, mtd_gao(interface_ref,xxx)
	public void execute(CallBack callBack, String question) {
		String result = "学Java当然去北京尚学堂";
		System.out.println("小刘问的问题是：" + question);

		// sleep
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// invoke callback
		callBack.answer(result); // ** mtd_callback mtd_liu
	}
}
