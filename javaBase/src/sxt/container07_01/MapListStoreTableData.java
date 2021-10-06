package sxt.container07_01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 测试表格数据的存储 ORM思想的简单实验：map表示一行数据，多行数据是多个map；将多个map放到list中
 * 
 * @author 高淇
 *
 */
// id：1, name: zhangsan, sex: male
// id: 2, name: lisi, sex: male
// id: 3, name: tangna, sex: female
public class MapListStoreTableData {

	public static void main(String[] args) {
		mtd0();
	}

	static void mtd0() {
		Map<String, Object> row0 = new HashMap<>();
		row0.put("id", 1);
		row0.put("name", "zhangsan");
		row0.put("sex", "male");

		Map<String, Object> row1 = new HashMap<>();
		row1.put("id", 2);
		row1.put("name", "lisi");
		row1.put("sex", "male");

		Map<String, Object> row2 = new HashMap<>();
		row2.put("id", 3);
		row2.put("name", "tangna");
		row2.put("sex", "female");

		List<Map<String, Object>> rowList = new ArrayList<>();
		rowList.add(row0);
		rowList.add(row1);
		rowList.add(row2);

		System.out.println("0 ++++++++++++++++");

		// ** iterator
		Iterator<Map<String, Object>> itr = rowList.iterator(); // ** Iterator<type_element> = List.iterator();
		for (; itr.hasNext();) {
			Map<String, Object> row = itr.next();
			System.out.println(row); // ** print map
		}

		System.out.println("1 ++++++++++++++++");
		// **
		for (Map<String, Object> row : rowList) { // ** List use foreach
			System.out.println(row); // ** print map
		}

		System.out.println("2 ++++++++++++++++");
		// **
		for (Map<String, Object> row : rowList) {
			System.out.println(row); // ** print map

			Set<String> keySet = row.keySet(); // ** Map<key, value> -> Set<key>
			for (String key : keySet) { // ** Set use foreach
				System.out.print(key + ":" + row.get(key) + "\t");
			}
			System.out.println();

		}

	}

}
