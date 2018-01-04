package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MESSAGE
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Zlecenie zostało usunięte poprawnie.\n" + "</div>";
		
		OrderDao dao = new OrderDao();
		List<Order> orders;
		int idOrder = Integer.parseInt(request.getParameter("idOrder"));

		// delete order
		dao.delete(idOrder);
		request.setAttribute("message", SUCCESS_MESSAGE);

		orders = dao.loadAll();
		request.setAttribute("orders", orders);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/allOrders.jsp").forward(request, response);
	}
}
