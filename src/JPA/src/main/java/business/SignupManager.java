package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

//checked

public class SignupManager {
	
	public boolean signup(String username, String password, String email, String nazionalita) {
		
		boolean _return = false;

		EntityManager em =  JPAUtil.getInstance().getEmf().createEntityManager();
		
		Utente check = em.find(Utente.class, username);   
		
		if(check == null) {
			check = new Utente();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			check.setUsername(username);
			check.setPassword(password);
			check.setEmail(email);
			check.setDataiscrizione(dtf.format(now));
				
			em.getTransaction().begin();
			em.persist(check);
			em.getTransaction().commit();
				
			_return = true;
		} 
		em.close();
		return _return;
	}
}
