package model.tipiSegn;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import model.Segnalazione;


public  abstract class Extended extends Segnalazione  {

private String type ;

       public Extended(String type) {
    	   this.type =type;
       };  
	
       public String getType() {
    	   return this.type;
       };    
       
       
}
