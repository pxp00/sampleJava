package sxt.net10_03;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 熟悉流程 创建服务器 1、指定端口 使用ServerSocket创建服务器 2、阻塞式等待连接 accept 3、操作: 输入输出流操作 4、释放资源
 * 
 * @author 裴新 QQ:3401997271
 *
 */
// ** tcp server
public class TCP0Server {

	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		mtd0();
	}

	static void mtd0() throws IOException {
		// 1. specify port create server
		ServerSocket server = new ServerSocket(8888);
		// 2.blocked and wait connect
		Socket client = server.accept();
		System.out.println("connected  already");
		// 3. inputStream
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
		System.out.println(data);

		// 4. release
		dis.close();
		client.close();
		server.close();
	}

}
