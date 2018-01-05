package pl.coderslab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.MixedDao;
import pl.coderslab.model.Customer;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

@WebServlet("/MixedQuestions")
public class MixedQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		String action = request.getParameter("action");
		int id;
		MixedDao dao = new MixedDao();

		// ALL CUSTOMER VEHICLES
		if (action.equals("allCustomerVehicles")) {
			id = Integer.parseInt(request.getParameter("idCustomer"));
			Map<Customer, Vehicle> customerAllVehicles = dao.loadCustomersAllVehicles(id);
			Set<Customer> customers = customerAllVehicles.keySet();
			Customer customer = customers.iterator().next();

			List<Vehicle> vehicles = new ArrayList<>();
			for (Vehicle lis : customerAllVehicles.values()) {
				vehicles.add(lis);
			}
			request.setAttribute("vehicles", vehicles);
			request.setAttribute("customer", customer);
			getServletContext().getRequestDispatcher("/views/allCustomerVehicles.jsp").forward(request, response);
		}

		// ALL EMPLOYEES ORDERS
		if (action.equals("allEmployeeOrders")) {
			id = Integer.parseInt(request.getParameter("idEmployee"));
			Map<Employee, Order> employeeAllOrders = dao.loadEmployeeAllOrders(id);
			Set<Employee> employees = employeeAllOrders.keySet();
			Employee employee = employees.iterator().next();

			List<Order> orders = new ArrayList<>();
			for (Order lis : employeeAllOrders.values()) {
				orders.add(lis);
			}
			request.setAttribute("orders", orders);
			request.setAttribute("employee", employee);
			getServletContext().getRequestDispatcher("/views/allEmployeeOrders.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String NO_RECORD = "<div class=\"alert alert-danger alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  W BAZIE NIE DANYCH O PODANYCH KRYTERIACH. \n" + "</div>";
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		String surname = request.getParameter("surname");
		boolean pageVisited = true;
		List<Customer> customers;
		CustomerDao dao = new CustomerDao();
		
		try {
			customers = dao.loadAllBySurname(surname);
			request.setAttribute("customers", customers);
		} catch (Exception e) {
			request.setAttribute("message", NO_RECORD);
		}
		
		request.setAttribute("pageVisited", pageVisited);
		getServletContext().getRequestDispatcher("/views/customerBySurname.jsp").forward(request, response);
		
		
	}

}
