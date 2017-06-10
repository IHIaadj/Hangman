/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendu ;

/**
 *
 * @author Hadjer
 */

public enum TypeQuestion {
		def("Définition",3), 
		syn("Synonyme", 2), 
		anto("antonyme", 1); 
	
	private String name = "";
	private int coefficient;
	   
	  //Constructeur
	  TypeQuestion(String name, int coefficient){
	    this.name = name;
	    this.coefficient = coefficient;
	  }
	  
	  public int getCoef(){
		  return coefficient; 
	  }
	  
	  public String getName(){
		  return name; 
	  }
		
		
}
