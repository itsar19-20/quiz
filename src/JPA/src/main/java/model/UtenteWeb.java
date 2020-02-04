package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UtenteWeb {
	
	@Id
	private String username;
	private String password;
	private boolean admin;
	@OneToMany(mappedBy = "risolutore")
	private List<Segnalazione> segnalazioni;
	
	public UtenteWeb() {
		segnalazioni = null;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
