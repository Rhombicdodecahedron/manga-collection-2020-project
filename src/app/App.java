package app;

import app.helpers.JfxPopup;
import app.presentation.MainCtrl;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Application JavaFX 8 (vue principale).
 *
 * @author Stella Alexis
 */
public class App extends Application {

    private static final String TITLE = "Manga Collection";
    private static final String LOGO = "resources/images/emf-info.png";
    private static final String MAIN_FXML = "/app/presentation/MainView.fxml";
    private static final String ERROR_TITLE = "Erreur";
    private static final String ERROR_MSG = "App.start:\nProblème avec le fichier";

    /**
     * Méthode de démarrage d'une application JavaFX. C'est une méthode
     * originale de la classe "Application" qui doit être surchargée.
     *
     * @param stage l'estrade (là où se déroulent les scènes = la fenêtre)
     */
    @Override
    public void start(Stage stage) {

        // charger la vue principale
        FXMLLoader loader = null;
        Parent view = null;
        try {
            loader = new FXMLLoader(getClass().getResource(MAIN_FXML));
            view = loader.load();
        } catch (java.lang.IllegalStateException | IOException ex) {
            String errMsg = ERROR_MSG + " " + MAIN_FXML + ".\n\n" + ex.getMessage();
            JfxPopup.displayError(ERROR_TITLE, null, errMsg);
            System.exit(-1);
        }

        // récupérer une référence sur son contrôleur
        MainCtrl mainCtrl = loader.getController();

        // préparer la première scène
        Scene scene = new Scene(view);

        // modifier l'estrade pour la première scène
        stage.setScene(scene);

        // choisir un titre pour la fenêtre principale (pour la pièce de théâtre)
        stage.setTitle(TITLE);

        // rajouter une icône dans la barre de titre de la vue principale
        stage.getIcons().add(new Image(LOGO));

        // afficher cette première vue (tirer le rideau)
        stage.show();

        // ajout d'un écouteur pour contrôler la sortie de l'application.
        stage.setOnCloseRequest(e -> {

            // pour éviter que la fenêtre principale ne se ferme dans tous les cas
            e.consume();

            // lors d'une demande de sortie, laisser le travail à faire au contrôleur
            mainCtrl.quitter();
        });

    }

}
