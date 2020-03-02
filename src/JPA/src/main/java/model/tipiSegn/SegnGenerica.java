package model.tipiSegn;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SegnGenerica
 *
 */
@Entity

public class SegnGenerica  extends Extended  implements Serializable {

	private static final long serialVersionUID = 1L;

	public SegnGenerica() {
		super("generica");
	}
   
	private String descrizione;

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	@Override
	public String toString() {
		return "SegnGenerica [descrizione=" + descrizione + ", getDescrizione()=" + getDescrizione() + ", getType()="
				+ getType() + ", getId()=" + getId() + ", getRisolutore()="
				+ getRisolutore() + ", getAutore()=" + getAutore() + ", getData()=" + getData() + ", isRisolta()="
				+ isRisolta() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
