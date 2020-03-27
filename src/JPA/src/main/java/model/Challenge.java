package model;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import model.Utente;

/**
 * Entity implementation class for Entity: challenger
 *
 */
@Entity
public class Challenge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String titolo;
	private String descrizione;
	@ManyToOne
	@JoinColumn(name = "creatore")
	private Utente creatore;
	
	private String categoria;
	private int rating;
	private int punteggio;
	private String data;
	private String flag;
	
	@OneToMany (mappedBy = "challenge")
	private List<Commento> commenti;
	
	
	
	@JsonIgnore
	public List<Commento> getCommenti() {
		return commenti;
	}
	
	@JsonIgnore
	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}
	
	
	
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
	
	@JsonGetter
	public Utente getCreatore() {
		return this.creatore;
	}
    @JsonSetter 
 	public void setCreatore(Utente creatore) {
		this.creatore = creatore;
	}   
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
