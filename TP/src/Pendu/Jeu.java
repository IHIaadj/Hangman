package Pendu;

import java.io.*;
import static java.lang.Character.isLetter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe englobante pour le jeu 
 * @author Hadjer
 */
public class Jeu {
    private HashSet<Joueur> joueurs = new HashSet<Joueur>(); // Liste de tous les joueurs 
    
    /**
     * Creation d'un nouveau joueur 
     * @param pseudo 
     */
    public void inscription(String pseudo){
        if (verifPseudo(pseudo)){
            Joueur j = new Joueur(pseudo); 
            joueurs.add(j);  
            this.SauvegarderJoueurs();  
        }
    }
    
    /**
     * 
     * @param pseudo
     * @return 
     */
    public boolean verifPseudo(String pseudo){
      return isLetter(pseudo.charAt(0));   
    }
    
    /**
     * Connexion d'un joueur et lancement de sa session 
     * @param pseudo
     * @throws IllegalUserException 
     */
    public boolean Connexion(String pseudo) throws IllegalUserException, FileNotFoundException{
        Iterator iter = joueurs.iterator(); 
        boolean trouv = false; 
        while(iter.hasNext() && !trouv){
            Joueur j = ((Joueur) iter.next()); 
            if(pseudo.equals(j.getPseudo())) {
                System.out.println("Bienvenu"); 
                j.ouvrirSession(); 
                trouv = true; 
            }  
            
        }
        return trouv; 
    }
    
    /**
     * Charger une liste de joueurs à partir d'un fichier 
     * @throws FileNotFoundException 
     */
    public void ChargerJoueurs() {
        	ObjectInputStream in;
                Joueur j = null; 
            
              
                    try {
                      FileInputStream fileIn = new FileInputStream("joueurs.dat");
                      in = new ObjectInputStream(fileIn);
                      Joueur p = (Joueur) in.readObject(); 
                      while (p != null){
                            
                                  System.out.println(p.getPseudo());
                                  j= p; 
                                  joueurs.add(j); 
                                  p = (Joueur) in.readObject(); 
                            }
                      in.close();
                      fileIn.close();
                 
                  } catch (ClassNotFoundException c) {
                      System.out.println("Joueur class not found");
                      c.printStackTrace();
                      return;
                  } catch (FileNotFoundException e){
                      System.out.println("File not found");
                  }catch (IOException ex) {
            Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
        }

	        System.out.println(joueurs);   
    }
    
    /**
     * Sauvegarde les joueurs lors de la cloture du jeu 
     */
    public void SauvegarderJoueurs(){
       ObjectOutputStream out = null;
            
                try {
                    out = new ObjectOutputStream(
                    new BufferedOutputStream(
                    new FileOutputStream(
                    new File("joueurs.dat"))));
                   
                    Iterator iter = joueurs.iterator(); 
                    while(iter.hasNext()){
                        Joueur j  = (Joueur) iter.next(); 
                        out.writeObject(j) ;
                    }
                       
                    out.close();
                } catch (FileNotFoundException e) {
                     e.printStackTrace();
                } catch (IOException e) {
                      e.printStackTrace();
                }       
    }
    
    public Joueur getJoueur(String pseudo){
        Iterator iter = joueurs.iterator(); 
  
        while(iter.hasNext() ){
            Joueur j = ((Joueur) iter.next()); 
            if(pseudo.equals(j.getPseudo())) {
                return j; 
            }  
            
        }
        return null; 
    }
    
    
}
