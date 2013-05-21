package hairdresser.load;

import hairdresser.data.Reservation;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
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

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		//Integer resId = null;
		try {
			transaction = session.beginTransaction();
			
			session.save(reservation.getClient());
			//resId = (Integer) session.save(reservation);
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
			Query query = session.getNamedQuery("selectDelRes");
			query.setParameter("reservationDate", resDate);
			Reservation reservation = (Reservation) query.list().get(0);
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
			
			//############ with named queries ####################
			// reservations = session.getNamedQuery("selectAll").list();
			// System.out.println(reservations.size());
			
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return reservations;
	}

}
