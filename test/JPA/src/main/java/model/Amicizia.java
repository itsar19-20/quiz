package model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import model.Utente;

/**
 * Entity implementation class for Entity: Amicizia
 *
 */
@Entity

public class Amicizia implements Serializable {

	   
	@Id
	private String id;
	private Utente utente1;
	private Utente utente2;
	private static final long serialVersionUID = 1L;

	public Amicizia() {
		super();
		
	}
	public void setUtenti(Utente utente1, Utente utente2) {
         this.utente1=utente1;
         this.utente2=utente2;
         setId();
	};
	
	
	   
	public String getId() {
		return this.id;
	}

	
	private void setId() {
		id= utente1.getUsername()+"/"+utente2.getUsername();
	};
	   
	public Utente getUtente1() {
		return this.utente1;
	}

	   
	public Utente getUtente2() {
		return this.utente2;
	}

	
   
}
