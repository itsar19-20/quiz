package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import model.Challenge;
import model.Utente;
import utility.JPAUtil;

//checked

public class ChallengeAdder {
	
	public boolean addChallenge(String titolo, String descrizione, Utente creatore, int punteggio, String flag) {
		boolean _return = false;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Challenge check = em.find(Challenge.class, titolo);
		if(check==null) {
			check = new Challenge();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			check.setTitolo(titolo);
			check.setDescrizione(descrizione);
			check.setCreatore(creatore);
			check.setPunteggio(punteggio);
			check.setData(dtf.format(now));
			check.setFlag(flag);
			
			em.getTransaction().begin();
			em.persist(check);
			em.getTransaction().commit();
			
			_return = true;
		}
		em.close();
		return _return;
	}
	
}
