package pl.coderslab.model;

public class Vehicle {
	
	private int idVehicle;
	private String model;
	private String mark;
	private int productionYear;
	private String registrationNr;
	private String nextTechnicalReviewDate;
	private int customerId;
	
	public Vehicle() {
		// TODO Auto-generated constructor stub
	}
	
	public Vehicle(int id, String model, String mark, int productionYear, String registrationNr,
			String nextTechnicalReviewDate, int customerId) {
		super();
		this.idVehicle = id;
		this.model = model;
		this.mark = mark;
		this.productionYear = productionYear;
		this.registrationNr = registrationNr;
		this.nextTechnicalReviewDate = nextTechnicalReviewDate;
		this.customerId = customerId;
	}

	public int getIdVehicle() {
		return idVehicle;
	}

	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public String getRegistrationNr() {
		return registrationNr;
	}

	public void setRegistrationNr(String registrationNr) {
		this.registrationNr = registrationNr;
	}

	public String getNextTechnicalReviewDate() {
		return nextTechnicalReviewDate;
	}

	public void setNextTechnicalReviewDate(String nextTechnicalReviewDate) {
		this.nextTechnicalReviewDate = nextTechnicalReviewDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
