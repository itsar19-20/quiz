package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.Utente;
import model.UtenteWeb;
import utility.JPAUtil;

public class UtentiOnlineManager {
UtenteWeb uw=new UtenteWeb();
public List<UtenteWeb> selezionaUtenti() {
	EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
	List<UtenteWeb> list=  em.createQuery("SELECT u FROM UtenteWeb u ", UtenteWeb.class).getResultList();
	Integer num=list.size();	
//if (uw!=null && uw.isAttivo()) {
		
	
	//}
	return em.createQuery("SELECT u  FROM UtenteWeb u", UtenteWeb.class).getResultList();
	}
}
