package business;

import java.security.SecureRandom;
import java.util.List;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class TokenGen {
	
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	
	public String emailCheck (String email) {
		String _return = null;
		
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
		boolean check = false;
		String token = null;
		while(check==false) {
			SecureRandom random = new SecureRandom();
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			token = bytes.toString();
			List<Utente> uList = em.createQuery("SELECT u FROM Utente u WHERE token = :token", Utente.class)
					.setParameter("token", token)
					.getResultList();
			if (uList.get(0)==null) {
				check = true;
			}
		}
		return token;
	}

}
