package model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import model.Challenge;
import model.tipiSegn.SegnSpoiler;

/**
 * Entity implementation class for Entity: Commento
 *
 */
@Entity

public class Commento implements Serializable {
	private static final long serialVersionUID = 1L;

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Boolean spoiler;
	private String data;
	private String testo;
	private Boolean segnalato;
	
	@ManyToOne
	private Challenge challenge;
	
	@ManyToOne
	private Utente autore;
	
	public Commento() {
		super();
		this.spoiler = false; 
		this.segnalato = false;
	}   
	

	public void setId(int id) {
		this.id = id;
	}   
	
	public int getId() {
		return this.id;
	}
	
	@JsonGetter
	public Challenge getChallenge() {
		return this.challenge;
	}
     
	@JsonSetter
	public void setChallenger(Challenge challenge) {
		this.challenge = challenge;
	}   
   
	@JsonGetter 
	public Utente getAutore() {
		return this.autore;
	}
 
	@JsonSetter
	public void setAutore(Utente autore) {
		this.autore = autore;
	}   

	
	
	
	
	
	public Boolean getSegnalato() {
		return segnalato;
	}


	public void setSegnalato(Boolean segnalato) {
		this.segnalato = segnalato;
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


	@Override
	public String toString() {
		return "Commento [id=" + id + ", spoiler=" + spoiler + ", data=" + data + ", testo=" + testo + ", segnalato="
				+ segnalato + ", challenge=" + challenge.toString() + ", autore=" + autore + "]";
	}
	
	
  
	
	
	
}
