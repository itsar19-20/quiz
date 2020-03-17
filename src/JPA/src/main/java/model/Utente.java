package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
/**
 * Entity implementation class for Entity: Utente
 *
 */
@Entity
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	private String email;
	private String dataiscrizione;
	private String password;
	private String token;
	private String immagine;
	private int punteggio;
	private String nazionalita;
	private String ultimoaccesso;
	//private boolean attivo;
	
	@OneToMany (mappedBy = "autore")
	private List<Commento> commenti;
    
    @OneToMany (mappedBy = "creatore")
    private List<Challenge> challengers;

    @OneToMany (mappedBy="autore") 
    private List<Segnalazione> segnalazioni;
    
    @OneToMany
    private List<Amicizia> amicizie;
	
    
    
    public Utente() {
		super();
        setToken("null");	
    	 setUltimoaccesso("null");
    	 setImmagine("null");	
	     setPunteggio(0);
		this.commenti=null;
		  this.segnalazioni=null;

    
    }

     @JsonIgnore
	public List<Commento> getCommenti() {
		return commenti;
		
	}



     @JsonIgnore
	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}


     @JsonIgnore
 	public List<Challenge> getChallengers() {
		return challengers;
	}



     @JsonIgnore
	public void setChallengers(List<Challenge> challengers) {
		this.challengers = challengers;
	}



     @JsonIgnore
	public List<Segnalazione> getSegnalazioni() {
		return segnalazioni;
	}




     @JsonIgnore
	public void setSegnalazioni(List<Segnalazione> segnalazioni) {
		this.segnalazioni = segnalazioni;
	}


  
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getDataiscrizione() {
		return this.dataiscrizione;
	}

	public void setDataiscrizione(String dataiscrizione) {
		this.dataiscrizione = dataiscrizione;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}   
	public String getImmagine() {
		return this.immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}   
	public int getPunteggio() {
		return this.punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}   
	public String getNazionalita() {
		return this.nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public String getUltimoaccesso() {
		return ultimoaccesso;
	}
	public void setUltimoaccesso(String ultimoaccesso) {
		this.ultimoaccesso = ultimoaccesso;
	}


/*
	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
  */ 
}
