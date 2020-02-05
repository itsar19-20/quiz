package business;
import model.Utente;
import model.Challenge;
import utility.JPAUtil;

import javax.persistence.EntityManager;

import business.exeception.*;

public class AddPointOnUserProfile {
	
	public void addPoint(String username, int punteggio) {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Utente u = em.find(Utente.class, username);
		em.getTransaction().begin();
		u.setPunteggio(punteggio);
		em.getTransaction().commit();
	}
	
	public void addPointWChallenge(String user , String challenge) 
			throws BasicException {
	
		try {
		//creo l'entity manager 
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	       // imposto come condizione  perche si  effettui la somma del punteggio
		   //che  la challenger deve esistere e che  il flag sengato sia giusto
		
		Challenge check = em.find(Challenge.class, challenge);
		if(check == null) {
			throw new NotFindInDbException ("Challenger",challenge);
		}	
			
		 Utente utente = em.find(Utente.class, user); 
	     if (utente== null) {
	    	 throw new NotFindInDbException ("Utente",user);
	     }
	     
	     em.getTransaction().begin();
		 utente.setPunteggio( utente.getPunteggio()+ check.getPunteggio()); 
		 em.getTransaction().commit();
		}
		catch(NotFindInDbException dbEx) {
			System.out.println(dbEx.toString());
		}
	};
	
	
	
}
