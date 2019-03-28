package sxt.net10_01;

import java.net.MalformedURLException;
import java.net.URL;

// 互联网三大基石之一(url -> http -> html);
// URL: 统一资源定位器, 区分资源: 1、协议,  2、域名、计算机,  3、端口: www默认80, 4、请求资源
//** URL: protocol(http) +host(://ww.baidu.com) +port(:80) +path(/index.html) +query(?..&..) + reference(#a)
// obj: getProtocol, getHost, getPort, getPath, getQuery, getRef;
public class URLTest01 {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://www.baidu.com:80/index.html?uname=shsxt&age=18#a");
		// 获取四个值
		System.out.println("协议:" + url.getProtocol());
		System.out.println("域名|ip:" + url.getHost()); // hostName
		System.out.println("端口:" + url.getPort());
		System.out.println("请求资源2:" + url.getPath()); // path
		// 参数
		System.out.println("参数:" + url.getQuery()); // 查询请求 parameter
		// 锚点
		System.out.println("锚点:" + url.getRef()); // reference
		// file = path + query
		System.out.println("请求资源1:" + url.getFile()); // path + query
	}

}
