package Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProvaMain {

	public static void main(String[] args) {
     EntityManagerFactory mf =  Persistence.createEntityManagerFactory("JPA");
     EntityManager em = mf.createEntityManager();
     
	}

}
