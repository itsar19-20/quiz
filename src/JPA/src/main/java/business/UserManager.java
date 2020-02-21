package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import model.Challenge;
import model.Utente;
import utility.JPAUtil;

public class UserManager {

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	public void addUser(String username, String email,String password) {

		Utente user = new Utente();
		Challenge check = new Challenge();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		
		user.setDataiscrizione(dtf.format(now));  
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		em.getTransaction().begin();   
		em.persist(user);
		em.getTransaction().commit();
	};



	public Utente getUser(String username) {
		return em.find(Utente.class, username);
	}

	public List<Utente> userSearch() {
		return em.createQuery("SELECT u FROM Utente u", Utente.class).getResultList();
	}

	public void removeUser(String username) {
		Utente delete = em.find(Utente.class, username);
		em.getTransaction().begin();
		em.remove(delete);
		em.getTransaction().commit();
	}

}
