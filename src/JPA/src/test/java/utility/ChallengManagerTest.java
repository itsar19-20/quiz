package utility;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import business.ChallengeManager;
import model.Challenge;
import model.Utente;

import org.junit.After;
import org.junit.Test;

public class ChallengManagerTest {
	private final String CREATORE ="MAtteo";
	private final String TITOLO ="pest";
	private final int RATING =4;
	private final String CATEGORIA="Coding";
	private final String FLAG = "soluione@@";
	private final String DESCRIZIONE ="qualcosa";
	
	private final Boolean CANCELLA = false;
	private final Boolean ADD = true;
	private final Boolean MOD = false;
	
	
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
	ChallengeManager chalMan = new ChallengeManager();
	
	
	@Test
	public void Add() {
	
	if(ADD) {	
	Challenge chal = new Challenge();	
	chalMan.addChallenge(TITOLO, DESCRIZIONE, CATEGORIA,  CREATORE, FLAG);
	System.out.print("l'add ha funzioanto");
	}
	
	if(MOD)
	chalMan.challengeModifier(TITOLO, null ,"666", "T"+FLAG);
	System.out.print("ha funzionato il challengeModifier");
	
	
	}

	
	
 @After
 public void delate() {
	 if(CANCELLA){
		 Challenge chal = em.find(Challenge.class, "prova");
		 em.getTransaction().begin();
		 em.remove(chal);
		 em.getTransaction().commit();
				 
				 
	 } 
	 
 }



}
