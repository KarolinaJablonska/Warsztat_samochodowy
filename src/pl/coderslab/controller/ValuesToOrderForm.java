package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Vehicle;

@WebServlet("/ValuesToOrderForm")
public class ValuesToOrderForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeDao dao1 = new EmployeeDao();
		List<Employee> employees = dao1.loadAll();

		VehicleDao dao2 = new VehicleDao();
		List<Vehicle> vehicles = dao2.loadAll();

		request.setAttribute("vehicles", vehicles);
		request.setAttribute("employees", employees);
		getServletContext().getRequestDispatcher("/views/addOrder.jsp").forward(request, response);
	}
}
