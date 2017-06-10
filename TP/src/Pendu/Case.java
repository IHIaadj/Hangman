
package Pendu;

/**
 *
 * @author Hadjer
 */

abstract public class Case {
		private char lettre; 
                private boolean echoue = true;
                
		public Case(char l){
			lettre = l; 
		}
	
	private boolean traite; // si la case a été déja traité 
	public abstract boolean repondre(char c);
	public abstract int calculscore();
	public void setLettre(char c) {lettre = c;}
	public char getLettre()  {return lettre;}
	public boolean getTraite() {return traite;}
	public void setTraite() { traite = true ;}
        public void setEchoue(boolean echoue){this.echoue = echoue ; }  
        public boolean getEchoue() { return echoue;}
    	
}
