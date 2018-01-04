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
import pl.coderslab.model.Vehicle;

@WebServlet("/AllCustomerVehicles")
public class AllCustomerVehicles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idCustomer = Integer.parseInt(request.getParameter("idCustomer"));
		
		MixedDao dao = new MixedDao();
		Map<Customer, Vehicle> customerAllVehicles = dao.loadCustomersAllVehicles(idCustomer);
		
		Set<Customer> customers = customerAllVehicles.keySet();
		Customer customer = customers.iterator().next();
		
		List<Vehicle> vehicles = new ArrayList<>();
		for (Vehicle lis : customerAllVehicles.values()) {
			vehicles.add(lis);
		}
		
		request.setAttribute("vehicles", vehicles);
		request.setAttribute("customer", customer);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/allCustomerVehicles.jsp").forward(request, response);

	}
}
