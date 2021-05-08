/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.workers;

import app.beans.Manga;
import app.beans.Tome;
import java.util.ArrayList;

/**
 *
 * @author Alexis
 */
public class WrkTome {

    /**
     * Constructeur de la classe WrkTome
     */
    public WrkTome() {
    }

    /**
     *
     * Cette méthode permet d'ajouter un nouveau tome à un manga. Elle prend en
     * paramètre l'objet manga et l'objet tome que nous voulons ajouter.
     *
     * @param manga représente le manga auquel nous voulons ajouter le tome.
     * @param tome représente le tome que nous voulons ajouter au manga.
     * @return un booléen true ou false si l'ajout s'est correctement effectué
     */
    public boolean ajouteTome(Manga manga, Tome tome) {
        boolean result = false;
        if (manga != null && tome != null) {
            if (manga.getTomes() != null) {
                manga.getTomes().add(tome);
                result = true;
            } else {
                ArrayList<Tome> tomes = new ArrayList<>();
                tomes.add(tome);
                manga.setTomes(tomes);
                result = true;
            }
        }
        return result;
    }

    /**
     *
     * Cette méthode permet de supprimer le tome du manga pris en paramètre.
     *
     * @param tome représente l'objet tome qu'il faut supprimer.
     * @param manga représente l'objet manga auquel nous voulons supprimer le
     * tome.
     * @return un booléen true ou false si la suppression s'est bien effectué.
     */
    public boolean supprimeTome(Tome tome, Manga manga) {
        return manga.getTomes().remove(tome);
    }

}
