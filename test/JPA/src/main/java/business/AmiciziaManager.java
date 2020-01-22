package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.Amicizia;
import model.Utente;
import utility.JPAUtil;

public class AmiciziaManager {

	EntityManager em = JPAUtil.getInstance().getEmf().createEntityManager();

	public boolean VerificaAmicizia(Utente utente1, Utente utente2) {
		boolean _return = false;
		String id = utente1.getUsername() + "/" + utente2.getUsername();
		String idInvertito = utente2.getUsername() + "/" + utente1.getUsername();
		if ((em.find(Amicizia.class, id) != null) || ((em.find(Amicizia.class, idInvertito) != null))) {
			_return = true;
		}
		return _return;
	};

	public boolean aggiungiAmicizia(Utente utente1, Utente utente2) {
		boolean _return = false;
		if ((VerificaAmicizia(utente1, utente2)) == false) {
			Amicizia amicizia = new Amicizia();
			amicizia.setUtenti(utente1, utente2);
			em.getTransaction().begin();
			;
			em.persist(amicizia);
			em.getTransaction().commit();
			_return = true;
		}
		return _return;
	};

	public boolean CancellaAmicizia(Utente utente1, Utente utente2) {
		boolean _return = false;
		if ((VerificaAmicizia(utente1, utente2) == false)) {
			Amicizia amicizia = new Amicizia();
			amicizia.setUtenti(utente1, utente2);
			em.getTransaction().begin();
			;
			em.remove(amicizia);
			em.getTransaction().commit();
			_return = true;
		}
		return _return;
	};

	public List<Amicizia> FindAllAmiciza(Utente utente) {
		// ArrayList<Amicizia> risultato = new ArrayList<Amicizia>();
		List<Amicizia> amicizie = em
				.createQuery("select a from  Amicizia   where a.utente1 " + utente + "=  or  a.utente1 " + utente + "",
						Amicizia.class)
				.getResultList();
		return amicizie;
	};

};
