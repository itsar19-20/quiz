package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.UtenteWeb;
import utility.JPAUtil;

public class WebUserSearcher {
	
	public List<UtenteWeb> GetModList() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		List<UtenteWeb> modList = em.createQuery("SELECT u FROM UtenteWeb u", UtenteWeb.class).getResultList();
		return modList;
	}

}
