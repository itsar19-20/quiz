package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.UtenteWeb;
import utility.JPAUtil;

public class WebUserManager {
	
	public void addWebUser(String username, String password, String admin) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteWeb u = new UtenteWeb();
		u.setUsername(username);
		u.setPassword(password);
		
		if(admin == "true") {
			
		}
		if(admin.contentEquals("true")) {
			u.setAdmin(true);
		} else if(admin.contentEquals("false")) {
			u.setAdmin(false);
		}
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}
	
	public List<UtenteWeb> getModList() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		return em.createQuery("SELECT u FROM UtenteWeb u", UtenteWeb.class).getResultList();
	}
	
	public void remove(String username) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteWeb u = em.find(UtenteWeb.class, username);
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}
	
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
