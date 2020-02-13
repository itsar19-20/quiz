package utility;

import javax.persistence.EntityManager;

import org.junit.Test;

import business.SegnalazioniManager;
import business.UserManager;
import business.exeception.BasicException;
import business.exeception.NotFindInDbException;
import model.Utente;

public class EntityManagerTest {

	@Test
	public void test() throws NotFindInDbException, BasicException {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		SegnalazioniManager sm = new SegnalazioniManager();
		UserManager um = new UserManager();
		Utente u = em.find(Utente.class, "panino");
		if (u == null) {
			System.out.print("no utente");
		}
		;
		sm.addSegnalazione(u, "Si");
		System.out.print("funziono forse");

	}

}
