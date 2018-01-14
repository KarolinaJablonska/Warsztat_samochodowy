package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Customer;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

@WebServlet("/SupportValuesToForms")
public class SupportValuesToForms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
				
		String action = request.getParameter("action");
		
		EmployeeDao dao1 = new EmployeeDao();
		VehicleDao dao2 = new VehicleDao();
		CustomerDao dao3 = new CustomerDao();
		OrderDao dao4 = new OrderDao();
		
		List<Employee> employees = dao1.loadAll();
		List<Vehicle> vehicles = dao2.loadAll();
		List<Customer> customers = dao3.loadAll();
		List<Order> orders = dao4.loadAll();

		request.setAttribute("vehicles", vehicles);
		request.setAttribute("employees", employees);
		request.setAttribute("customers", customers);
		request.setAttribute("orders", orders);
		
		if (action.equals("addVehicle")) {
			getServletContext().getRequestDispatcher("/views/addVehicle.jsp").forward(request, response);
		} if (action.equals("addOrder")) {
			getServletContext().getRequestDispatcher("/views/addOrder.jsp").forward(request, response);
		} if(action.equals("oneOrderById")) {
			getServletContext().getRequestDispatcher("/views/orderById.jsp").forward(request, response);
		}
	}
}
