package sxt.io08_03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/// 随机读取和写入流 RandomAccessFile
// mtd as a unit
// setp1(read code): code + reqs -> flow(comment)
// setp2 (write code): reqs -> flow(comment) -> code

// **reqs: a file splite to several files;
public class SplitFileMtd {

	public static void main(String[] args) throws IOException {
		// spliteFile("src/sxt/io08_03/RandTest02.java", "src/dst/", 1024);
		spliteFile1("src/sxt/io08_03/RandTest02.java", "src/dst/", 1024);
	}

	// loop read and save to files
	static void spliteFile(String filePath, String dirPath, int blockSize) {
		FileInputStream src = null;
		FileOutputStream dst = null;
		byte[] bytes = new byte[1024];
		int TotalSize = 0, remainSize = 0, len = 0, i = 0;

		File file = new File(filePath);
		File dir = new File(dirPath);
		dir.mkdirs();

		// read & write to several files
		try {
			src = new FileInputStream(file);
			dst = new FileOutputStream(dirPath + "copy" + (i++) + "java");
			while (-1 != (len = src.read(bytes))) {
				TotalSize += len;
				if (TotalSize <= blockSize) {
					dst.write(bytes);
				} else {
					remainSize = TotalSize - blockSize;
					dst.write(bytes, 0, (len - remainSize)); // finish
					dst.close();

					dst = new FileOutputStream(dirPath + "copy" + (i++) + "java"); // new
					dst.write(bytes, (len - remainSize), remainSize);
					TotalSize = remainSize;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != src) {
					src.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != src) {
					dst.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// flow: read a signed block and save to a file
	static void spliteFile1(String filePath, String dirPath, long blockSize) {
		// totalLen
		File src = new File(filePath);
		long fileSize = src.length();

		// blockNum = Math.ceil(totalLen/blocksize)
		long blockNum = (int) Math.ceil(fileSize * 1.0 / blockSize);

		// mkdirs
		File dst = new File(dirPath);
		dst.mkdirs();

		for (int i = 0; i < blockNum; i++) {
			if (i == (blockNum - 1)) {
				blockSize = fileSize - blockSize * (blockNum - 1);
			}
			getBlockAndSaveToFile(filePath, dirPath, blockSize, i);
			// get a block and save to file
		}
	}

	static void getBlockAndSaveToFile(String filePath, String dirPath, long blockSize, int blockNum) {
		RandomAccessFile src = null;
		RandomAccessFile dst = null;
		int len = -1, totalSize = 0;
		byte[] bytes = new byte[1024];
		try {
			src = new RandomAccessFile(new File(filePath), "r");
			dst = new RandomAccessFile(new File("src/dst/copy" + blockNum + ".java"), "rw");
			src.seek(blockNum * blockSize);
			while (-1 != (len = src.read(bytes))) {
				totalSize += len;
				if (totalSize < blockSize) {
					dst.write(bytes, 0, len);
				} else {
					dst.write(bytes, 0, (int) (len - (totalSize - blockSize)));
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != src) {
					src.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != src) {
					dst.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// ** flow: read a signed block save to file
	static void mtd() {
		File src = new File("src/sxt/io08_03/RandTest02.java");
		// 总长度
		long len = src.length();
		// 每块大小
		int blockSize = 1024;
		// 块数: 多少块
		int size = (int) Math.ceil(len * 1.0 / blockSize);
		System.out.println(size);

		// 起始位置和实际大小
		int beginPos = 0;
		int actualSize = (int) (blockSize > len ? len : blockSize);
		for (int i = 0; i < size; i++) {
			beginPos = i * blockSize;
			if (i == size - 1) { // 最后一块
				actualSize = (int) len;
			} else {
				actualSize = blockSize;
				len -= actualSize; // 剩余量
			}
			System.out.println(i + "-->" + beginPos + "-->" + actualSize);
			split(i, beginPos, actualSize);
		}
	}

	// 指定第i块的起始位置 和实际长度
	public static void split(int i, int beginPos, int actualSize) {
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(new File("src/sxt/io08_03/RandTest02.java"), "r");
			RandomAccessFile raf2 = new RandomAccessFile(new File("src/dst" + i + ".java"), "rw");
			// 随机读取
			raf.seek(beginPos); // ** beginPos
			// 读取
			// 3、操作 (分段读取)
			byte[] flush = new byte[1024]; // 缓冲容器
			int len = -1; // 接收长度
			while ((len = raf.read(flush)) != -1) {
				if (actualSize > len) { // 获取本次读取的所有内容
					raf2.write(flush, 0, len);
					actualSize -= len;
				} else {
					raf2.write(flush, 0, actualSize);
					break;
				}
			}
			raf2.close();
			raf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
