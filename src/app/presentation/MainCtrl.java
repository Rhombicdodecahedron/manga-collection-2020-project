package app.presentation;

import app.beans.Genre;
import app.beans.Manga;
import app.beans.Tome;
import app.helpers.JfxPopup;
import app.workers.Constantes;
import app.workers.Worker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import app.workers.WorkerItf;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Cette classe renferme l'implémentation Contrôleur de la vue principale.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class MainCtrl implements Initializable {

    @FXML
    public BorderPane bPane;
    @FXML
    private GridPane gridManga;
    @FXML
    private TextField titre;
    @FXML
    private TextField titre_original;
    @FXML
    private CheckBox enCours;
    @FXML
    private TextField date_sortie;
    @FXML
    private TextArea synopsis;
    @FXML
    private ComboBox<Genre> liste_genre;
    @FXML
    private TextField nouveau_genre;
    @FXML
    private TextField auteur_nom;
    @FXML
    private TextField auteur_prenom;
    @FXML
    private TextField dessinateur_nom;
    @FXML
    private TextField dessinateur_prenom;
    @FXML
    private ScrollPane tousLesMangas;
    @FXML
    private ComboBox<Genre> liste_genres;
    @FXML
    private TabPane tab;
    @FXML
    private TextField info_titre_original;
    @FXML
    private TextField info_annee_sortie;
    @FXML
    private TextField info_genre;
    @FXML
    private CheckBox info_enCours;
    @FXML
    private Label info_titre_manga;
    @FXML
    private ImageView info_image_manga;
    @FXML
    private TextField info_dessinateur_nom;
    @FXML
    private TextField info_auteur_nom;
    @FXML
    private TextField info_dessinateur_prenom;
    @FXML
    private TextField info_auteur_prenom;
    @FXML
    private TextArea info_synopsis;

    @FXML
    private TextField numero_tome;
    @FXML
    private GridPane gridTome;

    @FXML
    private TabPane tab_tome;
    @FXML
    private TextField barre_recherche;

    private WorkerItf wrk;

    private GridPane tousLesMangaGrid;
    @FXML
    private Label nbr_mangas;
    @FXML
    private Label nbr_tomes;
    @FXML
    private TextField txt_lien_image_manga;
    @FXML
    private TextField txt_lien_image_tome;

    /**
     * Cette méthode permet d'initialiser le controller de l'application.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wrk = Worker.getInstance();
        tousLesMangaGrid = new GridPane();
        actualiseTout();
    }

    /**
     * Cette méthode permet de quitter l'application. Elle demande une
     * confirmation lorsque l'on cliquer sur la croix.
     */
    public void quitter() {
        if (JfxPopup.askConfirmation("Confirmation", null, "Voulez-vous vraiment quitter ?")) {
            // obligatoire pour bien terminer une application JavaFX
            Platform.exit();
        }
    }

    /**
     * Cette méthode permet lorsque nous cliquons sur un genre de la liste, il y
     * a un trie de tous les mangas ayant ce genre.
     *
     * @param event représente l'action lorsque l'on clique sur un genre.
     */
    @FXML
    private void afficheMangaGenre(ActionEvent event) {
        afficheMangaGenre(liste_genres.getSelectionModel().getSelectedItem());
    }

    /**
     * Cette méthode permer lorsque l'on cliquer sur le bouton, faire apparaitre
     * une fenêtre pour pouvoir choisir l'image que l'on souhaite avec comme
     * image de couverture pour le manga.
     *
     * @param event représente l'action lorsque l'on clique sur le bouton.
     */
    /*
    private void browseImage(ActionEvent event) {
        lienImage = wrk.browseImageCouvertureManga();

    }
     */
    /**
     * Cette méthode permet d'actualiser tous les mangas et de donner le nombre
     * de mangas et de tomes que l'utilisateur possède. Tous d'abord elle
     * desérialise le fichier mangas.ser grâce à la méthode
     * "mangaDeserialisation". Ensuite, elle actualise les mangas dans l'onglet
     * "Mangas recemment ajoutés" et l'onglet "Tous les mangas". Elle permet
     * aussi d'actualiser la liste des genres.
     */
    public void actualiseTout() {
        try {
            wrk.mangaDeserialisation();
            rempliListeGenre();
            if (wrk.sortMangas() != null && !wrk.sortMangas().isEmpty()) {
                nbr_mangas.setText("Nbr de mangas: " + wrk.sortMangas().size());
                int nbrTomes = 0;
                for (Map.Entry<String, Manga> entry : wrk.sortMangas().entrySet()) {
                    if (entry.getValue().getTomes() != null) {
                        nbrTomes += entry.getValue().getTomes().size();
                    }
                }
                nbr_tomes.setText("Nbr de tomes: " + nbrTomes);
                afficheMangaGenre(new Genre("Tous les genres"));
                refreshDerniersMangasAjoutes();
            }
        } catch (NullPointerException e) {

        } catch (Exception e) {

        }

    }

    /**
     * Cette méthode permet lorsque nous cliquons sur le bouton "créer", de
     * pouvoir créer un nouveau mangas avec les paramètres qui ont été rempli.
     * Cette méthode va, si elle s'execute correctement, enregistrer l'action
     * dans l'historique, serialiser et deserialiser pour pouvoir ensuite
     * actualiser "les dernier mangas ajouter" et l'onglet "tous les mangas". Si
     * cette méthode rencontre une erreur, elle va afficher une popup d'erreur
     * sinon si tout fonctionne correctement, elle va afficher une popup de
     * succès.
     *
     * @param event représente l'action lorsque l'on clique sur le bouton
     * "créer".
     */
    @FXML
    private void creerNouveauManga(ActionEvent event) {
        if (JfxPopup.askConfirmation("Confirmation", null, "Voulez-vous vraiment ajouter un manga ?")) {
            if (!titre.getText().equals("") && !titre_original.getText().equals("") && !date_sortie.getText().equals("") && !synopsis.getText().equals("") && !auteur_nom.getText().equals("") && !dessinateur_nom.getText().equals("")) {
                if ((!liste_genre.getSelectionModel().getSelectedItem().toString().equals("") && nouveau_genre.getText().equals("")) || (liste_genre.getSelectionModel().getSelectedItem().toString().equals("Nouveau genre") && !nouveau_genre.getText().equals("")) && txt_lien_image_manga != null) {                 
                   try {
                        String genre = liste_genre.getSelectionModel().getSelectedItem().toString();
                        if (liste_genre.getSelectionModel().getSelectedItem().toString().equals("Nouveau genre") && !nouveau_genre.equals("")) {
                            genre = nouveau_genre.getText();
                            if (!wrk.ajouteGenre(genre)) {
                                JfxPopup.displayError("Erreur", null, "Erreur lors de l'ajout  !");
                            }
                        }
                        Boolean ok = wrk.ajouteManga(titre.getText(), titre_original.getText(), enCours.isSelected(), Integer.parseInt(date_sortie.getText()), synopsis.getText(), genre, auteur_nom.getText(), auteur_prenom.getText(), dessinateur_prenom.getText(), dessinateur_nom.getText(), txt_lien_image_manga.getText());
                        if (ok) {
                            if (wrk.mangaSerialise()) {
                                if (!wrk.ajouteHistorique(wrk.sortMangas(titre.getText()), "Le manga " + titre.getText() + " a correctement été ajouté !", "Ajout Manga")) {
                                    JfxPopup.displayError("Erreur", null, "Erreur lors de l'ajout de la modification dans l'historique !");
                                }
                            
                                actualiseTout();
                                actualiseChampsAjoutManga();
                                JfxPopup.displayInformation("Information", null, "Le manga " + titre.getText() + " à correctement été ajouté !");
                            }
                        } else {
                            JfxPopup.displayError("Erreur", null, "Le manga existe déjà ou une erreur est survenue lors de l'ajout du manga !");
                        }
                    } catch (NumberFormatException e) {
                        JfxPopup.displayError("Erreur", null, "Veuillez mettre un nombre et non du texte pour l'année de sortie !");
                    } catch (IllegalArgumentException e) {
                        actualiseTout();
                        actualiseChampsAjoutManga();
                    }
                } else {
                    JfxPopup.displayError("Erreur", null, "Erreur lors de l'ajout du manga ! Veuillez choisir soit un genre de manga dans la liste ou écrire le nouveau genre mais pas les deux !");
                }
            } else {
                JfxPopup.displayError("Erreur", null, "Veuillez remplir tous les champs !");
            }
        }
    }

    /**
     * Cette méthode permet, lorsque nous cliquons sur le bouton "modifier",
     * enregistrer les modifications effectuées au manga que nous regardons.
     * Cette méthode va, si elle s'execute correctement, enregistrer l'action
     * dans l'historique, serialiser et deserialiser pour pouvoir ensuite
     * actualiser "les dernier mangas ajouter" et l'onglet "tous les mangas". Si
     * cette méthode rencontre une erreur, elle va afficher une popup d'erreur
     * sinon si tout fonctionne correctement, elle va afficher une popup de
     * succès.
     *
     * @param event représente l'action lorsque l'on clique sur le bouton
     * "modifier".
     */
    @FXML
    private void actionEnregistreModification(ActionEvent event) {
        if (JfxPopup.askConfirmation("Confirmation", null, "Voulez-vous vraiment modifier le manga " + info_titre_manga.getText() + " ?")) {
            try {
                if (wrk.modifieManga(info_titre_manga.getText(), info_titre_original.getText(), info_enCours.isSelected(), Integer.parseInt(info_annee_sortie.getText()), info_synopsis.getText(), info_genre.getText(), info_auteur_nom.getText(), info_auteur_prenom.getText(), info_dessinateur_prenom.getText(), info_dessinateur_nom.getText())) {
                    actualiseInformationsManga(wrk.sortMangas(info_titre_manga.getText()));
                    if (!wrk.ajouteHistorique(wrk.sortMangas(info_titre_manga.getText()), "Le manga " + info_titre_manga.getText() + " a correctement été modifié !", "Modification Manga")) {
                        JfxPopup.displayError("Erreur", null, "Erreur lors de l'ajout de la modification dans l'historique !");
                    }
                    if (wrk.mangaSerialise()) {
                        JfxPopup.displayInformation("Information", null, "Le manga " + info_titre_manga.getText() + " a correctement été modifié !");
                    }
                }
            } catch (NumberFormatException e) {
                JfxPopup.displayError("Erreur", null, "Veuillez mettre un nombre et non du texte pour l'année de sortie !");
                info_annee_sortie.clear();
            } catch (Exception e) {
                JfxPopup.displayError("Erreur", null, "Erreur lors de la modification du manga !");
            }
        }
    }

    /**
     * Cette méthode permet, lorsque nous cliquons sur le bouton "supprimer", de
     * supprimer le manga que nous regardons. Cette méthode va, si elle
     * s'execute correctement, enregistrer l'action dans l'historique,
     * serialiser et deserialiser pour pouvoir ensuite actualiser "les dernier
     * mangas ajouter" et l'onglet "tous les mangas". Si cette méthode rencontre
     * une erreur, elle va afficher une popup d'erreur sinon si tout fonctionne
     * correctement, elle va afficher une popup de succès.
     *
     * @param event représente l'action lorsque l'on clique sur le bouton
     * "supprimer".
     */
    @FXML
    private void actionSupprimeManga(ActionEvent event) {
        if (JfxPopup.askConfirmation("Suppression", null, "Voulez-vous vraiment supprimer ce manga ?")) {
            try {
                Manga manga = wrk.sortMangas(info_titre_manga.getText());
                if (wrk.supprimeManga(wrk.sortMangas(manga.getTitre()))) {
                    if (wrk.mangaSerialise()) {
                        if (!wrk.ajouteHistorique(manga, "Le manga " + manga.getTitre() + " a correctement été supprimé", "Suppression Manga")) {
                            JfxPopup.displayError("Erreur", null, "Erreur lors de l'ajout de la suppression dans l'historique !");
                        }
                        actualiseTout();
                        actualiseInformationsManga(null);
                        refreshTome(null);
                        JfxPopup.displayInformation("Information", null, "Le manga  a correctement été supprimé !");
                    }
                } else {
                    JfxPopup.displayError("Erreur", null, "Le manga n'a pas pu être correctement supprimé !");
                }
            } catch (NullPointerException e) {
                JfxPopup.displayError("Erreur", null, "Erreur lors lu chargement de la liste de mangas !");

            } catch (Exception e) {
                JfxPopup.displayError("Erreur", null, "Erreur lors de la suppression du manga !");
            }
        }
    }

    /**
     * Cette méthode permet, lorsque nous cliquons sur le bouton "ajouter" dans
     * l'onglet "Ajouter un tome", ajouter un nouveau tome au manga que nous
     * regardons. Cette méthode va, si elle s'execute correctement, enregistrer
     * l'action dans l'historique, serialiser et deserialiser pour pouvoir
     * ensuite actualiser "les dernier mangas ajouter" et l'onglet "tous les
     * mangas". Si cette méthode rencontre une erreur, elle va afficher une
     * popup d'erreur sinon si tout fonctionne correctement, elle va afficher
     * une popup de succès.
     *
     * @param event représente l'action lorsque l'on clique sur le bouton
     * "ajouter".
     */
    @FXML
    private void ajouteTome(ActionEvent event) {
        if (JfxPopup.askConfirmation("Confirmation", null, "Voulez-vous vraiment ajouter un nouveau tome ?")) {
            try {
                if (wrk.ajouteTome(wrk.sortMangas(info_titre_manga.getText()), new Tome(Integer.parseInt(numero_tome.getText()), txt_lien_image_tome.getText()))) {
                    if (wrk.ajouteHistorique(wrk.sortMangas(info_titre_manga.getText()), "Le tome numéro " + numero_tome.getText() + " du manga " + wrk.sortMangas(info_titre_manga.getText()).getTitre() + " a correctement été ajouté", "Ajout Tome")) {
                        if (wrk.mangaSerialise()) {
                            tab_tome.getSelectionModel().select(0);
                            numero_tome.clear();
                            txt_lien_image_tome.clear();
                            actualiseTout();
                            actualiseInformationsManga(wrk.sortMangas(info_titre_manga.getText()));
                            refreshTome(wrk.sortMangas(info_titre_manga.getText()));
                            JfxPopup.displayInformation("Information", null, "Le tome a correctement été ajouté !");
                        }
                    } else {
                        JfxPopup.displayError("Erreur", null, "Erreur lors de l'ajout dans l'historique ");
                    }

                } else {
                    JfxPopup.displayError("Erreur", null, "Le tome n'a pas pu être correctement ajouté !");
                }
            } catch (NumberFormatException e) {
                JfxPopup.displayError("Erreur", null, "Erreur lors de l'ajout du tome !");
            }
        }
    }

    /**
     * Cette méthode permet d'actualiser tous les champs dans l'onglet "Ajout de
     * manga" lorsque nous cliquons sur "créer" et que l'ajout s'effectue
     * correctement. Plus précisement, elle va vider tous les champs pour
     * remettre à zéro l'onglet et ensuite va nous rediriger vers l'onglet
     * principal (derniers mangas ajoutés).
     */
    public void actualiseChampsAjoutManga() {
        tab.getSelectionModel().select(0);
        txt_lien_image_manga.clear();
        titre.clear();
        titre_original.clear();
        enCours.selectedProperty().setValue(false);
        date_sortie.clear();
        synopsis.clear();
        nouveau_genre.clear();
        auteur_nom.clear();
        auteur_prenom.clear();
        dessinateur_prenom.clear();
        dessinateur_nom.clear();

    }

    /**
     * Cette méthode permet d'actualiser tous les champs de l'onglet
     * "Informations manga" par les informations du manga reçu en paramètre.
     * Quand elle reçoit null en paramètre, elle doit vider tous les champs dans
     * l'onglet "Informations manga"
     *
     *
     * @param manga représente l'objet manga
     */
    public void actualiseInformationsManga(Manga manga) {
        String titreManga = "Informations manga", titreMangaOriginal = "", anneeSortie = "", genre = "", nomDessinateur = "", prenomDessinateur = "", nomAuteur = "", prenomAuteur = "", synopsis = "";
        boolean enCours = false;
        int selectTab = 0;
        Image img = null;
        if (manga != null) {
            selectTab = 3;
            titreManga = manga.getTitre();
            titreMangaOriginal = manga.getTitre_original();
            anneeSortie = Integer.toString(manga.getAnnee_sortie());
            genre = manga.getGenre().getGenre();
            nomDessinateur = manga.getDessinateur().getNom();
            prenomDessinateur = manga.getDessinateur().getPrenom();
            nomAuteur = manga.getAuteur().getNom();
            prenomAuteur = manga.getAuteur().getPrenom();

            File f = new File(manga.getNomImage());
            Image image = new Image(Constantes.CONSTANTE_IMAGE + "default.jpg");
            if (f.exists()) {
                try {
                    image = new Image(f.toURI().toURL().toExternalForm());
                } catch (MalformedURLException ex) {
                }
            }
            synopsis = manga.getSynopsis();
            enCours = manga.isEnCours();
        }
        tab_tome.getSelectionModel().select(0);
        numero_tome.setText("");
        txt_lien_image_manga.clear();
        refreshTome(manga);
        tab.getSelectionModel().select(selectTab);
        info_titre_manga.setText(titreManga);
        info_titre_original.setText(titreMangaOriginal);
        info_annee_sortie.setText(anneeSortie);
        info_genre.setText(genre);
        info_dessinateur_nom.setText(nomDessinateur);
        info_dessinateur_prenom.setText(prenomDessinateur);
        info_auteur_nom.setText(nomAuteur);
        info_auteur_prenom.setText(prenomAuteur);
        info_enCours.setSelected(enCours);
        info_image_manga.setFitHeight(340);
        info_image_manga.setFitWidth(280);
        info_image_manga.setImage(img);
        info_synopsis.setText(synopsis);

    }

    /**
     * Cette méthode permet d'actualiser les derniers mangas ajouté. Elle
     * regarde dans le fichier historique les huit derniers mangas ajoutés et
     * compare avec la liste des mangas. Si le manga est dans l'historique et
     * existe dans la liste des mangas, elle affiche l'image de couverture avec
     * le titre du manga.
     */
    public void refreshDerniersMangasAjoutes() {
        wrk.mangaDeserialisation();
        if (wrk.sortMangas() != null) {
            int index = 0;
            gridManga.getChildren().clear();
            ArrayList<String> historique = wrk.sortHistorique();
            ArrayList<String> mangaDejaAjoute = new ArrayList<>();
            Collections.reverse(historique);
            for (String h : historique) {
                String[] str = h.split(Constantes.SEPARATEUR);
                if (str[0].equals("Ajout Manga")) {
                    if (index <= 7) {
                        Manga manga = wrk.sortMangas(str[1]);
                        if (manga != null) {
                            if (!mangaDejaAjoute.contains(str[1])) {
                                ImageView imageView = null;
                                imageView = creerImageCouverture(manga.getNomImage(), manga.getTitre());
                                Text nomManga = new Text(10, 2, manga.getTitre());
                                nomManga.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));
                                nomManga.setStrikethrough(false);
                                nomManga.setFill(Color.WHITE);
                                nomManga.setStrokeWidth(1);
                                nomManga.setStroke(Color.BLACK);
                                nomManga.setWrappingWidth(180);
                                if (index < 4) {
                                    gridManga.add(imageView, index, 0);
                                    gridManga.add(nomManga, index, 0);
                                } else {
                                    gridManga.add(imageView, index - 4, 1);
                                    gridManga.add(nomManga, index - 4, 1);
                                }
                                mangaDejaAjoute.add(str[1]);
                                index++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Cette méthode permet de créer un image de couverture avec le lien de
     * l'image et le titre du manga. Elle donne un id à l'image qui est le nom
     * du manga et créer aussi un listener qui permet lorsque nous cliquons sur
     * l'image de couverture d'effectuer une fonction.
     *
     * @param str représente le lien de l'image du mangas
     * @param titreManga représente le titre du manga.
     * @return une ImageView qui contient l'image de couverture du manga
     */
    public ImageView creerImageCouverture(String str, String titreManga) {
        File f = new File(str);
        Image image = new Image(Constantes.CONSTANTE_IMAGE + "default.jpg");
        if (f.exists()) {
            try {
                image = new Image(f.toURI().toURL().toExternalForm());
            } catch (MalformedURLException ex) {
            }
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(240);
        imageView.setFitWidth(180);
        imageView.setId(titreManga);
        imageView.setPickOnBounds(true); // allows click on transparent areas
        imageView.setOnMouseClicked((MouseEvent e) -> {
            actualiseInformationsManga(wrk.sortMangas(imageView.getId()));
        });
        return imageView;
    }

    /**
     * Cette méthode permet de remplir la liste des genres qui se trouve dans le
     * fichier "genre.txt".
     */
    public void rempliListeGenre() {
        ArrayList<Genre> genres = new ArrayList<>();
        for (Genre genre : wrk.sortGenre()) {
            genres.add(new Genre(genre.getGenre()));
        }
        liste_genres.getItems().setAll(genres);
        liste_genres.getItems().add(new Genre("Tous les genres"));
        liste_genres.getSelectionModel().selectLast();
        liste_genre.getItems().setAll(genres);
        liste_genre.getItems().add(new Genre("Nouveau genre"));
        liste_genre.getSelectionModel().selectLast();
    }

    /**
     * Cette méthode permet d'écrire le titre du manga donné en paramètre avec
     * une police, une taille et différents styles comme une couleur de bordure
     * et une couleur de texte.
     *
     * @param titre représente le itre du manga
     * @return un Text qui contient le titre du manga avec les différents styles
     * appliqués.
     */
    public Text creerTitreMangaCouverture(String titre) {
        Text nomManga = new Text(10, 2, titre);
        nomManga.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 28));
        nomManga.setStrikethrough(false);
        nomManga.setFill(Color.WHITE);
        nomManga.setStrokeWidth(1);
        nomManga.setStroke(Color.BLACK);
        nomManga.setWrappingWidth(180);
        return nomManga;
    }

    /**
     * Cette méthode permet de supprimer un tome en appelant la méthode dans le
     * worker. Elle va ensuite serialiser, deserialiser et actualiser la liste
     * des tomes. Cette méthode va, si elle s'execute correctement, enregistrer
     * l'action dans l'historique et rediriger l'utilisateur dans le premier
     * onglet qui se nomme; "Tomes". Si cette méthode rencontre une erreur, elle
     * va afficher une popup d'erreur sinon si tout fonctionne correctement,
     * elle va afficher une popup de succès.
     *
     * @param tome représente le tome que nous voulons supprimer.
     * @param manga représente le manga auquel nous voulons supprimer le tome.
     */
    public void supprimeTome(Tome tome, Manga manga) {
        try {
            int numero = tome.getNumero();
            if (wrk.supprimeTome(tome, manga)) {
                if (wrk.ajouteHistorique(manga, "Le tome numéro " + numero + " du manga " + manga.getTitre() + " a correctement été supprimé !", "Suppression Tome")) {
                    if (wrk.mangaSerialise()) {
                        actualiseTout();
                        actualiseInformationsManga(wrk.sortMangas(manga.getTitre()));
                        refreshTome(wrk.sortMangas(info_titre_manga.getText()));
                        JfxPopup.displayInformation("Information", null, "Le tome numéro " + numero + " a correctement été supprimé !");

                    }
                }
            }
        } catch (NullPointerException e) {

        } catch (Exception e) {
        }
    }

    /**
     * Cette méthode permet d'actualiser/afficher la liste des tomes d'un manga
     * que nous passons en paramètre. Si le manga contient aucuns tomes, un
     * message par defaut d'affiche.
     *
     * @param manga représente l'objet manga
     */
    public void refreshTome(Manga manga) {
        gridTome.getChildren().clear();
        if (manga != null) {
            if (manga.getTomes() != null) {
                for (Tome tome : manga.getTomes()) {
                    Button supprimeTome = new Button("Supprimer");
                    gridTome.add(new Label(Integer.toString(tome.getNumero())), 0, manga.getTomes().indexOf(tome));
                    File f = new File(tome.getImage());
                    Image image = null;
                    if (f.exists()) {
                        try {
                            image = new Image(f.toURI().toURL().toExternalForm());
                        } catch (MalformedURLException ex) {
                        }
                    } else {
                        image = new Image(Constantes.CONSTANTE_IMAGE + "default.jpg");
                    }
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(90);
                    imageView.setFitWidth(60);
                    gridTome.add(imageView, 1, manga.getTomes().indexOf(tome));
                    gridTome.setPadding(new Insets(15, 15, 15, 15));
                    gridTome.setVgap(20);
                    gridTome.setHgap(35);
                    gridTome.add(supprimeTome, 2, manga.getTomes().indexOf(tome));
                    //supprimeTome.setId(Integer.toString(tome.getNumero()));
                    supprimeTome.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            supprimeTome(tome, manga);
                        }
                    });
                }
            } else {
                gridTome.add(new Label("Aucun tome n'a été ajouté !"), 0, 0);
            }
        }
    }

    /**
     * Cette méthode permet d'afficher/actualiser la liste des mangas que nous
     * trions par genre. Si nous selectons un genre, la méthode va afficher tous
     * les mangas associés à ce genre. Si nous selectionnons "Tous les genres",
     * tous les mangas vont s'afficher.
     *
     * @param genre représente le genre que nous voulons trier.
     */
    public void afficheMangaGenre(Genre genre) {
        GridPane tousLesMangasGrid = new GridPane();
        try {
            int index = 1, indexRow = 0;
            for (Map.Entry<String, Manga> m : wrk.sortMangas().entrySet()) {
                if (m.getValue().getGenre().toString().equals(genre.toString()) || genre.getGenre().equals("Tous les genres")) {
                    ImageView imageView = creerImageCouverture(m.getValue().getNomImage(), m.getKey());
                    Text nomManga = creerTitreMangaCouverture(m.getValue().getTitre());
                    tousLesMangasGrid.setHalignment(imageView, HPos.CENTER);
                    tousLesMangasGrid.setHalignment(nomManga, HPos.CENTER);
                    tousLesMangasGrid.setValignment(nomManga, VPos.BOTTOM);
                    tousLesMangasGrid.setPrefHeight(100);
                    tousLesMangasGrid.setPrefWidth(350);
                    tousLesMangasGrid.setPadding(new Insets(15, 15, 15, 15));
                    tousLesMangasGrid.setVgap(20);
                    tousLesMangasGrid.setHgap(35);
                    if ((index % 4) != 0) {
                        tousLesMangasGrid.add(imageView, index % 4, indexRow);
                        tousLesMangasGrid.add(nomManga, index % 4, indexRow);
                    } else {
                        tousLesMangasGrid.add(imageView, index % 4, indexRow);
                        tousLesMangasGrid.add(nomManga, index % 4, indexRow);
                        tousLesMangasGrid.addRow(indexRow);
                        indexRow++;
                    }
                    index++;
                }
            }
            tousLesMangas.setContent(tousLesMangasGrid);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Cette méthode permet lorsqu'on tape du texte dans la barre de recherche,
     * afficher les mangas qui contiennent ou commencent par ce que l'on écrit.
     *
     * @param event représente l'action lorsque l'on tape du texte dans la barre
     * de recherche.
     */
    @FXML
    private void recherche(KeyEvent event) {
        GridPane tousLesMangasGrid = new GridPane();
        try {
            int index = 1, indexRow = 0;
            for (Map.Entry<String, Manga> m : wrk.rechercheMangaContenant(barre_recherche.getText()).entrySet()) {
                ImageView imageView = creerImageCouverture(m.getValue().getNomImage(), m.getKey());
                Text nomManga = creerTitreMangaCouverture(m.getValue().getTitre());
                tousLesMangasGrid.setHalignment(imageView, HPos.CENTER);
                tousLesMangasGrid.setHalignment(nomManga, HPos.CENTER);
                tousLesMangasGrid.setValignment(nomManga, VPos.BOTTOM);
                tousLesMangasGrid.setPrefHeight(100);
                tousLesMangasGrid.setPadding(new Insets(15, 15, 15, 15));
                tousLesMangasGrid.setVgap(20);
                tousLesMangasGrid.setHgap(35);
                if ((index % 4) != 0) {
                    tousLesMangasGrid.add(imageView, index % 4, indexRow);
                    tousLesMangasGrid.add(nomManga, index % 4, indexRow);
                } else {
                    tousLesMangasGrid.add(imageView, index % 4, indexRow);
                    tousLesMangasGrid.add(nomManga, index % 4, indexRow);
                    tousLesMangasGrid.addRow(indexRow);
                    indexRow++;
                }
                index++;
            }
            tousLesMangas.setContent(tousLesMangasGrid);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Cette méthode permet de rechercher une image sur notre ordinateur. Après
     * avoir choisi une image, la méthode va remplir le champ texte
     * txt_lien_image_manga.
     *
     * @param event représente l'action lorsque clique sur le bouton de
     * recherche d'image.
     */
    @FXML
    private void browseImageManga(ActionEvent event) {
        txt_lien_image_manga.setText(wrk.browseImageCouvertureManga());
    }

    /**
     * Cette méthode permet de rechercher une image sur notre ordinateur. Après
     * avoir choisi une image, la méthode va remplir le champ texte
     * txt_lien_image_tome.
     *
     * @param event représente l'action lorsque clique sur le bouton de
     * recherche d'image.
     */
    @FXML
    private void browseImageTome(ActionEvent event) {
        txt_lien_image_tome.setText(wrk.browseImageCouvertureManga());
    }

}
