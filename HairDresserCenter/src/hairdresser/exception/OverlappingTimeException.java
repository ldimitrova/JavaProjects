package hairdresser.exception;

import java.util.Date;

public class OverlappingTimeException extends Exception{
	
	public OverlappingTimeException(Date reservationDate) {
		super("Date and time you entered for a reservation " + reservationDate + " is overlapped with other reservation!");
    }
}
