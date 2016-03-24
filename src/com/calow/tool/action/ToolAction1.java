package com.calow.tool.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calow.cim.nio.mutual.RunToolParam;
import com.calow.cim.nio.mutual.Tool;
import com.calow.tool.server.ToolServer1;

public class ToolAction1 extends Tool {

	@Override
	public void act(HttpServletRequest request, HttpServletResponse response) {
		String toolAction = (String) request.getParameter("toolAction");
		if(toolAction.equals("test")){
			testAction(request, response);
		}
	}

	@Override
	public Map<Object, Object> toolMain(RunToolParam rtp) {
		
		return null;
	}

	@Override
	public Map<Object, Object> toolMain(HttpServletRequest request,
			HttpServletResponse response) {
		
		return null;
	}
	
	public void testAction(HttpServletRequest request, HttpServletResponse response){
		List<Map<String, String>> result = null;
		Connection cnn = this.getConnection();
		ToolServer1 ts = new ToolServer1(cnn);
		result = ts.Test1();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			if(result != null){
				pw.write(result.toString());
			}else{
				pw.write("error with exception");
			}
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
