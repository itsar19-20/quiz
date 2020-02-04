package model;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.String;
import javax.persistence.*;
import model.Challenge;

/**
 * Entity implementation class for Entity: Commento
 *
 */
@Entity

public class Commento implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Boolean spoiler;
	private String data;
	private String testo;
	@ManyToOne
	private Challenge challenger;
	
	@ManyToOne
	private Utente autore;
	
	private static final long serialVersionUID = 1L;

	public Commento() {
		super();
	}   
	public Challenge getChallenger() {
		return this.challenger;
	}

	public void setChallenger(Challenge challenger) {
		this.challenger = challenger;
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
