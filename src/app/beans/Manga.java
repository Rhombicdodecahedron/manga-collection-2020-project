/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe renferme le bean Auteur de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 28.04.2020
 */
public class Manga implements Serializable {

    private String titre, titre_original, synopsis, nomImage;
    private int annee_sortie;
    private boolean enCours;
    private Auteur auteur;
    private Dessinateur dessinateur;
    private Genre genre;
    private ArrayList<Tome> tomes;

    /**
     * Constructeur du bean Manga, elle définit tous les atributs reçus en
     * paramètre.
     *
     * @param titre represente le titre du manga
     * @param titre_original represente le titre originale du manga
     * @param synopsis represente le resumé du manga
     * @param genre represente le genre du manga
     * @param nomImage represente le nom de l'image du manga ce trouvant dans le
     * dossier manga/cover/
     * @param annee_sortie represente l'annee de sortie du manga
     * @param enCours represente le nom de l'image du manga ce trouvant dans
     * @param auteur represente le nom de l'image du manga ce trouvant dans
     * @param dessinateur represente le nom de l'image du manga ce trouvant dans
     * @param tomes represente le nom de l'image du manga ce trouvant dans
     */
    public Manga(String titre, String titre_original, String synopsis, Genre genre, String nomImage, int annee_sortie, boolean enCours, Auteur auteur, Dessinateur dessinateur, ArrayList<Tome> tomes) {
        this.titre = titre;
        this.titre_original = titre_original;
        this.synopsis = synopsis;
        this.genre = genre;
        this.nomImage = nomImage;
        this.annee_sortie = annee_sortie;
        this.enCours = enCours;
        this.auteur = auteur;
        this.dessinateur = dessinateur;
        this.tomes = tomes;
    }

    @Override
    public String toString() {
        return "Manga{" + "titre=" + titre + ", titre_original=" + titre_original + ", synopsis=" + synopsis + ", genre=" + genre + ", lienImage=" + nomImage + ", annee_sortie=" + annee_sortie + ", enCours=" + enCours + ", auteur=" + auteur + ", dessinateur=" + dessinateur + ", tomes=" + tomes + '}';
    }

    /**
     * Getter titre du bean Manga
     *
     * @return le titre du manga
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Setter titre_original du bean Manga
     *
     * @param titre le titre du manga
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Getter titre_original du bean Manga
     *
     * @return le titre original du manga
     */
    public String getTitre_original() {
        return titre_original;
    }

    /**
     * Setter titre_original du bean Manga
     *
     * @param titre_original le titre original du manga
     */
    public void setTitre_original(String titre_original) {
        this.titre_original = titre_original;
    }

    /**
     * Getter synopsis du bean Manga
     *
     * @return le résumé du manga
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Setter synopsis du bean Manga
     *
     * @param synopsis le résumé du manga
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Getter nomImage du bean Manga
     *
     * @return le nom de l'image du manga
     */
    public String getNomImage() {
        return nomImage;
    }

    /**
     * Setter nomImage du bean Manga
     *
     * @param nomImage le nom de l'image du manga
     */
    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    /**
     * Getter annee_sortie du bean Manga
     *
     * @return un nombre entier qui représente l'année de sortie du manga
     */
    public int getAnnee_sortie() {
        return annee_sortie;
    }

    /**
     * Setter annee_sortie du bean Manga
     *
     * @param annee_sortie représente l'année de sortie du manga
     */
    public void setAnnee_sortie(int annee_sortie) {
        this.annee_sortie = annee_sortie;
    }

    /**
     * Getter du status du bean Manga
     *
     * @return un booléen du status du manga
     */
    public boolean isEnCours() {
        return enCours;
    }

    /**
     * Setter enCours du bean Manga
     *
     * @param enCours représente le status du manga
     */
    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    /**
     * Getter de l'objet auteur du bean Manga
     *
     * @return l'objet auteur
     */
    public Auteur getAuteur() {
        return auteur;
    }

    /**
     * Setter auteur du bean Manga
     *
     * @param auteur représente l'auteur du manga
     */
    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    /**
     * Getter dessinateur du bean Manga
     *
     * @return un dessinateur
     */
    public Dessinateur getDessinateur() {
        return dessinateur;
    }

    /**
     * Setter dessinateur du bean Manga
     *
     * @param dessinateur représente le dessinateur du manga
     */
    public void setDessinateur(Dessinateur dessinateur) {
        this.dessinateur = dessinateur;
    }

    /**
     * Getter genre du bean Manga
     *
     * @return un genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Setter genre du bean Manga
     *
     * @param genre représente le genre du manga
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Getter de la liste tomes du bean Manga
     *
     * @return du liste de tome
     */
    public ArrayList<Tome> getTomes() {
        return tomes;
    }

    /**
     * Setter tomes du bean Manga
     *
     * @param tomes représente la liste des tomes du manga
     */
    public void setTomes(ArrayList<Tome> tomes) {
        this.tomes = tomes;
    }

}
