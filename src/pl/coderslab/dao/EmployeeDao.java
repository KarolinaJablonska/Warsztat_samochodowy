package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Employee;

public class EmployeeDao {

	private static final String LOAD_ALL_EMPLOYEES = "SELECT * FROM Employee";
	private static final String SAVE_NEW = "INSERT INTO Employee(name, surname, phone, note, manHour, street, postalCode, city) VALUES (?,?,?,?,?,?,?,?)";
	private static final String UPDATE_BY_ID = "UPDATE Employee SET name=?, surname=?, phone=?, note=?, manHour=?, street=?, postalCode=?, city=? WHERE idEmployee=?";
	private static final String DELETE_ONE_BY_ID = "DELETE FROM Employee where idEmployee=?";

	/**
	 * Load all employees.
	 * 
	 * @return List of all employees.
	 */
	public List<Employee> loadAll() {

		List<Employee> employees = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(LOAD_ALL_EMPLOYEES);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Employee e = new Employee();
				e.setIdEmployee(rs.getInt("idEmployee"));
				e.setName(rs.getString("name"));
				e.setSurname(rs.getString("surname"));
				e.setPhone(rs.getString("phone"));
				e.setNote(rs.getString("note"));
				e.setManHour(rs.getDouble("manHour"));
				e.setStreet(rs.getString("street"));
				e.setPostalCode(rs.getString("postalCode"));
				e.setCity(rs.getString("city"));
				employees.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return employees;
	}

	/**
	 * Save new or updated info about employee to DataBase.
	 * 
	 * @param idEmployee
	 * @param name
	 * @param surname
	 * @param phone
	 * @param note
	 * @param manHour
	 * @param street
	 * @param postalCode
	 * @param city
	 */
	public void saveToDb(int idEmployee, String name, String surname, String phone, String note, double manHour, String street,
			String postalCode, String city) {
		if (idEmployee == 0) {
			try (Connection conn = DbUtil.getConnection()) {
				String[] generatedColumns = { "idEmployee" };
				PreparedStatement statement = conn.prepareStatement(SAVE_NEW, generatedColumns);
				statement.setString(1, name);
				statement.setString(2, surname);
				statement.setString(3, phone);
				statement.setString(4, note);
				statement.setDouble(5, manHour);
				statement.setString(6, street);
				statement.setString(7, postalCode);
				statement.setString(8, city);
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					idEmployee = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		} else {
			try (Connection conn = DbUtil.getConnection()) {
				PreparedStatement statement = conn.prepareStatement(UPDATE_BY_ID);
				statement.setString(1, name);
				statement.setString(2, surname);
				statement.setString(3, phone);
				statement.setString(4, note);
				statement.setDouble(5, manHour);
				statement.setString(6, street);
				statement.setString(7, postalCode);
				statement.setString(8, city);
				statement.setInt(9, idEmployee);
				statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Delete employee by id.
	 * 
	 * @param idEmployee
	 */
	public void delete(int idEmployee) {
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(DELETE_ONE_BY_ID);
			statement.setInt(1, idEmployee);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Niestety pracownik jest dalej na liście.");
		}
	}

}
