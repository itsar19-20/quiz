package business;

import java.util.List;

import javax.management.BadAttributeValueExpException;
import javax.persistence.EntityManager;

import business.exeception.BasicException;
import business.exeception.NotFindInDbException;
import model.Challenge;
import model.Segnalazione;
import model.UtenteWeb;
import utility.JPAUtil;

public class WebUserManager {
	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	public void addWebUser(String username, String password, String admin) {
		UtenteWeb u = new UtenteWeb();
		u.setUsername(username);
		u.setPassword(password);

		if(admin == "true") {

		}
		if(admin.contentEquals("true")) {
			u.setAdmin(true);
		} else if(admin.contentEquals("false")) {
			u.setAdmin(false);
		}
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	public List<UtenteWeb> getUtenteWebList() {
		EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();
		return em.createQuery("SELECT u FROM UtenteWeb u", UtenteWeb.class).getResultList();
	}






	public void remove(String username) {
		try {if (em.find(UtenteWeb.class, username )== null ) {
			throw new  NotFindInDbException("UtenteWeb",username);
		}

		UtenteWeb u = em.find(UtenteWeb.class, username);
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();

		}catch(NotFindInDbException ex) {
			System.out.print(ex.toString());
		}
	}


	public void changeWebUser(String username , String password , String admin)  {
		try {if (em.find(UtenteWeb.class, username )== null ) {
			throw new  NotFindInDbException("UtenteWeb",username);
		}

		   
		if(     (!admin.equals(null)) && 
				(!admin.equals("false"))  && 
				(!admin.equals("true"))   ) {
         throw new BasicException("Valore scorretto:",admin);      
		};

		UtenteWeb web = em.find(UtenteWeb.class, username ); 

		Boolean adminChange;


		if(password == null) {
			password=web.getPassword();
		}


		if(admin == null) {
			adminChange = web.getAdmin();
		}
		else {adminChange =Boolean.parseBoolean(admin);} 

		em.getTransaction().begin();
		web.setAdmin(adminChange);
		web.setPassword(password);
		em.getTransaction().commit();


		}catch(NotFindInDbException ex) {
			System.out.print(ex.toString());
		
		} catch (BasicException e) {
			System.out.print(e.toString());
		}



	}







	public void edit(String username, String password, String admin ) {

		UtenteWeb u = em.find(UtenteWeb.class, username);
		em.getTransaction().begin();
		u.setPassword(password);
		if(admin.contentEquals("true")) {
			u.setAdmin(true);
		} else if(admin.contentEquals("false")) {
			u.setAdmin(false);
		}


		em.getTransaction().commit();



	}

}
