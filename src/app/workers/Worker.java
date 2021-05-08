package app.workers;

import app.beans.Genre;
import app.beans.Manga;
import app.beans.Tome;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe renferme la classe Worker de l'application. Cette casse renferme
 * l'implémentation de la couche "métier" de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class Worker implements WorkerItf {

    /**
     * Instance de l'interface WorkerItf
     */
    private static WorkerItf instance = null;
    /**
     * Instance de la classe WrkManga
     */
    private final WrkManga wrkManga;
    /**
     * Instance de la classe WrkTome
     */
    private final WrkTome wrkTome;
    /**
     * Instance de la classe WrkGenre
     */
    private final WrkGenre wrkGenre;
    /**
     * Instance de la classe WrkHistorique
     */
    private final WrkHistorique wrkHistorique;

    /**
     * Constructeur de la classe Worker. Il définit les instances de WrkManga,
     * WrkHistorique, WrkGenre et WrkTome.
     */
    private Worker() {

        wrkManga = new WrkManga();
        wrkHistorique = new WrkHistorique();
        wrkTome = new WrkTome();
        wrkGenre = new WrkGenre();
    }

    /**
     * Cette méthode permet de créer l'instance de la classe Worker
     *
     * @return l'instance de la classe Worker.
     */
    public static WorkerItf getInstance() {
        if (instance == null) {
            instance = new Worker();
        }
        return instance;
    }

    /**
     *
     * Cette méthode appelle une autre méthode ce trouvant dans la classe
     * WrkManga. Elle permet d'ajouter un nouveau manga à la liste. Elle prend
     * en paramètre le titre, titre original, le status, l'année de sortie, le
     * genre,le nom/prénom du dessinateur, le nom/prénom de l'auteur et pour
     * terminer, le nom de l'image avec son extension. Ensuite la méthode
     * sérialise et désérialise le fichier Manga.ser pour mettre à jour la liste
     * de mangas.
     *
     * @param titre représente le titre du manga
     * @param titre_original représente le titre original du manga
     * @param enCours représente le status du manga en un boolean
     * @param annee_sortie représente l'année de sortie en int
     * @param synopsis représente le résumé du manga
     * @param genre représente le genre du manga
     * @param auteur_nom représente le nom de l'auteur
     * @param auteur_prenom représente le prénom de l'auteur
     * @param dessinateur_prenom représente le prénom du dessinateur
     * @param dessinateur_nom représente le nom du dessinateur
     * @param nomImage représente le nom de l'image avec son extension
     * @return un boolean true ou false si l'ajout s'est bien effectué ou pas.
     */
    @Override
    public boolean ajouteManga(String titre, String titre_original, boolean enCours, int annee_sortie, String synopsis, String genre, String auteur_nom, String auteur_prenom, String dessinateur_prenom, String dessinateur_nom, String nomImage) {
        return wrkManga.ajouteManga(titre, titre_original, enCours, annee_sortie, synopsis, genre, auteur_nom, auteur_prenom, dessinateur_prenom, dessinateur_nom, nomImage);
    }

    /**
     *
     * Cette méthode appelle une autre méthode ce trouvant dans la classe
     * WrkTome. Elle permet de supprimer un tome de la liste d'un manga.
     *
     * @param tome représente l'objet tome qu'il faut supprimer.
     * @param manga représente l'objet manga auquel nous voulons supprimer le
     * tome.
     * @return un booléen true ou false si la suppression s'est bien effectué.
     */
    @Override
    public boolean supprimeTome(Tome tome, Manga manga) {
        return wrkTome.supprimeTome(tome, manga);

    }

    /**
     *
     * Cette méthode appelle une autre méthode de la classe WrkManga. Elle
     * permet de modifier le manga qui est affiché dans l'onglet informations
     * manga. Elle prend en paramètre le titre, titre original, le status,
     * l'année de sortie, le genre,le nom/prénom du dessinateur, le nom/prénom
     * de l'auteur et pour terminer, le nom de l'image avec son extension.
     * Ensuite la méthode sérialise et désérialise le fichier Manga.ser pour
     * mettre à jour la liste de mangas.
     *
     *
     * @param titre représente le titre du manga
     * @param titre_original représente le titre original du manga
     * @param enCours représente le status du manga en un boolean
     * @param annee_sortie représente l'année de sortie en int
     * @param synopsis représente le résumé du manga
     * @param genre représente le genre du manga
     * @param auteur_nom représente le nom de l'auteur
     * @param auteur_prenom représente le prénom de l'auteur
     * @param dessinateur_prenom représente le prénom du dessinateur
     * @param dessinateur_nom représente le nom du dessinateur
     *
     * @return un boolean true ou false si la modification s'est bien effectué
     * ou pas.
     */
    @Override
    public boolean modifieManga(String titre, String titre_original, boolean enCours, int annee_sortie, String synopsis, String genre, String auteur_nom, String auteur_prenom, String dessinateur_prenom, String dessinateur_nom) {
        return wrkManga.modifieManga(titre, titre_original, enCours, annee_sortie, synopsis, genre, auteur_nom, auteur_prenom, dessinateur_prenom, dessinateur_nom);
    }

    /**
     *
     * Cette méthode appelle une autre méthode de la classe WrkManga. Elle
     * permet de rechercher le ou les mangas contenant ou debutant par le nom
     * recherché reçu en .
     *
     * @param recherche représente le nom du manga qui doit être recherché
     * @return une liste contenant comme clé, le nom du manga et comme valeur
     * l'objet manga
     */
    @Override
    public HashMap<String, Manga> rechercheMangaContenant(String recherche) {
        return wrkManga.rechercheMangaContenant(recherche);
    }

    /**
     *
     * Cette méthode appelle une autre méthode de la classe WrkManga. Elle
     * permet de connaitre le chemin le l'image choisi.
     *
     * @return une chaine de caractère contenant le chemin de l'image.
     */
    @Override
    public String browseImageCouvertureManga() {
        return wrkManga.browseImageCouvertureManga();
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkManga. Elle
     * permet de supprimer de la liste un manga reçu en paramètre.
     *
     * @param manga représente l'objet manga
     * @return un boolean true ou false si la suppression s'est bien effectué ou
     * pas.
     */
    @Override
    public boolean supprimeManga(Manga manga) {
        return wrkManga.supprimeManga(manga);
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkTome et permet
     * d'ajouter un nouveau tome dans la liste du manga reçu en paramètre.
     *
     * @param manga représente le manga auquel nous voulons ajouter le tome.
     * @param tome représente le tome que nous voulons ajouter au manga.
     * @return un booléen true ou false si l'ajout s'est correctement effectué
     */
    @Override
    public boolean ajouteTome(Manga manga, Tome tome) {
        return wrkTome.ajouteTome(manga, tome);
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkManga et permet
     * de desérialiser le fichier Mangas.ser qui contient la liste des Mangas
     * avec comme clé le nom du manga et comme valeur, l'objet manga lui même.
     *
     */
    @Override
    public void mangaDeserialisation() {
        wrkManga.mangaDeserialisation();
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkManga et permet
     * de serialiser la liste de mangas qui a pour clé le nom du manga et pour
     * valeur l'objet manga. Elle va serialiser tout ça dans le fichier
     * Mangas.ser qui se trouve dans un dossier par defaut.
     *
     * @return boolean true ou false si la serialisation s'est bien effectué ou
     * pas.
     */
    @Override
    public boolean mangaSerialise() {
        return wrkManga.serialiseManga();
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkGenre. Elle
     * permet de vérifier si le genre passé en paramètre existe déjà dans le
     * fichier txt, s'il n'existe pas, la méthode ajoute le nouveau genre dans
     * le fichier genre.txt et retourbe true si tout s'est bien effectué.
     *
     * @param genre représente le genre à ajouté
     * @return un booléen true ou false si l'ajout du genre s'est bien effectué
     * ou non.
     */
    @Override
    public boolean ajouteGenre(String genre) {
        return wrkGenre.ajoutGenre(genre);
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkManga. Elle
     * permet de sortir l'objet manga de la liste de tous les mangas grâce au
     * titre reçu en paramètre.
     *
     * @param titreManga représente le titre du manga.
     * @return l'objet manga se trouvant dans la liste de tous les mangas avec
     * comme titre le nom du manga reçu en paramètre ou null si rien n'a été
     * touvé.
     */
    @Override
    public Manga sortMangas(String titreManga) {
        return wrkManga.sortMangas(titreManga);
    }

    /**
     * Cette méthode appelle une autre méthode de la classe Wrkmanga. Elle
     * permet de sortir la liste des mangas.
     *
     * @return une liste contenant comme clé, le nom du manga et comme valeur
     * l'objet manga.
     */
    @Override
    public HashMap<String, Manga> sortMangas() {
        return wrkManga.sortMangas();
    }

    /**
     *
     * Cette méthode appelle une autre méthode de la classe WrkHistorique. Elle
     * permet de sortir toutes les lignes du fichier historique.txt et ensuite
     * en mettant ces lignes dans une ligne de chaine de caractère.
     *
     * @return un liste de chaine de caractère contenant le message d'ajout,
     * suppression ou de modification.
     */
    @Override
    public ArrayList<String> sortHistorique() {
        return wrkHistorique.sortHistorique();
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkGenre et permet
     * de sortir une liste d'objet genre en lisant le fichier "genre.txt".
     *
     * @return une liste d'objet Genre
     */
    @Override
    public ArrayList<Genre> sortGenre() {
        return wrkGenre.sortgenre();
    }

    /**
     * Cette méthode appelle une autre méthode de la classe WrkHistorique et
     * permet d'ajouter dans l'historique un ajout, une suppression ou une
     * modification effectué. Elle prend en paramètre l'objet manga, le message
     * et le type.
     *
     * @param manga représente l'objet manga
     * @param msg représente le message à mettre dans le fichier historique.txt
     * @param type représente un Ajout, une Suppression ou encore une
     * modification
     * @return un booléen true ou false si l'ajout de l'historique s'est bien
     * effectué ou pas.
     */
    @Override
    public boolean ajouteHistorique(Manga manga, String msg, String type) {
        return wrkHistorique.ajouteHistorique(manga, msg, type);
    }

}
