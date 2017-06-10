
package View;

import Pendu.*;
import java.io.File;
import java.util.Optional;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main Class for the game play 
 * @author Hadjer
 */
public class Pendu extends Application {
    private Joueur j; 
    private Jeu jeu; 
    private int qst ; 
    private Stage stage; 
    private int nbrat;
    private int k = 0; 
    
    
    public Pendu(Jeu jeu, Joueur j , Stage stage) throws Exception{
        this.jeu = jeu; 
        qst = 0;
        nbrat = 0;
        this.j = j;
        this.stage = stage; 
        start(stage);
    }
    
    public Joueur getJoueur ()
    {
        return j;
    }
    @Override
    public void start(Stage stage) throws Exception {
            Scene scene = createScene(); 
            stage.setScene(scene);
            stage.setTitle("Pendu");
            stage.show();
    }
    
    public Scene createScene() {
            k=0;    
            BorderPane border = new BorderPane();
            HBox hbox;
            hbox = Header();
            border.setTop(hbox);
            border.setLeft(Gauche());
          
            VBox pendu = Pendu();
            border.setCenter(pendu);
           

            border.setRight(Image(nbrat));
            Scene scene = new Scene(border,1400,700);
            return scene; 
    }
    
    public HBox Header() {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setPadding(new Insets(15, 12, 15, 12));
            hbox.setSpacing(10);
            Text title = new Text("Pendu :)");
            title.setStyle("-fx-font: 100px Tahoma;\n" +
                          "-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%);\n" +
                          "-fx-stroke: black;\n" +
                          "-fx-stroke-width: 1;"  ); 
           
            hbox.getChildren().add(title);
            hbox.setStyle("-fx-background-color: #336699;");
            Button help = new Button("Help");
            help.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pendu");
        alert.setHeaderText("Help :");
        alert.setContentText("Ce jeu a été developpé dans le cadre d'un projet de POO en 2CPI."
                + "\n  Pour les cases rouges , il faut trouver la premiere reponse d'un seul coup. /Pas de malus  "
                + "\n Pour les cases bleues , vous avez 03 chances . / Malus = -2 "
                + "\n Pour les cases jaunes , il faut choisir parmi les 04 propositions ./Malus = -1"
                + "\n Une définition a un coefficient de 3. "
                + "\n  Un synonyme  a un coefficient de 2. "
                + "\n Un antonyme  a un coefficient de 1."
                + "\n Si vous rater plus de 06 questions , vous perderez la session . "
                );
        alert.showAndWait();
    }
    });
            
            hbox.getChildren().add(help);
            return hbox;
    }
   
    public VBox Gauche() {
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
    String cssLayout = "-fx-border-color: black;\n" +
                   "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 3;\n";


    vbox.setStyle(cssLayout);
    ///////// Pseudo //////////////////
    HBox Pseudo = new HBox(); 
    Text pseudo = new Text("Pseudo : ");
    pseudo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    Pseudo.getChildren().add(pseudo);
    Text pseudoText = new Text(j.getPseudo());
    pseudoText.setFont(Font.font("Arial",  14));
    Pseudo.getChildren().add(pseudoText);
     vbox.getChildren().add(Pseudo);
    
     //////////////////// MS ///////////////////
    HBox MS = new HBox();
    Text title = new Text("Meilleur Score : ");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    MS.getChildren().add(title);
    Label score = new Label(String.valueOf(j.CalculMS())); 
    score.setMinWidth(80);
    score.setFont(Font.font("Arial",  14));
    MS.getChildren().add(score);
    vbox.getChildren().add(MS);

    /////////////////// Actual score ////////////
     HBox AS = new HBox();
    Text title2 = new Text("Score actuel :  ");
    title2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    AS.getChildren().add(title2);
    Label ascore = new Label(String.valueOf(j.getSession().getScore())); 
    score.setMinWidth(80);
    ascore.setFont(Font.font("Arial",  14));
    AS.getChildren().add(ascore);    
    vbox.getChildren().add(AS);

    ///////////// Questions ///////////////////// 
    for (int i = 0 ; i <10 ; i++){
        HBox questions = new HBox(); 
        
        Text question; 
        if (i == qst)
             question = new Text("*Question" + (i+1)); 
        else 
            question = new Text("Question" + (i+1)); 
        
        question.setFont(Font.font("Arial", FontWeight.BOLD, 14));
           questions.getChildren().add(question); 
        Button correct = new Button(); 
        correct.setDisable(true); 
        j.getSession().getQuestion(i).setTrouv();  
       
        if (i < qst)
            if (j.getSession().getQuestion(i).getTrouv())
                correct.setStyle("-fx-background-color : green; "); 
            else 
                 correct.setStyle("-fx-background-color : red; "); 
        
        if (qst == j.getSession().nbQuestion - 1 ) 
             if (j.getSession().getQuestion(j.getSession().nbQuestion -1).getTrouv())
                correct.setStyle("-fx-background-color : green; "); 
            else 
                 correct.setStyle("-fx-background-color : red; "); 
        
     
        
      questions.getChildren().add(correct); 
        vbox.getChildren().add(questions); 
        
    }
    ////////////SCORES//////////////////////
    Button scores = new Button("Scores");
    scores.setOnMouseClicked(new EventHandler<MouseEvent>() {
        public void handle(MouseEvent me) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Pendu");
        alert.setHeaderText("Vos scores : ");
        ArrayList<String> s = new ArrayList<String>();
        for (int i=0;i<j.getScores().size();i++) { s.add(j.getScores().get(i).toString());}
        alert.setContentText(s.toString());
        alert.showAndWait();
    }
    });
            vbox.getChildren().add(scores);

    return vbox;
}
    
    public VBox Pendu() {
    VBox vbox = new VBox();

    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);
    String cssLayout = "-fx-border-color: black;\n" +
                   "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 3;\n";


    vbox.setStyle(cssLayout);
    vbox.getChildren().add(Question(qst)); 
    vbox.getChildren().add(Cases(j.getSession().getQuestion(qst))); 
    
    return vbox;
}
    
    public VBox Question(int i){
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER); 
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        String cssLayout = "-fx-border-color: black;\n" +
                         "-fx-border-insets: 5;\n" +
                         "-fx-border-width: 3;\n";
        vbox.setStyle(cssLayout);
        Text typeQuestion = new Text(j.getSession().getQuestion(i).getType().getName()); 
        typeQuestion.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(typeQuestion);
        
        Text question = new Text(j.getSession().getQuestion(i).getQuestion());
        question.setFont(Font.font("Arial",  14));
        vbox.getChildren().add(question);
        
        return vbox; 

    }
    
    
    
    public Alert GameOver () 
    {
        
        qst = 0;
        nbrat = 0;
        Alert dBox = new Alert(AlertType.CONFIRMATION);
        dBox.setTitle("Pendu");
        dBox.setContentText(" Vous voulez ");
        ButtonType btnS = new ButtonType("Enregistrer et Rejouer ");
        ButtonType btnM = new ButtonType("Enregistrer et Quitter");
        ButtonType btnB = new ButtonType("Rejouer ");
        ButtonType btnC = new ButtonType("Quitter");
        dBox.getButtonTypes().setAll(btnS, btnM, btnB, btnC);
        Optional<ButtonType> choice = dBox.showAndWait();
         if (choice.get() == btnS) {
        j.enregistrer(j.getSession().getScore());
        jeu.SauvegarderJoueurs();
        try { j.addSession();  stage.close(); start(stage);
            }
        catch (Exception e) { System.out.print("Pas de nouvelle session ");}
         }
        else if (choice.get() == btnM) {
          j.enregistrer(j.getSession().getScore());
          jeu.SauvegarderJoueurs();
          stage.close();
          }
        else if (choice.get() == btnB) {
            try { j.addSession();  stage.close();  start(stage);  }
        catch (Exception e) { System.out.print("Pas de nouvelle session ");}
         }
        else {
            
            stage.close();
        }
         dBox.close();
         
        
return dBox;
    }
    
    
    public void  Mottrouve(Question q,int k)
    {
        Alert dialog = new Alert(AlertType.INFORMATION);
        if (q.getReponse().size() == k ) 
            if (qst == j.getSession().nbQuestion && nbrat<j.getSession().MaxRate)     {  Gangnant(q);}
            else { dialog.setContentText("Bravo,c'est le bon mot  "); dialog.showAndWait();
        qst++;
        stage.setScene(createScene());  } 
    }
    
    
    public void  Gangnant(Question q )
    {
        
        Alert dialog = new Alert(AlertType.INFORMATION);
                     dialog.setTitle("Pendu ");
                      File file = new File("C:/Users/PC.H.D.G/Desktop/TP/pendu.jpg");
            Image image = new Image(file.toURI().toString());
            ImageView pendu = new ImageView(image);
                    dialog.setContentText("Gagné !!!!! ");
                 
                     dialog.setGraphic(pendu);
                     dialog.showAndWait();
                                
    }
    
    public HBox Cases(Question q){
        ArrayList<Case> reponse = q.getReponse();
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER); 
         hbox.setPadding(new Insets(15, 12, 15, 12));
            hbox.setSpacing(10);
        for (Case c : reponse){
            if (c instanceof MultiChance) {
                TextField lettre = new TextField ();
                lettre.setMaxWidth(50);
                lettre.setStyle("-fx-background-color : blue;");
                hbox.getChildren().add(lettre);
                lettre.textProperty().addListener(new ChangeListener<String>() {  
                boolean trouv = false;    int l = 0;         boolean premier = true ;

                 @Override
                 public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
                    if (lettre.getText().length() > 1) {
                    String s = lettre.getText().substring(0, 1);
                    lettre.setText(s);
                    }
                    if(oldValue != newValue) // si il avait repondu 
                    {
                        if (l< ((MultiChance)c).getNbChances() && !trouv) 
                        {     
                                for (int j = 1 ; j < hbox.getChildren().size()+1; j++){
                                   if ( lettre != hbox.getChildren().get(j-1) )
                                        hbox.getChildren().get(j-1).setDisable(true);
                               }
                         trouv = c.repondre(lettre.getText().charAt(0));
                         lettre.clear();
                         l++;
                    }
                        else{
                      
                        c.setTraite();
                        if (trouv){
                            c.setEchoue(false);
                            lettre.setText(c.getLettre()+"");
                            lettre.setEditable(false);
                         if (premier) { q.CalculScore(c.calculscore());  k ++; premier = false ;}
                          int j = 1;
                              while (  j < hbox.getChildren().size()+1 ){
                                   if ( lettre != hbox.getChildren().get(j-1))
                                        hbox.getChildren().get(j-1).setDisable(false);
                                  j++;
                               } 
                       lettre.setStyle("-fx-background-color : green;");
                       Mottrouve(q,k);
                   }
                        else
                          {
                    if (q.getMalus()) q.CalculScore(((MultiChance) c).calculMalus());
                       lettre.setDisable(true);  
                       nbrat++;
                       if (nbrat== j.getSession().MaxRate )    GameOver(); 
                       else    Questionrate(q);       
                   }  
                    }}
                 }
                 
                 });
                            
           
        } 
            else if (c instanceof Proposition){
                  ComboBox<Character> lettre = new ComboBox<Character>();
                  char[] prop = ((Proposition) c).getProp(); 
                  //A modifier 
                  lettre.getItems().addAll(
                            prop[0],
                            prop[1],
                            prop[2],
                            prop[3]
                        );
                  lettre.setStyle("-fx-background-color : yellow;");
                  hbox.getChildren().add(lettre);
                  lettre.getSelectionModel().selectedItemProperty().addListener(new   ChangeListener<Character>() {
                 @Override
                public void changed(ObservableValue<? extends Character> arg0, final Character  arg1, final Character arg2) {
                 if (arg2 != null) {
                      lettre.setEditable(false);
                      if (c.repondre(arg2)) {  // si la reponse est bonne
                            c.setEchoue(false);
                        k++;
                          lettre.setStyle("-fx-background-color : green; "); 
                        q.CalculScore(c.calculscore());
                         Mottrouve(q,k);
                                               }
                        else  // mauvaise reponse 
                        { if (q.getMalus())  q.CalculScore(((Proposition) c).calculMalus());
                          lettre.setDisable(true); 
                        nbrat++;
                        if (nbrat==j.getSession().MaxRate)    GameOver(); 
                        else    Questionrate(q);                     
                        } 
                    
            }
                }}
);}
            
            else if (c instanceof ZeroChance){
                  TextField lettre = new TextField ();
                  lettre.setMaxWidth(50);
                  lettre.setStyle("-fx-background-color : red;");
                  hbox.getChildren().add(lettre); 
                  lettre.textProperty().addListener(new ChangeListener<String>() {
                 @Override
                 public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
                    if (lettre.getText().length() > 1) {
                    String s = lettre.getText().substring(0, 1);
                    lettre.setText(s);
                    }
                    if(oldValue != newValue) // si il avait repondu 
                    { 
                    lettre.setEditable(false);
                     if (c.repondre(lettre.getText().charAt(0))) {  // si la reponse est bonne 
                           c.setEchoue(false);
                       k++;
                         lettre.setStyle("-fx-background-color : green; "); 
                        j.getSession().getQuestion(qst).CalculScore(c.calculscore());
                         Mottrouve(q,k);
                        }
                        else  // mauvaise reponse 
                        {lettre.setDisable(true);  
                        nbrat++;
                        if (nbrat==j.getSession().MaxRate)    GameOver(); 
                        else    Questionrate(q);                     
                        } 
                    }}
                });
            }
    
        }
        return hbox; 
    }
   
    public void Questionrate(Question q )
    {
                     Alert dialog = new Alert(AlertType.INFORMATION);
                     dialog.setTitle("Mot raté ");
                     dialog.setContentText("Vous avez raté le mot , la bonne réponse était "+q.Answer());
                     dialog.showAndWait();
                     qst++;
                     if (qst == j.getSession().nbQuestion ) {  Gangnant(q); }
                     
                     else   stage.setScene(createScene());
                     
                     
                     
                     
                   
    }
    
    public VBox Image(int i) {
        
            VBox hbox = new VBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setPadding(new Insets(15, 12, 15, 12));
            hbox.setSpacing(10);
            System.out.println("image   "+i);
            
            File file = new File("C:/Users/PC.H.D.G/Desktop/TP/image"+i+".png");
            Image image = new Image(file.toURI().toString());
            ImageView pendu = new ImageView(image);

            
           
            pendu.setFitWidth(300);
            Button close = new Button(); 
            close.setText("Quitter le jeu"); 
            close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                GameOver();
    }
});
            hbox.getChildren().add(close);
            hbox.getChildren().add(pendu); 
            return hbox;
    }    
    
}
   
 
    

