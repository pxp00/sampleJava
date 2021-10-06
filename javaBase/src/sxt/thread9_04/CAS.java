package sxt.thread9_04;

import java.util.concurrent.atomic.AtomicInteger;

//1. 催促，赶紧做完！ 还有多少个没做 -> 压力山大
//2. 做一个收获一个：a word is a progress && focus on -> grow up & happiness

//atomicInteger ticketNum = new AtomicInteger(3);
//Integer left = ticketNum.get(); ticketNum.decrementAndGet(), data--;
// print
// reqs: rob tickets
// flow: multi_thread data--// AtomicInteger, obj_x.decrementAndGet()
public class CAS { // compare and set
	public AtomicInteger ticketNum = new AtomicInteger(3);

	void mtd1() {
		for (int i = 0; i < 5; i++) {
			new Thread(() -> { // cls_local
				// change var (atomic)
				Integer left = ticketNum.decrementAndGet(); // data--

				// judge
				if (left <= 0) {
					System.out.println("without ticket");
					return;
				}

				// blocked 200ms
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("left ticket: " + left);
			}).start();
		}
	}

	void mtd0() {
		for (int i = 0; i < 5; i++) {
			new Thread(() -> { // cls_local
				Integer left = ticketNum.get();

				// judge
				if (left <= 0) {
					System.out.println("mtd0--> without ticket");
					return;
				}

				// blocked 200ms
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// change var (atomic)
				left = ticketNum.decrementAndGet(); // data--

				System.out.println("mtd0--> left ticket: " + left);
			}).start();
		}
	}

	public static void main(String[] args) {
		CAS cas = new CAS();
		cas.mtd0();
		cas.mtd1();
	}

	// lambda expression: ()-> {...}
	static void mtd2() {
		new Thread(() -> {
			// ...
		}).start();
	}
}
