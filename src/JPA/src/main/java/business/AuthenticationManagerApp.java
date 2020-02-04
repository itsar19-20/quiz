package business;

import model.Utente;

//checked

public class AuthenticationManagerApp {
	
	public Utente login(String username, String password) {
		UserManager um = new UserManager();
		Utente _return = um.getUser(username);
		if(_return != null && !password.contentEquals(_return.getPassword())) {
			_return = null;
		}
		return _return;
	}
}
