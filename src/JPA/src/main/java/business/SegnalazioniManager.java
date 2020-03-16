package business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////	RICERCA DELLE SENGALAZIONI
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public List<Segnalazione> trovaSegnalazioni() {		
		List<Segnalazione>Segnalazioni = new ArrayList<Segnalazione>();
	    
		
		List<SegnSpoiler> SegnSpoilers= trovaSegnSpoilers();
		for (Segnalazione sp : SegnSpoilers) {
			Segnalazioni.add(sp);  		
		}
		
		List<SegnGenerica> SegnGeneriche = trovaSegnGeneriche(); 	
		for (Segnalazione sg : SegnGeneriche) {
			Segnalazioni.add(sg);  		
		}
	
		return Segnalazioni;		
	}



	//// RICERCA PER  I SINGOLI TIPI ////////////
	public List<SegnGenerica> trovaSegnGeneriche(){
		List<SegnGenerica> SegnGeneriche = em.createQuery("SELECT s FROM SegnGenerica s "
				, SegnGenerica.class).getResultList();		
		  return SegnGeneriche;
	}



    public List<SegnSpoiler> trovaSegnSpoilers(){
    	
    	List<SegnSpoiler> SegnSpoilers= em.createQuery("SELECT s1 FROM SegnSpoiler s1 ", 
				SegnSpoiler.class).getResultList();
           return SegnSpoilers;
    
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//RISOLVISEGNALAZIONE
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void risolviSegnalazione(Integer segnId , String   userNameRisolutore) {
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


	public int  addSegnalazioneGenerica(String idAutore,  String motivazione )   {
            int idGen;     
		try {
			Utente autore = em.find(Utente.class, idAutore);
			if (autore == null) {
				throw new NotFindInDbException("Utente", idAutore);
			}

			
			SegnGenerica segn = new SegnGenerica();

			segn.setDescrizione(motivazione);
			
			
			addBase(autore,segn,segn.getTipo());
			em.getTransaction().begin();
			em.persist(segn);
			em.getTransaction().commit();
           idGen =segn.getId();   
		
		}
		catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
			idGen=0;
		}	
	
	    return idGen;
	
	}


	public int addSegnalazioneSpoiler(String IdAutore,  int idComm)  {

		    int idSegn;
		
		try {
			Commento  comm = em.find(Commento.class,idComm);
			Utente   autore=em.find(Utente.class,IdAutore );
			if(autore == null) {
				throw new NotFindInDbException("Utente", ""+IdAutore+"");
			}

			if (comm == null) {

				throw new NotFindInDbException("Commneto", ""+idComm+"");
			}


			SegnSpoiler segn = new SegnSpoiler(); 

			  
			
			addBase(autore,segn,segn.getTipo());


			segn.setComm(comm);  

			
			
			em.getTransaction().begin();
			em.persist(segn);
			em.getTransaction().commit();

			idSegn= segn.getId();
			
		    em.getTransaction().begin();
		    comm.setSegnalato(true);
		    em.getTransaction().commit();
		
		    
		
		}
		catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
			idSegn = 0;
		}	
		return  idSegn;
	
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//	LE UTILITY
	////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	private void addBase( Utente autore ,Segnalazione segn , String tipo  ) {	

		try {
			if (em.find(Utente.class, autore.getUsername()) == null) {
				throw new NotFindInDbException("Utente", autore.getUsername());
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			segn.setData(dtf.format(now));
            segn.setTipo(tipo); 			
			segn.setAutore(autore);
			segn.setAutore(autore);
			


		}catch(NotFindInDbException nfdbx) {
			System.out.print(nfdbx.toString());
		}


	}; 	











};  






