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
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;

@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MESSAGES
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Zamówienie zostało dodane poprawnie.\n" + "</div>";

//		String INCORRECT_DATA_IN_FORM = "<div class=\"alert alert-danger alert-dismissable\">\n"
//				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
//				+ "Zamówienie nie zostało dodane - błędnie wypełniony formularz. \n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		OrderDao dao = new OrderDao();
		List<Order> orders;
		EmployeeDao dao2 = new EmployeeDao();
		List<Employee> employees = dao2.loadAll();
		
//		boolean isPhoneOk, isManHourOk, isPostalCodeOk;

		String acceptanceForRepairDate = request.getParameter("acceptanceForRepairDate");
		String plannedRepairDate = request.getParameter("plannedRepairDate");
		String startRepairDate = request.getParameter("startRepairDate");
		int repairedVehicleId = Integer.parseInt(request.getParameter("repairedVehicleId"));
		int servingEmployeeId = Integer.parseInt(request.getParameter("servingEmployeeId"));
		String problemDescription = request.getParameter("problemDescription");
		String repairDescription = request.getParameter("repairDescription");
		String status = request.getParameter("status");
		double costForCustomer;
		double costOfParts;
		double manHourCost;
		double manHourQuantity;
		
		
		try {
			costForCustomer = Double.parseDouble(request.getParameter("costForCustomer"));
			costOfParts = Double.parseDouble(request.getParameter("costOfParts"));
			manHourCost = Double.parseDouble(request.getParameter("manHourCost"));
			manHourQuantity = Double.parseDouble(request.getParameter("manHourQuantity"));

		} catch (Exception e) {
			costForCustomer = 0;
			costOfParts = 0;
			manHourCost = 0;
			manHourQuantity = 0;

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
		

		// CHECKING IF DATAS ARE OK -> SETTING BOOLEANS
		// if (phone.length() == 11 && !phone.matches(".*[a-żA-Ż]+.*")) {
		// isPhoneOk = true;
		// } else {
		// isPhoneOk = false;
		// request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		// }
		//
		// try {
		// manHour = Double.parseDouble(request.getParameter("manHour"));
		// isManHourOk = true;
		// } catch (Exception e) {
		// manHour = 0.00;
		// isManHourOk = false;
		// request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		// }
		//
		// if (postalCode.length() == 6 && !postalCode.matches(".*[a-żA-Ż]+.*")) {
		// isPostalCodeOk = true;
		// } else {
		// isPostalCodeOk = false;
		// request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		// }

		// CHECKING IF NECESSARY DATAS WERE FILLED AND BOOLEANS ARE TRUE
		// if (!name.isEmpty() && !surname.isEmpty() && !phone.isEmpty() &&
		// !street.isEmpty() && !postalCode.isEmpty()
		// && !city.isEmpty() && manHour != 0 && isManHourOk == true && isPhoneOk ==
		// true
		// && isPostalCodeOk == true) {
		// dao.saveToDb(0, name, surname, phone, note, manHour, street, postalCode,
		// city);
		// request.setAttribute("message", SUCCESS_MESSAGE);
		// } else {
		// request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		// }

		dao.saveToDb(0, acceptanceForRepairDate, plannedRepairDate, startRepairDate, servingEmployeeId,
				problemDescription, repairDescription, status, repairedVehicleId, costForCustomer, costOfParts, manHourCost, manHourQuantity);
		request.setAttribute("message", SUCCESS_MESSAGE);
		orders = dao.loadAll();
		request.setAttribute("orders", orders);
		request.setAttribute("employees", employees);
		getServletContext().getRequestDispatcher("/views/allOrders.jsp").forward(request, response);
	}

}
