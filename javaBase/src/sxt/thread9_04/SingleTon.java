package sxt.thread9_04;

// DCL单例模式: 懒汉式套路基础上，加入并发控制，保证在多线程环境下，对外只存在一个对象 
public class SingleTon {
	// 3. var_static ->save obj_x
	private static volatile SingleTon instance; // ** volatile 没有volatile其他线程可能访问一个没有初始化的对象

	// 1. private constructor -> avoid be newed obj_x by obj_other
	private SingleTon() {
	}

	// 2. mtd_static ->return obj_x
	public static SingleTon getInstance() {

		if (null != instance) { // check2, ** double-check
			return instance;
		}

		synchronized (SingleTon.class) { // ** synchronized cls_x
			if (null == instance) { // check1
				// new cls_x(): 1、开辟空间; 2、初始化对象信息; 3、返回对象的地址给引用; 可能指令重排
				instance = new SingleTon();
			}
		}
		return instance;
	}

	// main
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			System.out.println(SingleTon.getInstance()); // add1
		});
		t.start();

		System.out.println(SingleTon.getInstance());// add2
	}
}
