package business;

import java.util.List;


import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class UtentiOnlineManager {

Utente uw=new Utente();
	public List<Utente> selezionaUtenti() {
		EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
		if (uw.isAttivo()) {
			List<Utente> list=  em.createQuery("SELECT uw FROM Utente uw WHERE uw.attivo=true", Utente.class).getResultList();
			Integer num=list.size();	
		}
	
		return em.createQuery("SELECT uw FROM Utente uw  WHERE uw.attivo=true", Utente.class).getResultList();
	}
}
