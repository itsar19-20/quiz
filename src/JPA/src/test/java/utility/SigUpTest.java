package utility;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Test;

import business.SignupManager;
import model.Utente;

public class SigUpTest {
private final String USERNAME= "username";
private final String PASSWORD= "password";
private final String EMAIL ="email";
private final String NAZIONALITA= "nazionalita";
	

@Test
	public void test() {
		SignupManager sum = new SignupManager();
        
		sum.signup(USERNAME, PASSWORD,EMAIL , NAZIONALITA);
	}

	@After
	public void afeter() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
        Utente u = em.find(Utente.class, USERNAME);
        
        //System.out.print(u.toString());
        
       em.getTransaction().begin();
       em.remove(u);
       em.getTransaction().commit();
        
        
	};
	
}
