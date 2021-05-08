/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.workers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Cette classe renferme la classe worker Date de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class WrkDate {

    /**
     * Constante formatage de la date
     */
    public static final String FORMATDATE = "dd-MM-yyy hh:mm:ss";

    /**
     * Constructeur de la classe WrkDate.
     */
    public WrkDate() {
    }

    /**
     * Cette méthode permet de créer une chaine de caractère contenant la date
     * et l'heure actuelle.
     *
     * @return une chaine de caractère contenant la date et l'heure actuelle.
     */
    public String rechercheDateCourante() {
        Date now = new Date();
        SimpleDateFormat date = new SimpleDateFormat(FORMATDATE);
        String dateFormattee = date.format(now);
        return dateFormattee;
    }

}
