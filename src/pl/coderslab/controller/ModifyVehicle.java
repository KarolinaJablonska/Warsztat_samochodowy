package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Customer;
import pl.coderslab.model.Vehicle;

@WebServlet("/ModifyVehicle")
public class ModifyVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VehicleDao dao = new VehicleDao();
		CustomerDao dao2 = new CustomerDao();
		List<Vehicle> vehicles = dao.loadAll();
		List<Customer> customers = dao2.loadAll();
		Vehicle vehicleToModify;

		int idVehicle = Integer.parseInt(request.getParameter("idVehicle"));

		// get vehicle to modify (by id)
		for (Vehicle v : vehicles) {
			if (v.getIdVehicle() == idVehicle) {
				vehicleToModify = v;
				request.setAttribute("vehicle", vehicleToModify);
			}
		}
		
		request.setAttribute("customers", customers);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/modifyVehicle.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MESSAGES
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Samochód został dodany poprawnie.\n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		VehicleDao dao = new VehicleDao();
		List<Vehicle> vehicles;

		int idVehicle = Integer.parseInt(request.getParameter("idVehicle")); 				// required
		String model = request.getParameter("model"); 										// required
		String mark = request.getParameter("mark"); 										// required
		int productionYear = Integer.parseInt(request.getParameter("productionYear")); 		// required
		String registrationNr = request.getParameter("registrationNr"); 					// required
		String nextTechnicalReviewDate = request.getParameter("nextTechnicalReviewDate");
		int customerId = Integer.parseInt(request.getParameter("customerId")); 				// required

		// setting empty date suitable to Data Base input
		if (nextTechnicalReviewDate.equals("")) {
			nextTechnicalReviewDate = null;
		}

		dao.saveToDb(idVehicle, model, mark, productionYear, registrationNr, nextTechnicalReviewDate, customerId);
		request.setAttribute("message", SUCCESS_MESSAGE);

		vehicles = dao.loadAll();
		request.setAttribute("vehicles", vehicles);
		getServletContext().getRequestDispatcher("/views/allVehicles.jsp").forward(request, response);
	}

}
