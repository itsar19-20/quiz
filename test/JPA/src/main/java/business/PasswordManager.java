package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class PasswordManager {
	
	public boolean passwordModifier(String token, String nuovaPass) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		List<Utente> uList = em.createQuery("SELECT u FROM Utente u WHERE token = :token", Utente.class)
				.setParameter("token", token)
				.getResultList();
		if (uList.size() == 1) {
			if(uList.get(0)==null) {
				return false;
			}
			em.getTransaction().begin();
			uList.get(0).setToken(null);
			uList.get(0).setPassword(nuovaPass);
			em.getTransaction().commit();
		} else {
			return false;
		}
		return true;
	}
	
}
