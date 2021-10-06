/**
 * 
 */
package sxt.io08_02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

//C: char[] chars = char[3];
// sizeof(chars) -> chars total len
// chars[index] access element
// chars, chars + 1, &chars[1]//pointer

//Java: char[] chars = new char[3];
// chars is a ref, char.length -> array total length
// chars[index] could access to element
// chars is ref only: String(chars, offset, cnt); //must have offset

public class FileReadWrite {
	public static void main(String[] args) {
		FWriter();
		FReader();
	}

	// reader/writer string.toCharArray
	// reader/writer: str = new String(CharArray, 0, charArray.length);
	static void FWriter() {
		String str = "你a中国";
		char[] chars = str.toCharArray(); // ** encode: string -> charArray
		File dst = new File("src/sxt/io08_02/hello.txt");
		FileWriter fw = null;

		try {
			fw = new FileWriter(dst);
			fw.write(chars, 0, chars.length); // ** write from head_file
			fw.append("--> append1: hello").append("你好! ");
			fw.write(chars, 0, chars.length); // ** append
			fw.append("--> append2: hello").append("你好! ");
			fw.flush(); // ** as before eject Udisk
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	static void FReader() {
		// 1. create obj_file: link java & file by os
		String encoding = System.getProperty("file.encoding");
		System.out.println("Current file charset" + encoding); // UTF-8
		System.out.println("defaultCharset:" + Charset.defaultCharset().name()); // UTF-8

		File src = new File("src/sxt/io08_02/hello.txt"); // "你a中国"
		char[] chars = new char[1]; // ** char 2bytes
		FileReader fr = null;
		int len = 0;
		String str = null;

		try {
			// 2.reader
			fr = new FileReader(src);

			// 3. operate
			// '中'
			len = fr.read(chars); // 任何字符读取都是2bytes(完整性)
			System.out.println("charsLen = " + len);
			System.out.println("chars ctx = " + chars[0]); // 完整

			str = new String(chars); // ** decode: ArrayChars(2bytes) -> String(disp Chinese)3bytes(UTF-8)

			System.out.println("charsBytesLen = " + str.getBytes().length); // ** 3
			System.out.println("str = " + str);

			// 'a'
			len = fr.read(chars); // 任何字符读取都是2bytes -> 按可见的字符读取(完整性)
			System.out.println("charsLen = " + len);
			System.out.println("chars ctx = " + chars); // ref addr

			str = new String(chars); // ** ArrayChars(2bytes) -> String(disp Chinese)1bytes(UTF-8)

			System.out.println("charsBytesLen = " + str.getBytes().length); // ** 1
			System.out.println("str = " + str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			// 4. close
			if (null != fr) { // **
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
