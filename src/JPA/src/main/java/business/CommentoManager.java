package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import org.eclipse.jetty.server.Authentication.User;

import business.exeception.NotFindInDbException;
import model.Commento;
import model.Utente;
import model.Challenge;
import utility.JPAUtil;

public class CommentoManager {

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager(); 


	public void AddCommneto(String  autore , String challenge, Boolean spoiler , String testo) {
		try {if(em.find(Utente.class, autore)== null) {
			throw new NotFindInDbException("Utente", autore);
		}
		{if(em.find(Challenge.class, challenge)== null) {
			throw new NotFindInDbException("challenge", challenge);
		}
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();



		Commento comm = new Commento ();
		comm.setAutore(em.find(Utente.class,autore));
		comm.setChallenger(em.find(Challenge.class, challenge));
		comm.setSpoiler(spoiler);
		comm.setData(dtf.format(now));
		comm.setTesto(testo); 
		
		em.getTransaction().begin();
		em.persist(comm);
		em.getTransaction().commit();



		}catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}

	}


	public void ChangeSpoiler(int id_comm) {
		try {if(em.find(Commento.class, id_comm)== null) {
			throw new NotFindInDbException("Commento", ""+id_comm);
		}

		Commento comm =em.find(Commento.class, id_comm);

		em.getTransaction().begin();
		if(comm.getSpoiler()) {comm.setSpoiler(false);}else {comm.setSpoiler(true);}
		em.getTransaction().commit();

		}catch (NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}

	}


	public void remuveComment(int id_comm) {
		try {if(em.find(Commento.class, id_comm)== null) {
			throw new NotFindInDbException("Commento", ""+id_comm);
		}			

        Commento com = em.find(Commento.class, id_comm);
		
        em.getTransaction().begin();
        em.remove(com);
        em.getTransaction().commit();
		
		
		}catch (NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}


	}



}


