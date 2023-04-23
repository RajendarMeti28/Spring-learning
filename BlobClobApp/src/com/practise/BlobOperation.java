package com.practise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jdbcUtil.JdbcConnection;

public class BlobOperation {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcConnection.getConnection();
			
			if(conn!= null) {
				String sqlInsertQuery = "insert into person(`name`, `image`) values(?,?)";
				
				pstmt = conn.prepareStatement(sqlInsertQuery);
				
				if(pstmt!=null) {
					pstmt.setString(1, "Bunny");
					
					//Image files is reaching to java application.
					File f = new File("bunny.jpg");
					FileInputStream fis = new FileInputStream(f);
					
					//setting the input information from java sending the data to database.
					pstmt.setBlob(2, fis);
					
					System.out.println("Inserting image from:: "+ f.getAbsolutePath());
					
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
