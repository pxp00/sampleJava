package sxt.thread9_04;

// InheritableThreadLocal:继承上下文 环境的数据 ，拷贝一份给子线程
public class InheritableThreadLocalTst {
	private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

	public static void main(String[] args) {
		threadLocal.set(2);
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());

		// 线程由main线程开辟
		new Thread(() -> {
			// use thread_parent value
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
			threadLocal.set(200);
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}).start();

		// ** value delivery, thread_parent's value is unchanged
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
	}
}
