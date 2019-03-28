package sxt.io08_01;

import java.io.File;

// 递归: 方法自己调用自己 
//1.递归头: 何时结束递归
//2.递归体:重复调用**

public class Recursion {
	public static void main(String[] args) {
		System.out.println("---1----------------------");
		printTen(1);

		System.out.println("---2_0----------------------");
		printName(new File("src/sxt"), 0);

		System.out.println("---2_1----------------------");
		dir(new File("src/sxt"), 0);

		System.out.println("---3----------------------");
		f(6);

		System.out.println("---4----------------------");
		count(new File("src/sxt"));
		System.out.println("count: " + len);

		long size = dirSize(new File("src/sxt"));
		System.out.println("dirSize: " + size);
	}

	// ** f(n) = f(n-1) + n, f(1) = 1
	static int mtd0(int n) {
		if (1 == n) {
			return 1;
		}

		return mtd0(n - 1) + n;
	}

	// 1. 打印1-10的数
	// f(n) = print(n) + f(n -1), f(1) = print(1) + 1
	public static void printTen(int n) {
		if (n > 10) { // 1. end condition, 递归头: 结束递归
			return;
		}
		System.out.println(n);
		printTen(n + 1);// 2. mtd(x), 递归体:方法自己调用自己
	}

	// 2. n!: f(n) = f(n-1)*n, f(1) = 1 => f(n)= a. if(1 ==n) return_1 1; b. return_2 f(n-1)*n;
	static int f(int n) {
		int t = 0;
		if (1 == n) {
			return 1; // 1. end, recurs_head
		}

		t = f(n - 1) * n; // 2. f(x)
		System.out.println("n = " + n + ", t = " + t);
		return t;
	}

	// 3. reqs: all file name below dir
	// f(dir) = print(cur_dir) + a. end: if(file == cur_dir) return_1; b. get below_dir {f(below_dir)}, xxx, return_2;
	public static void printName(File src, int deep) {
		// 控制前面层次
		for (int i = 0; i < deep; i++) {
			System.out.print("-");
		}
		// 打印名称
		System.out.println(src.getName());
		if (null == src || !src.exists()) { // end, 递归头
			return;
		} else if (src.isDirectory()) { // 目录
			for (File s : src.listFiles()) {
				printName(s, deep + 1); // mtd(...) 递归体
			}
		} else {

		}
		return;
	}

	// reqs: print tree of files
	static void dir(File src, int deep) {
		// parameter illegal
		if (null == src || !src.exists()) {
			return;
		}

		String str = "";
		for (int i = 0; i < deep; i++) {
			str += "-";
		}
		System.out.println(str + src.getName());

		// ** a. end condition
		if (!src.isDirectory()) {
			return; // ** return_1
		}

		// ** b. recurse
		for (File file : src.listFiles()) {
			dir(file, deep + 1);
		}
		return; // ** return_2
	}

	// ** reqs: calc dir size
	// ** flow: a. if(src == file){return len}; b. get listFiles(len += dirSize(src)) return len;
	static long dirSize(File src) {
		len = 0;
		// a. end ctx
		if (src.isFile()) {
			return src.length(); // return_1
		}

		// b. recurse
		for (File file : src.listFiles()) {
			len += dirSize(file);
		}

		return len;
	}

	// 4. dir size
	private static long len = 0;

	public static void count(File src) {
		// 获取大小

		if (null != src && src.exists()) {
			if (src.isFile()) { // 大小
				len += src.length();
			} else { // 子孙级
				for (File s : src.listFiles()) {
					count(s);
				}
			}
		}
	}
}
