package hairdresser.load;

import hairdresser.data.Reservation;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class ReservationDAO {

	private SessionFactory sessionFactory;
	public ReservationDAO() {
		Configuration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
	
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public void saveReservation(Reservation reservation) {

//		Session session1 = sessionFactory.openSession();
//		Transaction transaction1 = null;
//		Long reservId = null;
//		try {
//			transaction1 = session1.beginTransaction();
//			reservId = (Long)session1.save(reservation.getClient());
//			transaction1.commit();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		} finally {
//			if (session1 != null) {
//				session1.close();
//			}
//		}
//		
//		reservation.setClientId(reservation.getClient().getClientId());
		
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		// Integer reservId = null;
		try {
			transaction = session.beginTransaction();
			// reservId = (Integer) session.save(reservation);
			session.save(reservation);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void deleteReservation(Date resDate) {

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			Reservation reservation = (Reservation) session.get(
					Reservation.class, resDate);
			session.delete(reservation);
			transaction.commit();

		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Reservation> listReservations() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Reservation> reservations = null;
		try {
			transaction = session.beginTransaction();
			reservations = session.createQuery("from Reservation").list();
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return reservations;
	}

}
