package utility;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import model.Commento;

import org.junit.After;
import org.junit.Test;

import business.CommentoManager;

public class CommentoManagerTest {
 private final String AUTORE="tizio";
 private final String CHALLENGER ="pest";
 private final Boolean SPOILER =false;
 private final String  TESTO ="questo è il flag $$$$$$$$";
 private final Boolean CANCELLA= false; 	

 CommentoManager commM = new CommentoManager();
EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager(); 
 
 @Test
	public void test() {

	 commM.AddCommneto(AUTORE, CHALLENGER, SPOILER, TESTO);
	//System.out.println("ha creato il commento "+em.find(Commento.class, 1).toString()+"");
 	
    //commM.ChangeSpoiler(1);	
 
 }

 @After
 public void remuve() {
	 if (CANCELLA) {
		 commM.remuveComment(1);
	     System.out.println("hai  rimosso il commento "+em.find(Commento.class, 1).toString()+"");
	 
	 };
 };
 
 
 
}
