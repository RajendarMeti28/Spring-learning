package com.jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

	
	private JdbcConnection() {
		
	}
	
	public static Connection getConnection() throws SQLException {
		
		Connection conn = null;

		String url = "jdbc:mysql://localhost:3306/spring";
		String username = "root";
		String password = "root";
		
		conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}
	
	public static void closeConnection(ResultSet rs, Statement statement, Connection conn) throws SQLException {
		
		
		if(rs!=null) {
			rs.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (conn != null) {
			conn.close();
		}

	}
}
