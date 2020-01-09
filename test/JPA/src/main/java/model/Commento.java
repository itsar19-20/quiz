package model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.*;
import model.Utente;
import model.Challenge;

/**
 * Entity implementation class for Entity: Commento
 *
 */
@Entity

public class Commento implements Serializable {

	
	private Challenge challenge;
	private Utente autore;   
	@Id
	private int id;
	private Boolean spoiler;
	private String data;
	private String testo;
	private static final long serialVersionUID = 1L;

	public Commento() {
		super();
	}   
	public Challenge getchallenge() {
		return this.challenge;
	}

	public void setchallenge(Challenge challenge) {
		this.challenge = challenge;
	}   
	public Utente getAutore() {
		return this.autore;
	}

	public void setAutore(Utente autore) {
		this.autore = autore;
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Boolean getSpoiler() {
		return this.spoiler;
	}

	public void setSpoiler(Boolean spoiler) {
		this.spoiler = spoiler;
	}   
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}   
	public String getTesto() {
		return this.testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
   
}
