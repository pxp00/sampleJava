package sxt.net10_04.chatSum;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TstClient {
	public static void main(String[] args) {

		System.out.println("---------- client --------------");
		// 1. create client
		Socket client = null;
		try {
			client = new Socket("localhost", 8888);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 2. new thread send
		new Thread(new TstSend(client)).start();

		// 3. new thread receive
		new Thread(new TstReceive(client)).start();
	}

}
