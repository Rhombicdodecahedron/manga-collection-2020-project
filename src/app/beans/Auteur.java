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
public class Auteur implements Serializable {

    private String nom, prenom;

    /**
     * Constructeur du bean bean Auteur. Ce constructeur definni le nom et le
     * prenom de l'auteur.
     *
     * @param nom
     * @param prenom
     */
    public Auteur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Getter nom du bean Auteur.
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom du bean Auteur.
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter prenom du bean Auteur
     *
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter prenom du bean Auteur.
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
