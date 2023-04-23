package com.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateApp {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		// 1:Load and register Driver.
		// From jdbc4.X, the above is done automatically.

		Connection conn = null;
		Statement statement = null;

		String url = "jdbc:mysql://localhost:3306/spring";
		String username = "root";
		String password = "root";

		try {

			// 2: Establish the Connection
			conn = DriverManager.getConnection(url, username, password);

			if (conn != null) {
				// 3: Create Statement..
				statement = conn.createStatement();

				if (statement != null) {
					// 4: Send and Execute the query.

					String updateSqlQuery = "update student set sname = 'rajendar' where sname='tyler'";
					int noOfRowsEffected = statement.executeUpdate(updateSqlQuery);
					// 5: Process the results..
					System.out.println("No. of rows Effected:" + noOfRowsEffected);
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6: close the resources used.
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

}
