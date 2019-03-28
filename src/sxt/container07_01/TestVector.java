package sxt.container07_01;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

//** 测试Vector thread_safe
public class TestVector {
	static int mI;

	public static void main(String[] args) {
		mtd0();
		mtd1();
	}

	static void mtd0() {
		List<Integer> vector = new Vector<>(); // vector obj_shared
		for (mI = 0; mI < 1000; mI++) {
			new Thread(() -> {
				vector.add(mI);
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

	static void mtd1() {
		List<Integer> arrayList = new ArrayList<>(); // arrayList obj_shared
		for (mI = 0; mI < 1000; mI++) {
			new Thread(() -> {
				arrayList.add(mI);
			}).start();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("arrayList size = " + arrayList.size());
	}
}
