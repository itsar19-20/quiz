package model.tipiSegn;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import model.Segnalazione;


public  abstract class Extended extends Segnalazione  {



       public Extended(String type) {
    	   setTipo(type);
       };  
	
          
       
       
}
