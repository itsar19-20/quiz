package business;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class GetUser {
	
	public static Utente getUser(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente _return = em.find(Utente.class, username);
		return _return;
	}
	
}
