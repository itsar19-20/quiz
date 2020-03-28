package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import business.exeception.NotFindInDbException;
import model.Challenge;
import model.Utente;
import utility.JPAUtil;

//checked

public class ChallengeManager {

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

///////////////////////////////////////////////////////////////////////////////////
////	FIND ALL
///////////////////////////////////////////////////////////////////////////////////	
public  List<Challenge>  findAllChallenger(){
	return em.createQuery("Select c from Challenge c ",Challenge.class).getResultList();
	
}	

	
////////////////////////////////////////////////////////////////////////////////////	
////	CREATE REMUVE
////////////////////////////////////////////////////////////////////////////////////	
	
	
	public boolean addChallenge(String titolo, String descrizione, String categoria, String creatore, String flag) {
		
		Utente u = em.find(Utente.class,creatore);

		try {if (u == null  ) {

			throw new NotFindInDbException("Utente", creatore);}

		Challenge security = em.find(Challenge.class, titolo);
		if(security == null) {
			Challenge check = new Challenge();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			check.setData(dtf.format(now));  
			check.setTitolo(titolo);
			check.setDescrizione(descrizione);
			check.setCategoria(categoria);
			check.setCreatore(u);
			int p;
			switch(categoria) {
				case "Coding":
					p = 100;
					break;
				case "Encription":
					p = 200;
					break;
				case "Security":
					p = 300;
					break;
				default:
					p = 0;
					break;
				
			}
			check.setPunteggio(p);
			
			check.setFlag(flag);
	
			em.getTransaction().begin();
			em.persist(check);
			em.getTransaction().commit();
			
			return true;
		} else {
			return false;
		}

		}catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		};
		return false;
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



////////////////////////////////////////////////////////////////////////////////////
///CHALLENGR MOD
////////////////////////////////////////////////////////////////////////////////////


	public void challengeModifier(String titolo, String descrizione, String punteggio, String flag) {
		try 
		
		{    Challenge chal =em.find(Challenge.class, titolo);
			if (chal==null) {
	    	throw new NotFindInDbException("Challenge", titolo);};
 
	    	if(descrizione== null){
               descrizione =chal.getDescrizione();
            } 
            
            int punteggioChange;
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
