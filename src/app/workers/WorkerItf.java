package app.workers;

import app.beans.Genre;
import app.beans.Manga;
import app.beans.Tome;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette interface permet de faire le lien entre le controller et de worker.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public interface WorkerItf {

    boolean ajouteManga(String titre, String titre_original, boolean enCours, int annee_sortie, String synopsis, String genre, String auteur_nom, String auteur_prenom, String dessinateur_prenom, String dessinateur_nom, String nomImage);

    boolean modifieManga(String titre, String titre_original, boolean enCours, int annee_sortie, String synopsis, String genre, String auteur_nom, String auteur_prenom, String dessinateur_prenom, String dessinateur_nom);

    String browseImageCouvertureManga();

    void mangaDeserialisation();

    boolean supprimeManga(Manga manga);

    boolean ajouteTome(Manga manga, Tome tome); //<-- 1

    Manga sortMangas(String titreManga); //<-- 2

    HashMap<String, Manga> sortMangas(); //<-- 3

    ArrayList<String> sortHistorique();

    ArrayList<Genre> sortGenre();


    boolean ajouteGenre(String genre);

    boolean ajouteHistorique(Manga manga, String msg, String type);

    HashMap<String, Manga> rechercheMangaContenant(String recherche); //<--- 4

    boolean supprimeTome(Tome tome, Manga manga); //<--- 5

    boolean mangaSerialise();

}
