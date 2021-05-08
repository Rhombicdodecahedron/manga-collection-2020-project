/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.beans;

import java.io.Serializable;

/**
 * Cette classe renferme le bean Auteur de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class Genre implements Serializable {

    private String genre;

    /**
     * Constructeur du bean genre. Ce constructeur definni le genre qu'il prend
     * en argument.
     *
     * @param genre
     */
    public Genre(String genre) {
        this.genre = genre;
    }

    /**
     * Methode toString du bean Genre. Elle permet d'afficher le genre.
     *
     * @return
     */
    @Override
    public String toString() {
        return genre;
    }

    /**
     * Getter genre du bean Genre.
     *
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter genre du bean Genre.
     *
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

}
