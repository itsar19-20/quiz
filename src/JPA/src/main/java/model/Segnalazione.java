package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;



@Entity


@Inheritance(strategy = InheritanceType.JOINED)
public class Segnalazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@ManyToOne
	@JoinColumn(name = "autore_username")
	private Utente autore;

	@ManyToOne
	@JoinColumn(name = "risolutore_username")
	private UtenteWeb risolutore;


	
	private String data;
	private boolean risolta;
    private String tipo;
    private boolean inLavorazione; 

	
    
    
    public String getTipo() {
		return tipo;
	}

	
    public void setTipo(String tipo) {
    	this.tipo =tipo;
    };
    
	public 	Segnalazione (){
		this.risolta = false;
		this.risolutore = null;
		this.inLavorazione = false;
	}

	;
	
	
	public Integer getId() {
		return id;
	}

	public UtenteWeb getRisolutore() {
		return risolutore;
	}

	public void setRisolutore(UtenteWeb risolutore) {
		this.risolutore = risolutore;
		if (this.risolutore != null) {
			this.risolta = true;
		}
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonGetter
	public Utente getAutore() {
		return autore;
	}

	@JsonSetter
	public void setAutore(Utente autore) {
		this.autore = autore;

	};


	public String getData() {
		return data;
	}

	public boolean isRisolta() {
		return risolta;
	}

	public void setData(String data) {
		this.data = data;
	}


	public boolean isInLavorazione() {
		return inLavorazione;
	}


	public void setInLavorazione(boolean inLavorazione) {
		this.inLavorazione = inLavorazione;
	}


	@Override
	public String toString() {
		return "Segnalazione [id=" + id + ", autore=" + autore + ", risolutore=" + risolutore + ", data=" + data
				+ ", risolta=" + risolta + ", tipo=" + tipo + ", inLavorazione=" + inLavorazione + "]";
	}

	
	

	

}