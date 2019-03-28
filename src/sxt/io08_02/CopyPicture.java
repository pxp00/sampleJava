/**
 * 
 */
package sxt.io08_02;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//reqs:  file ->  RAM -> copy-file
public class CopyPicture {
	public static void main(String[] args) {
		byte[] ramBytes = null;

		ramBytes = copyFileToRAM("p.png");
		copyRamToFile(ramBytes, "p-copy1.png");
	}

	// file ->(input) cup (output)-> RAM
	static byte[] copyFileToRAM(String pathName) {
		byte[] ramBytes = null;
		File src = new File(pathName);
		byte[] bufBytes = new byte[1024];
		int len = 0;
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(src);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while (-1 != (len = fis.read(bufBytes))) {
				baos.write(bufBytes, 0, len);
			}
			baos.flush();// ** the last time need flush only
			ramBytes = baos.toByteArray();
			System.out.println("RamDataLen = " + ramBytes.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ramBytes;
	}

	// ramBytes(big-dataBlock, 2k) ->(input)CPU(little-dataBlock, x bytes)(output) -> File(append to big-dataBlock)
	static void copyRamToFile(byte[] RamBytes, String pathName) {
		int len = 0;
		FileOutputStream fos = null;
		byte[] bufBytes = new byte[10];
		ByteArrayInputStream bais = new ByteArrayInputStream(RamBytes);

		try {
			fos = new FileOutputStream(pathName);
			while (-1 != (len = bais.read(bufBytes))) {
				fos.write(bufBytes, 0, len);
			}
			fos.flush(); // ** the last time
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
