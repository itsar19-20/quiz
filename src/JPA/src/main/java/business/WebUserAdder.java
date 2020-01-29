package business;

import javax.persistence.EntityManager;

import model.UtenteWeb;
import utility.JPAUtil;

public class WebUserAdder {
	
	public void addWebUser(String username, String password, String admin) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteWeb u = new UtenteWeb();
		u.setUsername(username);
		u.setPassword(password);
		if(admin.contentEquals("true")) {
			u.setAdmin(true);
		} else if(admin.contentEquals("false")) {
			u.setAdmin(false);
		}
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

}
