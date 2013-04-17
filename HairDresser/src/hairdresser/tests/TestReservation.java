package hairdresser.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import hairdresser.main.Client;
import hairdresser.main.Reservation;
import hairdresser.main.ReservationBook;
import hairdresser.main.ServiceType;

import java.util.Date;
import java.util.List;

import org.junit.Test;

public class TestReservation {
	@Test
	public void testEqualsReservation() {
		Date reservationDate = new Date();
		Client client = new Client("Krasimira", "Evgenieva", "0898444444");
		Reservation reservation = new Reservation(client,ServiceType.MAN_HAIRCUT,reservationDate);
		Reservation sameReservation = new Reservation(client,ServiceType.MAN_HAIRCUT,reservationDate);
		assertEquals(reservation, sameReservation);
	}
	
	
}
