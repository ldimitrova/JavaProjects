package hairdresser.tests;

import static org.junit.Assert.*;
import hairdresser.main.Client;
import hairdresser.main.Reservation;
import hairdresser.main.ReservationBook;
import hairdresser.main.ServiceType;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Test;


public class TestReservationBook {
	
	@Test
	public void testAddReservation() throws Exception {
		ReservationBook reservationBook = new ReservationBook();
		//Date reservationDate = new Date();
		String reservationDate="01.02.2013 12:00";
		Client client = new Client("Krasimira", "Evgenieva", "0898444444");
		Reservation reservation = new Reservation(client,ServiceType.MAN_HAIRCUT,reservationDate);
		reservationBook.addReservation(reservation);
		List<Reservation> allReservations = reservationBook.getAllReservations();
		assertNotNull("There is no reservation", allReservations);
		assertTrue("This reservation doesn't exist", allReservations.contains(reservation));
	}
	
	@Test
	public void testAddReservation_SameTime() throws Exception {

		ReservationBook reservationBook = new ReservationBook();
		Client client = new Client("Krasimira", "Evgenieva", "0898444444");
		String reservationDate="01.02.2013 12:00";
		Reservation reservation = new Reservation(client,ServiceType.MAN_HAIRCUT,reservationDate);
		reservationBook.addReservation(reservation);
		Date overlappingDate = Reservation.SDF.parse(reservationDate);
		long l = overlappingDate.getTime() + 15*60*1000;
		overlappingDate = new Date(l);
		Reservation overlappingReservation = new Reservation(client,ServiceType.MAN_HAIRCUT,overlappingDate );
		try {
			reservationBook.addReservation(overlappingReservation);
		} catch (Exception e) {
			assertEquals("You entered overlapping time", e.getMessage());
		}
		assertFalse("Overlapping Reservation is trying to be added", reservationBook.getAllReservations().contains(overlappingReservation));
	}
	

	@Test
	public void testAddReservation_OverTime() throws ParseException {

		ReservationBook reservationBook = new ReservationBook();
		Client client = new Client("Krasimira", "Evgenieva", "0898444444");
		String overtimeDate="01.02.2013 19:00";
		Date overtime = Reservation.SDF.parse(overtimeDate);
		Reservation overtimeReservation = new Reservation(client,ServiceType.MAN_HAIRCUT,overtime );
		try {
			reservationBook.addReservation(overtimeReservation);
		} catch (Exception e) {
			assertEquals("You entered overtime reservation", e.getMessage());
		}
		assertFalse("Overtime Reservation is trying to be added", reservationBook.getAllReservations().contains(overtimeReservation));
	}
	
	
}
