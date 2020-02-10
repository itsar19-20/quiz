package utility;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;

import org.junit.Test;

import business.SegnalazioniManager;
import business.exeception.BasicException;
import business.exeception.NotFindInDbException;
import model.Segnalazione;
import model.Utente;
import model.UtenteWeb;

public class SegnalazioneManagerTest {
	private  String AUTORE= "oliz";
	private UtenteWeb risolutore;
	private String MOTIVAZIONE= "bug";
	


	
	
	
	@Test
	public void test() throws NotFindInDbException, BasicException {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
        SegnalazioniManager sm = new SegnalazioniManager();
        
      //   Utente autore =  em.find(Utente.class, "oliz");
       
       //  sm.addSegnalazione(autore, MOTIVAZIONE); 
	  
        
          
          
    List<Segnalazione> segnalazioni= sm.trovaSegnalazioni();

	  System.out.println(segnalazioni.toString());
	  
	  for (int i=0 ; i> segnalazioni.size() ; i ++ ) {
		  UtenteWeb web = new  UtenteWeb();
		   web = segnalazioni.get(i).getRisolutore();
		   System.out.println(web.toString());
	  };
	   
	
	 
    //  System.out.println(segn.toString());
      // Integer id = 14;
	 //  sm.risolviSegnalazione(id, "Azazzello-95");
	  
	 
	   
	   
	   
	   
	
	}

}
