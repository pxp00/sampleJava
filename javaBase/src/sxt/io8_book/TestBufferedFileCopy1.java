/**
 * 
 */
package sxt.io8_book;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestBufferedFileCopy1 {

	public static void main(String[] args) {

		// // 使用普通字节流实现复制
		// long time3 = System.currentTimeMillis();
		// copyFile2("D:/learn/Java(SXT)/08_IO流技术/166_IO_开篇.mp4", "D:/learn/Java(SXT)/08_IO流技术/166_IO_开篇-copy2.mp4");
		// long time4 = System.currentTimeMillis();
		// System.out.println("普通字节流花费的时间为：" + (time4 - time3));

		bufCopyFile("D:/learn/Java(SXT)/08_IO流技术/166_IO_开篇.mp4", "D:/learn/Java(SXT)/08_IO流技术/166_IO_开篇-copy3.mp4");
	}

	// cls_parent = cls_child, auto convert
	// cls_child = (cls_child) obj_parent, force convert
	// obj_x != null && (cls_x/ cls_x_child) instanceof cls_x -> true
	static void bufCopyFile(String src, String dst) {
		FileInputStream fis = null;

		InputStream bis = null;
		OutputStream os = null, bos = null;
		int tmp;// ** int
		long timer1 = 0, timer2 = 0;

		System.out.println(fis instanceof InputStream); // ** judge var's ctx (null, cls_parent)
		System.out.println(fis instanceof FileInputStream); // null, cls_child
		System.out.println(bis instanceof FileInputStream); // null, cls_child
		// System.out.println(os instanceof FileInputStream);

		timer1 = System.currentTimeMillis(); // ** ret type_long
		try {
			fis = new FileInputStream(src);
			os = new FileOutputStream(dst);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(os);

			System.out.println(fis instanceof InputStream); // obj_child( != null), cls_child/cls_parent -> true
			System.out.println(fis instanceof FileInputStream);
			System.out.println(bis instanceof FileInputStream); // obj_parent, cls_child

			while (-1 != (tmp = bis.read())) { // ** read ret type_int
				bos.write(tmp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		timer2 = System.currentTimeMillis();
		System.out.println("bufferStream use time:" + (timer2 - timer1));
	}

	/** 普通节流实现的文件复制的方法 */
	static void copyFile2(String src, String dec) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		int temp = 0;
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dec);
			while ((temp = fis.read()) != -1) {
				fos.write(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}