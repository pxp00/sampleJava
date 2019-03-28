package sxt.thread_9;

// 静态代理 公共接口: 1、真实角色 2、代理角色
// flow: new obj_client ->  put on proxy -> start()//new WeddingCompany(obj_client).start();

//1. client
class ClientMarry {
	void marry() {
		System.out.println("Marry ing ...");
	}
}

// 2. wedding company(static proxy)
class WeddingCompany {
	ClientMarry mclientMarry;

	void before() {
		System.out.println("company wedding before do something ");
	}

	void end() {
		System.out.println("company wedding end do something ");
	}

	WeddingCompany(ClientMarry clientMarry) {
		mclientMarry = clientMarry; // delivery ref
	}

	void start() {
		before(); // before
		mclientMarry.marry(); // invoke mtd of ref
		end(); // end
	}
}

public class StaticProxy {
	// main
	public static void main(String[] args) {
		new WeddingCompany(new ClientMarry()).start();
	}
}
