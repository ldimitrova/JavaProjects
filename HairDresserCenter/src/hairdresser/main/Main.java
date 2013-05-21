package hairdresser.main;

import hairdresser.data.Client;
import hairdresser.data.Reservation;
import hairdresser.exception.OverlappingTimeException;
import hairdresser.exception.OvertimeException;

import java.util.Scanner;

	
public class Main {
	private static ReservationBook reservationBook = new ReservationBook();
	
	public static void MainMenu() throws Exception {
		while(true) {
			System.out.println("Please Make a selection:"); 
			System.out.println("[1] Add a new reservation"); 
			System.out.println("[2] Delete a reservation"); 
			System.out.println("[3] List all reservations"); 
			System.out.println("[4] List all reservations for the current Date"); 
			System.out.println("[5] Exit"); 
	     
			System.out.println("Selection: "); 

			Scanner scanner = new Scanner (System.in);
			while (!scanner.hasNextInt()) {
				System.out.println("That's not a number!");
				scanner.nextLine();
			}
			
			int selection=Integer.parseInt(scanner.nextLine()); 
			Reservation reservation = new Reservation();
		
			switch (selection){
	        	case 1:	System.out.println("Insert client's first name:");
	        			String firstname = scanner.nextLine();
	        		
	        			System.out.println("Insert client's last name:");
	        			String lastname = scanner.nextLine();
	        		
	        			System.out.println("Insert client's phonenumber:");
	        			String phoneNumber = scanner.nextLine();
	        
	        			Client client = new Client(firstname, lastname, phoneNumber);
	        			reservation.setClient(client);
	        		
	        			System.out.println("Insert client's reservation date in the format dd.mm.yyyy HH:mm:");
	        			String reservationDate = scanner.nextLine();
	        			reservation.setReservationDate(Reservation.SDF.parse(reservationDate));
	        		
	        			System.out.println("Insert service code: MAN_HAIRCUT - 1/ WOMAN_HAIRCUT - 2");
	        			int serviceType = scanner.nextInt();
	        			reservation.setService(serviceType);
	        			
	        			try{
	        				reservationBook.addReservation(reservation);
	        			} catch (OverlappingTimeException ex) {
	        				System.out.println(ex.getMessage() + "\n");
	        			} catch(OvertimeException ex) {
	        				System.out.println(ex.getMessage() + "\n");
	        			}
	        			break;
	          
	        	case 2:	System.out.println("Insert reservation time for deletion in the format dd.mm.yyyy HH:mm");
	        			String reservationDateForDelete = scanner.nextLine();
	        			reservationBook.deleteReservation(Reservation.SDF.parse(reservationDateForDelete));
	             		break;
	     
	        	case 3: reservationBook.listAllReservation();
	        			break;
	        			
	        	case 4: reservationBook.listCurrentDayReservations();
    					break;
    					
	        	case 5: System.out.println("Exit Successful");
            			System.exit(0); 
            			
	        	default:System.out.println("Please enter a valid selection.");
	        	
			};
		}
	  
	}
	public static void main(String[] args) throws Exception {
		MainMenu();
	}
}

