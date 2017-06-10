package View; 

import Pendu.IllegalUserException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author Hadjer
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField userName; 
    
    @FXML
    private void handleButtonAction() throws IOException, IllegalUserException, Exception {
        
        System.out.println("You clicked me!");
 
        Pendu p; 
        
        if(FadeApp.j.Connexion(userName.getText())){
             
           
         Stage stage = (Stage) label.getScene().getWindow();
        
         stage.close();
        p = new Pendu(FadeApp.j, FadeApp.j.getJoueur(userName.getText()),(Stage)label.getScene().getWindow());
        }else {
            label.setText("Aucun utilisateur avec ce compte ! "); 
        }
 
  
        
    }
    
    @FXML
    private void handleSignUpAction() {
        if (FadeApp.j.verifPseudo(userName.getText()))
            FadeApp.j.inscription(userName.getText());   
        else 
            label.setText("Pseudo incorrect ! "); 
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
