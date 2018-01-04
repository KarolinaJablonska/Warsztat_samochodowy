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

@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// MESSAGE
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Klient został dodany poprawnie.\n" + "</div>";
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		CustomerDao dao = new CustomerDao();
		List<Customer> customers;

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String birthDay = request.getParameter("birthDay");

		//setting empty data suitable to Data Base input
		if (birthDay.equals("")) {
			birthDay = null;
		}
	
		dao.saveToDb(0, name, surname, birthDay);
		request.setAttribute("message", SUCCESS_MESSAGE);

		customers = dao.loadAll();
		request.setAttribute("customers", customers);
		getServletContext().getRequestDispatcher("/views/customers.jsp").forward(request, response);
	}
}
