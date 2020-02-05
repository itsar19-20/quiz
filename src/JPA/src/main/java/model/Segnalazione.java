package model;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Segnalazione implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//METTERE UN ENUMERAZIONE PER I TIPI DI SEGNALAZIONE
	@ManyToOne
	@JoinColumn(name = "autore")
	private Utente autore;

	@ManyToOne 
	@JoinColumn(name = "risolutore")
	private UtenteWeb risolutore;
	private String motivazione;
	private String data;

	private boolean risolta;



  public 	Segnalazione (){
		 this.risolta = false;
		 this.risolutore = null;
	}

	public Integer getId()	 
	{
		return id;
	}
	public UtenteWeb getRisolutore() {
		return risolutore;
	}

	public void setRisolutore(UtenteWeb risolutore) {
		this.risolutore = risolutore;
		if(this.risolutore!= null) {
			this.risolta=true;
		}	
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Utente getAutore() {
		return autore;
	}
	public void setAutore(Utente autore) {
		this.autore = autore;

	};

	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	public String getData() {
		return data;
	}
	public boolean isRisolta() {
		return risolta;
	}


	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Segnalazione [id=" + id + ", autore=" + autore + ", risolutore=" + risolutore + ", motivazione="
				+ motivazione + ", data=" + data + ", risolta=" + risolta + "]";
	}


}
