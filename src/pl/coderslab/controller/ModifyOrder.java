package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

@WebServlet("/ModifyOrder")
public class ModifyOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		OrderDao dao = new OrderDao();
		EmployeeDao dao2 = new EmployeeDao();
		VehicleDao dao3 = new VehicleDao();
		List<Order> orders = dao.loadAll();
		List<Employee> employees = dao2.loadAll();
		List<Vehicle> vehicles = dao3.loadAll();
		Order orderToModify;

		int idOrder = Integer.parseInt(request.getParameter("idOrder"));

		// get order to modify (by id)
		for (Order o : orders) {
			if (o.getIdOrder() == idOrder) {
				orderToModify = o;
				request.setAttribute("order", orderToModify);
			}
		}

		request.setAttribute("employees", employees);
		request.setAttribute("vehicles", vehicles);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/modifyOrder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MESSAGE
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Zamówienie zostało dodane poprawnie.\n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		OrderDao dao = new OrderDao();
		EmployeeDao dao2 = new EmployeeDao();
		List<Order> orders;
		List<Employee> employees = dao2.loadAll();

		int idOrder = Integer.parseInt(request.getParameter("idOrder"));
		String acceptanceForRepairDate = request.getParameter("acceptanceForRepairDate"); 			// required
		String plannedRepairDate = request.getParameter("plannedRepairDate");
		String startRepairDate = request.getParameter("startRepairDate");
		int servingEmployeeId = Integer.parseInt(request.getParameter("servingEmployeeId")); 		// required
		String problemDescription = request.getParameter("problemDescription");
		String repairDescription = request.getParameter("repairDescription");
		String status = request.getParameter("status"); 											// required
		int repairedVehicleId = Integer.parseInt(request.getParameter("repairedVehicleId")); 		// required
		double costForCustomer;
		double costOfParts;
		double manHourCost;
		double manHourQuantity;

		try {
			costForCustomer = Double.parseDouble(request.getParameter("costForCustomer"));
		} catch (Exception e) {
			costForCustomer = 0.00;
		}
		try {
			costOfParts = Double.parseDouble(request.getParameter("costOfParts"));
		} catch (Exception e) {
			costOfParts = 0.00;
		}
		try {
			manHourCost = Double.parseDouble(request.getParameter("manHourCost"));
		} catch (Exception e) {
			manHourCost = 0.00;
		}
		try {
			manHourQuantity = Double.parseDouble(request.getParameter("manHourQuantity"));
		} catch (Exception e) {
			manHourQuantity = 0.00;
		}

		if (plannedRepairDate.equals("")) {
			plannedRepairDate = null;
		}
		if (startRepairDate.equals("")) {
			startRepairDate = null;
		}
		if (problemDescription.equals("")) {
			problemDescription = null;
		}
		if (repairDescription.equals("")) {
			repairDescription = null;
		}

		dao.saveToDb(idOrder, acceptanceForRepairDate, plannedRepairDate, startRepairDate, servingEmployeeId,
				problemDescription, repairDescription, status, repairedVehicleId, costForCustomer, costOfParts,
				manHourCost, manHourQuantity);
		request.setAttribute("message", SUCCESS_MESSAGE);

		orders = dao.loadAll();
		request.setAttribute("orders", orders);
		request.setAttribute("employees", employees);
		getServletContext().getRequestDispatcher("/views/allOrders.jsp").forward(request, response);
	}
}
