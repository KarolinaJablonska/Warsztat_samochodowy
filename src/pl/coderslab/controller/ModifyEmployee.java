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

		// MESSAGES
		String SUCCESS_MESSAGE = "<div class=\"alert alert-success alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Pracownik został dodany poprawie.\n" + "</div>";

		String INCORRECT_DATA_IN_FORM = "<div class=\"alert alert-danger alert-dismissable\">\n"
				+ "  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>\n"
				+ "  Pracownik nie został dodany - błędnie wypełniony formularz. \n" + "</div>";

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		EmployeeDao dao = new EmployeeDao();
		List<Employee> employees;
		boolean isPhoneOk, isManHourOk, isPostalCodeOk;

		int idEmployee = Integer.parseInt(request.getParameter("idEmployee"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String phone = request.getParameter("phone");
		String note = request.getParameter("note");
		String street = request.getParameter("street");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		double manHour;

		// CHECKING IF DATAS ARE OK -> SETTING BOOLEANS
		if (phone.length() == 11 && !phone.matches(".*[a-żA-Ż]+.*")) {
			isPhoneOk = true;
		} else {
			isPhoneOk = false;
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		}

		try {
			manHour = Double.parseDouble(request.getParameter("manHour"));
			isManHourOk = true;
		} catch (Exception e) {
			manHour = 0.00;
			isManHourOk = false;
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		}

		if (postalCode.length() == 6 && !postalCode.matches(".*[a-żA-Ż]+.*")) {
			isPostalCodeOk = true;
		} else {
			isPostalCodeOk = false;
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		}

		// CHECKING IF NECESSARY DATAS WERE FILLED AND BOOLEANS ARE TRUE
		if (!name.isEmpty() && !surname.isEmpty() && !phone.isEmpty() && !street.isEmpty() && !postalCode.isEmpty()
				&& !city.isEmpty() && manHour != 0 && isManHourOk == true && isPhoneOk == true
				&& isPostalCodeOk == true) {
			dao.saveToDb(idEmployee, name, surname, phone, note, manHour, street, postalCode, city);
			request.setAttribute("message", SUCCESS_MESSAGE);
		} else {
			request.setAttribute("message", INCORRECT_DATA_IN_FORM);
		}

		employees = dao.loadAll();
		request.setAttribute("employees", employees);
		getServletContext().getRequestDispatcher("/views/allEmployees.jsp").forward(request, response);
	}

}
