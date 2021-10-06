package sxt.net10_04.chat01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室: 服务器 目标: 实现一个客户可以正常收发消息
 * 
 * @author 裴新 QQ:3401997271
 *
 */
public class Chat {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		mtd0();
	}

	static void mtd0() throws IOException {
		// 1. create server
		ServerSocket server = new ServerSocket(8888);

		// 2.client blocked wait building connect
		Socket client = server.accept();
		System.out.println("server builded a connect");

		// 3. receive data
		DataInputStream dis = new DataInputStream(client.getInputStream()); // ** build a channel of read
		System.out.println("waiting receive ....");
		String str = dis.readUTF();
		System.out.println("server receive: " + str);

		// 4. send data
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(str);
		dos.flush();

		// 5. release
		client.close();
		dis.close();
		dos.close();
	}

	static void mtd1() throws IOException {
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);

		// 2、阻塞式等待连接 accept
		Socket client = server.accept();
		System.out.println("一个客户端建立了连接");

		// 3、接收消息
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String msg = dis.readUTF();

		// 4、返回消息
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();

		// 5. 释放资源
		dos.close();
		dis.close();
		client.close();
	}
}
