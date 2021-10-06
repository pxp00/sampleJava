package sxt.thread9_04;

//code -> flow -> code 
// 可重入锁: 同一线程,给相同对象多次加锁, 不用等待(阻塞) 
public class ReLockTst {
	ReLock lock = new ReLock();
	// ReentrantLock lock = new ReentrantLock(); // ** in fact use

	public void mtd1() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		mtd2();
		lock.unlock();
		System.out.println(lock.getHoldCount());
	}

	public void mtd2() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		// ...................
		lock.unlock();
		System.out.println(lock.getHoldCount());
	}

	public static void main(String[] args) throws InterruptedException {
		ReLockTst test = new ReLockTst();
		test.mtd1();
		Thread.sleep(1000);
		System.out.println(test.lock.getHoldCount());
	}

}

// 可重入锁
class ReLock {
	private int state = 0; // ** 0 - unlocked, n - locked times
	private Thread lockedBy = null; // ** stock ref_thread

	// mtd_lock
	public synchronized void lock() throws InterruptedException {
		Thread t = Thread.currentThread();
		// while(!= state) // not relock
		while (0 != state && lockedBy != t) { // ** is locked && loackedBy != thread_cur
			wait();
		}

		state++;
		lockedBy = t;
	}

	// mtd_unlock
	public synchronized void unlock() {
		if (Thread.currentThread() == lockedBy) {
			state--;
			if (state == 0) {
				notify();
				lockedBy = null;
			}
		}
	}

	public int getHoldCount() {
		return state;
	}
}
