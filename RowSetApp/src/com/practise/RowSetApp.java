package com.practise;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetApp {

	public static void main(String[] args) throws SQLException {
		
		RowSetFactory factory = RowSetProvider.newFactory();
		
		JdbcRowSet jdbcRowSet = factory.createJdbcRowSet();
		jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/spring");
		jdbcRowSet.setUsername("root");
		jdbcRowSet.setPassword("root");
		
		jdbcRowSet.setCommand("select * from student");
		jdbcRowSet.execute();
		
		System.out.println("\n\nStudent details in forward direction:");
		System.out.println("SID\tSNAME\tEMAIL");
		System.out.println("============");
		while(jdbcRowSet.next()) {
			System.out.println(jdbcRowSet.getInt(1)+"\t"+jdbcRowSet.getString(2)+"\t"+jdbcRowSet.getString(3));
			
		}
		
		System.out.println("\n\nStudent details in backward direction:");
		System.out.println("SID\tSNAME\tEMAIL");
		System.out.println("============");
		while(jdbcRowSet.previous()) {
			System.out.println(jdbcRowSet.getInt(1)+"\t"+jdbcRowSet.getString(2)+"\t"+jdbcRowSet.getString(3));
			
		}
		
		//performing insert operation using jdbcRowSet..
		jdbcRowSet.moveToInsertRow();
		jdbcRowSet.updateInt(1,4);
		jdbcRowSet.updateString(2,"Racegurram");
		jdbcRowSet.updateString(3, "race@gmail.com");
		
		jdbcRowSet.execute();
		
		System.out.println("Record inserted successfully");
		
		

	}

}
