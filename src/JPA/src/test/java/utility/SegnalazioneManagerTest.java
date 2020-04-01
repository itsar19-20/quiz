package utility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Test;
import business.SegnalazioniManager;
import business.exeception.NotFindInDbException;
import model.Commento;
import model.Segnalazione;
import model.Utente;
import model.UtenteWeb;
import model.tipiSegn.SegnGenerica;
import model.tipiSegn.SegnSpoiler;

public class SegnalazioneManagerTest {
	private final  String AUTORE= "oliz";
	private final String RISOLUTORE="phobos-1995";
    private final int  IDSEGN=2;  

	//SENGLAZIONE SPOILER
	private final int IDCOMMENTO= 2;
    
	// RISOLVI SPOILER 
     private final  int IDSEGNSPOILER=71;
	 private final  Boolean ISSPOILER= false;
     
	 //SEGNALAZIONE GENERICA
	private final String DESCRIZIONE ="app pallosa";
	
	//CONSEGNA GENERICA
	private final int IDSEGNCONSEGNA=2;

	//CONTROLLI
	private final Boolean PRENDIINCONSEGNA=true;
	private final Boolean DELATESPOILER=true;
	private final Boolean DELATEGENERICA=true;
    private final Boolean RISOLVI= false; 
    private final Boolean RISOLVI_SPOILER =false;
	//Utility

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	SegnalazioniManager sm = new SegnalazioniManager();

	int  idSpoiler;
	int  idGenerica;

	@Test
	public void test()  {

/*		//CREA SPOILER
		idSpoiler=sm.addSegnalazioneSpoiler(AUTORE, IDCOMMENTO);
		if(idSpoiler==0) {
			System.out.println("non è stata aggiunta la segnalazione Spoiler");
		}; 

		//CREA GENERICA
		idGenerica=sm.addSegnalazioneGenerica(AUTORE, DESCRIZIONE);
		if(idGenerica==0) {
			System.out.println("non è stata aggiunta la segnalazione Generica");
		};*/ 
 		
		//TROVA SEGNALZZIONI 
         List<Segnalazione> listSegn =sm.trovaSegnalazioni();
         for (Segnalazione segn : listSegn) {
			System.out.print(segn.toString());
		}

	   //RISOLVI SEGNALAZIONE
	  if(RISOLVI) {
         sm.risolviSegnalazione(IDSEGN, RISOLUTORE);
	  }
	
	
	  if (RISOLVI_SPOILER) {
		  sm.risolviSpoiler(IDSEGNSPOILER, RISOLUTORE, ISSPOILER);
	  } 
	
	
	//PRENDI IN CONSEGNA
	  
	  if (PRENDIINCONSEGNA) {
		//SegnGenerica segn =em.find(SegnGenerica.class, 2);  	
	   System.out.print("la nostra sengalazione"/*+segn.toString()*/);
		
	  }
	  
	}
	
	
	@After
	public void delate() {
		if (DELATESPOILER) {

			if (idSpoiler != 0) {
				SegnSpoiler ss = em.find(SegnSpoiler.class,idSpoiler );
				em.getTransaction().begin();
				em.remove(ss);
				em.getTransaction().commit();
			}
		}

		if ( DELATEGENERICA) {

			if(idGenerica!=0) {
				SegnGenerica sg = em.find(SegnGenerica.class, idGenerica);
				em.getTransaction().begin();
				em.remove(sg);
				em.getTransaction().commit();
			}


		}
	 
		 
	}			
				
				
}
