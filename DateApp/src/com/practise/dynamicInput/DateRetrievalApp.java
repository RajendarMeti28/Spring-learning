package com.practise.dynamicInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.jdbcUtil.JdbcConnection;

public class DateRetrievalApp {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		// 1:Load and register Driver.
		// From jdbc4.X, the above is done automatically.

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/spring";
		String username = "root";
		String password = "root";
		
		String sqlInsertQuery = "select name,dob from userdata where name = ?";
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter student name");
		String name = input.next();

		try {
			conn = JdbcConnection.getConnection();
			if(conn!=null) {
				pstmt = conn.prepareStatement(sqlInsertQuery);
				if(pstmt!=null) {
					pstmt.setString(1,name);
					rs = pstmt.executeQuery();
				}
				if(rs!=null) {
					if(rs.next()) {
						String uName = rs.getString(1);
						java.sql.Date sqlDate = rs.getDate(2);
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String dob = sdf.format(sqlDate);
						
						System.out.println("username: "+uName);
						System.out.println("SqlDate: "+sqlDate);
						System.out.println("DOB: "+dob);
					}
					else {
						System.out.println("NO RECORDS AVAIALBLE for name:" +name);
					}
				}
				
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6: close the resources used.
			JdbcConnection.closeConnection(rs, pstmt, conn);
			if(input != null) {
				input.close();
			}
		}
	}

}
