package com.practise;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.jdbcUtil.JdbcConnection;

public class TransactionApp {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		Scanner input = null;
		try {
			conn = JdbcConnection.getConnection();
			
			if(conn!= null) {
				
				stmt = conn.createStatement();
				System.out.println("DATA BEFORE TRANSACTION:");
				System.out.println("------------");
				resultSet = stmt.executeQuery("select name, balance from accounts");
				
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2));
				}
				
				System.out.println("\nTRANSACTION BEGINS:");
				
				//disabled the auto commit nature.
				conn.setAutoCommit(false);
				stmt.executeUpdate("update accounts set balance = balance + 3000 where name = 'rajendar'");
				stmt.executeUpdate("update accounts set balance = balance - 3000 where name = 'tyler'");
				
				System.out.println("Confirm to do Transaction?");
				input = new Scanner(System.in);
				String option = input.next();
				if(option.equalsIgnoreCase("yes")) {
					conn.commit();
					System.out.println("Transaction commited");
				}else {
					conn.rollback();
					System.out.println("Transaction rollback");
				}
				
				System.out.println("\nDATA AFTER TRANSACTION:");
				resultSet1 = stmt.executeQuery("select name, balance from accounts");
				while(resultSet1.next()) {
					System.out.println(resultSet1.getString(1)+"\t"+resultSet1.getInt(2));
				}
				
				
				
			}	
			
		}catch(SQLException | IOException se) {
			se.printStackTrace();
		}finally {
			try {
				JdbcConnection.closeConnection(resultSet, stmt, conn);
				if(input!=null)
					input.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
