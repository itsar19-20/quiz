package utility;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Test;

import business.SignupManager;
import model.*;
import business.*;

public class SigUpTest {
private final String USERNAME= "oliz";
private final String PASSWORD= "www";
private final String EMAIL ="@olizOne";
private final String NAZIONALITA= "ita";
	

@Test
	public void test() {
		SignupManager sum = new SignupManager();
        WebUserManager web = new WebUserManager();
        web.addWebUser("Azazzello-95", "password", "admin");
		sum.signup(USERNAME, PASSWORD,EMAIL , NAZIONALITA);
	}

	@After
	public void afeter() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
        Utente u = em.find(Utente.class, USERNAME);
        
        //System.out.print(u.toString());
        
       em.getTransaction().begin();
//       em.remove(u);
       em.getTransaction().commit();
        
        
	};
	
}
