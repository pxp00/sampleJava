package sxt.io08_04;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

// file or dir size
public class CIOTst {

	public static void main(String[] args) {
		srcSize();
	}

	// calc src size
	static void srcSize() {
		// dirSize
		String src = "src/sxt";
		long srcSize = FileUtils.sizeOf(new File(src));
		System.out.println("srsSize = " + srcSize);

		// file Size
		src = "src/sxt/io08_04/CIOTest01.java";
		srcSize = FileUtils.sizeOf(new File(src));
		System.out.println("srsSize = " + srcSize);
	}

	// write
	public static void write(String[] args) throws IOException {
		// 写出文件
		FileUtils.write(new File("bcd.txt"), "学习是一件伟大的事业\r\n", "UTF-8");
		FileUtils.writeStringToFile(new File("bcd.txt"), "学习是一件辛苦的事业\r\n", "UTF-8", true); // ** true: append
		FileUtils.writeByteArrayToFile(new File("bcd.txt"), "学习是一件幸福的事业\r\n".getBytes("UTF-8"), true);

		// 写出列表
		List<String> datas = new ArrayList<String>();
		datas.add("马云");
		datas.add("马化腾");
		datas.add("弼马温");

		FileUtils.writeLines(new File("bcd.txt"), datas, "。。。。。", true);
	}

	// read
	public static void read(String[] args) throws IOException {
		// 读取文件
		String msg = FileUtils.readFileToString(new File("abc.txt"), "UTF-8");
		System.out.println(msg);
		byte[] datas = FileUtils.readFileToByteArray(new File("abc.txt"));
		System.out.println(datas.length);

		// 逐行读取
		Collection<String> msgs = FileUtils.readLines(new File("abc.txt"), "UTF-8");
		for (String string : msgs) { // ** for each
			System.out.println(string);
		}

		LineIterator it = FileUtils.lineIterator(new File("abc.txt"), "UTF-8");
		while (it.hasNext()) {
			System.out.println(it.nextLine());
		}
	}

	// copy
	public static void copyTst(String[] args) throws IOException {
		// 复制文件
		FileUtils.copyFile(new File("p.png"), new File("p-copy.png"));
		// 复制文件到目录
		FileUtils.copyFileToDirectory(new File("p.png"), new File("lib"));
		// 复制目录到目录
		FileUtils.copyDirectoryToDirectory(new File("lib"), new File("lib2"));
		// rename
		FileUtils.copyDirectory(new File("lib"), new File("lib2"));

		// 拷贝URL内容
		String url = "https://pic2.zhimg.com/v2-7d01cab20858648cbf62333a7988e6d0_qhd.jpg";
		FileUtils.copyURLToFile(new URL(url), new File("marvel.jpg"));

		String datas = IOUtils.toString(new URL("http://www.163.com"), "gbk");
		System.out.println(datas);
	}

	// listFils: child
	static void listFils() {
		String src = "src/sxt";
		Collection<File> collSrc = null;

		System.out.println("-------1--------------");

		// ** use directory recurse, DirectoryFileFilter.INSTANCE
		collSrc = FileUtils.listFiles(new File(src), EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);

		for (File file : collSrc) {
			System.out.println(file.getAbsolutePath());
		}

		System.out.println("-------2--------------");
		// ** suffix, new SuffixFileFilter("java")
		collSrc = FileUtils.listFiles(new File(src), new SuffixFileFilter("java"), DirectoryFileFilter.INSTANCE);

		for (File file : collSrc) {
			System.out.println(file.getAbsolutePath());
		}

		System.out.println("-------3--------------");
		// ** or, FileFilterUtils.or(new SuffixFileFilter("java"), new SuffixFileFilter("class")
		collSrc = FileUtils.listFiles(new File(src), FileFilterUtils.or(new SuffixFileFilter("java"), new SuffixFileFilter("class"), EmptyFileFilter.EMPTY), DirectoryFileFilter.INSTANCE);
		for (File file : collSrc) { // ** element : list
			System.out.println(file.getAbsolutePath());
		}

		System.out.println("--------4-------------");
		// ** and, FileFilterUtils.and(new SuffixFileFilter("java"), EmptyFileFilter.NOT_EMPTY)
		collSrc = FileUtils.listFiles(new File(src), FileFilterUtils.and(new SuffixFileFilter("java"), EmptyFileFilter.NOT_EMPTY), DirectoryFileFilter.INSTANCE);

		for (File file : collSrc) {
			System.out.println(file.getAbsolutePath());
		}
	}
}
