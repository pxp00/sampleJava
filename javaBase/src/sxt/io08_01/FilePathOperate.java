package sxt.io08_01;

import java.io.File;

public class FilePathOperate {
	public static void main(String[] args) throws Exception {
		// ** current prj work directory
		System.out.println(System.getProperty("user.dir"));// D:\java\code\workspace\JavaBase

		File file = new File(" ");
		System.out.println("" + file.getAbsolutePath()); // D:\java\code\workspace\JavaBase\

		file = new File("src");
		System.out.println(file.getAbsolutePath()); // D:\java\code\workspace\JavaBase\src

		file = new File(""); // D:\java\code\workspace\JavaBase
		System.out.println(file.getAbsolutePath());

		file = new File("/src");// D:\src
		System.out.println(file.getAbsolutePath());

		file = new File("/");// D:\
		System.out.println(file.getAbsolutePath());

		String path = "src/sxt/io8_book";
		System.out.println("---1-------");
		File src = new File(path); // ** only create linking between java and file
		System.out.println(src.getAbsolutePath());
		System.out.println(src.exists());
		System.out.println(src.length()); // ** length_dir incorrect

		System.out.println("名称:" + src.getName()); // ** 名称:io8_book
		System.out.println("路径:" + src.getPath()); // ** all path 路径:src\sxt\io8_book
		System.out.println("绝对路径:" + src.getAbsolutePath()); // 绝对路径:D:\java\code\workspace\JavaBase\src\sxt\io8_book
		System.out.println("父路径:" + src.getParent()); // ** parent all path, 父路径:src\sxt
		System.out.println("父对象:" + src.getParentFile().getName()); // ** obj_file name, 父对象:sxt

		System.out.println("是否文件:" + src.isFile()); // false
		System.out.println("是否文件夹:" + src.isDirectory()); // true

		// ** 文件状态
		src = new File("xxx");
		if (null == src || !src.exists()) { // inexistence
			System.out.println("文件不存在");
		} else {
			if (src.isFile()) { // file
				System.out.println("文件操作");
			} else { // dir
				System.out.println("文件夹操作");
			}
		}

		System.out.println("---2-------");
		path = "src/sxt/io8_book/FileIO.java";
		src = new File(path);
		System.out.println(src.length());

		System.out.println("---3-------");
		// ** file inexist, create obj_file sucessful
		src = new File("D:/xxx/", "IO_study01/IO.png");
		System.out.println(src.getAbsolutePath());
		System.out.println(src.length());
		System.out.println(src.exists());

		// create new file
		System.out.println("---4-------");
		src = new File("src/sxt/io8_book/hello.java");
		boolean flag = src.createNewFile();
		System.out.println(flag);
		flag = src.delete();
		System.out.println(flag);

		System.out.println("---5-------");
		// 不是文件夹
		src = new File("src/sxt/io8_book/IO_study02"); // **
		flag = src.createNewFile();
		System.out.println(flag);
		flag = src.delete();
		System.out.println(flag);

		System.out.println("---6-------");
		// 补充: con com3... 操作系统的设备名，不能正确创建
		src = new File("D:/java300/IO_study01/con");
		flag = src.createNewFile();
		System.out.println(flag);
	}
}
