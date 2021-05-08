/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.workers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cette interface permet de définir les constantes de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public interface Constantes {

    /**
     * Constante de l'emplacement des images.
     */
    public static final String CONSTANTE_IMAGE = "/resources/manga/cover/";
    /**
     * Constante de l'emplacement du dossier des images.
     */
    public static final String CONSTANTE_IMAGE_DOSSIER = "./src/resources/manga/cover/";
    /**
     * Constante de l'emplacement de la serialisation des mangas.
     */
    public static final String CONSTANTE_SERIALISATION_MANGA = "./src/resources/manga/Mangas.ser";
    /**
     * Constante de l'emplacement du fichier de l'historique.
     */
    public static final String CONSTANTE_PATH_HISTORIQUE = "./src/resources/historique/historique.txt";
    /**
     * Constante de l'emplacement du fichier des genres de manga.
     */
    public static final String CONSTANTE_PATH_GENRE = "./src/resources/manga/genre.txt";
    /**
     * Constante du séparateur.
     */
    public static final String SEPARATEUR = "#";
    /**
     * Constante de la liste des extensions
     */
    public static final ArrayList<String> CONSTANTE_EXTENSION = new ArrayList<>(Arrays.asList("*.png", "*.jpg"));
    

}
