package sxt.net10_04.chatSum;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// ** flow
// 1. new thread
// 2. loop get from console and send by clients
public class TstSend implements Runnable {
	Socket mClient;
	DataOutputStream mDos;
	BufferedReader mBr;
	boolean mFlagRun;

	// 0. constructor init field
	public TstSend(Socket client) {
		mClient = client;
		try {
			mFlagRun = true;
			mDos = new DataOutputStream(client.getOutputStream());
			mBr = new BufferedReader(new InputStreamReader(System.in));// ** in type is InputStream

			// "input clientName"
			System.out.println("please input client Name: ");

			// get console str
			String clientName = getStrFromConsole();

			// send
			send(clientName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 1. get console str
	String getStrFromConsole() {
		String str = "";
		try {
			return mBr.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	// 2. send str of console
	void send(String str) {
		try {
			mDos.writeUTF(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 3. loop send
	@Override
	public void run() {
		String str = "";
		while (mFlagRun) {
			str = getStrFromConsole();
			if (!str.equals("")) {
				send(str);
			}
		}
	}

	// 3. release
	void release() {
		mFlagRun = false;
		TstSxtUtils.close(mClient, mDos, mBr);
	}
}
