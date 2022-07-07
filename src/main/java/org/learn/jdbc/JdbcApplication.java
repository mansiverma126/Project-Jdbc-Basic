package org.learn.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcApplication {
	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/query?useSSL=false";
	static final String USER_NAME = "root";
	static final String PASSWORD = "Education@123";
	
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		CallableStatement callableStatement =  null;

		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("Database connection established ");
			statement = connection.createStatement();
			
			
//			String sqlInsert = "INSERT INTO EMPLOYEE"
//					+ "(ID, NAME)"
//					+"VALUES(1001,'jOHN Doe')";
//			
//			statement.executeUpdate(sqlInsert);
			
//			String sqlCreateTable = "CREATE TABLE EMPLOYEE("
//			+"ID INT NOT NULL PRIMARY KEY,"
//			+"NAME VARCHAR(100) NOT NULL"
//			+")";
//	
//	statement.execute(sqlCreateTable);

//			String sqlUpdate = "UPDATE EMPLOYEE SET NAME='John Smith' WHERE ID=1001";
//			statement.executeUpdate(sqlUpdate);	
//			
//			String sqlDelete = "DELETE FROM EMPLOYEE WHERE ID=1001";
//			
//			ResultSet resultSet = statement.executeQuery(sqlQuery);
			
			/*
			 * while(resultSet.next()) { System.out.println("ID = "+ resultSet.getInt("ID")
			 * + " " + resultSet.getString("NAME"));
			 * System.out.println(resultSet.getMetaData()); }
			 */
			
//			statement.executeUpdate(sqlInsert);
			
//			String sqlPreparedInsert = "INSERT INTO EMPLOYEE (ID, NAME) VALUES(?, ?)";
//			
//			preparedStatement = connection.prepareStatement(sqlPreparedInsert);
//			preparedStatement.setInt(1, 1002);
//			preparedStatement.setString(2, "Kernel Smith");
//			preparedStatement.executeUpdate();
//			
//			preparedStatement.setInt(1, 1003);
//			preparedStatement.setString(2, "Richard Connor");
//			preparedStatement.executeUpdate();

			
			String sqlStoredProcedure = "{call our_test_procedure(?,?)}";
			callableStatement = connection.prepareCall(sqlStoredProcedure);
			callableStatement.setInt(1, 1004);
			callableStatement.setString(2, "Simon Knox");
			callableStatement.executeUpdate();
			
			String sqlQuery = "SELECT * FROM EMPLOYEE";
		
			ResultSet resultSet2 = statement.executeQuery(sqlQuery);
			
			while(resultSet2.next()) {
				System.out.println("ID = "+ resultSet2.getInt("ID") + " " + resultSet2.getString("NAME"));
//				System.out.println(resultSet.getMetaData());
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) {
				try {
					statement.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
