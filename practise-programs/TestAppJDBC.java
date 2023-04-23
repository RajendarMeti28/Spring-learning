
import com.mysql.cj.jdbc.Driver;
import java.sql.*;

public class TestAppJDBC  
{
	public static void main(String[] args) throws SQLException
	{
		//1:Load and Register Driver.
		Driver driver = new Driver();//Creating driver object for mysql.
		DriverManager.registerDriver(driver);
		System.out.println("Driver is Loaded and Registered Succesfully...");

		//2: Establish a connection b/w java and Database.
		//JDBC URL SYNTAX:: <main-protocol>:<subprotocol>:<subname>
		String url = "jdbc:mysql://localhost:3306/spring";
		String username = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connection created..");
		System.out.println("Implementation class name of connection is: " + conn.getClass().getName());

		//3: Create a Statement Object..
		Statement statement = conn.createStatement();
		System.out.println("Implementation class name of statement is: " + statement.getClass().getName());

		//4: Send and Execute the query..
		String sqlSelectQuery = "select sid, sname, semail from student";
		ResultSet rs = statement.executeQuery(sqlSelectQuery);
		System.out.println("Implementation class name of ResultSet is: " + rs.getClass().getName());
		System.out.println("query executed succesfully..");

		//5: Process the Query results.
		System.out.println("\tSID:\tSNAME:\tSEMAIL");
		while(rs.next()){

			Integer id = rs.getInt(1);
			String name = rs.getString(2);
			String email = rs.getString(3);
			
			System.out.println("\t"+id+"\t"+name+"\t"+email);
		
		}

		//6. Close the Connection..
		conn.close();
		System.out.println("Closed the connection..");
	}
}
