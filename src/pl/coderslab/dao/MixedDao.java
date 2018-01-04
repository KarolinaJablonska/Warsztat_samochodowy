package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import pl.coderslab.model.Customer;
import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Vehicle;

public class MixedDao {

	private static final String LOAD_CUSTOMERS_ALL_VEHICLES = "SELECT * FROM Customer JOIN Vehicle ON Customer.idCustomer=Vehicle.customerId WHERE Customer.idCustomer=?";

	
	
	public Map<Customer, Vehicle>  loadCustomersAllVehicles(int id) {

		Map<Customer, Vehicle> customerAllVehicles = new HashMap<Customer, Vehicle>();

		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(LOAD_CUSTOMERS_ALL_VEHICLES);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				Customer c = new Customer();
				c.setIdCustomer(rs.getInt("idCustomer"));
				c.setName(rs.getString("name"));
				c.setSurname(rs.getString("surname"));
				c.setBirthDay(rs.getString("birthDay"));
				

				Vehicle v = new Vehicle();
				v.setIdVehicle(rs.getInt("idVehicle"));
				v.setModel(rs.getString("model"));
				v.setMark(rs.getString("mark"));
				v.setProductionYear(rs.getInt("productionYear"));
				v.setRegistrationNr(rs.getString("registrationNr"));
				v.setNextTechnicalReviewDate(rs.getString("nextTechnicalReviewDate"));
				v.setCustomerId(rs.getInt("customerId"));

				customerAllVehicles.put(c, v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return customerAllVehicles;
	}
}
