package com.practise.dynamicInput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.jdbcUtil.JdbcConnection;

public class InsertApp {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException {

		// 1:Load and register Driver.
		// From jdbc4.X, the above is done automatically.

		Connection conn = null;
		PreparedStatement pstmt = null;

		String url = "jdbc:mysql://localhost:3306/spring";
		String username = "root";
		String password = "root";
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter name:");
		String name = input.next();
		System.out.println("Enter dob::(dd-MM-yyyy)");
		String sdob = input.next();
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		java.util.Date uDate = sdf.parse(sdob);
		
		long time = uDate.getTime();
		java.sql.Date sqlDate = new java.sql.Date(time);
		
		System.out.println("String dob is: "+sdob);
		System.out.println("Util date is: "+uDate);
		System.out.println("SQL date is: "+sqlDate);
		

		try {
			conn = JdbcConnection.getConnection();
			if(conn!=null) {
				String sqlInsertQuery = "insert into userdata(`name`,`dob`) values (?,?)";
				pstmt = conn.prepareStatement(sqlInsertQuery);
				if(pstmt!=null) {
					pstmt.setString(1, name);
					pstmt.setDate(2, sqlDate);
					int rowsCount  = pstmt.executeUpdate();
			
					System.out.println("No of rows effected: "+rowsCount);
				}
				
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6: close the resources used.
			JdbcConnection.closeConnection(null, pstmt, conn);
			if(input != null) {
				input.close();
			}
		}
	}

}
