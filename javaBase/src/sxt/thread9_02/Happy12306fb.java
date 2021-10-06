/**
 * 
 */
package sxt.thread9_02;

// reqs: buy trainTicket: client name, needTicketNums, RemainTicket,
// flow: multi_person, buy ticket; thread(obj_run, name, ticketNum), obj_run include src
public class Happy12306fb {
	public static void main(String[] args) {
		WebSever webSever = new WebSever(4);

		new Client(webSever, "zhangsan", 1).start(); // Thread(obj_runable, name, ticketNum).start(); //mtd_run access var_thread
		new Client(webSever, "lisi", 2).start();
	}
}

class Client extends Thread {
	String name;
	int bookTicketNum;

	protected Client(WebSever target, String name, int bookTicketNum) {
		super(target);
		this.name = name;
		this.bookTicketNum = bookTicketNum;
	}
}

class WebSever implements Runnable {
	int remainTicket; // unique src

	protected WebSever(int remainTicket) {
		this.remainTicket = remainTicket;
	}

	// Thread(r, name).start(); => Thread.currentThread.getName(); //obj_x = (objx_thread)Thread.currentThread
	// r implements Runnable{ run(){...}} =>lambda (...)->{...} => parameters & ret & process

	@Override
	public void run() {
		Client client = (Client) Thread.currentThread(); // ** mtd_run of cls_Runable access var of obj_thread
		bookTicket(client);
	}

	synchronized void bookTicket(Client client) { // sync obj_cur(this)

		System.out.println(client.name + " booking ticket");

		// 1. judge if(var)
		if (client.bookTicketNum > remainTicket) {
			System.out.println("ticket num not enough");
			return;
		}

		// 2. blocked
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 3. change var
		remainTicket -= client.bookTicketNum;
		System.out.println("book ticket successfully\n" + "remainTicket: " + remainTicket);
		return;
	}
}