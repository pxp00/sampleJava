package sxt.net10_04.chatSum;

import java.io.Closeable;

//** 
public class TstSxtUtils {
	// ** release src
	public static void close(Closeable... targets) {
		for (Closeable target : targets) { // ** element: list
			try {
				if (null != target) {
					target.close();
				}
			} catch (Exception e) {

			}
		}
	}
}
