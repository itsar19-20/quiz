package utility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;

import org.junit.After;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import business.SegnalazioniManager;
import business.exeception.BasicException;
import business.exeception.NotFindInDbException;
import model.Segnalazione;
import model.Utente;
import model.UtenteWeb;
import model.tipiSegn.SegnGenerica;
import model.tipiSegn.SegnSpoiler;

public class SegnalazioneManagerTest {
	private final  String AUTORE= "oliz";
	private final String RISOLUTORE="phobos-1995";
    private final int  IDSEGN=40;  

	//SENGLAZIONE SPOILER
	private int IDCOMMENTO= 1;


	//SEGNALAZIONE GENERICA
	private String DESCRIZIONE ="body shaming";

	//CONTROLLI

	private Boolean DELATESPOILER=true;
	private Boolean DELATEGENERICA=false;
    private Boolean RISOLVI= true; 
	//Utility

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	SegnalazioniManager sm = new SegnalazioniManager();

	int  idSpoiler;
	int  idGenerica;

	
	

	@Test
	public void test() throws NotFindInDbException, BasicException, JsonProcessingException {

		//CREA SPOILER
		idSpoiler=sm.addSegnalazioneSpoiler(AUTORE, IDCOMMENTO);
		if(idSpoiler==0) {
			System.out.println("non è stata aggiunta la segnalazione Spoiler");
		}; 

		//CREA GENERICA
		idGenerica=sm.addSegnalazioneGenerica(AUTORE, DESCRIZIONE);
		if(idGenerica==0) {
			System.out.println("non è stata aggiunta la segnalazione Generica");
		}; 
 		
		//TROVA SEGNALZZIONI 
         List<Segnalazione> listSegn =sm.trovaSegnalazioni();
         for (Segnalazione segn : listSegn) {
			System.out.print(segn );
		}

	   //RISOLVI SEGNALAZIONE
	  if(RISOLVI) {
         sm.risolviSegnalazione(IDSEGN, RISOLUTORE);
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
