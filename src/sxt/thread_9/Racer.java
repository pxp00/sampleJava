package sxt.thread_9;

// 模拟龟兔赛跑

// reqs: rabbit and tortoise racer (two racer, same distance, two thread, which one is winner ?)
// flow:
// 1. Thread(r, name_thread).start
// 2. Thread.sleep(1000);
// 3. Thread.currentThread().getName()

public class Racer implements Runnable {
	private String winner;// 胜利者

	public static void main(String[] args) {
		Racer racer = new Racer();
		new Thread(racer, "tortoise").start();
		new Thread(racer, "rabbit").start();
	}

	@Override
	public void run() {
		for (int steps = 1; steps <= 100; steps++) {

			// 模拟休息
			if (Thread.currentThread().getName().equals("rabbit") && steps % 10 == 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "-->" + steps);

			// 比赛是否结束
			boolean flag = gameOver(steps);
			if (flag) {
				break;
			}
		}
	}

	private boolean gameOver(int steps) {
		if (winner != null) { // 存在胜利者
			return true;
		} else {
			if (steps == 100) {
				winner = Thread.currentThread().getName();
				System.out.println("winner==>" + winner);
				return true;
			}
		}
		return false;
	}

	// **
	static void mtd0() {
		// define
		Thread thread0 = new Thread() {
			public void run() {
				System.out.println("hello");
			}
		};

		// start
		thread0.start();
	}

}
