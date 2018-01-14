package pl.coderslab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;


@WebServlet("/OrderById")
public class OrderById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		int id = Integer.parseInt(request.getParameter("idOrder"));
		OrderDao dao = new OrderDao();
		Order order = dao.loadOneById(id);

		List<Order> orders = new ArrayList<>();
		orders.add(order);
		request.setAttribute("order", order);
		request.setAttribute("orders", orders);

		getServletContext().getRequestDispatcher("/views/allOrders.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		int id = Integer.parseInt(request.getParameter("idOrder"));
		OrderDao dao = new OrderDao();
		Order order = dao.loadOneById(id);

		List<Order> orders = new ArrayList<>();
		orders.add(order);
		request.setAttribute("order", order);
		request.setAttribute("orders", orders);

		getServletContext().getRequestDispatcher("/views/allOrders.jsp").forward(request, response);
	}

}
