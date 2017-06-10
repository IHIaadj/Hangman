/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendu;


/**
 *
 * @author Hadjer
 */
public class MultiChance extends Case implements Malus {
	
	
	private final int score =1 ;
	private final int nbchance = 2;
	private final int malus = -2;
        
        public MultiChance(char l){
		super(l); 
	}
        
        public int getNbChances ()
        {
            return nbchance;
        }
        
        public int calculscore()
                {
                    return score;
                }
	public boolean repondre(char c)
	{
        
        return (c==getLettre());
        }
        
        
	@Override
	public int calculMalus() {
		// TODO Auto-generated method stub
		return malus;
	}
	
}
