/**
 * 
 */
package sxt.io8_book;

public class TransferVal {

	int id; // id
	String name; // 账户名
	String pwd; // 密码

	public TransferVal(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void testParameterTransfer01(TransferVal u) {
		u.name = "高小八";
	}

	public void testParameterTransfer02(TransferVal u) {
		u = new TransferVal(200, "高三");
	}

	public static void main(String[] args) {
		TransferVal u1 = new TransferVal(100, "高小七");

		// u1.field be changed
		u1.testParameterTransfer01(u1);
		System.out.println(u1.name);

		// u1 haven't change, u1 copy to u
		u1.testParameterTransfer02(u1); // u1 copy to u, use u, u don't be copied to u1
		System.out.println(u1.name);
	}

}
