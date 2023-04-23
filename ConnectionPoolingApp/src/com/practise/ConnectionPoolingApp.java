package com.practise;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class ConnectionPoolingApp {

	public static void main(String[] args) throws SQLException {
		
		
		//Creating a pooled connection object.
		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
		dataSource.setUser("root");
		dataSource.setPassword("root");
		
		Connection connection = dataSource.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("select sid, sname, semail from student");
		
		System.out.println("SID:\tSNAME:\tSEMAIL");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			
		}
		
		//sending the connection object back to connection pool.
		connection.close();
		

	}

}
