package mingri.Tst14_3;

import java.util.Iterator;
import java.util.TreeSet;

public class UpdateStu implements Comparable<Object> {
	String name;
	int id;

	public UpdateStu(String name, int id) {
		this.id = id;
		this.name = name;
	}

	public int compareTo(Object o) { // id > obj_x.id ? 1 升序 , x < id ? 1 // 小的排第一个
		UpdateStu upstu = (UpdateStu) o;
		int result = id > upstu.id ? 1 : (id == upstu.id ? 0 : -1); // ** id_last compare with o.id
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		UpdateStu stu1 = new UpdateStu("李同学", 01011); // ** OCT 1011(8) = 521
		UpdateStu stu2 = new UpdateStu("陈同学", 01021);
		UpdateStu stu3 = new UpdateStu("王同学", 01051);
		UpdateStu stu4 = new UpdateStu("马同学", 01012);
		TreeSet<UpdateStu> tree = new TreeSet<>();
		tree.add(stu1);
		tree.add(stu2);
		tree.add(stu3);
		tree.add(stu4);

		for (UpdateStu stu : tree) {
			System.out.println("id = " + stu.getId());
		}

		// **
		Iterator<UpdateStu> it = tree.iterator();
		System.out.println("Set集合中的所有元素：");
		while (it.hasNext()) {
			UpdateStu stu = (UpdateStu) it.next();
			System.out.println(stu.getId() + " " + stu.getName());
		}

		it = tree.headSet(stu2).iterator();
		System.out.println("截取前面部分的集合：");
		while (it.hasNext()) {
			UpdateStu stu = (UpdateStu) it.next();
			System.out.println(stu.getId() + " " + stu.getName());
		}
		it = tree.subSet(stu2, stu3).iterator(); // ** [head, end) 左闭右开区间
		System.out.println("截取中间部分的集合");
		while (it.hasNext()) {
			UpdateStu stu = (UpdateStu) it.next();
			System.out.println(stu.getId() + " " + stu.getName());
		}
	}
}