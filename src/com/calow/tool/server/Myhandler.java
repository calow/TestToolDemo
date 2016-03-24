/**
 * 用SAX解析XML的Handler
 */
package com.calow.tool.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Myhandler extends DefaultHandler {
	// 存储正在解析的元素的数据
	private List<String> childList = null;
	// 存储所有解析的元素的数据
	private List<List<String>> list = null;
	// 正在解析的元素的名字
	String currentTag = null;
	// 正在解析的元素的元素值
	String currentValue = null;
	// 开始解析的元素
	String nodeName = null;

	boolean attributeValue = false;

	public Myhandler(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<List<String>> getList() {
		return list;
	}

	// 开始解析文档，即开始解析XML根元素时调用该方法
	@Override
	public void startDocument() throws SAXException {
		// 初始化Map
		list = new ArrayList<List<String>>();
	}

	// 开始解析每个元素时都会调用该方法
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// 判断正在解析的元素是不是开始解析的元素
		if (qName.equals(nodeName)) {
			childList = new ArrayList<String>();
		}
		currentTag = qName; // 正在解析的元素
		if (attributes != null && childList != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				if (attributes.getQName(i).equals("target")
						&& attributes.getValue(i).equals("_blank")) {
					attributeValue = true;
				}
			}
		}
	}

	// 解析到每个元素的内容时会调用此方法
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (currentTag != null && childList != null) {
			currentValue = new String(ch, start, length);
			if (currentTag.equals("td")) {
				if (!currentValue.trim().equals("")
						&& !currentValue.trim().equals("\n")) {
					childList.add(currentValue.trim());
				}
			}
			if(currentTag.equals("a") && attributeValue){
				childList.add(currentValue.trim());
			}
		}
	}

	// 每个元素结束的时候都会调用该方法
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// 判断是否为一个节点结束的元素标签
		if (qName.equals(nodeName)) {
			list.add(childList);
			childList = null;
		}
		if(qName.equals("a")){
			attributeValue = false;
		}
	}

	// 结束解析文档，即解析根元素结束标签时调用该方法
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
}