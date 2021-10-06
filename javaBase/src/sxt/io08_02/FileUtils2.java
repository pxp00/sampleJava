package sxt.io08_02;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// try ...with...resource
public class FileUtils2 {
	public static void main(String[] args) {
		// 文件到文件
		try {
			InputStream is = new FileInputStream("abc.txt");
			OutputStream os = new FileOutputStream("abc-copy.txt");
			copy(is, os);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 文件到字节数组
		byte[] datas = null;
		try {
			InputStream is = new FileInputStream("p.png");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			copy(is, os);
			datas = os.toByteArray();
			System.out.println(datas.length);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 字节数组到文件
		try {
			InputStream is = new ByteArrayInputStream(datas);
			OutputStream os = new FileOutputStream("p-copy5.png");
			copy(is, os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void copy(InputStream is, OutputStream os) {
		int len = -1;
		byte[] bytes = new byte[1024];

		try {
			while (-1 != (len = is.read(bytes))) { // read
				os.write(bytes, 0, len); // write
			}
			os.flush(); // ** flush
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
