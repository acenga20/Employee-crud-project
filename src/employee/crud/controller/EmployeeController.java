package employee.crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import employee.crud.beans.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.dao.EmployeeDAOImpl;

@WebServlet("/")
public class EmployeeController extends HttpServlet {
	
	EmployeeDAO employeeDAO = null;
	
	private static final long serialVersionUID = 1L;
       

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		employeeDAO = new EmployeeDAOImpl();
	}
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//See reuqest and check which operation to perform
		System.out.println("EmployeeController, DoPost method is started");
		String action = request.getServletPath();
		
		System.out.println("Action ==>" + action);
		switch(action) {
		case "/add":
		{
			addNewEmployee(request, response);
		}
			
			break;
		case "/update":
		{
			updateEmployee(request, response);

			break;
		}
			
		
		
		case "/delete":
		{
			deleteEmployee(request, response);

			break;
		}
		
			
		case "/list":
		{
			getAllEmployees(request, response);

			break;
		}
	
			
		case "/get":
		{
			getEmployee(request, response);

			break;
		}
			
			
			
		default:{
			getAllEmployees(request, response);
			break;
		}
			
		}
	}
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Start deleteEmployee");
		String employeeIds = request.getParameter("employeeIds");
		
		System.out.println("Employee Details ==>" + employeeIds);
		StringTokenizer tokenizer = new StringTokenizer(employeeIds, ",");
		while(tokenizer.hasMoreElements()) {
			int employeeId = Integer.parseInt(tokenizer.nextToken());
			boolean result = employeeDAO.deleteEmployee(employeeId);
			System.out.println("deleteEmployee result is ==>" + result);
		}
		
		
		List<Employee> employees = employeeDAO.getAllEmployees();
		
		System.out.println("getAllEmployees result is ==>" + employees.size());
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees",employees);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {

			e.printStackTrace();
		} 
		
	}
	private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Start getEmployee");
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("Employee Details ==>" + id);
		
		
		Employee employee = employeeDAO.getEmployee(id);
		System.out.println("getEmployee result is ==>" + employee);
		
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			String employeeStr = mapper.writeValueAsString(employee);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			servletOutputStream.write(employeeStr.getBytes());
		} catch ( IOException e) {

			e.printStackTrace();
		} 
	}
	private void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Start getAllEmployees");
		
		List<Employee> employees = employeeDAO.getAllEmployees();
		
		System.out.println("getAllEmployees result is ==>" + employees.size());
		
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {

			e.printStackTrace();
		} 
		
	}
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Start updateEmployee");
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Employee employee = new Employee(id, name, email,phone, address);
		System.out.println("Employee Details ==>" + employee);
		List<Employee> employees = employeeDAO.getAllEmployees();
		System.out.println("getAllEmployees result is ==>" + employees.size());
		
		
		boolean result = employeeDAO.updateEmployee(employee);
		System.out.println("updateEmployee result is ==>" + result);
		
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
			request.setAttribute("employees",employees);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {

			e.printStackTrace();
		} 
		
	}
	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Start addNewEmployee");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Employee employee = new Employee(name, email,phone, address);
		System.out.println("Employee Details ==>" + employee);
		
		
		boolean result = employeeDAO.addEmployee(employee);
		System.out.println("addNewEmployee result is ==>" + result);
		
		List<Employee> employees = employeeDAO.getAllEmployees();
		
		System.out.println("getAllEmployees result is ==>" + employees.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
		try {
			request.setAttribute("employees", employees);
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {

			e.printStackTrace();
		} 
		
		
	}

}
