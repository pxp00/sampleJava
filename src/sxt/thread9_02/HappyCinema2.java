package sxt.thread9_02;

import java.util.ArrayList;
import java.util.List;

//list, set -> elements could repeatedly or not
//HashSet, TreeSet -> elements have order or not

//Collection: list, set, map
//1.List(ArrayList, LinkedList)// order, elements could repeatedly
//2.Set(HashSet, TreeSet); //disorder, elements couldn't repeatedly

//Map(k.v):
//1.HashMap //search more faster
//2.TreeMap //have sequence

//reqs: select specific NO._seat, 1,3; 2,5

// flow: src, select 1,3; select 2,5;
// 1. arrayList store No._seat // arr1.add(element)
// 2. copy arr1 to arr2 //arr2.addAll(arr1) have repeated elements ？
// 3. arr1.removeAll(arr2) && judge //arr1_copy -= (arr1_copy ∩ arr2) && arr1_org.size() == arr1_copy.size() + arr2.size()
// 4. ref assigned //arr1_org = arr1_copy

public class HappyCinema2 {
	public static void main(String[] args) {
		// 可用位置
		List<Integer> available = new ArrayList<Integer>();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(6);
		available.add(7);

		// 顾客需要的位置
		// customer0
		List<Integer> seats1 = new ArrayList<Integer>();
		seats1.add(1);
		seats1.add(2);

		// customer1
		List<Integer> seats2 = new ArrayList<Integer>();
		seats2.add(3);
		seats2.add(6);

		SxtCinema c = new SxtCinema(available, "happy sxt"); // unique obj_x
		new Thread(new HappyCustomer(c, seats1), "老高").start();
		new Thread(new HappyCustomer(c, seats2), "老裴").start();
	}
}

// 顾客
class HappyCustomer implements Runnable {
	SxtCinema cinema; // locked obj_x
	List<Integer> seats;

	public HappyCustomer(SxtCinema cinema, List<Integer> seats) {
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		synchronized (cinema) { // locked obj_x
			boolean flag = cinema.bookTickets(seats);
			if (flag) {
				System.out.println("出票成功" + Thread.currentThread().getName() + "-<位置为:" + seats);
			} else {
				System.out.println("出票失败" + Thread.currentThread().getName() + "-<位置不够");
			}
		}
	}
}

// 影院
class SxtCinema {
	List<Integer> available; // 可用的位置
	String name; // 名称

	public SxtCinema(List<Integer> available, String name) {
		this.available = available;
		this.name = name;
	}

	// 购票 //1,3,4,5,6
	public boolean bookTickets(List<Integer> seats) {
		System.out.println("欢迎光临" + this.name + "，当前可用位置为:" + available);

		// copy array
		List<Integer> copy = new ArrayList<Integer>();
		copy.addAll(available); // could be repeated

		// ** arr1.removeAll(arr2) => arr1 -= (arr1 ∩ arr2) && if(org_arr1.size() == arr1_cur.size() + arr2.size())
		copy.removeAll(seats);
		if (available.size() - copy.size() != seats.size()) {
			return false;
		}

		// ref assigned
		available = copy;
		return true;
	}
}
