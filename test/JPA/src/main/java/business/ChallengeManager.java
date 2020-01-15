package business;

import javax.persistence.EntityManager;

import model.Challenge;
import utility.JPAUtil;

public class ChallengeManager {
	
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	
	public void challengeRemover(String titolo) {
		Challenge challenge = challengeSearch(titolo);
		if (challenge != null) {
			em.getTransaction().begin();
			em.remove(challenge);
			em.getTransaction().commit();
			em.close();
		}
	}
	
	public boolean challengeModifier(String titolo, String nuovotitolo, String descrizione, int punteggio, String flag) {
		boolean _return = false;
		boolean databaseCheck = false;
		Challenge challenge = challengeSearch(titolo);
		if(challenge != null) {
			if(!titolo.contentEquals(nuovotitolo)) {
				titolo = nuovotitolo;
				Challenge check = em.find(Challenge.class, titolo);
				if(check != null) {
					_return = false;
				} else {
					databaseCheck = true;
				}
			} else {
				databaseCheck = true;
			}
			if(databaseCheck) {
				em.getTransaction().begin();
				challenge.setTitolo(titolo);
				challenge.setDescrizione(descrizione);
				challenge.setPunteggio(punteggio);
				challenge.setFlag(flag);
				em.getTransaction().commit();
				_return = true;
			}
		}
		em.close();
		return _return;
	}
	
	private Challenge challengeSearch(String titolo) {
		Challenge _return = em.find(Challenge.class, titolo);
		return _return;
	}

}
