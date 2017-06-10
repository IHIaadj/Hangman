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
public class ZeroChance extends Case  {
	        private final int score = 3 ;

    public ZeroChance(char l) {
        super(l);
    }
    public boolean repondre(char c)
	{
		setTraite();
		return (c==getLettre());
	}
    public int calculscore()
	{
    return score;
	}
	}