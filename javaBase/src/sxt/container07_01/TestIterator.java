package sxt.container07_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// ** 测试迭代器遍历List、Set、Map
public class TestIterator {
	public static void main(String[] args) {
		// tstIteratorList();
		// System.out.println("0 ++++++++++++++++++++++++");

		// testIteratorSet();

		// testIteratorMap();
		tstIteratorMap();

		// testRemove();
	}

	public static void tstIteratorList() {
		List<String> list = new ArrayList<>();
		list.add("aa");
		list.add("bb");
		list.add("cc");

		// retrieve
		for (int index = 0; index < list.size(); index++) {
			System.out.println(list.get(index));
		}

		// retrieve elements
		Iterator<String> itr = list.iterator();
		for (; itr.hasNext();) {
			String tmp = itr.next();
			System.out.println(tmp);
		}
	}

	public static void testIteratorSet() {
		Set<String> set = new HashSet<>();
		set.add("aa");
		set.add("bb");
		set.add("cc");

		// 使用iterator遍历Set
		for (Iterator<String> iter = set.iterator(); iter.hasNext();) {
			String temp = iter.next();
			System.out.println(temp);
		}
	}

	public static void tstIteratorMap() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "aa");
		map.put(2, "bb");
		map.put(3, "cc");

		System.out.println("print Map: " + map);
		// ** map.entrySet
		Set<Entry<Integer, String>> entrySet = map.entrySet(); // ** element type "Entry<k,v>"
		Iterator<Entry<Integer, String>> itr = entrySet.iterator();
		for (; itr.hasNext();) {
			Entry<Integer, String> element = itr.next(); // ** element type "Entry<k,v>"
			System.out.println(element.getKey() + "===" + element.getValue());
		}

		// ** map.keySet
		Set<Integer> keyset = map.keySet();
		Iterator<Integer> itr1 = keyset.iterator();
		for (; itr1.hasNext();) {
			Integer key = itr1.next(); // ** element type "Integer" //** genetic type must be ref_type
			System.out.println(key + "---" + map.get(key));
		}

	}

	// 测试边遍历，边删除
	public static void testRemove() {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add("gao" + i);
		}

		for (int i = 0; i < list.size(); i++) {
			String temp = list.get(i);
			if (temp.endsWith("2")) {
				list.remove(i);
			}
			System.out.println(list.size());
			System.out.println(list.get(i));
		}
	}
}
