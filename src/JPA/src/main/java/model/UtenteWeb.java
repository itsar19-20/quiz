package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import model.Segnalazione;

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
      
    };
	
	public String getUsername() {
		return username;
	}
	
	@JsonIgnore
	public List<Segnalazione> getSegnalazioni() {
		return segnalazioni;
	}

	@JsonIgnore
	public void setSegnalazioni(List<Segnalazione> segnalazioni) {
		this.segnalazioni = segnalazioni;
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
	public boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
