/**
 * 
 */
package sxt.thread9_04;

class SyncThread {
	private final Object lock = new Object();

	// cls
	public void foo() throws Exception {
		synchronized (SyncThread.class) { // lock cls
			for (int i = 0; i < 5; i++) {
				System.out.println(">>>foo: " + i); // 1. print
				Thread.sleep(1000); // 2. sleep -> blocked
			}
		}
	}

	// obj_cur
	public void bar() throws Exception {
		synchronized (this) { // lock obj_cur
			for (int i = 0; i < 5; i++) {
				System.out.println("<<<bar: " + i);
				Thread.sleep(1000);
			}
		}
	}

	// obj_lock
	public void cpp() throws Exception {
		synchronized (lock) { // lock obj_x
			for (int i = 0; i < 5; i++) {
				System.out.println("===cpp: " + i);
				Thread.sleep(1000);
			}
		}
	}

	// obj_cur
	public synchronized void der() throws Exception {// lock obj_cur (mtd_obj)
		for (int i = 0; i < 5; i++) {
			System.out.println("!!!der: " + i);
			Thread.sleep(1000);
		}
	}
}

public class SynchronizedTst {

	public static void main(String[] args) {
		final SyncThread syncThread = new SyncThread();
		// cls / mtd_static
		new Thread(() -> {
			try {
				syncThread.foo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		// obj_cur
		new Thread(() -> {
			try {
				syncThread.bar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		// obj_lock
		new Thread(() -> {
			try {
				syncThread.cpp();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		// obj_cur
		new Thread(() -> {
			try {
				syncThread.der();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}