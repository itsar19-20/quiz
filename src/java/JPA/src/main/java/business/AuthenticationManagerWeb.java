package business;

import javax.persistence.EntityManager;

import model.UtenteWeb;
import utility.JPAUtil;


public class AuthenticationManagerWeb {
	
	public UtenteWeb login(String username, String password) {
		if (username==null) {
			username="";
		}
		UtenteWeb _return = null;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		_return = em.find(UtenteWeb.class, username);
		if(_return != null) {
			if(!password.contentEquals(_return.getPassword())) {
				_return = null;
			}
		}
		em.close();
		return _return;
	}
}
