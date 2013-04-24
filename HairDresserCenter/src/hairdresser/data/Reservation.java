package hairdresser.data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
	
	public static final String SIMPLE_DATE_FORMAT = "dd.MM.yyyy HH:mm";
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

		this(client, service,SDF.parse(reservationDate) );
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
		return service;
	}

	public void setService(int serviceCode) {
		if(serviceCode == 1) {
			this.service = new ManHaircut();
		}
		else if(serviceCode == 2) {
			this.service = new WomanHaircut();
		}
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
			Reservation testReservation  = (Reservation) obj;
			result = this.client.equals(testReservation.getClient());
			result&= this.service.equals(testReservation.getService());
			result&= this.reservationDate.equals(testReservation.getReservationDate());
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
