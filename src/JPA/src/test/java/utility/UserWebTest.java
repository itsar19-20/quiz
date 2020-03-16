package utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import business.WebUserManager;

public class UserWebTest {
     
	private final String USERNAME ="phobos-1955";
	private final String PASSWORD ="pass";
	private final String ADMIN ="true";

	private final Boolean CANCELLA = false;
	private final Boolean CAMBIAUTENTE = true;
	private final Boolean ADDUTENTE = false;


	WebUserManager webM = new WebUserManager();

	@Test

	public void test() {
		if(ADDUTENTE) {
			webM.addWebUser(USERNAME, PASSWORD, ADMIN);
			System.out.println("l' add  ha funionato");
		}

		if(CAMBIAUTENTE){
			webM.changeWebUser("tEST", null, "false");
			System.out.println("La modifica funziona");
		}
	}

	@After
	public void delate() {
		if(CANCELLA){
			webM.remove(USERNAME);
		}
	}


}
