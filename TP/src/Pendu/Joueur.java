/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Hadjer
 */
public class Joueur implements Serializable  {
    
    private String pseudo; 
    private ArrayList<Integer> scores = new ArrayList<Integer>(); 
    private transient Session Encours; 
    
    public Joueur(String pseudo){
        this.pseudo = pseudo; 
    }
    
    public String getPseudo(){
        return this.pseudo; 
    }


    /**
     * Ferme la session et enregistre le score 
     */
    
    public ArrayList<Integer> getScores()
    {
        return scores;
    }
    public void FermerSession(){
           System.out.println("Voulez vous enregistrer votre score ? O/N");
           Scanner sc = new Scanner(System.in); 
           String c = sc.next(); 
           if (c == "O"){
               scores.add(Encours.getScore()); 
           }
     }
    public void  addSession()
    {
        try { Encours = new Session(); }
        catch (FileNotFoundException e ){ System.out.println("verifier le chemin ");}
    }
        
    
    /**
     * Calcul du meilleur score 
     */
    public int CalculMS(){
        int Max = -2000; 
          for(int i = 0 ; i < scores.size(); i++)
              if (scores.get(i) > Max) Max = scores.get(i); 
          if (Max == -2000) Max = 0;
        return Max; 
    }

    void ouvrirSession() throws FileNotFoundException {
        this.Encours = new Session(); 
    }
    
   public void enregistrer(int score)
    {
        scores.add(score);
        /*try {
         FileOutputStream fileOut =
         new FileOutputStream("joueurs.dat");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(this);
         out.close();
         fileOut.close();
      
      }catch(IOException i) {
         System.out.println("Score non enregistré ");
      }*/ 
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.pseudo);
        return hash;
    }
    
    @Override
    public boolean equals(Object o ){
        if (this.pseudo.equals(((Joueur)o).pseudo))
            return true; 
        else 
            return false; 
    }
    
    public Session getSession(){
        return Encours; 
    }
    
   
    
}
