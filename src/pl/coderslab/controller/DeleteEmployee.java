package pl.coderslab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Employee;

@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// MESSAGE
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Pracownik został usunięty poprawie.\n" + "</div>";

		EmployeeDao dao = new EmployeeDao();
		List<Employee> employees;
		int idEmployee = Integer.parseInt(request.getParameter("idEmployee"));

		// delete employee
		dao.delete(idEmployee);
		request.setAttribute("message", SUCCESS_MESSAGE);

		employees = dao.loadAll();
		request.setAttribute("employees", employees);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/allEmployees.jsp").forward(request, response);
	}
}
