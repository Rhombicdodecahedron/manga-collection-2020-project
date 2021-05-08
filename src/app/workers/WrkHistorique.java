/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.workers;

import app.beans.Historique;
import app.beans.Manga;
import java.util.ArrayList;

/**
 * Cette classe renferme la classe worker Historique de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class WrkHistorique {

    /**
     * Instance de la classe WrkTextFile
     */
    private final WrkTextFile wrkTextFile;
    /**
     * Instance de la classe WrkDate
     */
    private final WrkDate wrkDate;

    /**
     * Constructeur de la classe WrkHistorique. Il définit les instances des
     * classes WrkTextFile et WrkDate.
     */
    public WrkHistorique() {
        wrkTextFile = new WrkTextFile();
        wrkDate = new WrkDate();
    }

    /**
     * Cette méthode permet d'ajouter dans l'historique un ajout, une
     * suppression ou une modification effectué. Elle prend en paramètre l'objet
     * manga, le message et le type.
     *
     * @param manga représente l'objet manga
     * @param msg représente le message à mettre dans le fichier historique.txt
     * @param type représente un Ajout, une Suppression ou encore une
     * modification
     * @return un booléen true ou false si l'ajout de l'historique s'est bien
     * effectué ou pas.
     */
    public boolean ajouteHistorique(Manga manga, String msg, String type) {
        return wrkTextFile.appendToTextFile(Constantes.CONSTANTE_PATH_HISTORIQUE, new Historique(type, msg, manga, wrkDate.rechercheDateCourante()).toString());
    }

    /**
     *
     * Cette méthode permet de sortir toutes les lignes du fichier
     * historique.txt et ensuite en mettant ces lignes dans une ligne de chaine
     * de caractère.
     *
     * @return un liste de chaine de caractère contenant le message d'ajout,
     * suppression ou de modification.
     */
    public ArrayList<String> sortHistorique() {
        return wrkTextFile.readTextFile(Constantes.CONSTANTE_PATH_HISTORIQUE);
    }
}
