package sxt.io08_01;

import java.io.IOException;

//编码: 字符串-->字节
// 1. encode: string("xxx") ---charSet---> bytes // getBytes(charSet)
// 2. decode: bytes ---charSet---> string("xxx") // String​(byte[] bytes, int offset, int length, String charsetName) 
public class CharSet {

	public static void main(String[] args) throws IOException {
		String msg1 = "世界你好h";
		byte[] bytes = msg1.getBytes();
		msg1 = new String(bytes, 0, bytes.length, "UTF-8");
		System.out.println(msg1);

		String msg = "性命生命使命a";

		System.out.println("---1-------------------");

		// 1. ** encode: string -> bytes
		// "utf8"
		byte[] datas = msg.getBytes(); // 默认使用工程的字符集(UTF-8) //3, 1
		System.out.println(datas.length);

		// UTF-16LE
		datas = msg.getBytes("UTF-16LE"); // 2
		System.out.println(datas.length);

		// GBK
		datas = msg.getBytes("GBK"); // 2, 1
		System.out.println(datas.length);

		System.out.println("---2-------------------");

		// 2. ** decode: bytes -> string
		msg = new String(datas, 0, datas.length, "gbk");
		System.out.println(msg);

		// 编码: "utf-8"
		datas = msg.getBytes(); // 默认使用工程的字符集

		// 解码乱码:
		// 1)、字节数不够
		msg = new String(datas, 0, datas.length - 2, "utf8");
		System.out.println(msg);

		msg = new String(datas, 0, datas.length - 1, "utf8");
		System.out.println(msg);

		// 2)、字符集不统一
		msg = new String(datas, 0, datas.length, "gbk");
		System.out.println(msg);
	}
}
