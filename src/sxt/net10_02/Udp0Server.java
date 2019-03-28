package sxt.net10_02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 基本流程: 接收端 Address already in use: Cannot bind 同一个协议下端口不允许冲突 1、使用DatagramSocket 指定端口 创建接收端 2、准备容器 封装成DatagramPacket 包裹 3、阻塞式接收包裹receive​(DatagramPacket p) 4、分析数据 byte[] getData​() getLength​()
 * 5、释放资源
 * 
 * @author 裴新 QQ:3401997271
 *
 */
public class Udp0Server {

	public static void main(String[] args) throws Exception {
		mtd0();
	}

	static void mtd0() throws IOException {
		System.out.println("recieving .....");
		// 1. create server_receive
		DatagramSocket datagramSocket = new DatagramSocket(6666);

		// 2. container
		byte[] container = new byte[1024 * 60]; // ** 60K
		DatagramPacket datagramPacket = new DatagramPacket(container, container.length);

		// 3. receive
		datagramSocket.receive(datagramPacket); // ** blocked receive

		// 4. ** analysis data bytes -> string
		byte[] bytes = datagramPacket.getData();
		int len = datagramPacket.getLength();
		System.out.println(new String(bytes, 0, len));

		// 5.release
		datagramSocket.close();
	}
}
