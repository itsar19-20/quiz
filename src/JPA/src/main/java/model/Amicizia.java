package model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;


import model.Utente;

/**
 * Entity implementation class for Entity: Amicizia
 *
 */
@Entity

public class Amicizia implements Serializable {

	   
	@Id
	private String id;
	@ManyToOne
	private Utente utente;
	@ManyToOne
	private Utente amico;
	private static final long serialVersionUID = 1L;

	public Amicizia() {
		super();
	}
	
	public void setUtenti(Utente utente, Utente amico) {
         this.utente=utente;
         this.amico=amico;
         setId();
	}
	
	public String getId() {
		return this.id;
	}
	
	private void setId() {
		id= utente.getUsername()+"/"+amico.getUsername();
	}
	   
	public Utente getUtente() {
		return this.utente;
	}
	   
	public Utente getAmico() {
		return this.amico;
	}

}
