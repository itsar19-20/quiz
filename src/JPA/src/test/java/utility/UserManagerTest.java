package utility;

import static org.junit.Assert.*;

import java.util.List;

import business.UserManager;
import model.Utente;

import org.junit.After;
import org.junit.Test;

public class UserManagerTest {
	private final String USERNAME ="giocatore";
	private final String PASSWORD ="play";
	private final String EMAIL= "@gioco";
	private final Boolean CANCELLA =false;
	
    
	UserManager userM = new UserManager();
	
	@Test
	public void test() {
	 
		userM.addUser(USERNAME, EMAIL, PASSWORD);
		System.out.print("l'aggionta è andata a buon fine");

		

	}
	@After
	public void delate() {
    if(CANCELLA){
		userM.removeUser(USERNAME); 
    System.out.print("la cnacella zione è andata a buon fine");
    }
	
	}

}


