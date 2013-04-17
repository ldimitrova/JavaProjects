package hairdresser.main;
public class Client {
	private String firstName;
	private String lastName;
	private String phone;

	public Client(String firstName, String lastName, String phone) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

}
