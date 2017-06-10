/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendu;

import static java.lang.Character.toLowerCase;

/**
 *
 * @author Hadjer
 */
public class Proposition extends Case {
    private char[] lettres; 
    
    public Proposition(char l){
		super(l); 
		lettres = prop(); 
	}
    
        private final int nbprops = 4 ; //Le nombre des proposistions 
	private final  int score = 2;
	private final int malus = -1;

	public char[] prop()
	{
		char [] lettres  = new char[nbprops];
                int i;
                int j = (int) (Math.random() *10 )%4;
                lettres[j] = super.getLettre();
		for (i=0;i<nbprops;i++){
			char let = toLowerCase((char)((90-65+1)*(Math.random())+65));  
                        while (let == this.getLettre()) 
                            let = toLowerCase((char)((90-65+1)*(Math.random())+65)); 
                       if (i !=j)  lettres[i] = let; 
                        
		}                
		return lettres ;
	}
        @Override
	public boolean repondre(char c)
	{
		setTraite();
		return (c==getLettre());
	}
	@Override
	public int calculscore()
	{
                 return score;
	}

        public int calculMalus()
        {
            return malus;
        }
	
        
        public char[] getProp(){
            return lettres; 
        }

}
