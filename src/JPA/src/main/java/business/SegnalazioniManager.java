package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import model.Segnalazione;
import model.Utente;
import model.UtenteWeb;
import utility.JPAUtil;
import business.exeception.*;
public class SegnalazioniManager {


	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();


	SegnalazioniManager(){

	};


	public List<Segnalazione> trovaSegnalazioni(boolean stato) {
		List<Segnalazione> Segnalazioni = em.createQuery("SELECT s FROM Segnalazione s WHERE s.risolta = :risolta", Segnalazione.class)
	    .setParameter("risolta", stato)
	   .getResultList()
	    ;
		
		return Segnalazioni;		
	}
	
	
	public void risolviSegnalazione(Segnalazione segn , String   userNameRisolutore) throws NotFindInDbException{
	try {
		if (em.find(Segnalazione.class, segn.getId())== null ) {
			throw new  NotFindInDbException("Segnalazione", " data:"+segn.getData()+" autore: "+segn.getAutore().getUsername()+" motivazione:"+ segn.getMotivazione());
		} 
		
		if ((em.find(UtenteWeb.class, userNameRisolutore))== null ){
			throw new  NotFindInDbException("UtenteWeb", userNameRisolutore);			
		}
		UtenteWeb risolutore =(em.find(UtenteWeb.class, userNameRisolutore));
	    
		
		
	    em.getTransaction().begin();
	    segn.setRisolutore(risolutore);
	    em.getTransaction().commit();
	
	}	
	
	catch(NotFindInDbException ex) {
		System.out.print(ex.toString());
	}
	
	
	
	
	}
	
	
	
	public void addSegnalazione(String autore, String motivazione  ) throws NotFindInDbException , BasicException  {

		try {

			Utente creatore = em.find(Utente.class, autore);

			if (creatore == null) {
				throw new  NotFindInDbException("Utente", autore);   			    
			}	

			if (motivazione== null) {
				throw new BasicException("la motivazione è  nulla", motivazione);
			}

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			Segnalazione segn = new Segnalazione();
			segn.setData(dtf.format(now));
			segn.setAutore(creatore);
			segn.setMotivazione(motivazione);
			
			

			em.getTransaction().begin();
			em.persist(segn);
			em.getTransaction().commit();
		}

		catch(NotFindInDbException ndbex) {
			System.out.print(ndbex.toString());
		}	

		catch(BasicException bex) {
		   System.out.print(bex.toString());
		}	




	}


};  






