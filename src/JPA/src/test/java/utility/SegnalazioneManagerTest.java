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
	private  Utente AUTORE;
	private UtenteWeb risolutore;
	private String MOTIVAZIONE= "si";
	


	
	
	
	@Test
	public void test() throws NotFindInDbException, BasicException {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
        SegnalazioniManager sm = new SegnalazioniManager();
        
       AUTORE =  em.find(Utente.class, "panino");
       
          sm.addSegnalazione(AUTORE, MOTIVAZIONE); 
	  
    //   List<Segnalazione> segnalazioni= sm.trovaSegnalazioni();

	//   System.out.println(segnalazioni.toString());
	   
	
//	   Integer id = 1;
      //System.out.println(segn.toString());
	   
	//   sm.risolviSegnalazione(id, "Azazzello-95");
	  
	 
	}

}
