package sxt.serverlet11_02;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 熟悉SAX解析流程
 * 
 * @author 裴新 QQ:3401997271
 *
 */
public class XmlTest02 {

	public static void main(String[] args) throws Exception {

		System.out.println(System.getProperty("user.dir")); // User’s current working directory
		System.out.println(System.getProperty("java.class.path")); // all class path
		// dir_bin: file:/D:/java/code/workspace/JavaBase/bin/sxt/serverlet11_02/
		System.out.println(XmlTest02.class.getResource(""));
		// dir_bin: file:/D:/java/code/workspace/JavaBase/bin/
		System.out.println("URL: " + Thread.currentThread().getContextClassLoader().getResource(""));
		// default under "src" dir
		System.out.println("URL: " + Thread.currentThread().getContextClassLoader().getResourceAsStream("web.xml"));

		// SAX parse
		// 1、获取解析工厂
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2、从解析工厂获取解析器
		SAXParser parse = factory.newSAXParser();
		// 3、编写处理器
		WebHandler handler = new WebHandler();
		// 4、加载文档 Document 注册处理器 //f_web under dir_src
		parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("web.xml"), handler);
		// parse.parse(file, handler);
		// get data
		WebContext context = new WebContext(handler.getEntitys(), handler.getMappings());

		// 假设你输入了 /login
		String className = context.getClz("/g");
		System.out.println(className);
		Class<?> clz = Class.forName(className); // class<?> use as left value; define generic use class<T>
		Servlet servlet = (Servlet) clz.getConstructor().newInstance(); // polymorphism of interface
		System.out.println(servlet);
		servlet.service(); // invoke mtd of interface
	}

}

class WebHandler extends DefaultHandler {
	private List<Entity> entitys = new ArrayList<Entity>();
	private List<Mapping> mappings = new ArrayList<Mapping>();
	private Entity entity;
	private Mapping mapping;
	private String tag; // 存储操作标签
	private boolean isMapping = false; // ** diff_flag

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (null != qName) {
			tag = qName; // 存储标签名
			if (tag.equals("servlet")) {
				entity = new Entity();// new obj_Bean
				isMapping = false; // set flag
			} else if (tag.equals("servlet-mapping")) {
				mapping = new Mapping();
				isMapping = true;
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contents = new String(ch, start, length).trim();
		if (null != tag) { // 处理了空
			if (isMapping) { // judge flag
				if (tag.equals("servlet-name")) {
					mapping.setName(contents);
				} else if (tag.equals("url-pattern")) {
					mapping.addPattern(contents);
				}
			} else { // 操作servlet
				if (tag.equals("servlet-name")) {
					entity.setName(contents);
				} else if (tag.equals("servlet-class")) {
					entity.setClz(contents);
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (null != qName) {
			if (qName.equals("servlet")) {
				entitys.add(entity); // add element to container
			} else if (qName.equals("servlet-mapping")) {
				mappings.add(mapping);
			}
		}
		tag = null; // tag丢弃了
	}

	public List<Entity> getEntitys() {
		return entitys;
	}

	public List<Mapping> getMappings() {
		return mappings;
	}

}
