package sxt.container07_01;

import java.util.HashMap;
import java.util.Map;

public class MapJavaBean {
	public static void main(String[] args) {

		Employee e1 = new Employee(1001, "高淇", 50000);
		Employee e2 = new Employee(1002, "小二", 5000);
		Employee e3 = new Employee(1003, "小五", 6000);
		Employee e4 = new Employee(1001, "小六", 7000);

		Map<Integer, Employee> map = new HashMap<>();
		map.put(1001, e1); // key, obj_bean
		map.put(1002, e2);
		map.put(1003, e3);
		map.put(1001, e4);

		Employee emp = map.get(1001);
		System.out.println(emp.getEname());

		System.out.println(map); // ** key = value.toString()
	}
}

// 雇员信息 java Bean(as a structure)
class Employee {
	private int id;
	private String ename;
	private double salary;

	public Employee(int id, String ename, double salary) {
		super();
		this.id = id;
		this.ename = ename;
		this.salary = salary;
	}

	@Override
	public String toString() { // ** toString use in print
		return "[" + "id:" + id + ", name:" + ename + ", 薪水:" + salary + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
