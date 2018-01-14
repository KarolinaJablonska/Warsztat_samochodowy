package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Order;

public class OrderDao {

	private static final String LOAD_ALL_ORDERS = "SELECT * FROM `Order`";
	private static final String LOAD_ONE_ORDER_BY_ID = "SELECT * FROM `Order` WHERE idOrder=?";
	private static final String LOAD_ALL_ACTUAL_ORDERS = "SELECT * FROM `Order` WHERE `Order`.status LIKE '%naprawie%'";
	private static final String SAVE_NEW = "INSERT INTO `Order`(acceptanceForRepairDate, plannedRepairDate, startRepairDate, servingEmployeeId, problemDescription, repairDescription, status, repairedVehicleId, costForCustomer, costOfParts, manHourCost, manHourQuantity) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_BY_ID = "UPDATE `Order` SET acceptanceForRepairDate=?, plannedRepairDate=?, startRepairDate=?, servingEmployeeId=?, problemDescription=?, repairDescription=?, status=?, repairedVehicleId=?, costForCustomer=?, costOfParts=?, manHourCost=?, manHourQuantity=?  WHERE idOrder=?";
	private static final String DELETE_ONE_BY_ID = "DELETE FROM `Order` where idOrder=?";

	/**
	 * Load all orders.
	 * 
	 * @return List of orders.
	 */
	public List<Order> loadAll() {

		List<Order> orders = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(LOAD_ALL_ORDERS);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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
				orders.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return orders;
	}

	public List<Order> loadAllActualOrders() {

		List<Order> orders = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(LOAD_ALL_ACTUAL_ORDERS);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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
				orders.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return orders;
	}

	/**
	 * Load one order by Id.
	 * @param idOrder
	 * @return Order
	 */
	public Order loadOneById(int id) {

		Order order = new Order();
		try (Connection conn = DbUtil.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(LOAD_ONE_ORDER_BY_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
			order = new Order();
			order.setIdOrder(rs.getInt("idOrder"));
			order.setAcceptanceForRepairDate(rs.getString("acceptanceForRepairDate"));
			order.setPlannedRepairDate(rs.getString("plannedRepairDate"));
			order.setStartRepairDate(rs.getString("startRepairDate"));
			order.setServingEmployeeId(rs.getInt("servingEmployeeId"));
			order.setProblemDescription(rs.getString("problemDescription"));
			order.setRepairDescription(rs.getString("repairDescription"));
			order.setStatus(rs.getString("status"));
			order.setRepairedVehicleId(rs.getInt("repairedVehicleId"));
			order.setCostForCustomer(rs.getDouble("costForCustomer"));
			order.setCostOfParts(rs.getDouble("costOfParts"));
			order.setManHourCost(rs.getDouble("manHourCost"));
			order.setManHourQuantity(rs.getDouble("manHourQuantity"));
			
			}
			return order;
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Coś nie tak :(");
		}
		return null;
	}

	/**
	 * Saves new or updated order.
	 * 
	 * @param idOrder
	 * @param acceptanceForRepairDate
	 * @param plannedRepairDate
	 * @param startRepairDate
	 * @param servingEmployeeId
	 * @param problemDescription
	 * @param repairDescription
	 * @param status
	 * @param repairedVehicleId
	 * @param costForCustomer
	 * @param costOfParts
	 * @param manHourCost
	 * @param manHourQuantity
	 */
	public void saveToDb(int idOrder, String acceptanceForRepairDate, String plannedRepairDate, String startRepairDate,
			int servingEmployeeId, String problemDescription, String repairDescription, String status,
			int repairedVehicleId, double costForCustomer, double costOfParts, double manHourCost,
			double manHourQuantity) {
		if (idOrder == 0) {
			try (Connection conn = DbUtil.getConnection()) {
				String[] generatedColumns = { "idOrder" };
				PreparedStatement statement = conn.prepareStatement(SAVE_NEW, generatedColumns);
				statement.setString(1, acceptanceForRepairDate);
				statement.setString(2, plannedRepairDate);
				statement.setString(3, startRepairDate);
				statement.setInt(4, servingEmployeeId);
				statement.setString(5, problemDescription);
				statement.setString(6, repairDescription);
				statement.setString(7, status);
				statement.setInt(8, repairedVehicleId);
				statement.setDouble(9, costForCustomer);
				statement.setDouble(10, costOfParts);
				statement.setDouble(11, manHourCost);
				statement.setDouble(12, manHourQuantity);
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					idOrder = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		} else {
			try (Connection conn = DbUtil.getConnection()) {
				PreparedStatement statement = conn.prepareStatement(UPDATE_BY_ID);
				statement.setString(1, acceptanceForRepairDate);
				statement.setString(2, plannedRepairDate);
				statement.setString(3, startRepairDate);
				statement.setInt(4, servingEmployeeId);
				statement.setString(5, problemDescription);
				statement.setString(6, repairDescription);
				statement.setString(7, status);
				statement.setInt(8, repairedVehicleId);
				statement.setDouble(9, costForCustomer);
				statement.setDouble(10, costOfParts);
				statement.setDouble(11, manHourCost);
				statement.setDouble(12, manHourQuantity);
				statement.setInt(13, idOrder);
				statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Delete order by id.
	 * 
	 * @param idOrder
	 */
	public void delete(int idOrder) {
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(DELETE_ONE_BY_ID);
			statement.setInt(1, idOrder);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Zamówienie wciąż istnieje.");
		}
	}

}
