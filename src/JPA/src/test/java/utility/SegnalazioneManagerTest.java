package utility;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import business.SegnalazioniManager;
import business.exeception.NotFindInDbException;
import model.Commento;
import model.Utente;
import model.Segnalazione;




public class SegnalazioneManagerTest {
private String AUTORE= "tizio";
private String RISOLUTORE ="tEst";

// SEGNALAZIONE SPOILER

private int ID_COMMENTO= 1;

//SEGNALAZIONE GENRICA

private String DESCRIZIONE ="non mi fa accedere da un altro computer";

// COMANDI TEST

private Boolean ADD_SPOILER = false;
private Boolean ADD_GENRIC= false;
private Boolean RISOLVI = false; 
private Boolean LIST = true; 
	

EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
SegnalazioniManager sm = new SegnalazioniManager();
	
	@Test
	public void test() throws NotFindInDbException {
		if (ADD_SPOILER) {
			sm.addSegnalazioneSpoiler(em.find(Utente.class, AUTORE), em.find(Commento.class, ID_COMMENTO));
		}
		
	
	if (ADD_GENRIC) {	
		sm.addSegnalazioneGenerica(em.find(Utente.class, AUTORE), DESCRIZIONE);		
		
	}

	if(RISOLVI) {
		sm.risolviSegnalazione(5, RISOLUTORE);
				};
	

	if (LIST) {
		List<Segnalazione> sengS =sm.trovaSegnalazioni();
		
		
		
		 
		 
		 
		 
	}			
				
				
}
}