package business;

import javax.persistence.EntityManager;

import model.Challenge;
import utility.JPAUtil;

//checked

public class ChallengeManager {
	
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	
	public void challengeRemover(String titolo) {
		Challenge challenge = challengeSearch(titolo);
		if (challenge != null) {
			em.getTransaction().begin();
			em.remove(challenge);
			em.getTransaction().commit();
		}
	}
	
	public boolean challengeModifier(String titolo, String descrizione, int punteggio, String flag) {
		boolean _return = false;
		Challenge challenge = challengeSearch(titolo);
		if(challenge != null) {
			em.getTransaction().begin();
			challenge.setDescrizione(descrizione);
			challenge.setPunteggio(punteggio);
			challenge.setFlag(flag);
			em.getTransaction().commit();
			_return = true;
		}
		return _return;
	}
	
	private Challenge challengeSearch(String titolo) {
		Challenge _return = em.find(Challenge.class, titolo);
		return _return;
	}

}
