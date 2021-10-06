package sxt.serverlet11_01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
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
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// SAX
		// 1、factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2、parse
		SAXParser parse = factory.newSAXParser();
		// 3、new handler, extends DefaultHandler firstly
		PersonHandler handler = new PersonHandler();
		// 4. loader document, handler, parse doc
		parse.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("sxt/serverlet11_01/p.xml"), handler);

		// get ArrayList data
		List<Person> persons = handler.getPersons();
		System.out.println(persons.toString()); // ** obj_person addr
		for (Person p : persons) { // foreach(list: element)
			System.out.println(p.getName() + "-->" + p.getAge());
		}
	}
}

// flow: startDocumemt,recurse(startElement ->characters ->sub(...) ->endElement ->characters)-> endDocument
class PersonHandler extends DefaultHandler {
	private List<Person> persons;
	private Person person;
	private String tag; // 存储操作标签

	@Override
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>(); // startDocument run once only, init ArrayList
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (null != qName) {
			tag = qName; // ** get tag
			if (tag.equals("person")) { // if tag == "preson", create obj_person(element)
				person = new Person();
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contents = new String(ch, start, length).trim(); // ** get content
		if (null != tag) {
			if (tag.equals("name")) {
				person.setName(contents); // set on obj_javaBean
			} else if (tag.equals("age")) {
				if (contents.length() > 0) {
					person.setAge(Integer.valueOf(contents)); // str -> int
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (null != qName) {
			if (qName.equals("person")) {
				persons.add(person); // add obj_person to list
			}
		}
		tag = null; // tag丢弃了
	}

	@Override
	public void endDocument() throws SAXException {
	}

	public List<Person> getPersons() {
		return persons;
	}
}
