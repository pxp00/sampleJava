package sxt.net10_04.chatSum;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

//** flow
//1. new  thread
//2. loop receive
public class TstReceive implements Runnable {
	Socket mClient;
	DataInputStream mDis;
	boolean mFlagRun;

	// 0. constructor init fields
	public TstReceive(Socket client) {
		mClient = client;
		mFlagRun = true;
		try {
			mDis = new DataInputStream(mClient.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			release();
		}
	}

	// 1.receive
	String receive() {
		String str = "";
		try {
			str = mDis.readUTF();
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
			release();
		}
		return str;
	}

	// 2. loop receive
	public void run() {
		String str = "";
		while (mFlagRun) {
			str = receive();
		}
	}

	// 3. release src
	void release() {
		mFlagRun = false;
		TstSxtUtils.close(mClient, mDis);
	}

}
