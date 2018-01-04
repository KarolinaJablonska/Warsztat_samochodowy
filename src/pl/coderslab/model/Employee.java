package pl.coderslab.model;

public class Employee {

	private int idEmployee;
	private String name;
	private String surname;
	private String phone;
	private String note;
	// manHour cost
	private double manHour;
	// address
	private String street;
	private String postalCode;
	private String city;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int idEmployee, String name, String surname, String phone, String note, double manHour, String street,
			String postalCode, String city) {
		this.idEmployee = idEmployee;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.note = note;
		this.manHour = manHour;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getManHour() {
		return manHour;
	}

	public void setManHour(double manHour) {
		this.manHour = manHour;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
