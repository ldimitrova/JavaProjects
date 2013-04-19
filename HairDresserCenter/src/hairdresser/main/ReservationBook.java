package hairdresser.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationBook {

	private List<Reservation> reservations =  new ArrayList();

	public void addReservation(Reservation reservation) throws Exception {
		long newReservationTime = reservation.getReservationDate().getTime();
		int newReservationHours = reservation.getReservationDate().getHours();
		for (Reservation currentReservation : reservations) {
			long currentReservationTime = currentReservation.getReservationDate().getTime();
			int currentServiceDuration = currentReservation.getService().getDuration()*60*1000;
			if((newReservationTime > currentReservationTime) 
					&& (newReservationTime < currentReservationTime + currentServiceDuration)) {
				throw new Exception("You entered overlapping time");
			}
			
		}
		if(newReservationHours < 9 || newReservationHours > 18) {
			throw new Exception("You entered overtime reservation");
		}
		reservations.add(reservation);
	}

	public List<Reservation> getAllReservations() { 
		return reservations;
		
		
	}


	public void deleteReservation(Date reservationDate) {
		Reservation reservationForDelete = null;
		for (Reservation currentReservation : reservations) {
			Date currentReservationDate = currentReservation.getReservationDate();
			if (currentReservationDate.compareTo(reservationDate) == 0) {
				reservationForDelete = currentReservation;
			} 
				
		}
		reservations.remove(reservationForDelete);
	}
	
}
