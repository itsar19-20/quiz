package business;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.UtenteWeb;
import utility.JPAUtil;


public class AuthenticationManagerWeb {
	private static Logger log=LoggerFactory.getLogger(AuthenticationManagerWeb.class);
	
	public UtenteWeb login(String username, String password) {
		UtenteWeb _return = null;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		_return = em.find(UtenteWeb.class, username);
		log.debug("perfect");
		if (_return == null)
			return null;
		if(password.contentEquals(_return.getPassword())) {
			return _return;
		}
		return null;
	}
}
