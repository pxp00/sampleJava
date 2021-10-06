package sxt.io08_03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 数据流: 1、写出后读取 2、读取的顺序与写出保持一致
 * 
 * DataOutputStream DataInputStream
 * 
 * @author TW
 *
 */
public class DataSteam {

	public static void main(String[] args) throws IOException {
		mtd0();
	}

	static void mtd0() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));

		// write
		dos.writeByte(1);
		dos.writeUTF("你好中国");
		dos.write(23);
		dos.writeInt(18);
		dos.writeShort(123);
		dos.writeLong(1234);
		dos.writeDouble(123.0);// def is double
		dos.writeFloat(12.03f);// float
		dos.writeBoolean(true);
		dos.flush(); // **
		System.out.println(dos.toString().getBytes().length);

		// read
		byte[] bytes = baos.toByteArray();// ** src => bytes of Ram
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));
		System.out.println(dis.readByte());
		System.out.println(dis.readUTF());
		System.out.println("" + (int) dis.read());
		System.out.println((int) dis.readInt());
		System.out.println((short) dis.readShort());
		System.out.println((short) dis.readLong());
		System.out.println((double) dis.readDouble());
		System.out.println("" + (float) dis.readFloat());
		System.out.println((Boolean) dis.readBoolean());
	}
}
