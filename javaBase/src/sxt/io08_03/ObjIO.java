package sxt.io08_03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjIO {

	public static void main(String[] args) {
		// mtd1();
		mtd2();
	}

	// reqs: ObjIO, byteArray write & read
	static void mtd1() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // RAM(dst)
		try {
			// 1. write to RAM
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos));
			oos.writeObject(new Person("hugo", 18, "male"));
			oos.writeInt(123);
			oos.flush(); // ** flush

			// ** after write to read; 2.read from RAM(bytes: baos.toByteArray())
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(baos.toByteArray()))); // input from RAM(byteArray)
			Person person = (Person) ois.readObject();
			System.out.println(person.toString());

			int num = ois.readInt();
			System.out.println("num = " + num);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// ** ByteArrayStream needn't release system res

		}

	}

	// reqs: ObjOI, File write & read
	static void mtd2() {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			// 1. write to File
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("obj.a"))); // ** file(dst)

			oos.writeObject(new Person("hugo", 18, "male"));
			oos.writeInt(123);
			oos.flush(); // ** flush

			// 2.read from File
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("obj.a")));
			Person person = (Person) ois.readObject();
			System.out.println(person.toString());

			int num = ois.readInt();
			System.out.println("num = " + num);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != oos) {
					oos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (null != ois) {
					ois.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

// ** obj_x implements serializable
class Person implements Serializable {
	private static final long serialVersionUID = 1L; // ** serialVersionUID
	transient String name; // value don't be delivery, var exist still
	int age;
	String sex;

	protected Person(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name = " + name + ", age = " + age + ", sex = " + sex;
	}
}
