package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import pl.coderslab.model.Customer;
import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;
import pl.coderslab.model.Vehicle;

public class MixedDao {

	private static final String LOAD_CUSTOMERS_ALL_VEHICLES = "SELECT * FROM Customer JOIN Vehicle ON Customer.idCustomer=Vehicle.customerId WHERE Customer.idCustomer=?";
	private static final String LOAD_EMPLOYEE_ALL_ORDERS = "SELECT * FROM Employee JOIN `Order` ON Employee.idEmployee=`Order`.servingEmployeeId WHERE Employee.idEmployee=?";
	
	
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
	
	public Map<Employee, Order>  loadEmployeeAllOrders(int id) {

		Map<Employee, Order>  employeeAllOrders = new HashMap<>();

		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(LOAD_EMPLOYEE_ALL_ORDERS);
			statement.setInt(1, id);
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
				
				Order o = new Order();
				o.setIdOrder(rs.getInt("idOrder"));
				o.setAcceptanceForRepairDate(rs.getString("acceptanceForRepairDate"));
				o.setPlannedRepairDate(rs.getString("plannedRepairDate"));
				o.setStartRepairDate(rs.getString("startRepairDate"));
				o.setServingEmployeeId(rs.getInt("servingEmployeeId"));
				o.setProblemDescription(rs.getString("problemDescription"));
				o.setRepairDescription(rs.getString("repairDescription"));
				o.setStatus(rs.getString("status"));
				o.setRepairedVehicleId(rs.getInt("repairedVehicleId"));
				o.setCostForCustomer(rs.getDouble("costForCustomer"));
				o.setCostOfParts(rs.getDouble("costOfParts"));
				o.setManHourCost(rs.getDouble("manHourCost"));
				o.setManHourQuantity(rs.getDouble("manHourQuantity"));

				employeeAllOrders.put(e, o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return employeeAllOrders;
	}
	
	
}
