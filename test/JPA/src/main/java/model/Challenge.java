package model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import model.Utente;

/**
 * Entity implementation class for Entity: challenger
 *
 */
@Entity
public class Challenge implements Serializable {

	@Id
	private String titolo;
	private String descrizione;
	private Utente creatore;
	private int rating;
	private int punteggio;
	private String data;
	private String flag;
	private static final long serialVersionUID = 1L;

	public Challenge() {
		super();
	}   
	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}   
	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}   
	public Utente getCreatore() {
		return this.creatore;
	}

	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}   
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		
		this.rating = rating;
	}   
	public int getPunteggio() {
		return this.punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}   
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
   
}
