package com.calow.server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calow.cim.nio.mutual.Tool;

public interface ToolServer {
	public void initTool(Tool tool, HttpServletRequest request, HttpServletResponse response);
}
