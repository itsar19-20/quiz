package utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import business.WebUserManager;

public class UserWebTest {

	private final String USERNAME ="Dio";
	private final String PASSWORD ="Mi piaccio solo io";
	private final String ADMIN ="true";

	private final Boolean CANCELLA = false;
	private final Boolean CAMBIAUTENTE = false;
	private final Boolean ADDUTENTE = true;
	private final Boolean CAHNGEUTENTE= false;


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


		if(CAHNGEUTENTE) {
			webM.changeWebUser(USERNAME, null, "true");
		};


	}





	@After
	public void delate() {
		if(CANCELLA){
			webM.remove(USERNAME);
		}
	}


}
