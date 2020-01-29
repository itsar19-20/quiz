package business.exeception;

public class BasicException extends Exception  {

	private String field;
	private String message;
	
	public BasicException (String message , String field) {
		super(message);
		this.field=field;
		this.message=message;
	}

	public String toString() {
		return field+":"+message ;
	};
	
	public void fixProblem() {};
}
