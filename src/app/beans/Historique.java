/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.beans;

import app.workers.Constantes;

/**
 * Cette classe renferme le bean Auteur de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class Historique {

    private final String type;

    private final String msg;
    private final String date;
    private final Manga manga;

    /**
     * Constructeur du bean Historique. Il définit le type, le message, le manga
     * et la date qu'il reçoit en paramètre.
     *
     * @param type
     * @param msg
     * @param manga
     * @param date
     */
    public Historique(String type, String msg, Manga manga, String date) {
        this.type = type;
        this.msg = msg;
        this.manga = manga;
        this.date = date;
    }

    /**
     * Methode toString du bean Historique. Elle permet de transformer les
     * paramètres reçu par le constructeur en une chaine de caractères qui va
     * être ensuite écrit dans un fichier txt nommé historique.txt
     *
     * @return
     */
    @Override
    public String toString() {
        return type + Constantes.SEPARATEUR + manga.getTitre() + Constantes.SEPARATEUR + msg + Constantes.SEPARATEUR + date;
    }

}
