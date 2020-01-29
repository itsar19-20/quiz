package business.exeception;

public class NotFindInDbException extends BasicException{

	public NotFindInDbException (String db, String campo) {
		super ("non abbiamo trovato il campo nel db","db:"+db+" campo:"+campo);
	}
}
