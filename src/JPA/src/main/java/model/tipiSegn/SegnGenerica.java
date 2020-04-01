package model.tipiSegn;

import java.io.Serializable;
import javax.persistence.*;

import model.UtenteWeb;

/**
 * Entity implementation class for Entity: SegnGenerica
 *
 */
@Entity

public class SegnGenerica  extends Extended  implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@JoinColumn(name="consegna")
	private UtenteWeb consegna;
	
	
	
	private String descrizione;


	public SegnGenerica() {
		super("generica");
          	
	}
	
	public UtenteWeb getConsegna() {
		return consegna;
	}

	
	public void setConsegna(UtenteWeb consegna) {
		this.consegna = consegna;
	}

	
	
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	
	
	
	
	@Override
	public String toString() {

		return "SegnGenerica [id=" + getId()+ ", risolutore=" + getRisolutore() + ", autore=" + getAutore() + ", data=" + getData()
				+ ", risolta=" + isRisolta() +", descrizione=" + descrizione +", tipo=" + getTipo() +" consegna="+ getConsegna()+"]";
	}

	
	
	
	
	
}
