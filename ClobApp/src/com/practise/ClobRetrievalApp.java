package com.practise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import com.jdbcUtil.JdbcConnection;

public class ClobRetrievalApp {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			if (conn != null) {
				String sqlInsertQuery = "select name,history from cities where name = ?";

				pstmt = conn.prepareStatement(sqlInsertQuery);

				if (pstmt != null) {
					pstmt.setString(1, "Hyderabad");

					rs = pstmt.executeQuery();
					if (rs != null) {
						if (rs.next()) {
							// fetching the name..
							String name = rs.getString(1);

							// fetching the image and keeping it in hard disk.
							Reader reader = rs.getCharacterStream(2);
							
							String fileName = "hyd_info.txt";
							File f = new File(fileName);
							
							Writer writer = new FileWriter(f);
							
//							byte[] buffer = new byte[2048];
//							while (is.read(buffer)>0) {
//								fos.write(buffer);
//							}
//							fos.flush();
							IOUtils.copy(reader, writer);
							writer.flush();
							
							System.out.println(name);
							System.out.println("File is saved to the location:: "+f.getAbsolutePath());

						} else {
							System.out.println("Record not available for the given name :: ");
						}
					}
				}

			}

		} catch (SQLException | IOException se) {
			se.printStackTrace();
		} finally {
			JdbcConnection.closeConnection(rs, pstmt, conn);
		}

	}

}
