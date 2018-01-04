package pl.coderslab.model;

public class Customer {

	private int idCustomer;
	private String name;
	private String surname;
	private String birthDay;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String surname, String birthDay) {
		this.name = name;
		this.surname = surname;
		this.birthDay = birthDay;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdCustomer() {
		return idCustomer;
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

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
}
