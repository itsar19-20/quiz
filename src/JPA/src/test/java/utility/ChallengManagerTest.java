package utility;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import business.ChallengeManager;
import model.Challenge;
import model.Utente;

import org.junit.After;
import org.junit.Test;

public class ChallengManagerTest {
	private final String CREATORE ="tizio";
	private final String TITOLO ="pest";
	private final int RATING =4;
	private final int PUNTEGGIO=12;
	private final String FLAG = "soluione@@";
	private final String DESCRIZIONE ="qualcosa";
	private final Boolean AUTORIZZAZIONE = false;
	
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	@Test
	public void Add() {
	Challenge chal = new Challenge();
	ChallengeManager chalMan = new ChallengeManager();
	
	chalMan.addChallenge(TITOLO, DESCRIZIONE,  CREATORE, PUNTEGGIO, FLAG);
	System.out.print("l'add ha funzioanto");
	
	String punteggioString =""+PUNTEGGIO;
	
	chalMan.challengeModifier(TITOLO, null ,"9"+punteggioString, "T"+FLAG);
	System.out.print("ha funzionato il challengeModifier");
	
	
	}

	
	
 @After
 public void delate() {
	 if(AUTORIZZAZIONE){
		 Challenge chal = em.find(Challenge.class, "prova");
		 em.getTransaction().begin();
		 em.remove(chal);
		 em.getTransaction().commit();
				 
				 
	 } 
	 
 }



}
