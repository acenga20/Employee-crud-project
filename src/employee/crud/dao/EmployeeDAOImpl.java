package employee.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import employee.crud.beans.Employee;
import employee.crud.db.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private static Connection connection = DBConnection.connection;

	@Override
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		System.out.println("Start addEmployee");
		try {
			
			// defining the sql insert statement
			String insertStatement = "INSERT INTO employee_db.employee(name, email, phone, address) VALUES (?, ?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());
			
			int result = preparedStatement.executeUpdate();
			
			return result == 1? true : false;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		
		System.out.println("Start updateEmployee");
		try {
			
			// defining the sql insert statement
			String updateStatement = "UPDATE employee_db.employee SET NAME = ?, email = ?, phone = ?, address = ? WHERE id= ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhone());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setInt(5, employee.getId());
			
			int result = preparedStatement.executeUpdate();
			
			return result == 1? true : false;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deleteEmployee(int employeeID) {
		
		
		
		System.out.println("Start deleteEmployee");
		try {
			
			// defining the sql insert statement
			String deleteStatement = "DELETE FROM employee_db.employee WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setInt(1, employeeID);
			
			int result = preparedStatement.executeUpdate();
			
			return result == 1? true : false;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		
		System.out.println("Start getAllEmployees");
		try {
			
			// defining the sql insert statement
			String getAllStatement = "SELECT * FROM employee_db.employee;";
			PreparedStatement preparedStatement = connection.prepareStatement(getAllStatement);
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));
				
				employees.add(employee);
				
			}
			
			return employees;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Employee getEmployee(int employeeID) {
		
		System.out.println("Start getEmployee");
		try {
			
			// defining the sql insert statement
			String getInstanceStatement = "SELECT * FROM employee_db.employee WHERE id = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(getInstanceStatement);
			preparedStatement.setInt(1, employeeID);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			Employee employee = new Employee();
			
			while(resultSet.next()) {
				employee.setId(resultSet.getInt("id"));
				employee.setName(resultSet.getString("name"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPhone(resultSet.getString("phone"));
				employee.setAddress(resultSet.getString("address"));
			}
			
			return employee;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		
		Employee employee = new Employee();
		employee.setId(12);
		employee.setName("Rubin");
		employee.setEmail("raga20@epoka.edu.al");
		employee.setPhone("0697877898");
		employee.setAddress("AL");
		
		
		EmployeeDAOImpl employeeDAOimpl = new EmployeeDAOImpl();
		//System.out.println(employeeDAOimpl.deleteEmployee(2));
		//System.out.println(employeeDAOimpl.getAllEmployees().get(0));
		System.out.println(employeeDAOimpl.getEmployee(12));
		//System.out.println(employeeDAOimpl.updateEmployee(employee));
		//System.out.println(employeeDAOimpl.addEmployee(employee));
		
		
	}

}
