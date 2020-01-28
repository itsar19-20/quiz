package buisness;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.AddPointOnUserProfile;
import business.exception.BasicException;
import business.exception.NotFindInDbException;
import model.Challenge;
import model.Utente;
import utility.JPAUtil;

public class AddPointOnUserProfileTest2 {

	
	private final String USERNAME = "username";
    private final String PASSWORD= "password";
    private final String EMAIL = "email";	
    private final String TOKEN = "token";
    private final String IMMAGINE ="immagine";
    private final int UPUNTEGGIO =100;
    private final String NAZIONALITA ="nazioanlita";
    
    private final String TITOLO= "titolo";
    private final String DESRCRIZIONE ="descrizione";
    private final int CPUNTEGGIO =100;
    private final String FLAG ="flag"; 
    
	
    
    //@Before
    @Test
	public void before() {
    	//CREA UTENTE
    	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    	LocalDateTime now = LocalDateTime.now();
    	Utente u = new Utente();
    	u.setUsername(USERNAME);
    	u.setPassword(PASSWORD);
    	u.setEmail(EMAIL);
    	u.setDataiscrizione(dtf.format(now));
    	u.setToken(TOKEN);
    	u.setImmagine(IMMAGINE);
     	u.setNazionalita(NAZIONALITA);
    	u.setPunteggio(UPUNTEGGIO);
        
       em.getTransaction().begin();
       em.persist(u);
       em.getTransaction().commit();
       
        
        //CARICA UTENTE
        
     
       
       
    	
        //CREA CHALLENGER
       
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
	 	LocalDateTime now1 = LocalDateTime.now();
        Challenge c = new Challenge(); 
        
         c.setTitolo(TITOLO);
		c.setDescrizione(DESRCRIZIONE);
		c.setCreatore(u);
		c.setPunteggio(CPUNTEGGIO);
		c.setData(dtf1.format(now1));
		c.setFlag(FLAG);
		
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
    	em.close();
      
    }
   // @After
    //@Test  
     //  public void after() {
    	 
   // 	EntityManager rem = JPAUtil.getInstance().getEmf().createEntityManager(); 
    	
    //	    Utente u = rem.find(Utente.class, USERNAME); 
       
        
      //  emc.getTransaction().begin();
       // Challenge c = emc.find(Challenge.class, TITOLO);  
      //  emc.close();
      
      //  rem.getTransaction().begin();
       // rem.remove(c);
      //  rem.remove(u);
       // rem.getTransaction().commit();
       // rem.close();
     
    }    
    
    //@Test
	//public void test() throws NotFindInDbException, BasicException {
   // 	AddPointOnUserProfile apoup = new AddPointOnUserProfile();  
	//		apoup.addPoint(FLAG, USERNAME, TITOLO);
	//		apoup.addPoint(FLAG, "USERNAMEERRATO", TITOLO);
		//	apoup.addPoint(FLAG, USERNAME, "TITOLOERRATO");
		 //   apoup.addPoint("FLAGERRATO", USERNAME, TITOLO);
		
//}
//}