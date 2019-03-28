package sxt.container07_01;

import java.util.Map;
import java.util.TreeMap;

//** 测试TreeMap的使用  setKey  key排序
public class TestTreeMap {
	public static void main(String[] args) {
		Map<Integer, String> treemap1 = new TreeMap<>();
		treemap1.put(20, "aa");
		treemap1.put(3, "bb");
		treemap1.put(6, "cc");

		// 按照key递增的方式排序
		for (Integer key : treemap1.keySet()) {
			System.out.println(key + "---" + treemap1.get(key));
		}

		System.out.println("+++++++++++++++++++++++++");

		Map<TstEmp, String> treemap2 = new TreeMap<>();
		treemap2.put(new TstEmp(100, "张三", 50000), "张三是一个好小伙");
		treemap2.put(new TstEmp(200, "李四", 5000), "李四工作不积极");
		treemap2.put(new TstEmp(150, "王五", 6000), "王五工作还不错");
		treemap2.put(new TstEmp(50, "赵六", 6000), "赵六是个开心果");

		// 按照key递增的方式排序
		for (TstEmp key : treemap2.keySet()) {
			System.out.println(key + "---" + treemap2.get(key));
		}
	}
}

class TstEmp implements Comparable<TstEmp> { // ** TreeSet: Comparable<TstEmp>
	int mId;
	String mName;
	int mSalary;

	protected TstEmp(int id, String name, int salary) {
		mId = id;
		mName = name;
		mSalary = salary;
	}

	@Override
	public String toString() {
		return "id:" + mId + ", name:" + mName + ", salary:" + mSalary;
	}

	@Override
	public int compareTo(TstEmp o) { // **compareTo, x < mSalary ? 1 : 小的排第 1，升序
		if (o.mSalary < mSalary) {
			return 1;
		} else if (o.mSalary > mSalary) {
			return -1;
		} else {
			if (o.mId < mId) { // x < mSalary ? 1 : 小的排第 -1, 降序
				return -1;
			} else if (o.mId > mId) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
