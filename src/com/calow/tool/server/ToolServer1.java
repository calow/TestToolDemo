package com.calow.tool.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolServer1 {
	
	public Connection connection;
	
	public ToolServer1(Connection connection){
		this.connection = connection;
	}
	
	public List<Map<String, String>> Test1(){
		String sql = "select * from testtool tt where tt.tool_id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setObject(1, "1");
			ResultSet resultSet = statement.executeQuery();
			String tool_id = null;
			String tool_name = null;
			String remark = null;
			List<Map<String, String>> result = new ArrayList<Map<String,String>>();
			Map<String, String> map = null;
			while(resultSet.next()){
				map = new HashMap<String, String>();
				tool_id = resultSet.getString("tool_id");
				map.put("id", tool_id);
				tool_name = resultSet.getString("tool_name");
				map.put("name", tool_name);
				remark = resultSet.getString("remark");
				map.put("remark", remark);
				result.add(map);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Map<String, String>> TestAll(){
		String sql = "select * from testtool";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			String tool_id = null;
			String tool_name = null;
			String remark = null;
			List<Map<String, String>> result = new ArrayList<Map<String,String>>();
			Map<String, String> map = null;
			while(resultSet.next()){
				map = new HashMap<String, String>();
				tool_id = resultSet.getString("tool_id");
				map.put("id", tool_id);
				tool_name = resultSet.getString("tool_name");
				map.put("name", tool_name);
				remark = resultSet.getString("remark");
				map.put("remark", remark);
				result.add(map);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
