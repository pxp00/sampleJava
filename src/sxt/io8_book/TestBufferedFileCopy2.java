/**
 * 
 */
package sxt.io8_book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class TestBufferedFileCopy2 {

	public static void main(String[] args) {
		bufRWCopy("abc.txt", "abc-copy.txt");
		System.out.println("finish");
	}

	static void bufRWCopy(String src, String dst) {
		Reader reader = null;
		Writer writer = null;
		BufferedReader bufReader = null;
		BufferedWriter bufWriter = null;
		int len = -1;
		String tmpString = "";

		try {
			reader = new FileReader(src); // ** input obj_str(src_path)
			writer = new FileWriter(dst);
			bufReader = new BufferedReader(reader);
			bufWriter = new BufferedWriter(writer);
			while (null != (tmpString = bufReader.readLine())) { // ** readLine -> obj_str
				bufWriter.write(tmpString);
				bufWriter.newLine(); // ** newLine
			}
			bufWriter.flush(); // ** fush
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != writer) {
					writer.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (null != bufReader) {
					bufReader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (null != bufWriter) {
					bufWriter.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}