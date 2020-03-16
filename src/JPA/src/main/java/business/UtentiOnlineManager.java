package business;

import java.util.List;


import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class UtentiOnlineManager {

	public List<Utente> selezionaUtenti() {
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();	
		return em.createQuery("SELECT uw FROM Utente uw  WHERE uw.attivo=true", Utente.class).getResultList();
	}
}
