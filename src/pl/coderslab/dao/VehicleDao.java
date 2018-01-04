package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.model.DbUtil;
import pl.coderslab.model.Vehicle;

public class VehicleDao {

	private static final String LOAD_ALL_VEHICLES = "SELECT * FROM Vehicle";
	private static final String SAVE_NEW = "INSERT INTO Vehicle(model, mark, productionYear, registrationNr, nextTechnicalReviewDate, customerId) VALUES (?,?,?,?,?,?)";
	private static final String UPDATE_BY_ID = "UPDATE Vehicle SET model=?, mark=?, productionYear=?, registrationNr=?, nextTechnicalReviewDate=?, customerId=?  WHERE idVehicle=?";
	private static final String DELETE_ONE_BY_ID = "DELETE FROM Vehicle where idVehicle=?";

	/**
	 * Load all vehicles.
	 * 
	 * @return List of all vehicles.
	 */
	public List<Vehicle> loadAll() {

		List<Vehicle> vehicles = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(LOAD_ALL_VEHICLES);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Vehicle v = new Vehicle();

				v.setIdVehicle(rs.getInt("idVehicle"));
				v.setModel(rs.getString("model"));
				v.setMark(rs.getString("mark"));
				v.setProductionYear(rs.getInt("productionYear"));
				v.setRegistrationNr(rs.getString("registrationNr"));
				v.setNextTechnicalReviewDate(rs.getString("nextTechnicalReviewDate"));
				v.setCustomerId(rs.getInt("customerId"));
				vehicles.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Błąd.");
		}
		return vehicles;
	}

	/**
	 * Save new or updated info about vehicle to DataBase.
	 * @param idVehicle
	 * @param model
	 * @param mark
	 * @param productionYear
	 * @param registrationNr
	 * @param nextTechnicalReviewDate
	 * @param customerId
	 */
	public void saveToDb(int idVehicle, String model, String mark, int productionYear, String registrationNr,
			String nextTechnicalReviewDate, int customerId) {
		if (idVehicle == 0) {
			try (Connection conn = DbUtil.getConnection()) {
				String[] generatedColumns = { "id" };
				PreparedStatement statement = conn.prepareStatement(SAVE_NEW, generatedColumns);
				statement.setString(1, model);
				statement.setString(2, mark);
				statement.setInt(3, productionYear);
				statement.setString(4, registrationNr);
				statement.setString(5, nextTechnicalReviewDate);
				statement.setInt(6, customerId);
				statement.executeUpdate();
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					idVehicle = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}

		} else {
			try (Connection conn = DbUtil.getConnection()) {
				PreparedStatement statement = conn.prepareStatement(UPDATE_BY_ID);
				statement.setString(1, model);
				statement.setString(2, mark);
				statement.setInt(3, productionYear);
				statement.setString(4, registrationNr);
				statement.setString(5, nextTechnicalReviewDate);
				statement.setInt(6, customerId);
				statement.setInt(7, idVehicle);
				statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	/**
	 * Delete vehicle by idVehicle.
	 * 
	 * @param idVehicle
	 */
	public void delete(int idVehicle) {
		try (Connection conn = DbUtil.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(DELETE_ONE_BY_ID);
			statement.setInt(1, idVehicle);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Niestety pojazd jest dalej na liście.");
		}
	}

}
