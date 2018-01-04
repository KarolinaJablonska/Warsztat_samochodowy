package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

@WebServlet("/DeleteVehicle")
public class DeleteVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MESSAGE
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Samochód został usunięty poprawie.\n" + "</div>";

		VehicleDao dao = new VehicleDao();
		List<Vehicle> vehicles;
		int idVehicle = Integer.parseInt(request.getParameter("idVehicle"));

		// delete vehicle
		dao.delete(idVehicle);
		request.setAttribute("message", SUCCESS_MESSAGE);

		vehicles = dao.loadAll();
		request.setAttribute("vehicles", vehicles);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/allVehicles.jsp").forward(request, response);
	}

}
