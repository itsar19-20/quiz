package business;

import java.security.SecureRandom;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class TokenGen {
	
	public String emailCheck (String email) {
		String _return = null;
		
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		
		Utente u = em.find(Utente.class, email);
		
		if (u != null) {
			_return = generator();
			em.getTransaction().begin();
			u.setToken(_return);
			em.getTransaction().commit();
			em.close();
		}
		
		return _return;
	}
	
	public String generator() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String token = bytes.toString();
		return token;
	}

}
