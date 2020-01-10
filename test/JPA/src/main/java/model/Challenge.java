package model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import model.Utente;

/**
 * Entity implementation class for Entity: Challenge
 *
 */
@Entity

public class Challenge implements Serializable {

	   
	@Id
	private String titolo;
	private String descrizione;
	private Utente creatore;
	private Difficolta rating;
	private int punteggio;
	private String data;
	private Categoria categora ;
	public Categoria getCategora() {
		return categora;
	}
	public void setCategora(Categoria categora) {
		this.categora = categora;
	}

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
	public Difficolta getRating() {
		return this.rating;
	
	
	}

	public void setRating(Difficolta rating) {
		
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
   
}
