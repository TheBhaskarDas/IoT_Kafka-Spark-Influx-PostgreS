package com.opternaam.iot.sparkintegrator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Validators {
	static	Logger	log	=	Logger.getLogger(Validators.class);
	
	public	static	Float	tempVal;
	public	static	Float	humiVal;
	
	public	static	Boolean	tempValidator(String	tempPart) {
		try {	
			tempVal	=	Float.parseFloat(tempPart);  //CONVERTING THE STRING TO FLOAT INORDER TO CHECK FOR THE CORRECT DATA TYPE 
			return	true;
		}catch(Exception	e) {
			System.out.println("AT TEMP VALUE CATCH");
			log.error("TEMPERATURE IS NOT IN CORRECT FORMAT");
			return false;
		}
	
	}
	
	
	public	static	Boolean	humiValidator(String	humiPart) {
		try {	
			humiVal	=	Float.parseFloat(humiPart);		//CONVERTING THE STRING TO FLOAT INORDER TO CHECK FOR THE CORRECT DATA TYPE
		return	true;
		}catch(Exception	e) {
			System.out.println("AT HUMI VALUE CATCH");
			log.error("HUMIDITY IS NOT IN CORRECT FORMAT");
			return	false;
		}
	}
}
