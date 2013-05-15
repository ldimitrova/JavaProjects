package hairdresser.data;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client {
	
	@Id 
    @Column(name = "CLIENT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
		
	@Column(name="PHONE")
	private String phone;
	
	private Collection<Reservation> reservations; 
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="client",targetEntity=Reservation.class,fetch=FetchType.EAGER)
	public Collection<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public Client(String firstName, String lastName, String phone) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setClientId(long id) {
		this.clientId = id;
	}

	public long getClientId() {
		return this.clientId;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
