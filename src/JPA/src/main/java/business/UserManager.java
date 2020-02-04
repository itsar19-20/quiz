package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class UserManager {
	
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	
	public Utente getUser(String username) {
		return em.find(Utente.class, username);
	}
	
	public List<Utente> userSearch() {
		return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
	}
	
	public void removeUser(String username) {
		Utente delete = em.find(Utente.class, username);
		em.getTransaction().begin();
		em.remove(delete);
		em.getTransaction().commit();
	}
	
}
