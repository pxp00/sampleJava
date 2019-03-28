/**
 * 
 */
package sxt.io08_02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//OutputStream: write, flush, close
//InputStream: read, close
public class FileIOStream {

	public static void main(String[] args) {

		byteOutputFile();
		byteFileInput();

		bytesFileInput();
		bytesOutputFile();
	}

	static void byteOutputFile() {
		boolean flag = false;
		String str = "你a中国";
		byte[] bytes = new byte[1024];
		OutputStream os = null;

		// 1. create obj_file: link java & file by os
		File dst = new File("src/sxt/io08_02/hello.txt");

		try {
			flag = dst.createNewFile();
			System.out.println(flag);
			// 2. create obj_stream
			os = new FileOutputStream(dst);

			// 3. operate
			bytes = str.getBytes(); // string -> bytes
			System.out.println("bytesLen = " + bytes.length); // ** bytes is a ref, 10
			System.out.println("strLen = " + str.length()); // ** 4

			os.write(bytes, 0, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 4. close
			if (null != os) { // **
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	static void byteFileInput() {
		FileInputStream fis = null;
		int ctx = -1;
		// 1. create obj_file: link java & file by os
		File src = new File("src/sxt/io08_02/hello.txt");

		try {
			// 2. stream
			fis = new FileInputStream(src); // read int -> char
			// 3. operate
			while (-1 != (ctx = fis.read())) { // ** read 1 byte, file end = -1
				System.out.println((char) ctx);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // ** relese sys_src
			if (null != fis) { // ** null != fis
				try {
					// 4. release
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static void bytesOutputFile() {
		String str = "2. hello world";
		OutputStream os = null;
		byte[] bytes = null; // ** bytes is ref

		// 1. dst
		File dst = new File("src/sxt/io08_02/hello.txt");
		try {
			// 2. stream
			os = new FileOutputStream(dst);

			// 3. operate
			bytes = str.getBytes();
			System.out.println("2. bytesLen = " + bytes.length); // ** bytes is a ref
			os.write(bytes, 0, bytes.length);
			os.flush();// ** like eject Udisk
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != os) { // ** 4. release sys_res
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static void bytesFileInput() {
		InputStream is = null;
		String str = null;
		byte[] bytes = new byte[2];
		int len = 0;

		// 1. create obj_file: link java & file by os
		File src = new File("src/sxt/io08_02/hello.txt");
		System.out.println("bytesLen = " + bytes.length);

		try {
			// 2. inputStream
			is = new FileInputStream(src); // read int -> char

			// 3. operate
			// ** before read, no need clr arrayBytes
			while (-1 != (len = is.read(bytes))) { // ** -1, read finished
				str = new String(bytes, 0, len);
				System.out.println(str + "\n\r" + "len = " + len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { // ** relese sys_src
			if (null != is) { // ** null != fis
				try {
					// 4. release
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
