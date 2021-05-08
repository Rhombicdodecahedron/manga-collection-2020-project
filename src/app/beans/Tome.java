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
public class Tome implements Serializable {

    private int numero;
    private String image;

    /**
     * Constructeur du bean Tome. Il définit le numéro et le nom de l'image reçu
     * en paramètre.
     *
     * @param numero du tome
     * @param image, nom de l'image du manga
     */
    public Tome(int numero, String image) {
        this.numero = numero;
        this.image = image;
    }

    /**
     * Getter numero du bean Tome
     *
     * @return le numéro du tome
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Setter numero du bean Tome
     *
     * @param numero le résumé du tome
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Getter image du bean Tome
     *
     * @return le nom de l'image du tome
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter image du bean Tome
     *
     * @param image le résumé du tome
     */
    public void setImage(String image) {
        this.image = image;
    }

}
