
package Pendu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Session de jeu pour un joueur 
 * @author Hadjer
 */
public class Session {
    public final static int nbQuestion = 10; 
    private Question qst[] = new Question[nbQuestion]; 
    public final static int MaxRate = 6; 
    private final static int minTaille = 6;
    /**
     * Constructeur de la session de jeu 
     * @throws FileNotFoundException 
     */
    public Session() throws FileNotFoundException{
			genererQuestions(); 
    }
	
    /**
     * Genere une serie de nbQuestions pour le debut de la session 
     * @throws FileNotFoundException 
     */
    public void genererQuestions() throws FileNotFoundException {
        int i = 0; 
	 while(i < nbQuestion)
         {
            String s = choose(new File("D:/TP/mots.poo"));
            String[] question = s.split(";");
            if (question[2].length() > minTaille ){
             qst[i] = (new Question(question)); 
             i++; 
            }
         }
    }

    /**
     * Selectionne une question au hazard 
     * @param f
     * @return
     * @throws FileNotFoundException 
     */
     public String choose(File f) throws FileNotFoundException
      {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for(Scanner sc = new Scanner(f); sc.hasNext(); )
        {
            ++n;
            String line = sc.nextLine();
            if(rand.nextInt(n) == 0)
                result = line;         
        }
	     return result;      
      }

     public Question getQuestion(int i){
        return qst[i]; 
    }
    
    
     /**
      * Choisit une question et commence à y repondre (question numero n)  
      * @param n 
      */
    
    public int getScore(){
        
        int som=0;
        for (int i =0;i<nbQuestion;i++)
        {
           som +=qst[i].getScore(); 
        }
        return som;
    }
    
    
}
