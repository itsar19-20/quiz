package business;

import javax.persistence.EntityManager;

import model.UtenteWeb;
import utility.JPAUtil;

public class WebUserEdit {
	
	public void edit(String username, String password, String admin) {
		
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteWeb u = em.find(UtenteWeb.class, username);
		em.getTransaction().begin();
		u.setPassword(password);
		if(admin.contentEquals("true")) {
			u.setAdmin(true);
		} else if(admin.contentEquals("false")) {
			u.setAdmin(false);
		}
		em.getTransaction().commit();
		
	}

}
