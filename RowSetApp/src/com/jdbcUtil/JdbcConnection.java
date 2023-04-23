package com.jdbcUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcConnection {

	
	private JdbcConnection() {
		
	}
	
	public static Connection getConnection() throws SQLException, IOException {
		
		Connection conn = null;
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("src/com/properties/jdbc.properties");
		
		props.load(fis);
		
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
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
