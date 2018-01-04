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

@WebServlet("/ModifyEmployee")
public class ModifyEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EmployeeDao dao = new EmployeeDao();
		List<Employee> employees = dao.loadAll();
		Employee employeeToModify;

		int idEmployee = Integer.parseInt(request.getParameter("idEmployee"));

		// get employee to modify (by id)
		for (Employee e : employees) {
			if (e.getIdEmployee() == idEmployee) {
				employeeToModify = e;
				request.setAttribute("employee", employeeToModify);
			}
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		getServletContext().getRequestDispatcher("/views/modifyEmployee.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// MESSAGE
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Dane o pracowniku zostały zmodyfikowane poprawnie.\n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		EmployeeDao dao = new EmployeeDao();
		List<Employee> employees;
		
		int idEmployee = Integer.parseInt(request.getParameter("idEmployee")); 	// required
		String name = request.getParameter("name"); 							// required
		String surname = request.getParameter("surname"); 						// required
		String phone = request.getParameter("phone"); 							// required
		String note = request.getParameter("note");
		double manHour = Double.parseDouble(request.getParameter("manHour")); 	// required
		String street = request.getParameter("street"); 						// required
		String postalCode = request.getParameter("postalCode"); 				// required
		String city = request.getParameter("city"); 							// required

		// setting empty data suitable to Data Base input
		if (note.equals("")) {
			note = null;
		}

		dao.saveToDb(idEmployee, name, surname, phone, note, manHour, street, postalCode, city);
		request.setAttribute("message", SUCCESS_MESSAGE);

		employees = dao.loadAll();
		request.setAttribute("employees", employees);
		getServletContext().getRequestDispatcher("/views/allEmployees.jsp").forward(request, response);
	}

}
