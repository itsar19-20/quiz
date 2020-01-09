package model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.*;
import model.Utente;
import model.Challenge;

/**
 * Entity implementation class for Entity: Statochallenge
 *
 */
@Entity

public class StatoChallenge implements Serializable {

	   
	@Id
	private String id ;
	private Utente utente;
	private Challenge challenge;
	private Boolean risolto;
	private static final long serialVersionUID = 1L;


	public StatoChallenge() {
		super();
	}   
 
	 
	
	public String getId() {
		return this.id;
	}

	public void setId() {
		id =utente.getEmail()+utente.getUsername()+challenge.getTitolo();
	}   
	
	
	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}   
	public Challenge getChallenge() {
		return this.challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}   
	public Boolean getRisolto() {
		return this.risolto;
	}

	public void setRisolto(Boolean risolto) {
		this.risolto = risolto;
	}
   
}
