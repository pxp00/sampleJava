package sxt.io08_01;

import java.io.File;

// "/" separator (recommend)
// "\" 转义字符
public class FileSeparator {
	//
	public static void main(String[] args) {

		// current prj dir
		System.out.println(System.getProperty("user.dir"));

		String path = "D:\\java300\\IO_study01\\IO.png";
		System.out.println(path);

		// 1、** "/" recommend
		path = "D:/java300/IO_study01/IO.png";
		System.out.println(path);

		// 2、常量拼接
		path = "D:" + File.separator + "java300" + File.separator + "IO_study01";
		System.out.println(path);
	}

}
