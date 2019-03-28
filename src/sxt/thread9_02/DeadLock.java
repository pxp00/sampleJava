package sxt.thread9_02;

// 死锁: 过多的同步可能造成相互不释放资源 从而相互等待，一般发生于同步中持有多个对象的锁

// 避免: 不要在同一个代码块中，同时持有多个对象的锁
// reqs: deadLock(mirror, lipstick)
// flow:
// 1. thread_1: locked obj_mirror -> sleep(blocked -> let thread_2 lock obj_lipstick) -> need get lock of obj_lipstick
// 2. thread_2: locked obj_lipstick ->sleep(blocked ->let thread_1 lock obj_mirror) -> need get lock of obj_mirror

//main
public class DeadLock {
	public static void main(String[] args) {
		new Thread(new MakeUp("佩奇")).start();
		new Thread(new MakeUp("小猪")).start();
	}
}

// 1. lipstick
class Lipstick {

}

// 2. mirror
class Mirror {

}

// 3. makeup
class MakeUp implements Runnable {

	Lipstick lipstick = new Lipstick(); // obj_x var isn't unique (multi_copy)(create obj_makeUp -> new obj_lipstick)
	// static Lipstick lipstick = new Lipstick(); // init once only
	static Mirror mirror = new Mirror(); // obj_x var is unique (one copy only)
	String name;

	public MakeUp(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		operation();
	}

	// thr_1 lock obj_src1 -> blocked(thr_2 lock obj_src2) -> thr_1 get lock of obj_src2
	void operation() {
		if ("佩奇" == name) {
			// a. get lock_lipstick
			synchronized (lipstick) { // obj_x isn't unique
				System.out.println(name + " using lipstick");
				// b. blocked
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// c. get lock_mirror
				synchronized (mirror) {
					System.out.println(name + " using Mirror");
				}
			}
		} else {
			// a. get lock_mirror
			synchronized (mirror) {
				System.out.println(name + "using Mirror");
				// b. blocked
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// c. get lock_lipstick
				synchronized (lipstick) {
					System.out.println(name + " using lipstick");
				}
			}
		}
	}
}
