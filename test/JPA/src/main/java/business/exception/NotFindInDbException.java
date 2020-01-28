package business.exception;

public class NotFindInDbException   extends BasicException {
	 
		

	
	public  NotFindInDbException(String entity ,String id ){
          super("non abbiamo trovato l'item al intenro del db", "entity:"+entity+" id:"+id);   
	};

   


}
