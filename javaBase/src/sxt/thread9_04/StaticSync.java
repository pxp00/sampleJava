package sxt.thread9_04;

public class StaticSync {
	public static void main(String[] args) {
		SyncThread0 syncThread1 = new SyncThread0();
		SyncThread0 syncThread2 = new SyncThread0();

		Thread thread1 = new Thread(syncThread1, "syncThread1");
		Thread thread2 = new Thread(syncThread2, "syncThread2");
		thread1.start();
		thread2.start();
	}
}

/**
 * 同步线程
 */
class SyncThread0 implements Runnable {
	private static int count;

	public SyncThread0() {
		count = 0; // var_static init once
	}

	public synchronized static void method() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		method();
	}
}