package utility;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import business.WebUserManager;

public class UserWebTest {
     private final String USERNAME ="phobos-1995";
     private final String PASSWORD ="pass";
     private final String ADMIN ="false";
     private final Boolean CANCELLA = false;
     
     WebUserManager webM = new WebUserManager();
     
     @Test
	public void test() {
	webM.addWebUser(USERNAME, PASSWORD, ADMIN);
	System.out.println("l' add  ha funionato");
	
	webM.changeWebUser(USERNAME, null, "mmmm");
	System.out.println("La modifica funziona");
	}

   
   @After
   public void delate() {
	   if(CANCELLA){
		   webM.remove(USERNAME);
	   }
   }


}
