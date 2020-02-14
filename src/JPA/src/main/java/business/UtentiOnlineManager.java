package business;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Utente;
import model.UtenteWeb;
import utility.JPAUtil;
import javax.persistence.TypedQuery;

public class UtentiOnlineManager {

UtenteWeb uw=new UtenteWeb();
public List<UtenteWeb> selezionaUtenti() {
	EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
	if (uw.isAttivo()) {

		List<UtenteWeb> list=  em.createQuery("SELECT uw FROM UtenteWeb uw WHERE uw.attivo=true", UtenteWeb.class).getResultList();
		Integer num=list.size();	
		
		
	}

	
	return em.createQuery("SELECT uw FROM UtenteWeb uw  WHERE uw.attivo=true", UtenteWeb.class).getResultList();
	}
}
