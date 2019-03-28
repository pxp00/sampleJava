package sxt.net10_01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebSpider {

	public static void main(String[] args) throws Exception {
		// note: func1 or func2
		func2();
		// mtd0();
	}

	static void mtd0() throws Exception {
		String str;
		// reqs: get JD.com info

		// 1. new URL
		URL url = new URL("https://www.jd.com");

		// 2. open obj_stream
		InputStream is = url.openStream();

		// ** 3. bytes -> string -> buffer (is -> isr -> br)
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// 4. read data and print
		while (null != (str = br.readLine())) {
			System.out.println(str);
		}

		// 5. close
		br.close();
	}

	// mtd2
	static void func2() throws Exception {

		// 1. get url
		URL url = new URL("https://www.dianping.com");

		// 2. access as browser //enter browsers' developer model -> F12
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");

		// 3. get context
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String msg = null;
		while (null != (msg = br.readLine())) {
			System.out.println(msg);
		}

		br.close();
		// 分析
		// 处理。。。。
	}
}