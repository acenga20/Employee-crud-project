package employee.crud.dao;

import java.util.List;

import employee.crud.beans.Employee;

public interface EmployeeDAO {
	
	//insert method
	
	public boolean addEmployee(Employee employee);
	//update method
	
	public boolean updateEmployee(Employee employee);
	
	//delete method
	
	public boolean deleteEmployee(int employeeID);
	
	//getAll
	
	public List<Employee> getAllEmployees();
	
	//get Instance (single employee)

	public Employee getEmployee(int employeeID);
}
