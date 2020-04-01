	package utility;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import model.Commento;
import model.Utente;
import model.Challenge;

import org.junit.After;
import org.junit.Test;

import business.CommentoManager;

public class CommentoManagerTest {
	private final String AUTORE="Matteo";
	private final String CHALLENGER ="pest";
	private final Boolean SPOILER =false;
	private final String  TESTO =" bella merda";
	private final Boolean CANCELLA= false; 	
	private final Boolean ADD = true;
	private final Boolean CHANGESPOILER = false;

	CommentoManager commM = new CommentoManager();
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager(); 

	@Test
	public void test() {

		if(ADD) {
			commM.AddCommneto(AUTORE, CHALLENGER, SPOILER, TESTO);
		}

		if (CHANGESPOILER) {
			Commento comm = new Commento();


     		comm=em.createQuery("Select c from Commento c where autore = : a and"
					+" challenge = :c and"
					+ " testo = : t", Commento.class).
					setParameter("a", em.find(Utente.class, AUTORE)).
					setParameter("c", em.find(Challenge.class, CHALLENGER)).
					setParameter("t", TESTO).
					getSingleResult();
					
		   commM.ChangeSpoiler(comm.getId());
		};


	}

	@After
	public void remuve() {
		if (CANCELLA) {
			commM.remuveComment(1);
			System.out.println("hai  rimosso il commento "+em.find(Commento.class, 1).toString()+"");

		};
	};



}
