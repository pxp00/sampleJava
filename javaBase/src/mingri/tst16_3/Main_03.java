package mingri.tst16_3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

// reqs: reflect mtd
//flow1: new obj -> getClass access -> getDeclaredMethods
//flow2: getModifiers, getName, getParameterTypes, getExceptionTypes, getReturnType, invoke, setAccessible
public class Main_03 {
	public static void main(String[] args) {
		boolean isLoopFlag;
		// get all mtd_curCls include mtd_private
		Example_03 example_03 = new Example_03();
		Class<?> example_03C = example_03.getClass();
		// get all mtd_curCls
		Method[] methods = example_03C.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];

			// getName
			String mtdName = method.getName();
			System.out.println("mtdName: " + mtdName);

			// getParameterTypes ret: Class<?>
			System.out.println("getParameterTypes:");
			Class<?>[] mtdParameterTypes = method.getParameterTypes();
			for (Class<?> type : mtdParameterTypes) {
				System.out.println("" + type);
			}

			// getTypeReturn ret: Class<?>
			Class<?> mtdReturnType = method.getReturnType();
			System.out.println("getReturnType:" + mtdReturnType);

			// getTypeReturn ret: Class<?>
			System.out.println("getExceptionTypes:");
			Class<?>[] mtdExceptionTypes = method.getExceptionTypes();
			for (Class<?> type : mtdExceptionTypes) {
				System.out.println("" + type);
			}

			// getModifiers
			System.out.println("getModifiers:" + Modifier.toString(method.getModifiers()));

			isLoopFlag = true;
			while (isLoopFlag) {
				isLoopFlag = false;
				try {
					if ("staticMethod".equals(mtdName)) {
						method.invoke(example_03);

					} else if ("publicMethod".equals(mtdName)) {
						System.out.println("returnValue: " + method.invoke(example_03, 123));

					} else if ("privateMethod".equals(mtdName)) {
						Object[] obj = new Object[] { new String[] { "H", "U", "G", "O" } };
						System.out.println("returnValue: " + method.invoke(example_03, obj));

					} else if ("staticMethod".equals(mtdName)) {
						System.out.println("returnValue: " + method.invoke(example_03, 22, 1));
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					method.setAccessible(true); // mtd accessible
					isLoopFlag = true;
				}
				System.out.println();
			}
		}
	}
}

// public class Main_03 {
// public static void main(String[] args) {
// boolean isAccessible;
// Example_03 example = new Example_03();
// Class<?> exampleC = example.getClass();
//
// // 获得所有方法
// // getMethods() get all mtd_public include mtd_super
// Method[] declaredMethods = exampleC.getDeclaredMethods(); // mtd_curCls, inlcude mtd_private
// for (int i = 0; i < declaredMethods.length; i++) {
// Method method = declaredMethods[i]; // 遍历方法
// System.out.println("名称为：" + method.getName()); // 获得方法名称
// System.out.println("是否允许带有可变数量的参数：" + method.isVarArgs());
//
// // 获得所有参数类型
// System.out.println("入口参数类型依次为：");
// Class<?>[] parameterTypes = method.getParameterTypes();
// for (int j = 0; j < parameterTypes.length; j++) {
// System.out.println(" " + parameterTypes[j]);
// }
//
// // 获得方法返回值类型
// System.out.println("返回值类型为：" + method.getReturnType());
//
// // 获得方法可能抛出的所有异常类型
// System.out.println("可能抛出的异常类型有：");
// Class<?>[] exceptionTypes = method.getExceptionTypes();
// for (int j = 0; j < exceptionTypes.length; j++) {
// System.out.println(" " + exceptionTypes[j]);
// }
//
// isAccessible = true;
// while (isAccessible) {
// // 如果该方法的访问权限为private，则抛出异常，即不允许访问
// try {
// isAccessible = false;
// if ("staticMethod".equals(method.getName())) {
// method.invoke(example); // 执行没有入口参数的方法
// } else if ("publicMethod".equals(method.getName())) {
// System.out.println("返回值为：" + method.invoke(example, 168)); // 执行方法
// } else if ("protectedMethod".equals(method.getName())) {
// System.out.println("返回值为：" + method.invoke(example, "7", 5)); // 执行方法
// } else if ("privateMethod".equals(method.getName())) {
// // Object[] parameters = new Object[] { new String[] { "M", "W", "Q" } }; // 定义二维数组
// Object[] parameters = new Object[] { new String[] { "M", "W", "Q" } };
// System.out.println("返回值为：" + method.invoke(example, parameters));
// }
// } catch (Exception e) {
// System.out.println("在执行方法时抛出异常，" + "下面执行setAccessible()方法！");
// method.setAccessible(true); // 设置为允许访问
// isAccessible = true;
// }
// }
// System.out.println();
// }
// }
// }
