package sxt.net10_03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * 
 * @author 裴新 QQ:3401997271
 *
 */
// ** 模拟登录 双向 创建客户端
// 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
// 2、操作: 输入输出流操作
// 3、释放资源
public class LoginTwoWayClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("请输入用户名:");
		String uname = console.readLine();
		System.out.print("请输入密码:");
		String upwd = console.readLine();

		// 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、操作: 输入输出流操作

		// ** a.send
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF("uname=" + uname + "&" + "upwd=" + upwd);
		dos.flush();

		// ** b.receive
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String result = dis.readUTF();
		System.out.println(result);
		// 3、释放资源
		dos.close();
		dis.close();
		client.close();
	}

}
