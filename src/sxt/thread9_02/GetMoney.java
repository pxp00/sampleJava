package sxt.thread9_02;

/**
 * 线程安全: 在并发时保证数据的正确性、效率尽可能高 synchronized 1、同步方法 2、同步块 ,目标更明确
 * 
 * @author 裴新 QQ:3401997271
 * 
 */

// reqs: a account multi_person draw money
// flow:
// 1. a account is a object_src;
// 2. person is a obj_thread;

public class GetMoney {
	public static void main(String[] args) {
		Accnt accnt = new Accnt(100);
		new Person(accnt, "hugo", 20).start(); // thread(obj_src, name, money).start
		new Person(accnt, "xixi", 90).start();
		new Person(accnt, "haha", 30).start();
	}
}

class Accnt {
	int mTotalMoney;

	protected Accnt(int totalMoney) {
		mTotalMoney = totalMoney;
	}
}

class Person extends Thread {
	Accnt mAccnt;
	int mDrawMoney; // default, cur_package

	protected Person(Accnt accnt, String name, int drawMoney) {
		super(name); // set thread_name
		mDrawMoney = drawMoney;
		mAccnt = accnt;
	}

	public void run() {
		drawMoney();
	}

	void drawMoney() {
		// 3. double check(debug)
		if (mAccnt.mTotalMoney - mDrawMoney <= 0) {
			return;
		}

		synchronized (mAccnt) { // get monitor of obj_mAccnt, after obj_src changed, next thread could change obj_src
			// 0. judge
			if (mAccnt.mTotalMoney - mDrawMoney <= 0) {
				return;
			}

			// 1. blocked
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 2. change var
			mAccnt.mTotalMoney -= mDrawMoney;
			// this.getName, get threadName
			System.out.println(this.getName() + "draw: " + mDrawMoney + "\n" + "Remain Money:" + mAccnt.mTotalMoney);
		}
	}
}
