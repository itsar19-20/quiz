package business;

import model.Utente;

//checked

public class AuthenticationManagerApp {
	
	public Utente login(String username, String password) {
		Utente _return = GetUser.getUser(username);
		if(_return != null) {
			if(!password.contentEquals(_return.getPassword())) {
				_return = null;
			}
		}
		return _return;
	}
}
