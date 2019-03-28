package sxt.net10_02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

//** send_flow 
//1、使用DatagramSocket 指定端口 创建发送端 
//2、准备数据 一定转成字节数组 
//3、 封装成DatagramPacket 包裹，需要指定目的地 
//4、发送包裹send​(DatagramPacket p) 
//5、释放资源

public class Udp0Client {

	public static void main(String[] args) throws Exception {
		mtd0();
	}

	static void mtd0() throws IOException {
		System.out.println("sending .....");
		// 1. create obj_send terminate
		DatagramSocket client = new DatagramSocket(8888);
		// 2. data(bytes)
		byte[] bytes = "hello world".getBytes();
		// 3. obj_packet
		DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length, new InetSocketAddress("localhost", 6666));
		// 4. obj_send.send(obj_packet)
		client.send(datagramPacket);
		// 5. release obj_send.close()
		client.close();
	}
}
