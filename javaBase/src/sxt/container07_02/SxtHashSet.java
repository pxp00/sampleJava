package sxt.container07_02;

import java.util.HashMap;

/**
 * 手动实现一个HashSet，更深刻理解HashSet底层原理
 * 
 * @author 高淇
 *
 */
public class SxtHashSet {

	private HashMap<Object, Object> map;

	private static final Object PRESENT = new Object(); // ** fixed value

	public SxtHashSet() {
		map = new HashMap<Object, Object>();// init create a map
	}

	public int size() {
		return map.size();
	}

	public void add(Object o) {
		map.put(o, PRESENT); // ** use hashMap realize, value is fixed
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (Object key : map.keySet()) { // map.keySet, foreach
			sb.append(key + ",");
		}
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	public static void main(String[] args) {
		SxtHashSet set = new SxtHashSet();
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");

		System.out.println(set);
	}
}
