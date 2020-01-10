package business;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class SignupManager {
	
	public boolean signup(String username, String password, String email, int nazionalita) {
		
		boolean _return = false;

		EntityManager em =  JPAUtil.getInstance().getEmf().createEntityManager();
		
		Utente check = em.find(Utente.class, username);
		
		if(check != null) {
			if(!password.contentEquals(check.getPassword())) {
				Utente u = new Utente();
				u.setUsername(username);
				u.setPassword(password);
				u.setEmail(email);
				
				em.getTransaction().begin();
				em.persist(u);
				em.getTransaction().commit();
				
				_return = true;
				em.close();
			}
			return _return;
		} else {
			em.close();
			return _return;
		}
	}
}
