package com.practise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbcUtil.JdbcConnection;

public class ClobOperation {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcConnection.getConnection();
			
			if(conn!= null) {
				String sqlInsertQuery = "insert into cities(`name`, `history`) values(?,?)";
				
				pstmt = conn.prepareStatement(sqlInsertQuery);
				
				if(pstmt!=null) {
					pstmt.setString(1, "Hyderabad");
					
					//Image files is reaching to java application.
					File f = new File("hyd_history.txt");
					FileReader reader = new FileReader(f);
					
					//setting the input information from java sending the data to database.
					pstmt.setCharacterStream(2, reader);
					
					System.out.println("Inserting text file from:: "+ f.getAbsolutePath());
					
					int noOfRows = pstmt.executeUpdate();
					
					if(noOfRows == 1) {
						System.out.println("record inserted succesfully..");
					}else {
						System.out.println("No records inserted..");
					}
				}
				
			}	
			
		}catch(SQLException | FileNotFoundException se) {
			se.printStackTrace();
		}finally {
			
		}

	}

}
