package com.calow.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.io.SAXReader;

import com.calow.cim.nio.mutual.Tool;
import com.calow.mutual.Params;
import com.calow.server.ToolServer;
import com.calow.util.ContextHolder;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ToolAction extends ActionSupport implements ModelDriven<Params> {

	/**
	 * 工具请求拦截器
	 */
	private static final long serialVersionUID = 1L;
	
	Params params = new Params();

	@Override
	public Params getModel() {
		return params;
	}
	
	public void runTool(){
		System.out.println("runTool...");
		String toolName = params.getToolName();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("userAccount", params.getUserAccount());
		request.setAttribute("forwardUrl", "runTool4ward.action?url=/WEB-INF/" + toolName + "/pages/&toolName=" + toolName);
		request.setAttribute("resourceUrl", "tools/" + toolName + "/");
		request.setAttribute("requestUrl", "actRunTool.action?placeholder=1");
		try {
			request.getRequestDispatcher("/WEB-INF/" + toolName + "/pages/main.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actRunTool(){
		System.out.println("actRunTool...");
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String clazzName = SAX("EntryClass");
		ToolServer toolService = (ToolServer) ContextHolder
				.getBean("toolServer");
		try {
			Class<?> myClass = getClass().getClassLoader().loadClass(clazzName);
			Tool tool = (Tool) myClass.newInstance();
			toolService.initTool(tool, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runTool4ward(){
		System.out.println("runTool4ward...");
		String toolName = params.getToolName();
		String path = params.getPath();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("forwardUrl", "runTool4ward.action?url=/WEB-INF/" + toolName + "/pages/&toolName=" + toolName);
		request.setAttribute("resourceUrl", "tools/" + toolName + "/");
		request.setAttribute("requestUrl", "actRunTool.action?placeholder=1");
		try {
			request.getRequestDispatcher("/WEB-INF/" + toolName + "/pages/" + path).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("path = " + path);
	}
	
	public void postResult(HttpServletResponse response,
			HashMap<String, Object> datamap) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(new Gson().toJson(datamap));
			pw.flush();
		} catch (IOException e) {
			datamap.put("code", 500);
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}
	
	public String SAX(String entry) {
		String value = null;
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream("config.xml");
//			File file = new File("config.xml");
//			FileInputStream in = new FileInputStream(file);
			SAXReader reader = new SAXReader();
			org.dom4j.Document document = reader.read(inputStream);
			org.dom4j.Element element = document.getRootElement();
			value = element.elementText(entry).trim();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return value;
	}

}
