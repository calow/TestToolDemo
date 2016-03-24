package com.calow.server.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calow.cim.nio.mutual.Tool;
import com.calow.dao.ToolDao;
import com.calow.server.ToolServer;
import com.calow.util.ContextHolder;

public class ToolServerImpl implements ToolServer {

	@Override
	public void initTool(Tool tool, HttpServletRequest request,
			HttpServletResponse response) {
		ToolDao toolDao = (ToolDao) ContextHolder
				.getBean("toolDao");
		Connection connection =  toolDao.getConnection();
		tool.setConnection(connection);
		tool.act(request, response);
	}


}
