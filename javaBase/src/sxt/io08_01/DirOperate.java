package sxt.io08_01;

import java.io.File;

public class DirOperate {
	public static void main(String[] args) {
		File dir = new File("src/hello/IO_study01");

		System.out.println("---1----------------");
		// mkdirs(): 上级目录可以不存在，不存在一同来创建
		boolean flag = dir.mkdirs();
		System.out.println(flag);

		flag = dir.delete();
		System.out.println(flag);

		dir = new File("src/hello");
		flag = dir.delete();
		System.out.println(flag);

		// mkdir() : 确保上级目录存在，不存在创建失败
		dir = new File("src/world/IO_study01");
		flag = dir.mkdir();
		System.out.println(flag);

		flag = dir.delete();
		System.out.println(flag);

		System.out.println("---2----------------");
		dir = new File("src");

		// list(), 列出下级名称(file & dir)
		String[] subNames = dir.list(); // String[]
		for (String s : subNames) {
			System.out.println(s);
		}

		// 下级obj_file(file & dir) listFiles()
		File[] subFiles = dir.listFiles(); // File[]
		for (File s : subFiles) {
			System.out.println(s.getAbsolutePath());
		}

		// all roots
		File[] roots = File.listRoots(); // File[], mtd_static
		for (File r : roots) {
			System.out.println(r.getAbsolutePath());
		}

	}
}
