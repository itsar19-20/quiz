
package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import model.Utente;
import utility.JPAUtil;

//checked

public class SignupManager {
	
	public boolean signup(String username, String password, String email, String nazionalita) {
		EntityManager em =  JPAUtil.getInstance().getEmf().createEntityManager();
		
		Utente check = em.find(Utente.class, username);   
		
		if(check == null) {
			check = new Utente();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			
			check.setUsername(username);
			check.setEmail(email);
			check.setDataiscrizione(dtf.format(now));
	    	check.setPassword(password);
	 	    check.setNazionalita(nazionalita);
	 	    
			em.getTransaction().begin();
			em.persist(check);
			em.getTransaction().commit();
			
				
			return true;
		} 
		em.close();
		return false;
	}
}
