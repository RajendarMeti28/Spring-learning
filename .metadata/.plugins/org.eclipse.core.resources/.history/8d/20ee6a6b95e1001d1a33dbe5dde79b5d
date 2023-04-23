package com.practise;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbcUtil.JdbcConnection;

public class SavePointApp {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		Scanner input = null;
		try {
			conn = JdbcConnection.getConnection();
			
			if(conn!= null) {
				stmt = conn.createStatement();
				
				conn.setAutoCommit(false);
				
				stmt.executeUpdate("insert into politicians values('modi', 'bjp')");
				stmt.executeUpdate("insert into politicians values ('stalin','dmk')");
				stmt.executeUpdate("insert into politicians values('babu', 'tdp')");
				
				Savepoint sp = conn.setSavepoint();
				stmt.executeUpdate("insert into politicians values ('kcr','tdp')");
				
				System.out.println("Oops, something went wrong, need to rollback...");
				
				conn.rollback(sp);
				
				conn.commit();
				
			}	
			
		}catch(SQLException | IOException se) {
			se.printStackTrace();
		}finally {
			try {
				JdbcConnection.closeConnection(resultSet, stmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
