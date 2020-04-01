package business;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

public class UtenteAttivoManager {
	EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
	public void utenteAttivo(String username) {
		em.getTransaction().begin();
		Utente u=em.find(Utente.class, username);
		u.setAttivo(true);
		em.getTransaction().commit();
		System.out.println("verificatobusiness " + u);
	}
    

}