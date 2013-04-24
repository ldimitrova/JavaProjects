package hairdresser.tests;

import static org.junit.Assert.assertEquals;
import hairdresser.data.Client;
import hairdresser.data.ManHaircut;
import hairdresser.data.Reservation;
import hairdresser.data.ServiceType;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ReservationTest {
	
	private Date reservationDate;
	private Client client;
	private ServiceType service;
	private Reservation reservation;
	private Reservation sameReservation;
	
	@Before
	public void setup() {
		reservationDate = new Date();
		client = new Client("Krasimira", "Evgenieva", "0898444444");
		service = new ManHaircut();
	}
	
	@Test
	public void testEqualsReservation() {

		reservation = new Reservation(client,service,reservationDate);
		sameReservation = new Reservation(client,service,reservationDate);
		assertEquals(reservation, sameReservation);
	}
	
	
}
 