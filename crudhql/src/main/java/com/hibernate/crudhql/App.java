package com.hibernate.crudhql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		// Creating first object
		User t = new User();
		t.setId(101);
		t.setF_Name("priyanka");
		t.setEmail("xyz@gmail.com");
		t.setL_Name("chopra");

		// Creating second object
		User t1 = new User();
		t1.setId(102);
		t1.setF_Name("pariniti");
		t1.setEmail("pari@gmail.com");
		t1.setL_Name("chopra");

		// Creating third object
		User t2 = new User();
		t2.setId(103);
		t2.setF_Name("amitabh");
		t2.setEmail("bachpan@gmail.com");
		t2.setL_Name("bachpan");

		// Creating fourth object
		User t3 = new User();
		t3.setId(104);
		t3.setF_Name("salmon");
		t3.setEmail("bhoi@gmail.com");
		t3.setL_Name("bhoi");

		Transaction tx = session.beginTransaction();
		// Insert operation
		session.save(t);
		session.save(t1);
		session.save(t2);
		session.save(t3);

		// fetching data/Read data
		String query = "from User";
		Query q = session.createQuery(query);
		List<User> list1 = q.getResultList();
		for (User User : list1) {
			System.out.println("Fist name: " + User.getF_Name() + " Last name: " + User.getL_Name() + " User Id: "
					+ User.getId() + " User's Email: " + User.getEmail());
		}
		// Update Operation
		Query q1 = session.createQuery("update  User set f_Name=:f where id=:i");
		q1.setParameter("f", "priti");
		q1.setParameter("i", 101);
		int count = q1.executeUpdate();// the number of entities updated or deleted
		System.out.println(count + "Updated");

		// Delete Operation
		Query q2 = session.createQuery("delete from  User  t where t.id=:id");
		q2.setParameter("id", 103);
		int count1 = q2.executeUpdate();// the number of entities updated or deleted
		System.out.println("Deleted");
		System.out.println(count);
		tx.commit();
		session.close();
		factory.close();
	}
}
