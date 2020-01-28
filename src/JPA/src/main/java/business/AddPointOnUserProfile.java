package business;
import model.Utente;
import model.Challenge;
import utility.JPAUtil;

import javax.persistence.EntityManager;

///NEL CASO SI STABILISCA CHE  NEL DATABSE DEVE ESSERCI UN TABELLA 
///CHE DESCRIVA QUALI UTENTI HANNO COMPLETATO QUALI CHALLENGER SARÀ NECESSARIO
///INSERIE NEL IF LA CONDIZIONE CHE QUEL UTENTE NON ABBIA GIÀ COMPLETATO QUELLA
///CHALLENGER

public class AddPointOnUserProfile {
	
	public boolean AddPoint(String flag , String user , String challenge) {
		boolean _return =false ;
		
		//creo l'entity manager 
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		Challenge check = em.find(Challenge.class, challenge);
		
	       // imposto come condizione  perche si  effettui la somma del punteggio
		   //che  la challenger deve esistere e che  il flag sengato sia giusto
		if(challenge!= null  &&  flag.contentEquals(check.getFlag())) {
		   Utente utente = em.find(Utente.class, user); 
		  
		    
		    em.getTransaction().begin();
		    utente.setPunteggio( utente.getPunteggio()+ check.getPunteggio());
			em.getTransaction().commit();
			
			_return = true;


		}
		em.close();
		return _return ;
		
	};
	
	
	
}
