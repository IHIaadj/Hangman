/**
 *
 * @author Hadjer
 */
 
package View;

import Pendu.Jeu;
import java.io.FileNotFoundException;
import javafx.application.Application;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;


public class FadeApp extends Application {
    
    public static final String SPLASH_IMAGE =
            "file:///C:/Users/PC.H.D.G/Desktop/TP/jeu.png";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;
    public static Jeu j = new Jeu(); 
    public static void main(String[] args){
        launch(); 
    }
    
    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Will find account for playing . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                "-fx-background-color: skyblue; " +
                "-fx-border-width:5; " +
                "-fx-border-color: " +
                    "linear-gradient(" +
                        "to bottom, " +
                        "darkblue, " +
                        "derive(darkblue, 50%)" +
                    ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        
        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                // Will be changed to the users 
                ObservableList<String> foundUsers =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> availableUsers =
                        FXCollections.observableArrayList(
                                "PLAY", "GO !! ", "HEY"
                        );

                updateMessage("Finding users. . .");
                for (int i = 0; i < availableUsers.size(); i++) {
                    Thread.sleep(400);
                    updateProgress(i + 1, availableUsers.size());
                    String nextUser = availableUsers.get(i);
                    foundUsers.add(nextUser);
                    updateMessage("Finding users . . . found " + nextUser);
                }
            
                j.ChargerJoueurs();
                Thread.sleep(400);
                updateMessage("All users found.");

                return foundUsers;
            }
        };

        showSplash(
                initStage,
                friendTask,
                () -> {
            try {
                showMainStage();
            } catch (IOException ex) {
                Logger.getLogger(FadeApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        new Thread(friendTask).start();
    }

    /**
     * Affiche la scene de connexion à partir d'un fichier FXML 
     * @throws IOException 
     */
    private void showMainStage() throws IOException {
        mainStage = new Stage(); 
        
        mainStage.setTitle("Connexion");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * Affiche initStage (Chargement du jeu) 
     * @param initStage
     * @param task
     * @param initCompletionHandler 
     */
    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } 
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    public interface InitCompletionHandler {
        void complete();
    }
}