package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import business.exeception.NotFindInDbException;
import model.Challenge;
import model.Utente;
import utility.JPAUtil;

//checked

public class ChallengeManager {

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	public void addChallenge(String titolo, String descrizione, String  creatore, int punteggio, String flag) {

		try {if (em.find(Utente.class,creatore)== null  ) {

			throw new NotFindInDbException("Utente", creatore); }

		Challenge check = new Challenge();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		check.setData(dtf.format(now));  
		check.setTitolo(titolo);
		check.setDescrizione(descrizione);
		check.setCreatore(em.find(Utente.class,creatore));
		check.setPunteggio(punteggio);
		
		check.setFlag(flag);

		em.getTransaction().begin();
		em.persist(check);
		em.getTransaction().commit();

		}catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		};


	}

	public void challengeRemover(String titolo) {
		try { if (em.find(Challenge.class, titolo)==null) {
			throw new NotFindInDbException("Challenge", titolo);
		};
		Challenge challenge= new Challenge();	 
		em.getTransaction().begin();
		em.remove(challenge);
		em.getTransaction().commit();

		}catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}
	}








	public void challengeModifier(String titolo, String descrizione, String punteggio, String flag) {
		try {if (em.find(Challenge.class, titolo)==null) {
	    	throw new NotFindInDbException("Challenge", titolo);};
            
            
            Challenge chal =em.find(Challenge.class, titolo);
            int punteggioChange;
            
            
            if(descrizione== null){
               descrizione =chal.getDescrizione();
            } 
            
            if (punteggio == null){
               punteggioChange =chal.getPunteggio();
            }else { 
            	 punteggioChange=Integer.parseInt(punteggio);
            }
            
            if(flag== null) {
            	flag=chal.getFlag();
            }
            
            em.getTransaction().begin();
    		chal.setDescrizione(descrizione);
    		chal.setPunteggio(punteggioChange);
    		chal.setFlag(flag);
    		em.getTransaction().commit();
            
            
		}catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}            	
	}

}
