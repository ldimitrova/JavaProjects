package hairdresser.main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

	public static final String SIMPLE_DATE_FORMAT = "dd.mm.yyyy HH:mm";
	private Client client;
	private ServiceType service;
	private Date reservationDate;
	
	public static final SimpleDateFormat SDF =  new SimpleDateFormat(SIMPLE_DATE_FORMAT);

	public Reservation(Client client, ServiceType service, Date reservationDate) {

		this.client = client;
		this.service = service;
		this.reservationDate = reservationDate;
	}

	public Reservation(Client client, ServiceType service, String reservationDate) throws ParseException {

		this.client = client;
		this.service = service;
		this.reservationDate = SDF.parse(reservationDate);
	}
	
	public Client getClient() {
		return client;
	}

	public ServiceType getService() {
		return service;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Reservation) {
			Reservation testReservation  = (Reservation) obj;
			result = this.client.equals(testReservation.getClient());
			result&= this.service.equals(testReservation.getService());
			result&= this.reservationDate.equals(testReservation.getReservationDate());
		}
		
	
		return result;
	}

}
