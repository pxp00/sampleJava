package sxt.net10_01;

import java.net.InetAddress;
import java.net.UnknownHostException;

// IP:定位一个节点:计算机、路由、通讯设备等 InetAddress: 多个静态方法 1、getLocalHost:本机 2、getByName:根据域名DNS | IP地址 -->IP

// InetAddress: IP <-> string
// 1.cls(static): getLocalHost(), getByName(IP/domain),
// 2.obj: getHostAddress()//IP, getHostName()//domain
public class IPTest {

	public static void main(String[] args) throws UnknownHostException {
		// 使用getLocalHost方法创建InetAddress对象 本机
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr.getHostAddress()); // ** IP(hostAddress)
		System.out.println(addr.getHostName()); // ** domain(hostName)

		// 根据域名得到InetAddress对象
		addr = InetAddress.getByName("www.shsxt.com");
		System.out.println(addr.getHostAddress()); // domain -> IP
		System.out.println(addr.getHostName()); // domain

		// 根据ip得到InetAddress对象
		addr = InetAddress.getByName("123.56.138.186");
		System.out.println(addr.getHostAddress()); // IP
		System.out.println(addr.getHostName()); // 输出IP而不是域名. 如果这个IP地 址不存在或DNS服务器不允许进行IP地址和域名的映射，
	}
}
