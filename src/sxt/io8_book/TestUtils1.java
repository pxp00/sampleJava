/**
 * 
 */
package sxt.io8_book;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class TestUtils1 {
	public static void main(String[] args) throws Exception {
		String content = FileUtils.readFileToString(new File("abc.txt"), "UTF-8");
		System.out.println(content);
	}
}

class TestUtils2 {
	public static void copy1(String[] args) throws Exception {
		FileUtils.copyDirectory(new File("d:/aaa"), new File("d:/bbb"), new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				// 使用FileFilter过滤目录和以html结尾的文件
				if (pathname.isDirectory() || pathname.getName().endsWith("html")) {
					return true;
				} else {
					return false;
				}
			}
		});
	}
}

class TestUtils3 {
	public static void copy2(String[] args) throws Exception {
		String content = IOUtils.toString(new FileInputStream("abc.txt"), "gbk");
		System.out.println(content);
	}
}