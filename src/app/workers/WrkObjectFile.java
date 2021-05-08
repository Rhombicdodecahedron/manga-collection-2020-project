package app.workers;

import app.beans.IhmState;
import app.beans.Manga;
import java.io.*;
import java.util.HashMap;

/**
 * Cette classe renferme le worker de WrkObjectFile de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 29.04.2020
 */
public class WrkObjectFile {

    /**
     * Cette méthode tente de désérialiser un objet de type
     * "HashMap<String, Manga>" depuis le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait
     * contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public HashMap<String, Manga> readObjectHashMapStringManga(String filepath) {
        HashMap<String, Manga> result = null;
        try {
            result = (HashMap<String, Manga>) deserialiseObjet(filepath);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Cette méthode tente de désérialiser un objet de type "IhmState" depuis le
     * fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser et qui devrait
     * contenir un tel objet
     * @return l'objet demandé ou null pour tout problème rencontré
     */
    public IhmState readObjectAppState(String filepath) {
        IhmState result = null;
        try {
            result = (IhmState) deserialiseObjet(filepath);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * Cette méthode tente de sérialiser un objet de type
     * "HashMap<String, Manga>" dans le fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la
     * sérialisation de cet objet
     * @param mangas l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectHashMapStringManga(String filepath, HashMap<String, Manga> mangas) {
        return serialiseObjet(filepath, mangas);
    }

    /**
     * Cette méthode tente de sérialiser un objet de type "IhmState" dans le
     * fichier spécifié en paramètres.
     *
     * @param filepath le chemin complet du fichier à utiliser pour la
     * sérialisation de cet objet
     * @param state l'objet a sérialiser
     * @return true si et seulement si tout s'est parfaitement bien passé
     */
    public boolean writeObjectAppState(String filepath, IhmState state) {
        return serialiseObjet(filepath, state);
    }

    /**
     * Cette fonction sérialise l'objet passé en paramètre dans un fichier.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @param obj l'objet a sérialiser
     * @return l'objet a-t-il été correctement sérialisé ?
     */
    private boolean serialiseObjet(String filepath, Object obj) {

        boolean result = false;
        ObjectOutputStream serialisation = null;
        try {
            serialisation = new ObjectOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(filepath)));
            serialisation.writeObject(obj);
            serialisation.flush();
            serialisation.close();
            serialisation = null;

            result = true;
        } catch (Exception e) {
        } finally {
            // Toujours fermer le fichier si pas déjà fait !
            if (serialisation != null) {
                try {
                    serialisation.close();
                    serialisation = null;
                } catch (IOException ioe2) {
                    // On peut l'ignorer, le cas est déjà traité
                }
            }
        }
        return result;

    }

    /**
     * Cette fonction desérialise un objet précédemment sérialisé dans un
     * fichier.
     *
     * @param filepath le chemin complet du fichier à utiliser
     * @return l'objet s'il a été correctement désérialisé, sinon null
     */
    private Object deserialiseObjet(String filepath) {
        Object result = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(filepath)));
            result = in.readObject();
            in.close();
            in = null;

        } catch (Exception e) {
            result = null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;

                } catch (Exception e) {

                }
            }
        }

        return result;

    }
}
