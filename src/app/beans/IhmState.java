package app.beans;

import java.awt.*;
import java.io.Serializable;

//
// ######################################################################
// #                        _       _        ____  ____   __            #
// #        /\/\   ___   __| |_   _| | ___  |___ \|___ \ / /_           #
// #       /    \ / _ \ / _` | | | | |/ _ \   __) | __) | '_ \          #
// #      / /\/\ \ (_) | (_| | |_| | |  __/  / __/ / __/| (_) |         #
// #      \/    \/\___/ \__,_|\__,_|_|\___| |_____|_____|\___/  FRI     #
// #                        __                                          #
// #                       |_   |    /  \  \_/                          #
// #                       |    |__  \__/  / \                          #
// #                                                                    #
// ######################################################################
// # Projet "caisse à outils" flux pour apprenant, avec Ihm soignée,    #
// # pour les flux texte, flux binaire et sérialisation d'objets.       #
// ######################################################################
// # Ecrit par # Paolo Friedli      # VERSION # 1.0 # DATE # 02.05.2012 #
// ######################################################################
//

/**
 * Cette classe est un bean qui permets de transporter des informations
 * d'état de l'ihm. Ce bean peut être sérialisé (=implémente l'interface
 * java.io.Serializable).
 *
 * @author <a href="mailto:friedlip@edufr.ch">Paolo Friedli</a>
 * @version 1.0
 * @since 02.05.2012
 */
public class IhmState implements Serializable {

    /**
     * Le constructeur de la classe.
     *
     * @param lastTextFilePath   le chemin complet sur le fichier texte qui est actuellement à l'écran
     * @param lastBinaryFilePath le chemin complet sur le fichier binaire qui est actuellement à l'écran
     * @param lastObjectFilePath le chemin complet sur le fichier de sérialisation qui est actuellement à l'écran
     * @param lastSelectedTab    le N° de l'onglet actuellement visible à l'écran
     * @param width              la largeur de l'ihm
     * @param height             la hauteur de l'ihm
     * @param location           la position de l'ihm à l'écran
     */
    public IhmState(String lastTextFilePath, String lastBinaryFilePath, String lastObjectFilePath, int lastSelectedTab, int width, int height,
                    Point location) {
        this.lastTextFilePath = lastTextFilePath;
        this.lastBinaryFilePath = lastBinaryFilePath;
        this.lastObjectFilePath = lastObjectFilePath;
        this.lastSelectedTab = lastSelectedTab;
        this.width = width;
        this.height = height;
        this.location = location;
    }

    /**
     * Getter du chemin complet sur le fichier texte qui est actuellement à l'écran.
     *
     * @return chemin complet sur le fichier texte
     */
    public String getLastTextFilePath() {
        return lastTextFilePath;
    }

    /**
     * Setter du chemin complet sur le fichier texte qui est actuellement à l'écran.
     *
     * @param lastTextFilePath chemin complet sur le fichier texte
     */
    public void setLastTextFilePath(String lastTextFilePath) {
        this.lastTextFilePath = lastTextFilePath;
    }

    /**
     * Getter du chemin complet sur le fichier binaire qui est actuellement à l'écran.
     *
     * @return chemin complet sur le fichier binaire
     */
    public String getLastBinaryFilePath() {
        return lastBinaryFilePath;
    }

    /**
     * Setter du chemin complet sur le fichier binaire qui est actuellement à l'écran.
     *
     * @param lastBinaryFilePath chemin complet sur le fichier binaire
     */
    public void setLastBinaryFilePath(String lastBinaryFilePath) {
        this.lastBinaryFilePath = lastBinaryFilePath;
    }

    /**
     * Getter du chemin complet sur le fichier de sérialisation qui est actuellement à l'écran.
     *
     * @return chemin complet sur le fichier de sérialisation
     */
    public String getLastObjectFilePath() {
        return lastObjectFilePath;
    }

    /**
     * Setter du chemin complet sur le fichier de sérialisation qui est actuellement à l'écran.
     *
     * @param lastObjectFilePath chemin complet sur le fichier de sérialisation
     */
    public void setLastObjectFilePath(String lastObjectFilePath) {
        this.lastObjectFilePath = lastObjectFilePath;
    }

    /**
     * Getter du N° de l'onglet actuellement visible à l'écran.
     *
     * @return N° de l'onglet actuellement visible
     */
    public int getLastSelectedTab() {
        return lastSelectedTab;
    }

    /**
     * Setter du N° de l'onglet actuellement visible à l'écran.
     *
     * @param lastSelectedTab N° de l'onglet actuellement visible
     */
    public void setLastSelectedTab(int lastSelectedTab) {
        this.lastSelectedTab = lastSelectedTab;
    }

    /**
     * Getter de la largeur de l'ihm.
     *
     * @return la largeur de l'ihm
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter de la largeur de l'ihm.
     *
     * @param width la largeur de l'ihm
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter de la hauteur de l'ihm.
     *
     * @return la hauteur de l'ihm
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter de la hauteur de l'ihm.
     *
     * @param height la hauteur de l'ihm
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter de la position de l'ihm à l'écran.
     *
     * @returnla position de l'ihm à l'écran
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Setter de la position de l'ihm à l'écran.
     *
     * @param location la position de l'ihm à l'écran
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Le chemin complet sur le fichier texte qui est actuellement à l'écran.
     */
    private String lastTextFilePath;
    /**
     * Le chemin complet sur le fichier binaire qui est actuellement à l'écran.
     */
    private String lastBinaryFilePath;
    /**
     * Le chemin complet sur le fichier de sérialisation qui est actuellement à l'écran.
     */
    private String lastObjectFilePath;
    /**
     * e N° de l'onglet actuellement visible à l'écran.
     */
    private int lastSelectedTab;
    /**
     * La largeur de l'ihm.
     */
    private int width;
    /**
     * La hauteur de l'ihm.
     */
    private int height;
    /**
     * La position de l'ihm à l'écran.
     */
    private Point location;
}
