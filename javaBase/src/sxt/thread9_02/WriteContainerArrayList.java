package sxt.thread9_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

// 线程不安全：操作容器 , write to container
public class WriteContainerArrayList {
	public static void main(String[] args) throws InterruptedException {
		UnsafeContainer();
		tstVector();
		copyOnWrite();
		syncContainer();
	}

	static void UnsafeContainer() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
				list.add(Thread.currentThread().getName());
			}).start();
		}

		// ** wait thread run finish
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(list.size());
	}

	static void tstVector() { // ** thread_safe
		List<String> vector = new Vector<>(); // vector obj_shared
		for (int i = 0; i < 1000; i++) {
			new Thread(() -> {
				vector.add(Thread.currentThread().getName());
			}).start();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("vector size = " + vector.size());
	}

	static void copyOnWrite() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
				list.add(Thread.currentThread().getName());
			}).start();
		}

		// ** wait thread run finish
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(list.size());
	}

	static void syncContainer() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
				// 同步块
				synchronized (list) { // ** obj_shared synchronized
					list.add(Thread.currentThread().getName());
				}
			}).start();
		}

		// ** wait thread run finish
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(list.size());
	}
}
