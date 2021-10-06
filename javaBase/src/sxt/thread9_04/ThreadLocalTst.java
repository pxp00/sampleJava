package sxt.thread9_04;

// ThreadLocal: 分析上下文 环境起点; 1.构造器: 哪里调用 就属于哪里 找线程体; 2.run方法: 本线程自身的;
public class ThreadLocalTst {
	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

	public static void main(String[] args) {
		new Thread(new MyRun()).start();
		new Thread(new MyRun()).start();
	}

	public static class MyRun implements Runnable {
		// thread_main from 1 set to -100
		public MyRun() {
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
			threadLocal.set(-100);
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}

		// thread_work threadLocal = 1
		public void run() {
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}
	}

}
