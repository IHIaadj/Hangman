
package Pendu;

import java.util.ArrayList;

/**
 *
 * @author Hadjer
 */
public class Question {
    private TypeQuestion type; 
    private int score =0; 
    private String question; 
    ArrayList<Case> reponse = new ArrayList<Case>(); 
    private boolean trouve; 
    private boolean malus;
    public final static int maxmalus = 5; // Le nb envoye ds le fichier clarification 
    private final static int nbMaxZero = 3; 
    
    /**
     * Constructeur 
     * @param question 
     */
    public Question(String[] question){
				
	switch (question[0]) {
				
		case "DEFINITION" : type = TypeQuestion.def; 
		 break; 
		case "ANTONYME" : type = TypeQuestion.anto; 
                 break; 
		case "SYNONYME" : type = TypeQuestion.syn; 
                 break; 
        }
     
		   System.out.println("Type = " + question[0]);
        System.out.println("Suestion = " + question[1]); 
        System.out.println("Reponse = " + question[2]);
        System.out.println("-----------------------");		
	this.question = question[1]; 
        malus = false;
        score = 0;
        trouve = true;
        this.reponse = genererCase(question[2]); 
				
    }
    public boolean getMalus()
    {
        return malus ;
    }
     public boolean getTrouv()
    {
        return trouve;
    }
    
     public void setTrouv(){
       boolean trouv = true; 
        for (Case c : reponse){
            if(c.getEchoue()) trouv = false; 
        }
        this.trouve = trouv; 
   }
  public TypeQuestion getType(){
      return type; 
  }
  
  public int getScore()
  {
      return score;
  }
  
  public String getQuestion(){
      return question; 
  }
  
  public ArrayList<Case> getReponse(){
      return reponse; 
  }
  public String Answer()
  {
      String str = "";
      for (int i=0;i<reponse.size();i++) {  str+=reponse.get(i).getLettre();}
      return str;
  }
	
    /**
     * Genere le type des cases de la reponse 
     * @param reponse
     * @return 
     */
    @SuppressWarnings("null")
    public ArrayList<Case> genererCase(String reponse){
        int k =0;
        ArrayList<Case> Rep = new ArrayList<Case>(); 
        int nbZero = 0; 
        System.out.println("Generer  = " + reponse + reponse.length());
        for (int i = 0 ; i <reponse.length() ; i++){
           int j =  (int) (Math.random()*(3)+1);
           if (nbZero < 3 ){
                switch(j){
                    case 1 : 
                        k++;
                        MultiChance c = new MultiChance(reponse.charAt(i));
                        Rep.add(c); 
                        break; 
                    case 2 : 
                        k++;
                        Proposition p = new Proposition(reponse.charAt(i)); 
                        Rep.add(p); 
                        nbZero++; 
                        break; 
                    case 3 : 
                        ZeroChance z = new ZeroChance(reponse.charAt(i)); 
                        Rep.add(z); 
                        nbZero++; 
                        break; 
                }
           }else { 
               k++;
                MultiChance c = new MultiChance(reponse.charAt(i));
                        Rep.add(c); 
           }
        }
        malus = k>maxmalus ;
        return Rep; 
			
}
    
    /**
     * Entame la reponse 
     */
                    
                        
   public int CalculScore(int s )
	{
	      score += s*type.getCoef();
              return score;}           
}
