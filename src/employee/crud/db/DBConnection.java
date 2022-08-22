package employee.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static final String dbURL = "jdbc:mysql://localhost:3306/employee_db";
	public static final String dbUserName = "user"; //user
	public static final String dbPassword = "root";

	public static Connection getConnection() {
		
		System.out.println("Start getConnection");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
			if(connection != null) {
				System.out.println("Connected");
				return connection;
			}
			else {
				System.out.println("Connection issues");
				return null;
			}
		
		} catch(Exception e) {
			System.out.println("Exception in DB Connection ==>" + e.getMessage());
			e.printStackTrace();
			return null;
			
		}
	}
	public static Connection connection = getConnection();
	
	public static void main(String[] args) {
		System.out.println(DBConnection.connection);
	}
}
