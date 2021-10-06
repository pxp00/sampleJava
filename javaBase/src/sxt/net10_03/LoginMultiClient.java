package sxt.net10_03;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

// 1、建立连接:使用Socket创建客户端+服务的地址和端口
// 2、操作:输入输出流操作
// 3、释放资源**

//** reqs:
// 1. client(name & pwd) -> server;
// 2. server(multi_thread, result) -> client(display ret)

public class LoginMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");

		// 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888); // ** client(server IP & PORT)

		// 2、操作: 输入输出流操作 先请求后响应

		new LoginMultiClient().new SendT(client).send();
		new LoginMultiClient().new ReceiveT(client).receive();
		client.close();
	}

	// ** obj_send
	class SendT {
		Socket mClient;
		DataOutputStream mDos;

		public SendT(Socket client) throws IOException {
			mClient = client;
			mDos = new DataOutputStream(mClient.getOutputStream());
		}

		void send() throws IOException {
			mDos.writeUTF(consoleInput());
			mDos.flush();
		}

		private String consoleInput() {
			String str = "";

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				System.out.println("plz input user name: ");
				str = br.readLine();
				str += "&";
				System.out.println("plz input pwd: ");
				str += br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
	}

	// ** obj_receive
	class ReceiveT {
		Socket mClient;
		DataInputStream mDis;

		protected ReceiveT(Socket client) throws IOException {
			mClient = client;
			mDis = new DataInputStream(client.getInputStream());
		}

		void receive() throws IOException {
			String str;
			str = mDis.readUTF();
			System.out.println(str);
		}
	}
}
