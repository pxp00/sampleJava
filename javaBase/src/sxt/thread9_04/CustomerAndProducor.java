package sxt.thread9_04;

import java.util.ArrayList;
import java.util.List;

//协作模型:生产者消费者实现方式一:管程法 借助缓冲区
public class CustomerAndProducor {
	public static void main(String[] args) {
		SyncContiainerT container = new SyncContiainerT();
		new Productor(container).start();
		new Consumer(container).start();
	}
}

// 生产者
class Productor extends Thread {
	SyncContiainerT container;

	public Productor(SyncContiainerT container) {
		this.container = container;
	}

	public void run() {
		// 生产
		for (int i = 0; i < 100; i++) {
			System.out.println("生产-->" + i + "个馒头");
			container.push(new Steamedbun(i));
		}
	}
}

// 消费者
class Consumer extends Thread {
	SyncContiainerT container;

	public Consumer(SyncContiainerT container) {
		this.container = container;
	}

	public void run() {
		// 消费
		for (int i = 0; i < 100; i++) {
			System.out.println("消费-->" + container.pop().id + "个馒头");
		}
	}
}

// 馒头
class Steamedbun {
	int id;

	public Steamedbun(int id) {
		this.id = id;
	}
}

// push:
// if var_x full ?
// a. N, wait;
// b. Y, push elements, notify custom, end;

// pop:
// if var_x empty ?
// a. N, wait;
// b. Y, element pop, notify produce, end;

class SyncContiainerT {
	// container save src
	List<Steamedbun> containerBuns = new ArrayList<>();

	// produce
	synchronized void push(Steamedbun bun) {
		// container is empty produce src, container full wait
		while (containerBuns.size() > 10) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		containerBuns.add(bun);
		this.notifyAll();
		return;
	}

	// consume
	synchronized Steamedbun pop() {
		// container have src consume, empty wait
		while (containerBuns.size() <= 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Steamedbun steamedbun = containerBuns.remove(containerBuns.size() - 1);
		this.notifyAll();
		return steamedbun;
	}
}

// 缓冲区
class SynContainer {
	// 1. src
	Steamedbun[] buns = new Steamedbun[10]; // 存储容器
	int count = 0; // 计数器

	// 2. push: produce
	public synchronized void push(Steamedbun bun) {
		// 何时能生产 容器存在空间
		// 不能生产 只有等待
		while (count == buns.length) { // ** use "while" substitute "if", after be notified will judge again
			try {
				this.wait(); // 线程阻塞 消费者通知生产解除 //wait() belong to cls_lang
			} catch (InterruptedException e) {
			}
		}

		// 存在空间 可以生产
		buns[count] = bun; // element add at the end of array
		count++;

		// 存在数据了，通知消费了
		this.notifyAll();
	}

	// 3. pop: consume
	public synchronized Steamedbun pop() {
		// 何时消费 容器中是否存在数据
		// 没有数据 只有等待
		while (count == 0) { // ** after be notified, judge again
			try {
				this.wait(); // 线程阻塞 生产者通知消费解除
			} catch (InterruptedException e) {
			}
		}

		// 存在数据可以消费
		count--;
		Steamedbun bun = buns[count]; // remove element at the end position

		this.notifyAll(); // 存在空间了，可以唤醒对方生产了
		return bun;
	}
}
