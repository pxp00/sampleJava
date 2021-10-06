package sxt.thread9_02;

//reqs: cinema get ticket

//flow: cinema(src), customer(runable), Thread 
// 1. Thread(obj_src, name, ticket) //mtd_run access ticket & src
// 2. Thread(obj_r, name, ticket) // mtd_run of obj_r access ticket & src
// 3. Thread(obj_r(new obj_src, ticket), name) // obj_src, obj_runable, obj_thread(proxy model)

//2:8 -> focus on 20% -> keypoint;  apply -> comprehend(logic flow) & retrieve(repeatedly)

public class HappyCinema {
	public static void main(String[] args) {
		CinemaT cinemaT = new CinemaT(3, "hugoCinema");
		CustomerT customerT0 = new CustomerT(1, cinemaT);
		CustomerT customerT1 = new CustomerT(3, cinemaT);
		new Thread(customerT0, "hugo0").start();
		new Thread(customerT1, "hugo1").start();
	}
}

class CinemaT {
	// field
	int mAvailableSeat;
	String mName;

	protected CinemaT(int availableSeat, String name) {
		mAvailableSeat = availableSeat;
		mName = name;
	}

	// mtd
	boolean bookTicket(int seats) {
		if (mAvailableSeat - seats < 0) {
			return false;
		}

		mAvailableSeat -= seats;
		return true;
	}
}

class CustomerT implements Runnable {
	int mSeats;
	CinemaT mCinemaT;

	protected CustomerT(int seats, CinemaT cinemaT) { // pdpp: protected: package_cur, cls_chiled
		mSeats = seats;
		mCinemaT = cinemaT;
	}

	public void run() {
		synchronized (mCinemaT) {
			if (mCinemaT.bookTicket(mSeats)) {
				System.out.println(Thread.currentThread().getName() + "-->getseat: " + mSeats);
			} else {
				System.out.println(Thread.currentThread().getName() + "seat no enough remain: " + mCinemaT.mAvailableSeat);
			}
		}
	}
}
