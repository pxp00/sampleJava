package sxt.net10_03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

// ** 上传文件 创建客户端 1、建立连接: 使用Socket创建客户端 +服务的地址和端口 2、操作: 输入输出流操作 3、释放资源
// ** system.in/read()从此输入流中读取数据字节。如果没有输入可用，则此方法将阻塞; 如果已到达文件末尾，则返回 -1;
public class FileTCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("-----Client-----");
		mtd0(); // ** run after server be run
	}

	static void mtd0() throws UnknownHostException, IOException {
		// 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、操作: 拷贝 上传
		InputStream is = new BufferedInputStream(new FileInputStream("src/file/ndl.png"));
		OutputStream os = new BufferedOutputStream(client.getOutputStream());
		byte[] flush = new byte[1024]; // ** bytes
		int len = -1;
		while ((len = is.read(flush)) != -1) { // ** read
			os.write(flush, 0, len); // ** write
			System.out.println("client write len = " + len);
		}
		os.flush();

		// 3、释放资源
		os.close();
		is.close();
		client.close();
	}

	static void mtd1() throws UnknownHostException, IOException {
		// 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);

		// 2、操作: 拷贝 上传
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("please input: ");
		String str = br.readLine();

		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
		dos.writeUTF(str);
		dos.flush();

		// 3、释放资源
		dos.close();
		dos.close();
		client.close();
	}

}
