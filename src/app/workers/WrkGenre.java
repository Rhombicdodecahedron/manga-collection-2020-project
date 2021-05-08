/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.workers;

import app.beans.Genre;
import java.util.ArrayList;

/**
 * Cette classe renferme la classe worker Genre de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class WrkGenre {

    /**
     * Instance de la classe WrkTextFile
     */
    private final WrkTextFile wrkTextFile;

    /**
     * Constructeur de la classe WrkGenre. Il définit l'instance de la classe
     * WrkTextFile.
     */
    public WrkGenre() {
        wrkTextFile = new WrkTextFile();
    }

    /**
     * Cette méthode permet de vérifier si le genre passé en paramètre existe
     * déjà dans le fichier txt, s'il n'existe pas, la méthode ajoute le nouveau
     * genre dans le fichier genre.txt et retourbe true si tout s'est bien
     * effectué.
     *
     * @param genre représente le genre à ajouté
     * @return un booléen true ou false si l'ajout du genre s'est bien effectué
     * ou non.
     */
    public boolean ajoutGenre(String genre) {
        boolean result = false;
        if (!wrkTextFile.readTextFile(Constantes.CONSTANTE_PATH_GENRE).contains(genre)) {
            if (wrkTextFile.appendToTextFile(Constantes.CONSTANTE_PATH_GENRE, genre)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Cette méthode permet de sortir une liste d'objet genre en lisant le
     * fichier "genre.txt".
     *
     * @return une liste d'objet Genre
     */
    public ArrayList<Genre> sortgenre() {
        ArrayList<Genre> genres = new ArrayList<>();
        if (wrkTextFile.readTextFile(Constantes.CONSTANTE_PATH_GENRE) != null) {
            for (String genre : wrkTextFile.readTextFile(Constantes.CONSTANTE_PATH_GENRE)) {
                genres.add(new Genre(genre));
            }
        }
        return genres;
    }

}
