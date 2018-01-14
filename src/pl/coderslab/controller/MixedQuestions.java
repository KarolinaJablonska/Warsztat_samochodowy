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

		// ALL EMPLOYEE ORDERS
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

		// ALL ACTUAL EMPLOYEE ORDERS
		if (action.equals("allActualEmployeeOrders")) {
			id = Integer.parseInt(request.getParameter("idEmployee"));
			Map<Employee, Order> employeeAllActualOrders = dao.loadEmployeeAllActualOrders(id);
			Set<Employee> employees = employeeAllActualOrders.keySet();
			Employee employee = employees.iterator().next();

			List<Order> orders = new ArrayList<>();
			for (Order lis : employeeAllActualOrders.values()) {
				orders.add(lis);
			}
			request.setAttribute("orders", orders);
			request.setAttribute("employee", employee);
			getServletContext().getRequestDispatcher("/views/index.jsp").forward(request, response);
		}

		// ALL VEHICLE ORDERS
		if (action.equals("vehicleOrderHistory")) {
			id = Integer.parseInt(request.getParameter("idVehicle"));
			Map<Vehicle, Order> vehicleAllOrders = dao.loadVehicleAllOrders(id);
			Set<Vehicle> vehicles = vehicleAllOrders.keySet();
			Vehicle vehicle = vehicles.iterator().next();

			List<Order> orders = new ArrayList<>();
			for (Order lis : vehicleAllOrders.values()) {
				orders.add(lis);
			}
			request.setAttribute("orders", orders);
			request.setAttribute("vehicle", vehicle);
			getServletContext().getRequestDispatcher("/views/allVehicleOrders.jsp").forward(request, response);
		}
	}
}
