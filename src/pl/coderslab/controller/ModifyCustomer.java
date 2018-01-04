package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

@WebServlet("/ModifyCustomer")
public class ModifyCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CustomerDao dao = new CustomerDao();
		List<Customer> customers = dao.loadAll();
		Customer customerToModify;

		int idCustomer = Integer.parseInt(request.getParameter("idCustomer"));

		// get customer to modify (by id)
		for (Customer c : customers) {
			if (c.getIdCustomer() == idCustomer) {
				customerToModify = c;
				request.setAttribute("customer", customerToModify);
			}
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/modifyCustomer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// MESSAGES
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Dane o kliencie zostały zmodyfikowane poprawie.\n" + "</div>";

		String FORM_NOT_FILLED = "<div class=\"alert alert-danger alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Dane nie zostały zmienione - niepoprawnie wypełniony formularz. \n" + "</div>";

		String WRONG_DATA = "<div class=\"alert alert-danger alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Dane nie zostały zmienione - błędnie podana data urodzin w formularzu. \n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		CustomerDao dao = new CustomerDao();
		List<Customer> customers;
		boolean isBirthDayOk;

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String birthDay = request.getParameter("birthDay");
		int idCustomer = Integer.parseInt(request.getParameter("idCustomer"));

		// checking if date of birth is correct
		if (birthDay.equals("")) {
			birthDay = null;
			isBirthDayOk = true;
		} else if (birthDay.matches(".*[a-żA-Ż]+.*")) {
			isBirthDayOk = false;
			request.setAttribute("message", WRONG_DATA);
		} else {
			isBirthDayOk = true;
		}

		// checking if name and surname are not empty
		if (!name.isEmpty() && !surname.isEmpty() && isBirthDayOk == true) {
			dao.saveToDb(idCustomer, name, surname, birthDay);
			request.setAttribute("message", SUCCESS_MESSAGE);
		} else if (name.isEmpty() || surname.isEmpty()) {
			request.setAttribute("message", FORM_NOT_FILLED);
		}

		customers = dao.loadAll();
		request.setAttribute("customers", customers);
		getServletContext().getRequestDispatcher("/views/customers.jsp").forward(request, response);
	}
}
