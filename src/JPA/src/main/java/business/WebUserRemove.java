package business;

import javax.persistence.EntityManager;

import model.UtenteWeb;
import utility.JPAUtil;

public class WebUserRemove {
	
	public void remove(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteWeb u = em.find(UtenteWeb.class, username);
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}

}
