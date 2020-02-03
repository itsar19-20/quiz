package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import model.Challenge;
import model.Utente;
import utility.JPAUtil;

//checked

public class ChallengeManager {
	
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	
	public boolean addChallenge(String titolo, String descrizione, Utente creatore, int punteggio, String flag) {
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
			
			return true;
		}
		em.close();
		return false;
	}
	
	public void challengeRemover(String titolo) {
		Challenge challenge = challengeSearch(titolo);
		if (challenge != null) {
			em.getTransaction().begin();
			em.remove(challenge);
			em.getTransaction().commit();
		}
	}
	
	public boolean challengeModifier(String titolo, String descrizione, int punteggio, String flag) {
		Challenge challenge = challengeSearch(titolo);
		if(challenge != null) {
			em.getTransaction().begin();
			challenge.setDescrizione(descrizione);
			challenge.setPunteggio(punteggio);
			challenge.setFlag(flag);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}
	
	private Challenge challengeSearch(String titolo) {
		return em.find(Challenge.class, titolo);
	}

}
