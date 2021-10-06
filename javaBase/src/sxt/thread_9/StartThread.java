package sxt.thread_9;

//** Q: get & set ? A: source->... // "shift + alt + s" -> Generate getters and setters
//** override mtd ?// "shift + alt + s" -> override/implements methods

// reqs: multi_thread
// flow: a. extends Thread, override run(); b. start(); c. Thread.currentThread.getName();

// 1. extends Thread, override run
public class StartThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("hugo" + i + "coding");
		}
		System.out.println("currentThread: " + Thread.currentThread().getName());// Thread-0
	}

	public static void main(String[] args) {
		// 2. call start()
		new StartThread().start();

		System.out.println("----------------------------");
		System.out.println("currentThread: " + Thread.currentThread().getName());// main
	}
}
