package com.calow.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.calow.dao.ToolDao;

public class ToolDaoImpl extends HibernateDaoSupport implements ToolDao {

	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
