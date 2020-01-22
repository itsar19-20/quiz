package business;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class TokenCheck {
	
	public boolean check (String token) {
		boolean _return = false;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = (Utente) em.createQuery("SELECT u FROM Utente u WHERE token = :token", Utente.class)
				.setParameter("token", token)
				.getSingleResult();
		if(u != null) {
			_return = true;
		}
		return _return;
	}
	
}
