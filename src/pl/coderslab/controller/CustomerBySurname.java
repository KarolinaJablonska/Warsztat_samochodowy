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

@WebServlet("/CustomerBySurname")
public class CustomerBySurname extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String NO_RECORD = "<div class=\"alert alert-danger alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  NIESTETY W BAZIE NIE MA REKORDÓW O PODANYCH KRYTERIACH. \n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		String surname = request.getParameter("surname");
		boolean pageVisited = true;
		boolean record;
		List<Customer> customers;
		CustomerDao dao = new CustomerDao();

		try {
			customers = dao.loadAllBySurname(surname);
			if (!customers.isEmpty() && !customers.equals(null)) {
				record = true;
				request.setAttribute("customers", customers);
			} else {
				record = false;
				request.setAttribute("message", NO_RECORD);
			}

		} catch (Exception e) {
			record = false;
			request.setAttribute("message", NO_RECORD);
		}

		request.setAttribute("record", record);
		request.setAttribute("pageVisited", pageVisited);
		getServletContext().getRequestDispatcher("/views/customerBySurname.jsp").forward(request, response);
	}

}
