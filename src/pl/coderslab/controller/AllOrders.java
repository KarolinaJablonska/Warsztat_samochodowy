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

@WebServlet("/AllOrders")
public class AllOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderDao dao = new OrderDao();
		List<Order> orders = dao.loadAll();

		request.setAttribute("orders", orders);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/allOrders.jsp").forward(request, response);
	}
}
