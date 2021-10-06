package sxt.thread9_02;

//线程不安全: 数据有负数、重复
// reqs: get tickets
// flow:
// 1.judge if(var)
// 2.blocked sleep(200)
// 3.change var
// 4.add flag_terminal
public class TicketSyncUnsafe {
	public static void main(String[] args) { // mtd_static belong to cls
		TicketSyncUnsafe.Ticket ticket = new TicketSyncUnsafe().new Ticket();

		for (int i = 0; i < 3; i++) { // ** if thread destroy(mtd_run return), must new and start again
			Thread t0 = new Thread(ticket, "zhangsan");
			Thread t1 = new Thread(ticket, "lisa");
			Thread t2 = new Thread(ticket, "tangna");
			System.out.println("start0");
			t0.start();

			System.out.println("start1");
			t1.start();

			System.out.println("start2");
			t2.start();
		}
	}

	// synchronized fifo + lock(obj_x(atomic) + code block)
	class Ticket implements Runnable { // belong to obj_UnsafeTest01
		int ticketNum = 6; // src_shared
		boolean flag_isAlive = true; // flaga of all thread

		@Override
		public void run() { // locked obj_x(will finish all change atomic)

			// 4. add flag_isAlive
			while (flag_isAlive) { // stop flag
				getTicket();
			}

		}

		private void getTicket() {
			// 1. judge if(var)
			if (ticketNum <= 0) {
				flag_isAlive = false;
				return;
			}

			// 2. blocked
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 3. change var
			System.out.println(Thread.currentThread().getName() + "-->" + ticketNum--);
		}
	}
}
