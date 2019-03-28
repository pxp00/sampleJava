
//different thread operate same obj_src at same code block
//different thread operate same obj_src at different code block -> synchronized same obj_src

package sxt.thread9_02;

public class ThreadTerminate implements Runnable {
	String name;
	boolean isLive = true;// 标记变量，表示线程是否可中止；

	public ThreadTerminate(String name) {
		super();
		this.name = name;
	}

	public void run() {
		int i = 0;
		// 当live的值是true时，继续线程体；false结束循环, mtd_run return -> thread terminate
		while (isLive) {
			System.out.println(name + "-->" + Thread.currentThread().getName() + " i=" + (i++));
		}
	}

	public void terminate() {
		isLive = false;
	}

	public static void main(String[] args) {
		ThreadTerminate ttc = new ThreadTerminate("线程A:");
		Thread t1 = new Thread(ttc, "threadName_x");// 新生状态
		t1.start();// 就绪状态 runable

		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + i); // thread_main
		}

		ttc.terminate();
		System.out.println("ttc stop!");
	}
}
