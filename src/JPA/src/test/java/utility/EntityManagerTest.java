package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OneToMany;

import business.*;
import business.exeception.BasicException;
import business.exeception.NotFindInDbException;
import model.*;
import controller.*;
import utility.*;
import org.junit.Test;

public class EntityManagerTest {
    
	@Test
	public void test() throws NotFindInDbException, BasicException {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
        SegnalazioniManager sm = new SegnalazioniManager();
        UserManager um = new UserManager();
        Utente u = em.find(Utente.class, "panino");
        if (u == null) {
        	System.out.print("no utente");
        };
        sm.addSegnalazione(u,"Si");
        System.out.print("funziono forse");
      
      	  	     
	         
        
	}

	
	
	
}
