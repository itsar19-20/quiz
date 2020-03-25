package business;

import javax.persistence.EntityManager;

import jdk.internal.jline.internal.Log;
import model.Utente;
import utility.JPAUtil;

public class UtenteAttivoManager {
	UserManager um=new UserManager();
	
	EntityManager em=JPAUtil.getInstance().getEmf().createEntityManager();
	public void utenteAttivo(String username) {
		Utente u=um.getUser(username);
		em.getTransaction().begin();
		u.setAttivo(true);
		em.getTransaction().commit();
		System.out.println("verificatobusiness");
		
		
		
	
		
	}
    

}