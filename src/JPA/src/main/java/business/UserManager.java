package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class UserManager {
	
	static EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	
	public static Utente getUser(String username) {
		return em.find(Utente.class, username);
	}
	
	public static List<Utente> userSearch() {
		return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
	}
	
	public static void removeUser(String username) {
		Utente delete = em.find(Utente.class, username);
		em.getTransaction().begin();
		em.remove(delete);
		em.getTransaction().commit();
	}
	
}
