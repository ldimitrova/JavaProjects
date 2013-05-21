package hairdresser.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
		@NamedQuery(name = "selectAll", query = "select r from Reservation r"),
		@NamedQuery(name = "selectDelRes", query = "select r from Reservation r where r.reservationDate = :reservationDate") })
@Entity
@Table(name = "RESERVATION")
public class Reservation {
	public static final String SIMPLE_DATE_FORMAT = "dd.MM.yyyy HH:mm";

	@Id
	@Column(name = "RESERV_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long resId;

	/*
	 * @Column(name = "CLIENT_ID") private long clientId;
	 */

	@ManyToOne(optional = false)
	@JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
	private Client client;

	@Transient
	private ServiceType service;

	@Column(name = "RESVICE_CODE")
	private int serviceCode;

	@Column(name = "RESERVATION_DATE")
	private Date reservationDate;

	public static final SimpleDateFormat SDF = new SimpleDateFormat(
			SIMPLE_DATE_FORMAT);

	public Reservation(Client client, ServiceType service, Date reservationDate) {

		this.client = client;
		this.service = service;
		this.reservationDate = reservationDate;
	}

	public Reservation(Client client, ServiceType service,
			String reservationDate) throws ParseException {

		this(client, service, SDF.parse(reservationDate));
	}

	public Reservation() {

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ServiceType getService() {

		if (service == null) {
			setService(serviceCode);
		}
		return service;
	}

	public void setService(int serviceCode) {
		
		this.serviceCode = serviceCode;
		if (serviceCode == 1) {
			this.service = new ManHaircut();
		} else if (serviceCode == 2) {
			this.service = new WomanHaircut();
		}
	}

	public int getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Reservation) {
			Reservation testReservation = (Reservation) obj;
			result = this.client.equals(testReservation.getClient());
			result &= this.service.equals(testReservation.getService());
			result &= this.reservationDate.equals(testReservation
					.getReservationDate());
		}

		return result;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");
		result.append(this.getClient().getFirstName() + " ");
		result.append(this.getClient().getLastName() + " ");
		result.append(this.getClient().getPhone() + " ");
		result.append(this.getReservationDate() + " ");
		result.append(this.getService().getServiceName());

		return result.toString();
	}

}
