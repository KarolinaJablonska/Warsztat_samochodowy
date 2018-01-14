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

@WebServlet("/AllActualOrders")
public class AllActualOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		OrderDao dao = new OrderDao();
		List<Order> actualOrders = dao.loadAllActualOrders();
		request.setAttribute("actualOrders", actualOrders);
		getServletContext().getRequestDispatcher("/views/index.jsp").forward(request, response);
	}

}
