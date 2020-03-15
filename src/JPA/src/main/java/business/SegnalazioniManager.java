package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;

import model.Commento;
import model.tipiSegn.*;
import model.Segnalazione;
import model.Utente;
import model.UtenteWeb;
import utility.JPAUtil;
import business.exeception.*;
public class SegnalazioniManager {

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	// questo metodo andrea modificato
	public List<Segnalazione> trovaSegnalazioni() {
		List<Segnalazione> Segnalazioni = em.createQuery("SELECT s FROM Segnalazione s ", Segnalazione.class)
				.getResultList()
				;

		return Segnalazioni;		
	}



	public void risolviSegnalazione(Integer segnId , String   userNameRisolutore) throws NotFindInDbException{
		try {
			
			if (em.find(Segnalazione.class, segnId)== null ) {
				throw new  NotFindInDbException("Segnalazione","segnId");
			} 

			if ((em.find(UtenteWeb.class, userNameRisolutore))== null ){
				throw new  NotFindInDbException("UtenteWeb", userNameRisolutore);			
			}
			
			if((em.find(Segnalazione.class, segnId).getRisolutore()!= null) ) {
				throw new BasicException("la query è già stata risolta",""+segnId);			
				}

			UtenteWeb risolutore =(em.find(UtenteWeb.class, userNameRisolutore)); 

			Segnalazione s =em.find(Segnalazione.class, segnId);



			em.getTransaction().begin(); 
			s.setRisolutore(risolutore);
			em.getTransaction().commit();

		}	

		catch(NotFindInDbException ex) {
			System.out.print(ex.toString());
		
		} catch (BasicException e) {
			e.toString();
		}
	}




	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TUTTI GLI ADD SEGNALAZIONE
	///////////////////////////////////////////////////////////////////////////////////////////////////


	public void addSegnalazioneGenerica(Utente autore,  String motivazione )   {

		try {
			if (autore == null) {
				throw new NotFindInDbException("Utente", "autore");
			}

			
			SegnGenerica segn = new SegnGenerica();
			addBase(autore,segn);
			segn.setDescrizione(motivazione);
			
			em.getTransaction().begin();
			em.persist(segn);
			em.getTransaction().commit();

		}
		catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}	
	}


	public void addSegnalazioneSpoiler(Utente autore,  Commento comm)  {

		try {

			if (comm == null) {
				throw new NotFindInDbException("Commneto", ""+comm );
			}



			SegnSpoiler segn = new SegnSpoiler(); 

			addBase(autore,segn);
			segn.setComm(comm);  

			em.getTransaction().begin();
			em.persist(segn);
			em.getTransaction().commit();

		}
		catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}	
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//	LE UTILITY
	////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private void addBase( Utente autore ,Segnalazione segn ) {	

		try {
			if (em.find(Utente.class, autore.getUsername()) == null) {
				throw new NotFindInDbException("Utente", autore.getUsername());
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			segn.setData(dtf.format(now));
			segn.setAutore(autore);
			


		}catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}


	}; 	











};  






