package pl.coderslab.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

@WebServlet("/ModifyVehicle")
public class ModifyVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VehicleDao dao = new VehicleDao();
		List<Vehicle> vehicles = dao.loadAll();
		Vehicle vehicleToModify;

		int idVehicle = Integer.parseInt(request.getParameter("idVehicle"));

		// get vehicle to modify (by id)
		for (Vehicle v : vehicles) {
			if (v.getIdVehicle() == idVehicle) {
				vehicleToModify = v;
				request.setAttribute("vehicle", vehicleToModify);
			}
		}

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

		String INCORRECT_DATA_IN_FORM = "<div class=\"alert alert-danger alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Samochód nie został dodany - błędnie wypełniony formularz. \n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		VehicleDao dao = new VehicleDao();
		List<Vehicle> vehicles;
		boolean isProductionYearOk, isNextTechnicalReviewDateOk, isRegistrationNrOk;

		String model = request.getParameter("model");
		String mark = request.getParameter("mark");
		String registrationNr = request.getParameter("registrationNr");
		String nextTechnicalReviewDate = request.getParameter("nextTechnicalReviewDate");
		int productionYear;
		int idVehicle = Integer.parseInt(request.getParameter("idVehicle"));

		// CHECKING IF DATAS ARE OK -> SETTING BOOLEANS
		try {
			productionYear = Integer.parseInt(request.getParameter("productionYear"));
			int year = Calendar.getInstance().get(Calendar.YEAR);

			if (productionYear > 1900 && productionYear <= year) {
				isProductionYearOk = true;
			} else {
				isProductionYearOk = false;
				request.setAttribute("message", INCORRECT_DATA_IN_FORM);
			}
		} catch (Exception e) {
			productionYear = 0000;
			isProductionYearOk = false;
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		}

		if (registrationNr.length() == 7 && registrationNr.matches(".*[a-zA-Z0-9]+.*")) {
			isRegistrationNrOk = true;
		} else {
			isRegistrationNrOk = false;
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		}

		if (nextTechnicalReviewDate.equals("")) {
			nextTechnicalReviewDate = null;
			isNextTechnicalReviewDateOk = true;
		} else if (nextTechnicalReviewDate.matches(".*[a-żA-Ż]+.*")) {
			isNextTechnicalReviewDateOk = false;
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		} else {
			isNextTechnicalReviewDateOk = true;
		}

		// CHECKING IF NECESSARY DATAS WERE FILLED AND BOOLEANS ARE TRUE
		if (!model.isEmpty() && !mark.isEmpty() && productionYear != 0 && !registrationNr.isEmpty()
				&& isProductionYearOk == true && isNextTechnicalReviewDateOk == true && isRegistrationNrOk == true) {
			dao.saveToDb(idVehicle, model, mark, productionYear, registrationNr, nextTechnicalReviewDate);
			request.setAttribute("message", SUCCESS_MESSAGE);
		} else {
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		}

		vehicles = dao.loadAll();
		request.setAttribute("vehicles", vehicles);
		getServletContext().getRequestDispatcher("/views/allVehicles.jsp").forward(request, response);
	}

}
