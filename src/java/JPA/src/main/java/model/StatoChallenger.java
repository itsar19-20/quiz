package model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.*;
import model.Utente;
import model.challenger;

/**
 * Entity implementation class for Entity: StatoChallenger
 *
 */
@Entity

public class StatoChallenger implements Serializable {

	   
	@Id
	private String id ;
	private Utente utente;
	private challenger challenger;
	private Boolean risolto;
	private static final long serialVersionUID = 1L;


	public StatoChallenger() {
		super();
	}   
 
	 
	
	public String getId() {
		return this.id;
	}

	public void setId() {
		id =utente.getEmail()+utente.getUsername()+challenger.getTitolo();
	}   
	
	
	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}   
	public challenger getChallenger() {
		return this.challenger;
	}

	public void setChallenger(challenger challenger) {
		this.challenger = challenger;
	}   
	public Boolean getRisolto() {
		return this.risolto;
	}

	public void setRisolto(Boolean risolto) {
		this.risolto = risolto;
	}
   
}
