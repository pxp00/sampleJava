/**
 * 
 */
package sxt.io08_02;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

//analysis(input): reqs(mtd) &  code -> flow 
//feedback(output, write): reqs -> flow -> code 
public class ByteArrayStreamTst {
	public static void main(String[] args) {
		BAInputStream();
		BAOutputStream();
	}

	// 1. str -> chars, str -> bytes
	// str.toCharArray
	// str.getBytes

	// 2. chars -> str, bytes -> str
	// new String(chars, 0, chars.length);
	// new String(bytes, 0, bytes.length);

	// reqs: RAM(bytes) -> CPU(bytes -> string)
	static void BAInputStream() {
		// 1. RAM data
		byte[] bytes = "hello 你好!".getBytes(); // Ram big-dataBlock
		byte[] bufBytes = new byte[16]; // buffer
		int len = 0;

		// 2. inputStream
		ByteArrayInputStream abis = new ByteArrayInputStream(bytes); // ** without close, JC will release auto
		try {
			// 3. read to CPU
			while (-1 != (len = abis.read(bufBytes))) {
				System.out.println("readLen = " + len + "\n" + "bufBytesLen = " + bufBytes.length);
				String str = new String(bufBytes, 0, len);
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// reqs: CPU(string ->bytes) -> RAM(bytes -> string)
	static void BAOutputStream() {
		System.out.println("BAOutputStream------------------");
		// 1. CPU data
		String src = "hello 你好！", str2 = null;
		byte[] bytes = src.getBytes(); // string -> bytes

		// 2. stream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		// 3. write to RAM
		baos.write(bytes, 0, bytes.length);

		// 4. verify print
		str2 = baos.toString(); // ** RAM data toString
		System.out.println(str2);
	}
}
