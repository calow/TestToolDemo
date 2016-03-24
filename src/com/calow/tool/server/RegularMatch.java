package com.calow.tool.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RegularMatch {
	public List<Map<String, String>> match(String content) {
		List<Map<String, String>> result = null;
		try {
			Pattern p1 = Pattern.compile("<form([\\s\\S]*)</form>");
			Matcher m1 = p1.matcher(content);
			String value1 = null;
			while (m1.find()) {
				value1 = m1.group();
			}
			Pattern p2 = Pattern
					.compile("<table[^>]*>[\\s\\S]*?</table>[\\s\\S]*?(<table[^>]*>[\\s\\S]*?</table>)");
			Matcher m2 = p2.matcher(value1);
			String value2 = null;
			while (m2.find()) {
				value2 = m2.group(1);
			}
			String s1 = value2.replaceAll(
					"<img src=\"images/fangda.jpg\"  alt=\"显示地图\">", "");
			String s2 = s1.replaceAll("<br>", "");
			String s3 = s2.replaceAll(
					"<td width=\"25%\"  align=\"center\"><b>机构名称</b></td>", "");
			String s4 = s3.replaceAll(
					"<td width=\"25%\"  align=\"center\"><b>服务地址</b></td>", "");
			String s5 = s4.replaceAll(
					"<td width=\"25%\"  align=\"center\"><b>联系电话</b></td>", "");
			String s6 = s5.replaceAll(
					"<td width=\"25%\"  align=\"center\"><b>网址</b></td>", "");

			// 创建一个解析XML的工厂对象
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			// 创建一个解析XML的对象
			SAXParser parser = parserFactory.newSAXParser();
			// 创建一个解析助手类
			Myhandler myhandler = new Myhandler("tr");
			parser.parse(new ByteArrayInputStream(s6.getBytes("UTF-8")),
					myhandler);
			List<List<String>> lists = myhandler.getList();
			result = new ArrayList<Map<String,String>>();
			int i = 0;
			for(List<String> list : lists){
				if(i > 0){
					Map<String, String> map = new HashMap<String, String>();
					map.put("name", list.get(0));
					map.put("address", list.get(1));
					map.put("phone", list.get(2));
					if(list.size() > 3){
						map.put("website", list.get(3));
					}else{
						map.put("website", "");
					}
					result.add(map);
				}
				i++;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
