package sxt.net10_04.chatSum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

//** reqs -> flow -> coding(brave to write) -> debug
//** TCP server accept  mutli_client
public class TstChat {
	static CopyOnWriteArrayList<Channel> allChannels = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws IOException {

		System.out.println("------ server ----------");
		ServerSocket server = new ServerSocket(8888);

		while (true) {
			// 2. blocked wait build connect
			Socket client = server.accept();
			System.out.println("build a new connnect");

			// c. new channel(thread) to receive and send data
			Channel channel = new Channel(client);
			allChannels.add(channel);
			new Thread(channel).start();
		}
	}
}

class Channel implements Runnable {
	Socket mClient;
	DataInputStream mDis;
	DataOutputStream mDos;
	boolean mFlagRun;
	String mClientName;

	// ** init
	protected Channel(Socket client) {
		mClient = client;
		try {
			mFlagRun = true;
			mDis = new DataInputStream(client.getInputStream());
			mDos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mClientName = this.receive();
		this.send("welcome to chat");
		this.otherSend(mClientName + " login", true);
	}

	// receive
	String receive() {
		String str = "";
		try {
			str = mDis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	// send
	void send(String str) {
		try {
			mDos.writeUTF(str);
			mDos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// othersend @xxx: hello world
	void otherSend(String str, boolean isSys) {
		int index = str.indexOf(":");
		boolean flag = str.startsWith("@");
		String targetName = null;
		boolean isPrivate = false;

		System.out.println("recv string is: " + str);
		System.out.println("index = " + index + "\n" + "flag = " + flag);
		// @xxx: hello world
		if (flag && index != -1) {
			targetName = str.substring(1, index);
			System.out.println("targetName: " + targetName);
			for (Channel channel : TstChat.allChannels) {
				System.out.println("channel.mClientName: " + channel.mClientName);
				if (channel.mClientName.equals(targetName)) {
					channel.send(mClientName + " private msg: " + str.substring(index + 1));
					isPrivate = true;
					break;
				}
			}
		}

		if (!isPrivate) {
			for (Channel channel : TstChat.allChannels) {
				if (channel != this) {
					if (isSys) {
						channel.send(str);
					} else {

						channel.send(mClientName + ":" + str);
					}
				}
			}
		}
	}

	// stop run & release src
	void release() {
		mFlagRun = false;
		TstSxtUtils.close(mClient, mDis, mDos);
	}

	// run
	@Override
	public void run() {
		String str = "";
		while (mFlagRun) {
			// receive & send
			str = receive();
			if (!str.equals("")) {
				otherSend(str, false);
			}
		}
	}
}