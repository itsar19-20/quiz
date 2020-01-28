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
		
		/*
		 * List<Utente> ls =
		 * em.createQuery("SELECT u FROM Utente u WHERE email = :email", Utente.class)
		 * .setParameter("email", email) .getResultList()
		 */;
		
		Utente u = em.createQuery("SELECT u FROM Utente u WHERE email = :email", Utente.class)
				.setParameter("email", email)
				.getSingleResult();
		
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
		Integer temp = 0;
		while(check==false || temp <= 0) {
			SecureRandom random = new SecureRandom();
			temp = random.nextInt();
			token = temp.toString();
			List<Utente> uList = em.createQuery("SELECT u FROM Utente u WHERE token = :token", Utente.class)
					.setParameter("token", token)
					.getResultList();
			if (uList.isEmpty()) {
				check = true;
			}
		}
		return token;
	}

}
