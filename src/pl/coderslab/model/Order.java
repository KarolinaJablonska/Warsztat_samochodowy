package pl.coderslab.model;

public class Order {
	
	private int idOrder;
	private String acceptanceForRepairDate;
	private String plannedRepairDate;
	private String startRepairDate;
	private int servingEmployeeId;
	private String problemDescription;
	private String repairDescription;
	private String status;
	private int repairedVehicleId;
	private double costForCustomer;
	private double costOfParts;
	private double manHourCost;
	private double manHourQuantity;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(int idOrder, String acceptanceForRepairDate, String plannedRepairDate, String startRepairDate,
			int servingEmployeeId, String problemDescription, String repairDescription, String status,
			int repairedVehicleId, double costForCustomer, double costOfParts, double manHourCost,
			double manHourQuantity) {
		this.idOrder = idOrder;
		this.acceptanceForRepairDate = acceptanceForRepairDate;
		this.plannedRepairDate = plannedRepairDate;
		this.startRepairDate = startRepairDate;
		this.servingEmployeeId = servingEmployeeId;
		this.problemDescription = problemDescription;
		this.repairDescription = repairDescription;
		this.status = status;
		this.repairedVehicleId = repairedVehicleId;
		this.costForCustomer = costForCustomer;
		this.costOfParts = costOfParts;
		this.manHourCost = manHourCost;
		this.manHourQuantity = manHourQuantity;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getAcceptanceForRepairDate() {
		return acceptanceForRepairDate;
	}

	public void setAcceptanceForRepairDate(String acceptanceForRepairDate) {
		this.acceptanceForRepairDate = acceptanceForRepairDate;
	}

	public String getPlannedRepairDate() {
		return plannedRepairDate;
	}

	public void setPlannedRepairDate(String plannedRepairDate) {
		this.plannedRepairDate = plannedRepairDate;
	}

	public String getStartRepairDate() {
		return startRepairDate;
	}

	public void setStartRepairDate(String startRepairDate) {
		this.startRepairDate = startRepairDate;
	}

	public int getServingEmployeeId() {
		return servingEmployeeId;
	}

	public void setServingEmployeeId(int servingEmployeeId) {
		this.servingEmployeeId = servingEmployeeId;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getRepairDescription() {
		return repairDescription;
	}

	public void setRepairDescription(String repairDescription) {
		this.repairDescription = repairDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRepairedVehicleId() {
		return repairedVehicleId;
	}

	public void setRepairedVehicleId(int repairedVehicleId) {
		this.repairedVehicleId = repairedVehicleId;
	}

	public double getCostForCustomer() {
		return costForCustomer;
	}

	public void setCostForCustomer(double costForCustomer) {
		this.costForCustomer = costForCustomer;
	}

	public double getCostOfParts() {
		return costOfParts;
	}

	public void setCostOfParts(double costOfParts) {
		this.costOfParts = costOfParts;
	}

	public double getManHourCost() {
		return manHourCost;
	}

	public void setManHourCost(double manHourCost) {
		this.manHourCost = manHourCost;
	}

	public double getManHourQuantity() {
		return manHourQuantity;
	}

	public void setManHourQuantity(double manHourQuantity) {
		this.manHourQuantity = manHourQuantity;
	}
	
	

}
