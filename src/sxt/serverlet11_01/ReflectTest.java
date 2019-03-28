package sxt.serverlet11_01;

/**
 * 反射: 把java类中的各种结构(方法、属性、构造器、类名)映射成一个个的Java对象。 1、获取Class对象 三种方式: Class.forName("完整路径") 2、可以动态创建对象 clz.getConstructor().newInstance()
 * 
 * @author 裴新 QQ:3401997271
 *
 */
public class ReflectTest {
	public static void main(String[] args) throws Exception {

		// 1、obj.getClass()
		Iphone iphone = new Iphone1();
		Class<?> clz = iphone.getClass(); // Class<?> as lVal

		// 2、class/interface.class
		clz = Iphone.class;

		// 3、Class.forName("package.clsName")
		// a. "src" same as the dir_root, package path don't contain "src"
		clz = Class.forName("sxt.serverlet11_01.Iphone1");
		// b. interface polymorphism
		Iphone iphone2 = (Iphone) clz.getConstructor().newInstance();
		iphone2.mtd();
	}
}

interface Iphone {
	void mtd();
}

class Iphone1 implements Iphone {
	public Iphone1() { // ** if ignore, geConstructor exception: NoSuchMethodException
	}

	public void mtd() { // implements mtd_interface must be public
		System.out.println("iphone1 now");
	}
}
