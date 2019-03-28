package sxt.thread9_02;

// synchronized: synchronized(obj_x/cls_x){...}
// 1. src: cls/obj_x;
// 2. scope: block code;

// 线程安全: 在并发时保证数据的正确性、效率尽可能高 synchronized: 1、同步方法 2、同步块
public class DoubleCheck {

	public static void main(String[] args) {

		// 一份资源
		SynWeb12306 web = new SynWeb12306();
		// 多个代理
		new Thread(web, "码畜").start();
		new Thread(web, "码农").start();
		new Thread(web, "码蟥").start();
	}
}

class SynWeb12306 implements Runnable {
	// 票数
	private int ticketNums = 10; // src
	private boolean flag = true; // flag_terminate

	@Override
	public void run() {
		while (flag) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			test2();
		}
	}

	// 1. judge var -> 2.change var -> 3.simulate blocked(sleep)

	// 线程安全:尽可能锁定合理的范围(不是指代码 指数据的完整性)
	// double checking
	public void test2() {
		// 0. 1st judge
		if (ticketNums <= 0) {// ** double check, thread terminate; 考虑的是没有票的情况, no need queue always
			flag = false;
			return;
		}

		synchronized (this) {
			// 1. 2nd judge condition
			if (ticketNums <= 0) {// 考虑最后的1张票
				flag = false;
				return;
			}

			// 2. blocked
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 3. change var
			System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
		}
	}

	// 线程安全 范围太大 -->效率低下
	public void test1() {
		synchronized (this) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟延时
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
		}
	}

	void mtd0() {
		// 0. judge, no need queue on obj_x
		if (ticketNums <= 0) {
			flag = false; // terminate
			return;
		}

		synchronized (this) {
			// 1. judge
			if (ticketNums <= 0) {
				flag = false; // terminate
				return;
			}

			// 2. block: some operate
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 3. change var
			ticketNums--;
		}
	}
}