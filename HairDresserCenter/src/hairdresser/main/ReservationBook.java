package hairdresser.main;

import hairdresser.data.Reservation;
import hairdresser.exception.OverlappingTimeException;
import hairdresser.exception.OvertimeException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReservationBook {

	private List<Reservation> reservations =  new ArrayList();

	public void addReservation(Reservation reservation) throws Exception {
		
		long newReservationTime = reservation.getReservationDate().getTime();
		int newReservationHours = reservation.getReservationDate().getHours();
		
		for (Reservation currentReservation : reservations) {
			long currentReservationTime = currentReservation.getReservationDate().getTime();
			int currentServiceDuration = currentReservation.getService().getServiceDuration()*60*1000;
			if((newReservationTime > currentReservationTime) 
					&& (newReservationTime < currentReservationTime + currentServiceDuration)) {
				throw new OverlappingTimeException(reservation.getReservationDate());
			}
			
		}
		if(newReservationHours < 9 || newReservationHours > 18) {
			throw new OvertimeException("You entered a reservation over working hours ( 09.00h-18.00h)..");
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
	
	
	public void listAllReservation() {
		
		Iterator<Reservation> iterator = reservations.iterator(); 
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
			
		}
		
	}	
	
	public void listCurrentDayReservations() {
		Date currentDate = new Date();
		
		Iterator<Reservation> iterator = reservations.iterator(); 
		while (iterator.hasNext()) {
			 Reservation res = (Reservation) iterator.next();
			 
			if(res.getReservationDate().getDate() == currentDate.getDate() && res.getReservationDate().getMonth() == currentDate.getMonth() && res.getReservationDate().getYear() == currentDate.getYear())
				System.out.println(res.toString());
			
		}
		
	}
}
