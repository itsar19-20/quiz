package business;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class UserManager {
	
	static EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	
	public static Utente getUser(String username) {
		Utente _return = em.find(Utente.class, username);;
		return _return;
	}
	
	public static void removeUser(String username) {
		Utente delete = em.find(Utente.class, username);
		em.getTransaction().begin();
		em.remove(delete);
		em.getTransaction().commit();
	}
	
}
