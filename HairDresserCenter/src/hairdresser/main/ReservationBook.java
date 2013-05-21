package hairdresser.main;

import hairdresser.data.Reservation;
import hairdresser.exception.OverlappingTimeException;
import hairdresser.exception.OvertimeException;
import hairdresser.load.ReservationDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationBook {

	private List<Reservation> reservations = new ArrayList();
	private ReservationDAO resDAO = new ReservationDAO();

	public void addReservation(Reservation reservation) throws Exception {

		long newReservationTime = reservation.getReservationDate().getTime();
		int newReservationHours = reservation.getReservationDate().getHours();

		for (Reservation currentReservation : reservations) {
			long currentReservationTime = currentReservation
					.getReservationDate().getTime();
			int currentServiceDuration = currentReservation.getService()
					.getServiceDuration() * 60 * 1000;
			if ((newReservationTime > currentReservationTime)
					&& (newReservationTime < currentReservationTime
							+ currentServiceDuration)) {
				throw new OverlappingTimeException(
						reservation.getReservationDate());
			}

		}
		if (newReservationHours < 9 || newReservationHours > 18) {
			throw new OvertimeException(
					"You entered a reservation over working hours ( 09.00h-18.00h)..");
		}
		// reservations.add(reservation);

		resDAO.saveReservation(reservation);
	}

	public List<Reservation> getAllReservations() {
		return reservations;

	}

	public void deleteReservation(Date reservationDate) {
		Reservation reservationForDelete = null;

		for (Reservation currentReservation : reservations) {
			Date currentReservationDate = currentReservation
					.getReservationDate();

			if (currentReservationDate.compareTo(reservationDate) == 0) {
				reservationForDelete = currentReservation;
			}

		}
		// reservations.remove(reservationForDelete);
		resDAO.deleteReservation(reservationForDelete.getReservationDate());
	}

	public void listAllReservation() {

		reservations = resDAO.listReservations();
		for (Reservation currentReservation : reservations) {
			System.out.println(currentReservation.toString());
		}
	}

	public void listCurrentDayReservations() {
		
		reservations = resDAO.listReservations();
		for (Reservation currentReservation : reservations) {

			Date reservationDate = currentReservation.getReservationDate();
			if (compareDate(reservationDate)) {
				System.out.println(currentReservation);
			}
		}
	}

	private boolean compareDate(Date resDate) {
		// Calendar currDate = new GregorianCalendar();
		if (Calendar.DAY_OF_YEAR == resDate.getDay()) {
			return true;
		}
		System.out.println();
		return false;
	}
}
