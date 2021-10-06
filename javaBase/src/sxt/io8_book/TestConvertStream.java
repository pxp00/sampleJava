/**
 * 
 */
package sxt.io8_book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestConvertStream {
	public static void main(String[] args) {
		tstConvertByte2Char();
	}

	static void tstConvertByte2Char() {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;

		try {
			while (null != (str = br.readLine())) { // ** read str
				bw.write(str); // ** write str
				bw.newLine(); // ** newline
				bw.flush();// ** must to flush
				if ("exit".equals(str)) { // ** compare ctx
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != br) {
					bw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != br) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
