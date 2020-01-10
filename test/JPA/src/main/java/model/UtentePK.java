package model;

import java.io.Serializable;
import java.lang.String;

/**
 * ID class for entity: Utente
 *
 */ 
public class UtentePK  implements Serializable {   
   
	         
	private String username;         
	private String email;
	private static final long serialVersionUID = 1L;

	public UtentePK() {}

	

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
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof UtentePK)) {
			return false;
		}
		UtentePK other = (UtentePK) o;
		return true
			&& (getUsername() == null ? other.getUsername() == null : getUsername().equals(other.getUsername()))
			&& (getEmail() == null ? other.getEmail() == null : getEmail().equals(other.getEmail()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getUsername() == null ? 0 : getUsername().hashCode());
		result = prime * result + (getEmail() == null ? 0 : getEmail().hashCode());
		return result;
	}
   
   
}
