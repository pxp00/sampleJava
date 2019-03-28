package sxt.io08_03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//stream (bytes), read/writer(chars)
public class BufferStreamRW {
	public static void main(String[] args) {
		// mtd0("abc.txt", "abc-copy.txt");
		mtd1("abc.txt", "abc-copy1.txt");
	}

	// req: buffered Stream copy file
	static void mtd1(String src, String dst) {
		byte[] bytes = new byte[1024]; // **
		int len = -1;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(dst));
			while (-1 != (len = bis.read(bytes))) { // ** read bytes, -1 != len
				bos.write(bytes, 0, len); // len: in fact length
			}
			bos.flush(); // ** flush
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != bis)
					bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != bos)
					bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// req: bufferRW copy txt file
	static void mtd0(String src, String dst) {
		String str = ""; // **
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			br = new BufferedReader(new FileReader(src));
			bw = new BufferedWriter(new FileWriter(dst));
			while (null != (str = br.readLine())) { // ** readLine, null != str
				bw.write(str);
				bw.newLine(); // ** newLine
			}
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != br)
					br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != br)
					bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
