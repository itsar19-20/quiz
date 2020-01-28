package utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import model.Challenge;
import model.Utente;

public class CreaEntity {

		
	static public void creaUtente(String username, String password, String email, String token ,String immagine, int punteggio, String nazionalita) {
	EntityManager cu = JPAUtil.getInstance().getEmf().createEntityManager();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	Utente u = new Utente();
	u.setUsername(username);
	u.setPassword(password);
	u.setEmail(email);
	u.setDataiscrizione(dtf.format(now));
	u.setToken(token);
	u.setImmagine(immagine);
	u.setNazionalita(nazionalita);
	
	cu.getTransaction().begin();
	cu.persist(u);
	cu.getTransaction().commit();
	cu.close();
	
	}

   static 	  public void creaChallenger(String TITOLO , String DESRCRIZIONE, Utente CREATORE, int PUNTEGGIO ,String FLAG ) {
	   EntityManager en = JPAUtil.getInstance().getEmf().createEntityManager();
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		Challenge check = new Challenge();;
		check.setTitolo(TITOLO);
		check.setDescrizione(DESRCRIZIONE);
		check.setCreatore(CREATORE);
		check.setPunteggio(PUNTEGGIO);
		check.setData(dtf.format(now));
		check.setFlag(FLAG);
		
		en.getTransaction().begin();
		en.persist(check);
		en.getTransaction().commit();
        en.close();
   };


}
