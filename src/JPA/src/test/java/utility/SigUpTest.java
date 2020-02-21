package utility;



import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Test;

import business.SignupManager;
import model.*;
import business.*;

public class SigUpTest {
private final String USERNAME= "tizio";
private final String PASSWORD= "nnn";
private final String EMAIL ="@tizio";
private final String NAZIONALITA= "ita";
	

@Test
	public void test() {
		SignupManager sum = new SignupManager();
        WebUserManager web = new WebUserManager();
        web.addWebUser("Phobos-2009", "pass", "noadmin");
		sum.signup(USERNAME, PASSWORD,EMAIL , NAZIONALITA);
	}

	@After
	public void afeter() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		UtenteWeb u = em.find(UtenteWeb.class, USERNAME);
        
        //System.out.print(u.toString());
        
       em.getTransaction().begin();
//       em.remove(u);
       em.getTransaction().commit();
        
        
	};
	
}
