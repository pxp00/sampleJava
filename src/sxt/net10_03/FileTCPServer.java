package sxt.net10_03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 存储文件 创建服务器 1、指定端口 使用ServerSocket创建服务器 2、阻塞式等待连接 accept 3、操作: 输入输出流操作 4、释放资源
 * 
 * @author 裴新 QQ:3401997271
 *
 */
// ** read()从此输入流中读取数据字节。如果没有输入可用，则此方法将阻塞; 如果已到达文件末尾，则返回 -1;
public class FileTCPServer {

	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		Socket client = server.accept(); // ** blocked
		System.out.println("一个客户端建立了连接");
		// 3、操作: 文件拷贝 存储
		InputStream is = new BufferedInputStream(client.getInputStream());
		OutputStream os = new BufferedOutputStream(new FileOutputStream("src/file/tcp1.png"));
		byte[] flush = new byte[2048]; // ** bytes
		int len = -1;

		System.out.println("reading .....");
		len = is.read(flush); // blocked wait read
		System.out.println("read len = " + len);

		while (len != -1) { // ** read
			os.write(flush, 0, len); // ** write
			System.out.println("reading .....");
			len = is.read(flush); // blocked waited
			System.out.println("read len = " + len);
		}
		os.flush();

		// 3、释放资源
		os.close();
		is.close();

		// 4、释放资源
		client.close();
		server.close();
	}

}
