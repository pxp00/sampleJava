package sxt.net10_01;

import java.net.InetSocketAddress;

//port
//1、区分软件 
//2、2个字节 0-65535 UDP TCP 
//3、同一个协议, 端口不能冲突(相同)
//4、定义端口越大越好(1024 ~4915);

//InetSocketAddress
//1. constructor: new InetSocketAddress(ip/domain, port);
//2、obj: getAddress​(), getHostName(), getPort();

public class PortTest {
	public static void main(String[] args) {
		// ** InetSocketAddress include port: InetSocketAddress - InetAddress = port
		InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 8080);
		InetSocketAddress socketAddress2 = new InetSocketAddress("localhost", 9000);
		System.out.println(socketAddress.getHostName()); // ** domain
		System.out.println(socketAddress.getAddress()); // ** domain&IP
		System.out.println(socketAddress2.getHostName()); // ** domain
		System.out.println(socketAddress2.getAddress()); // domain & IP
		System.out.println(socketAddress2.getPort()); // ** port
	}
}