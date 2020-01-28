package business.exception;

public class BasicException extends Exception {
 private String field;
 
  public BasicException (String message, String field ) {
	  super (message);
	  this.field=field;
  };
  
   public String getField() {
	   return field;
   };

   public void risolveProblem(){} 
   
   
   public String toString(){
	   return this.getField()+":"+this.getMessage();
   };
}
