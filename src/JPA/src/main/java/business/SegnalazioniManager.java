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
		//trova gli spoiler risolti e  inrissolit
		List<SegnSpoiler> SegnSpoilersTrue= em.createQuery("SELECT s1 FROM SegnSpoiler s1 Where s1.risolta = :true ", 
				SegnSpoiler.class).setParameter("true", true).getResultList();

		List<SegnSpoiler> SegnSpoilersFalse= em.createQuery("SELECT s1 FROM SegnSpoiler s1 Where s1.risolta = :false ", 
				SegnSpoiler.class).setParameter("false", false).getResultList();

		List<Integer> idCom = new ArrayList<Integer>();
		List<SegnSpoiler> SegnSpoilerFinal = new ArrayList<SegnSpoiler>();

		//aggiungi tutti  gli spoiler risolti al risultato 
		for (SegnSpoiler ss : SegnSpoilersTrue) {
			SegnSpoilerFinal.add(ss);
		}

		//aggiungi al risultato solo gli spoiler irrisolti senza ripetere quelli con  lo stesso commento 		
		for (SegnSpoiler ss : SegnSpoilersFalse) {
			if (idCom.isEmpty()) {
				idCom.add(ss.getComm().getId());
				SegnSpoilerFinal.add(ss);

			}else {
				if (!idCom.contains(ss.getComm().getId())){
					idCom.add(ss.getComm().getId());
					SegnSpoilerFinal.add(ss);
				}


			}


		} 	

		return SegnSpoilerFinal;

	}







	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//RISOLVISEGNALAZIONE
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public void risolviSegnalazione(Integer segnId , String   userNameRisolutore) {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		try {
			
			UtenteWeb risolutore =(em.find(UtenteWeb.class, userNameRisolutore)); 

			Segnalazione s =em.find(Segnalazione.class, segnId);
			
			if (s == null ) {
				throw new  NotFindInDbException("Segnalazione","segnId");
			} 

			if (risolutore == null ){
				throw new  NotFindInDbException("UtenteWeb", userNameRisolutore);			
			}

			if(s.getRisolutore()!= null) {
				throw new BasicException("la query è già stata risolta",""+segnId);			
			}


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


	/////////////////////////////////////////////////////////////////////////////////////
	//RISOLVI SPOILER	
	///////////////////////////////////////////////////////////////////////////////////	
	public void  risolviSpoiler(int segnId , String risolutore, Boolean spoiler ) {
		try { SegnSpoiler  segn = em.find(SegnSpoiler.class, segnId);
		UtenteWeb risol = em.find(UtenteWeb.class, risolutore);

		if (risol== null) {
			throw new  NotFindInDbException("UtenteWeb",risolutore);
		};
		if(segn== null) {
			if(em.find(SegnSpoiler.class, segnId)!=null){
				throw new BasicException("Questa Segn non è Spoiler",""+segnId); }   	 
			else{throw new  NotFindInDbException("SegnSpoiler",""+segnId);}}
		Commento comm =segn.getComm();
		List<SegnSpoiler> listSpoiler = em.createQuery("SELECT s FROM SegnSpoiler s WHERE s.comm = :commento  "
				, SegnSpoiler.class).setParameter("commento", comm).getResultList();
		for (SegnSpoiler ss : listSpoiler) {
			risolviSegnalazione(ss.getId(),risolutore);
		}  	
		em.getTransaction().begin();
		comm.setSpoiler(spoiler);
		em.getTransaction().commit();
		}	catch(NotFindInDbException ex) {
			System.out.print(ex.toString());

		}   catch (BasicException e) {
			e.toString();
		}

	};

	

	/////////////////////////////////////////////////////////////////////////////////
	//SEGNA IN LAVORAZIONE
	/////////////////////////////////////////////////////////////////////////////////

	public void SegnaLavorazione(int segnId,Boolean stato) {
		try {Segnalazione segn = em.find(Segnalazione.class, segnId);
		if (segn == null) {
			throw new  NotFindInDbException("Segnalazione", ""+segnId);
		}

		em.getTransaction().begin();
		segn.setInLavorazione(stato);
		em.getTransaction().commit();			

		}catch(NotFindInDbException e ) {
			e.toString();
		}


	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GENRICA METTI IN CONSEGNA 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public  void consegna(int segnId, String  risolutoreName){
		try {SegnGenerica segn = em.find(SegnGenerica.class, segnId);
             UtenteWeb risolutore = em.find(UtenteWeb.class, risolutoreName);  

          if (risolutore == null) {
     			throw new  NotFindInDbException("UtenteWeb", ""+risolutoreName);}
 
          if (segn == null) {
			throw new  NotFindInDbException("SegnGenerica", ""+segnId);}
          

		em.getTransaction().begin();
		segn.setConsegna(risolutore);
		em.getTransaction().commit();			

        

		}catch(NotFindInDbException e ) {
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

				addBase(autore,segn,segn.getTipo());

				segn.setDescrizione(motivazione);



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






