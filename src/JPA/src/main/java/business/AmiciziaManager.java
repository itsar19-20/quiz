package business;

import java.util.List;

import javax.persistence.EntityManager;

import business.exeception.BasicException;
import model.Amicizia;
import model.Utente;
import utility.JPAUtil;

public class AmiciziaManager {

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	public Amicizia verificaAmicizia(Utente utente1, Utente utente2) {

		Amicizia _return = null;

		if(utente1 == utente2) {
			return _return;
		}
		String id = utente1.getUsername() + "/" + utente2.getUsername();
		String idInvertito = utente2.getUsername() + "/" + utente1.getUsername();
		_return = em.find(Amicizia.class, id);
		if (_return == null) {
			_return = em.find(Amicizia.class, idInvertito);
		}
		return _return;
	}

	public void aggiungiAmicizia(Utente utente1, Utente utente2) throws BasicException {
		try {	
			Amicizia check = verificaAmicizia(utente1, utente2);
			if (check != null) 
			{throw new BasicException("l'amicizia è già presente nel db",utente1.getUsername()+"/"+utente2.getUsername()); }
			Amicizia amicizia = new Amicizia();
			amicizia.setUtenti(utente1, utente2);
			em.getTransaction().begin();
			em.persist(amicizia);
			em.getTransaction().commit();

		}
		catch(BasicException bx)
		{ System.out.print(bx.toString());

		}

	}

	public boolean cancellaAmicizia(Utente utente1, Utente utente2) {
		Amicizia check = verificaAmicizia(utente1, utente2);
		if (check != null) {
			em.getTransaction().begin();
			em.remove(check);
			em.getTransaction().commit();
			return true;
		}
		return false;
	}


	public List<Amicizia> findAllAmiciza(Utente utente) {
		return em.createQuery("select a from  Amicizia a where utente = :utente", Amicizia.class)
				.setParameter("utente", utente)
				.getResultList();
	}

}
