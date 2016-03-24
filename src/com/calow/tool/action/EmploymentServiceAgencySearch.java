package com.calow.tool.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calow.cim.nio.mutual.RunToolParam;
import com.calow.cim.nio.mutual.Tool;
import com.calow.tool.server.RegularMatch;
import com.calow.tool.server.SendHttpServer;

import net.sf.json.JSONArray;


public class EmploymentServiceAgencySearch extends Tool {

	@Override
	public void act(HttpServletRequest request, HttpServletResponse response) {
		String str = request.getParameter("toolAction");
		if (str.equals("search")) {
			searchAction(request, response);
		} else if (str.equals("area")) {
			areaAction(request, response);
		}
	}

	public void searchAction(HttpServletRequest request,
			HttpServletResponse response) {
		String url = "http://www.gd.lm.gov.cn/sofpro/gecs/jyw/Agencyinformation/institutionright.jsp";
		String keywords = request.getParameter("keywords");
		try {
			keywords = "keywords=" + URLEncoder.encode(keywords, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = SendHttpServer.getInstance()
				.sendWithHttpUrlConnectionPost(url, keywords);
		RegularMatch match = new RegularMatch();
		List<Map<String, String>> map = match.match(result);
		// 转换成JSON数据类型
		JSONArray json = JSONArray.fromObject(map);
		response.setCharacterEncoding("utf-8");
		try {
			Writer writer = response.getWriter();
			writer.write(json.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void areaAction(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		String url = "http://www.gd.lm.gov.cn/sofpro/gecs/jyw/Agencyinformation/institutionright.jsp?code="
				+ code + "&type=" + type;
		String result = SendHttpServer.getInstance()
				.sendWithHttpUrlConnectionGet(url);
		RegularMatch match = new RegularMatch();
		List<Map<String, String>> map = match.match(result);
		// 转换成JSON数据类型
		JSONArray json = JSONArray.fromObject(map);
		response.setCharacterEncoding("utf-8");
		try {
			Writer writer = response.getWriter();
			writer.write(json.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<Object, Object> toolMain(RunToolParam arg0) {
		return null;
	}

	@Override
	public Map<Object, Object> toolMain(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		return null;
	}

}
