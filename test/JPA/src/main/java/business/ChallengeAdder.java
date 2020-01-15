package business;

import javax.persistence.EntityManager;

import model.Challenge;
import model.Utente;
import utility.JPAUtil;

public class ChallengeAdder {
	
	public boolean addChallenge(String titolo, String descrizione, Utente creatore, int punteggio, String data, String flag) {
		boolean _return = false;
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Challenge check = em.find(Challenge.class, titolo);
		if(check==null) {
			check = new Challenge();
			check.setTitolo(titolo);
			check.setDescrizione(descrizione);
			check.setCreatore(creatore);
			check.setPunteggio(punteggio);
			check.setData(data);
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
