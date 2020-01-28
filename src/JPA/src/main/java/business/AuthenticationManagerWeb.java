package business;

import javax.persistence.EntityManager;

import model.UtenteWeb;
import utility.JPAUtil;


public class AuthenticationManagerWeb {
	
	public UtenteWeb login(String username, String password) {
		UtenteWeb _return = null;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		_return = em.find(UtenteWeb.class, username);
		if (_return == null)
			return null;
		if(password.contentEquals(_return.getPassword())) {
			return _return;
		}
		return null;
	}
}
