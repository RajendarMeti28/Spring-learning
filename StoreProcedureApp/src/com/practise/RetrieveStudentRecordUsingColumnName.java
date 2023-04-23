package com.practise;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.jdbcUtil.JdbcConnection;

public class RetrieveStudentRecordUsingColumnName {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			if (conn != null) {
				
				//Syntax for stored procedure => {call storedProcedurename(?,?)}
				String storedProcedure = "{call getStudentsById(?,?,?)}";
				
				cstmt = conn.prepareCall(storedProcedure);
				
				if(cstmt!=null) {
					cstmt.setInt(1, 1);
					
					cstmt.registerOutParameter(2,  Types.VARCHAR);
					cstmt.registerOutParameter(3, Types.VARCHAR);
					
					cstmt.execute();
					
					System.out.println("Name of the student is:: "+cstmt.getString(2));
					System.out.println("Name of the student is:: "+cstmt.getString(3));
					
				}			

				
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			JdbcConnection.closeConnection(rs, cstmt, conn);
		}

	}

}
