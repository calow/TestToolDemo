package com.calow.mutual;

import java.io.Serializable;

public class Params implements Serializable {

	/**
	 * 运行工具请求参数实体类
	 */
	private static final long serialVersionUID = 1L;
	
	private String toolName;
	
	private String userAccount;
	
	private String url;
	
	private String path;
	
	private String placeholder;

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
}
