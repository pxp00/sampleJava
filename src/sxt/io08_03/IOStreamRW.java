package sxt.io08_03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 转换流: InputStreamReader OutputStreamWriter 1、以字符流的形式操作字节流（纯文本的） 2、指定字符集
 * 
 * @author TW
 *
 */
public class IOStreamRW {

	public static void main(String[] args) {
		// mtd2();
		mtd3();
	}

	// reqs: read a URL to a file
	// Stream(bytes), Reader/ Writer(chars/str)
	static void mtd3() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		String str = "";
		try {
			// ** InputStreamReader/OutputStreamWriter: str -> bytes/ bytes -> str => use charset
			br = new BufferedReader(new InputStreamReader(new URL("http://www.baidu.com").openStream(), "UTF-8"));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"), "UTF-8"));
			while (null != (str = br.readLine())) {
				bw.write(str);
				bw.newLine();
			}
			bw.flush();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != null) {
					bw.close(); // ** release
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// reqs: read/write from console
	static void mtd2() {
		String str = "";
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in, "UTF-8")); // ** InputStreamReader(bytes -> str) => charset
			bw = new BufferedWriter(new OutputStreamWriter(System.out, "UTF-8"));
			while ("exit" != (str = br.readLine())) {
				bw.write(str);
				bw.newLine();
				bw.flush(); // ** flush
				System.out.println("reading ...");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != null) {
					bw.close(); // ** release
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
