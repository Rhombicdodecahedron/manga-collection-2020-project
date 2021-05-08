/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.workers;

import app.beans.Auteur;
import app.beans.Dessinateur;
import app.beans.Genre;
import app.beans.Manga;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.stage.FileChooser;

/**
 * Cette classe renferme le worker de la classe Manga de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class WrkManga {

    /**
     * Instance de l'interface WorkerItf
     */
    private static final WorkerItf instance = null;
    /**
     * Instance de la classe WrkObjectFile
     */
    private final WrkObjectFile wrkObjectFile;
    /**
     * Liste qui contiendra comme clé, le nom du manga et comme valeur l'objet
     * manga.
     */
    private final HashMap<String, Manga> mangas;

    /**
     * Constructeur de la classe WrkManga. Il permet d'initialiser la liste de
     * mangas et d'instancier la classe WrkObjectFile.
     */
    WrkManga() {
        mangas = new HashMap<>();
        wrkObjectFile = new WrkObjectFile();
    }

    /**
     * Cette méthode permet de désérialisé tous le fichier Manga.ser se trouvant
     * dans le dossier manga. Elle va ensuite remplir la liste de manga avec
     * comme clé le nom du manga et comme valeur l'objet manga lui même.
     *
     */
    public void mangaDeserialisation() {
        if (wrkObjectFile.readObjectHashMapStringManga(Constantes.CONSTANTE_SERIALISATION_MANGA) != null) {
            mangas.clear();
            mangas.putAll((HashMap<String, Manga>) wrkObjectFile.readObjectHashMapStringManga(Constantes.CONSTANTE_SERIALISATION_MANGA));
        }
    }

    /**
     * Cette methode permet de sérialisé dans un fichier Manga.ser les
     * infomations du/des manga(s). Ces informations sont prisent dans une liste
     * contenant comme clé le nom du manga et comme valeur l'objet manga lui
     * même.
     *
     * @return boolean true ou false en fonction du résultat obtenu.
     */
    public boolean serialiseManga() {
        return wrkObjectFile.writeObjectHashMapStringManga(Constantes.CONSTANTE_SERIALISATION_MANGA, mangas);
    }

    /**
     * Cette methode permet de sortir la liste des mangas.
     *
     * @return une liste contenant comme clé, le nom du manga et comme valeur
     * l'objet manga.
     */
    public HashMap<String, Manga> sortMangas() {
        return mangas;
    }

    /**
     * Cette méthode permet de sortir l'objet manga de la liste de tous les
     * mangas grâce au titre reçu en paramètre.
     *
     * @param titreManga représente le titre du manga.
     * @return l'objet manga se trouvant dans la liste de tous les mangas avec
     * comme titre le nom du manga reçu en paramètre ou null si rien n'a été
     * touvé.
     */
    public Manga sortMangas(String titreManga) {
        Manga manga = null;
        if (!titreManga.equals("")) {
            if (mangas.containsKey(titreManga)) {
                manga = mangas.get(titreManga);
            }
        }
        return manga;
    }

    /**
     * Cette methode permet de récupérer le chemin d'une image choisi pour
     * pouvoir la retourné.
     *
     * @return une chaine de caractère contenant le chemin de l'image.
     */
    public String browseImageCouvertureManga() {
        String result = null;
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image de couverture", Constantes.CONSTANTE_EXTENSION));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            result = f.getAbsolutePath();
        }
        return result;
    }

    /**
     * Cette méthode permet d'ajouter un nouveau manga à la liste. Elle prend en
     * paramètre le titre, titre original, le status, l'année de sortie, le
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
    public boolean ajouteManga(String titre, String titre_original, boolean enCours, int annee_sortie, String synopsis, String genre, String auteur_nom, String auteur_prenom, String dessinateur_prenom, String dessinateur_nom, String nomImage) {
        boolean result = false;
        if (!mangas.containsKey(titre)) {
            mangas.put(titre, new Manga(titre, titre_original, synopsis, new Genre(genre), nomImage, annee_sortie, enCours, new Auteur(auteur_nom, auteur_prenom), new Dessinateur(dessinateur_nom, dessinateur_prenom), null));
            result = true;
        }
        return result;
    }

    /**
     * Cette méthode permet de modifier le manga qui est affiché dans l'onglet
     * informations manga. Elle prend en paramètre le titre, titre original, le
     * status, l'année de sortie, le genre,le nom/prénom du dessinateur, le
     * nom/prénom de l'auteur et pour terminer, le nom de l'image avec son
     * extension. Ensuite la méthode sérialise et désérialise le fichier
     * Manga.ser pour mettre à jour la liste de mangas.
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
    public boolean modifieManga(String titre, String titre_original, boolean enCours, int annee_sortie, String synopsis, String genre, String auteur_nom, String auteur_prenom, String dessinateur_prenom, String dessinateur_nom) {
        boolean result = false;
        try {
            Manga manga = sortMangas(titre);
            manga.setTitre_original(titre_original);
            manga.setGenre(new Genre(genre));
            manga.setAnnee_sortie(annee_sortie);
            manga.setAuteur(new Auteur(auteur_nom, auteur_prenom));
            manga.setDessinateur(new Dessinateur(dessinateur_nom, dessinateur_prenom));
            manga.setSynopsis(synopsis);
            manga.setEnCours(enCours);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Cette méthode permet de supprimer un manga de la liste de mangas. Elle
     * prend paramètre l'objet manga qui doit être supprimé. Ensuite la méthode
     * sérialise et désérialise le fichier Manga.ser pour mettre à jour la liste
     * de mangas.
     *
     * @param manga représente l'objet manga
     * @return un boolean true ou false si la suppression s'est bien effectué ou
     * pas.
     */
    public boolean supprimeManga(Manga manga) {
        return mangas.remove(manga.getTitre()) != null;
    }

    /**
     * Cette méthode permet de rechercher un mangas se trouvant dans la liste
     * des mangas tout en retournant à la fin une liste contenant comme clé, le
     * nom du manga et comme valeur l'objet manga.
     *
     * @param recherche représente le nom du manga qui doit être recherché
     * @return une liste contenant comme clé, le nom du manga et comme valeur
     * l'objet manga
     */
    public HashMap<String, Manga> rechercheMangaContenant(String recherche) {
        HashMap<String, Manga> mangasRecherche = new HashMap<>();
        if (recherche != null) {
            String rechercheManga = recherche.toLowerCase();
            for (Map.Entry<String, Manga> entry : mangas.entrySet()) {
                if (entry.getKey().toLowerCase().contains(rechercheManga)) {
                    mangasRecherche.put(entry.getKey(), mangas.get(entry.getKey()));
                }
            }
        }

        return mangasRecherche;
    }

}
