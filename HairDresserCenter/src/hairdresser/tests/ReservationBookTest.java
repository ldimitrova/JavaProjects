package hairdresser.tests;
 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hairdresser.data.Client;
import hairdresser.data.ManHaircut;
import hairdresser.data.Reservation;
import hairdresser.data.ServiceType;
import hairdresser.exception.OverlappingTimeException;
import hairdresser.exception.OvertimeException;
import hairdresser.main.ReservationBook;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReservationBookTest {
	
	public static final String CORRECT_RESERVATION_DATE = "01.02.2013 12:00";
	public static final String OVERTIME_RESERVATION_DATE="01.02.2013 19:00";
	
	private ReservationBook reservationBook;
	private Client client;
	private ServiceType service;
	private Reservation reservation;
	private Reservation overlappingReservation;
	private Reservation overtimeReservation;
	
	private Date overlappingReservationDate;
	private Date overtimeReservationDate;
	
	@Before
	public void setup() {
		reservationBook = new ReservationBook();
		client = new Client("Krasimira", "Evgenieva", "0898444444");
		service = new ManHaircut();
	}
	
	@Test
	public void testAddReservation() throws Exception  {
		reservation = new Reservation(client,service,CORRECT_RESERVATION_DATE);
		reservationBook.addReservation(reservation);
		
		List<Reservation> allReservations = reservationBook.getAllReservations();
		
		assertFalse("There are no reservations", allReservations.isEmpty());
		assertTrue("This reservation doesn't exist", allReservations.contains(reservation));
	}
	
	@Test(expected = OverlappingTimeException.class)
	public void testAddReservation_overlappingReservation() throws Exception {

		reservation = new Reservation(client,service,CORRECT_RESERVATION_DATE);
		reservationBook.addReservation(reservation);
		
		overlappingReservationDate = Reservation.SDF.parse(CORRECT_RESERVATION_DATE);
		overlappingReservationDate = new Date(overlappingReservationDate.getTime() + 15*60*1000);
		
		overlappingReservation = new Reservation(client,service,overlappingReservationDate );
		reservationBook.addReservation(overlappingReservation);
	
		assertFalse("Overlapping Reservation is trying to be added", reservationBook.getAllReservations().contains(overlappingReservation));
	}
	
	
	@Test(expected = OvertimeException.class)
	public void testAddReservation_overtimeReservation() throws Exception {

		overtimeReservationDate = Reservation.SDF.parse(OVERTIME_RESERVATION_DATE);
		overtimeReservation = new Reservation(client,service,overtimeReservationDate );
		
		reservationBook.addReservation(overtimeReservation);
		assertFalse("Overtime Reservation is trying to be added", reservationBook.getAllReservations().contains(overtimeReservation));
	}
	
	@Test
	public void testDeleteReservation() throws Exception {
		reservation = new Reservation(client,service,CORRECT_RESERVATION_DATE);
		reservationBook.addReservation(reservation);
		reservationBook.deleteReservation(reservation.getReservationDate());
		
		List<Reservation> allReservations = reservationBook.getAllReservations();
		assertFalse("This reservation doesn't exist", allReservations.contains(reservation));
		
	}
	
}
