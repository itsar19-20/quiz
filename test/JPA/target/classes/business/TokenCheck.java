package business;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class TokenCheck {
	
	public boolean check (String token) {
		boolean _return = false;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = em.find(Utente.class, token);
		if(u != null) {
			_return = true;
		}
		return _return;
	}
	
}
