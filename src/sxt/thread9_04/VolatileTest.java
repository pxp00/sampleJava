package sxt.thread9_04;

// volatile: 保证数据的同步，也就是线程间可见性
public class VolatileTest {
	private volatile static Boolean isAliveFlag = true;

	public static void main(String[] args) throws InterruptedException {
		// 1.thread_work
		new Thread(() -> {
			while (isAliveFlag) { // 此处不要编写代码
				// loop...
			}
		}).start();

		// 2.thread_main
		Thread.sleep(1000);
		isAliveFlag = false;
	}
}
