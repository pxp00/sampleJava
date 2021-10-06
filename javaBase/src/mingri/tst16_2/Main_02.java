package mingri.tst16_2;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

//reqs: field
//flow1: new obj -> getClass access -> getDeclaredFields
//flow2: getModifiers, getName, getType, setvalue(setInt, setFloat, setBoolean),setAccessible
public class Main_02 {
	public static void main(String[] args) {
		// get fileds' array: obj_ex -> obj_class -> obj_fieldArry
		boolean isLoopFlag;
		Example_02 example = new Example_02();
		Class<?> exampleC = example.getClass();
		Field[] declaredFields = exampleC.getDeclaredFields();

		for (int i = 0; i < declaredFields.length; i++) { // retrieve
			Field field = declaredFields[i];

			Class<?> type = field.getType(); // type
			System.out.println("type:" + type);

			String string = field.getName(); // name
			System.out.println("name:" + string);

			// key words
			System.out.println("getModifiers:" + Modifier.toString(field.getModifiers()));

			isLoopFlag = true; // loopflag for exception
			while (true == isLoopFlag) {
				isLoopFlag = false;
				try {
					System.out.println("before modify var" + i + " = " + field.get(example)); // value

					if (int.class == type) {
						field.setInt(example, 123);

					} else if (float.class == type) {
						field.setFloat(example, 00.11f);

					} else if (boolean.class == type) {
						field.setBoolean(example, true);
					} else {
						field.set(example, "hello"); // set String
					}

					System.out.println("after modified var" + i + " = " + field.get(example));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.println("throws excption");
					field.setAccessible(true); // setAccessible
					isLoopFlag = true;
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println();
		}
	}

}

// public class Main_02 {
// public static void main(String[] args) {
// Example_02 example = new Example_02();
// Class<?> exampleC = example.getClass(); // 获得所有成员变量
// Field[] declaredFields = exampleC.getDeclaredFields();
// for (int i = 0; i < declaredFields.length; i++) {
// Field field = declaredFields[i]; // 遍历成员变量
//
// // 获得成员变量名称
// System.out.println("名称为：" + field.getName());
//
// // 获得成员变量类型
// Class<?> fieldType = field.getType();
// System.out.println("类型为：" + fieldType);
//
// boolean isAccessible = true;
// while (isAccessible) { // 如果该成员变量的访问权限为private，则抛出异常，即不允许访问
// try {
// isAccessible = false;
// // 获得成员变量值
// System.out.println("修改前的值为：" + field.get(example)); // when access s throws exception
//
// // 判断成员变量的类型是否为int型
// if (fieldType.equals(int.class)) {
// System.out.println("利用方法setInt()修改成员变量的值");
// field.setInt(example, 168); // 为int型成员变量赋值
// // 判断成员变量的类型是否为float型
// } else if
//
// (fieldType.equals(float.class)) {
// System.out.println("利用方法setFloat()修改成员变量的值"); // 为float型成员变量赋值
// field.setFloat(example, 99.9F); // 判断成员变量的类型是否为boolean型
// } else if (fieldType.equals(boolean.class)) {
// System.out.println("利用方法setBoolean()修改成员变量的值"); // 为boolean型成员变量赋值
// field.setBoolean(example, true);
// } else {
// System.out.println("利用方法set()修改成员变量的值"); // 可以为各种类型的成员变量赋值
// field.set(example, "MWQ");
// } // 获得成员变量值
// System.out.println("修改后的值为：" + field.get(example));
// } catch (Exception e) {
// System.out.println("在设置成员变量值时抛出异常，" + "下面执行setAccessible()方法！");
// field.setAccessible(true); // 设置为允许访问
// isAccessible = true;
// }
// }
// System.out.println();
// }
// }
// }
