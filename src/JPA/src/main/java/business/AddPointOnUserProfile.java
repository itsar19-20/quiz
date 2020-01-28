package business;
import model.Utente;
import model.Challenge;
import utility.JPAUtil;

import javax.persistence.EntityManager;

import business.exception.BasicException;
import business.exception.NotFindInDbException;

///NEL CASO SI STABILISCA CHE  NEL DATABSE DEVE ESSERCI UN TABELLA 
///CHE DESCRIVA QUALI UTENTI HANNO COMPLETATO QUALI CHALLENGER SARÀ NECESSARIO
///INSERIE NEL IF LA CONDIZIONE CHE QUEL UTENTE NON ABBIA GIÀ COMPLETATO QUELLA
///CHALLENGER

public class AddPointOnUserProfile {
	
	public void addPoint(String flag , String user , String challenge) 
			throws NotFindInDbException, BasicException {
	
		try {
		//creo l'entity manager 
		EntityManager fCheck = JPAUtil.getInstance().getEmf().createEntityManager();
		EntityManager fUtente = JPAUtil.getInstance().getEmf().createEntityManager();
		EntityManager wpunteggio = JPAUtil.getInstance().getEmf().createEntityManager();
	       // imposto come condizione  perche si  effettui la somma del punteggio
		   //che  la challenger deve esistere e che  il flag sengato sia giusto
		
		Challenge check = fCheck.find(Challenge.class, challenge);
		if(check == null) {throw new NotFindInDbException ("Challenger",challenge);}
		fCheck.close();
		 
		if (flag.contentEquals(check.getFlag())) {
			 throw new BasicException("il flag non corrisponde",flag);
		 }		
			
		 Utente utente = fUtente.find(Utente.class, user); 
	     if (utente== null) {throw new NotFindInDbException ("Utente",user);
	     }
	     fUtente.close();     
		    
	     
	     wpunteggio.getTransaction().begin();
		 utente.setPunteggio( utente.getPunteggio()+ check.getPunteggio()); 
		 wpunteggio.getTransaction().commit();
		 wpunteggio.close();
		}
		catch(NotFindInDbException dbEx) {
			System.out.println(dbEx.toString());
		}
		catch(BasicException bEx) {
			System.out.println(bEx.toString());
		}
	};
	
	
	
}
