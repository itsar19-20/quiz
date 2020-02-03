package utility;

import javax.persistence.EntityManager;
import business.*;
import model.Utente;
import model.UtenteWeb;
import utility.*;
import org.junit.Test;

public class EntityManagerTest {
    
	@Test
	public void test() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
         UtenteWeb u = new  UtenteWeb();   
         u.setAdmin(true);
         u.setUsername("stocazzo");
         u.setPassword("password");
         
         em.getTransaction();
         	
         WebUserAdder wua = new WebUserAdder();
         wua.addWebUser("stocazzo", "password", "admin");
	}

}
