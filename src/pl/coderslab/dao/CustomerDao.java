package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.model.Customer;
import pl.coderslab.model.DbUtil;

public class CustomerDao {

	private static final String LOAD_ALL_CUSTOMERS = "SELECT * FROM Customer";
	private static final String LOAD_ONE_BY_ID = "SELECT * FROM Customer where idCustomer=?";
	private static final String DELETE_ONE_BY_ID = "DELETE FROM Customer where idCustomer=?";
	private static final String SAVE_NEW = "INSERT INTO Customer(name, surname, birthDay) VALUES (?,?,?)";
	private static final String UPDATE_BY_ID = "UPDATE Customer SET name=?, surname=?, birthDay=? WHERE idCustomer=?";

	/**
	 * Load all customers.
	 * 
	 * @return List of all customers.
	 */
	public List<Customer> loadAll() {

		List<Customer> customers = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(LOAD_ALL_CUSTOMERS);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Customer c = new Customer();
				c.setIdCustomer(rs.getInt("idCustomer"));
				c.setName(rs.getString("name"));
				c.setSurname(rs.getString("surname"));
				c.setBirthDay(rs.getString("birthDay"));
				customers.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return customers;
	}
	
	/**
	 * Load one customer by id.
	 * @param idCustomer
	 * @return customer
	 */
	public Customer loadOneById(int idCustomer) {

		try (Connection conn = DbUtil.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(LOAD_ONE_BY_ID);
			statement.setInt(1, idCustomer);
			ResultSet rs = statement.executeQuery();

			Customer customer = new Customer();
			customer.setIdCustomer(rs.getInt("idCustomer"));
			customer.setName(rs.getString("name"));
			customer.setSurname(rs.getString("surname"));
			customer.setBirthDay(rs.getString("birthDay"));

			return customer;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return null;
	}
	
	/**
	 * Save customer to Data Base.
	 * @param idCustomer
	 * @param name
	 * @param surname
	 * @param birthDay
	 */
	public void saveToDb(int idCustomer, String name, String surname, String birthDay) {
		if (idCustomer == 0) {
			try(Connection conn = DbUtil.getConnection()) {
				String[] generatedColumns =	{"idCustomer"};
				PreparedStatement statement = conn.prepareStatement(SAVE_NEW, generatedColumns);
				statement.setString(1, name);
				statement.setString(2, surname);
				statement.setString(3, birthDay);
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					idCustomer = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		} else {
			try(Connection conn = DbUtil.getConnection()) {
				PreparedStatement statement = conn.prepareStatement(UPDATE_BY_ID);
				statement.setString(1, name);
				statement.setString(2, surname);
				statement.setString(3, birthDay);
				statement.setInt(4, idCustomer);
				statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Delete customer by id.
	 * @param idCustomer
	 */
	public void delete(int idCustomer) {
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(DELETE_ONE_BY_ID);
			statement.setInt(1, idCustomer);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Niestety klient jest dalej na liście.");
		}
	}
}
