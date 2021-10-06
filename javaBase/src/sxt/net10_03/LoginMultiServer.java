package sxt.net10_03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// ** 模拟登录 多个客户端请求
public class LoginMultiServer {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");

		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		boolean isRunning = true;

		// 2、阻塞式等待连接 accept
		while (isRunning) { // ** one port, multi_thread
			Socket client = server.accept();
			System.out.println("一个客户端建立了连接");
			new Thread(new Channel(client)).start();
		}

		// 3. release
		server.close();
	}

	// 一个channel就代表一个客户端
	static class Channel implements Runnable {
		private Socket client;
		// 输入流
		private DataInputStream dis;
		// 输出流
		private DataOutputStream dos;

		public Channel(Socket client) {
			this.client = client;
			try {
				// 输入
				dis = new DataInputStream(client.getInputStream());
				// 输出
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				release();
			}
		}

		// 接收数据
		private String receive() {
			String datas = "";
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return datas;
		}

		// 释放资源
		private void release() {
			// 4、释放资源
			try {
				if (null != dos) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (null != dis) {
					dis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (null != client) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 发送数据
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String name = "", pwd = "";
			String[] strs = receive().split("&");
			name = strs[0];
			pwd = strs[1];
			// blocked tst one port, multi_thread
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (name.equals("hugo") && pwd.equals("123456")) {
				send("login successful");
			} else {
				send("login failed");
			}
			release();
		}
	}
}
