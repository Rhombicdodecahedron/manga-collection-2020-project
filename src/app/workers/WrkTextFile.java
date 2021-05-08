package app.workers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Cette classe renferme le worker de WrkTextFile de l'application.
 *
 * @author Stella Alexis
 * @version 1.0
 * @since 29.04.2020
 */
public class WrkTextFile {

    /**
     * Cette constante spécifie l'encodage à utiliser lors de l'écriture et la
     * lecture du fichier texte. Il est très important de toujours spécifier
     * afin d'éviter de dépendre de la valeur par défaut qui est justement
     * dépendante de la plateforme, voir même la version de Java.
     */
    public final static Charset TEXT_FILE_CHARSET = StandardCharsets.UTF_8;

    /**
     * Lit et retourne l'ensemble des lignes présentes dans un fichier texte. En
     * cas de problème(s) rencontré aucune ligne ne sera retournée (null) de
     * manière à ce qu'on puisse s'en rendre compte.
     *
     * @param filepath le chemin complet du fichier texte à lire
     *
     * @return l'ensemble des lignes du fichier texte, ou null en cas de
     * problème(s) rencontré(s)
     */
    public ArrayList<String> readTextFile(String filepath) {

        ArrayList<String> resultat = null;

        BufferedReader br = null;

        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filepath), TEXT_FILE_CHARSET));

            resultat = new ArrayList<String>();

            String ligne;
            while ((ligne = br.readLine()) != null) {
                resultat.add(ligne);
            }

            br.close();
            br = null;
        } catch (Exception e) {
            resultat = null;
        } finally {
            // Toujours fermer le fichier si pas déjà fait !
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException ioe2) {
                    // On peut l'ignorer, le cas est déjà traité
                }
            }
        }

        return resultat;
    }

    /**
     * Écrit des lignes de texte dans un fichier texte. Cette méthode s'assure
     * que le fichier texte produit soit complet et refermé ou alors, en cas de
     * problème(s) rencontré, celui-ci sera supprimé et ne traînera pas
     * incomplet.
     *
     * @param filepath le chemin complet sur le fichier texte à produire
     * @param linesToWrite l'ensemble des lignes de texte à écrire dans le
     * fichier texte
     *
     * @return vrai si et seulement si le fichier texte a pu être produit avec
     * l'intégralité des lignes de texte et correctement refermé
     */
    public boolean writeTextFile(String filepath, ArrayList<String> linesToWrite) {

        boolean resultat = false;

        if (linesToWrite != null) {    // S'il n'y a rien à faire on ne fait rien (pas même effacer le fichier) !

            BufferedWriter bw = null;

            try {
                // L'ancien fichier sera remplacé par le nouveau contenu, même si vide
                bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(filepath, false), TEXT_FILE_CHARSET));

                for (String ligne : linesToWrite) {
                    if (ligne != null) {
                        bw.write(ligne);
                        bw.newLine();
                    }
                }

                bw.flush();
                bw.close();
                bw = null;

                resultat = true;    // Si on est ici c'est que tout roule !
            } catch (Exception e) {
            } finally {
                // Toujours fermer le fichier si pas déjà fait !
                if (bw != null) {
                    try {
                        bw.close();
                        bw = null;
                    } catch (IOException ioe2) {
                        // On peut l'ignorer, le cas est déjà traité
                    }
                }

                // Si l'écriture a échoué d'une façon où d'une autre, ne pas laisser un fichier incomplet
                if (!resultat) {
                    try {
                        new File(filepath).delete();
                    } catch (Exception e) {
                    }
                }
            }
        }

        return resultat;
    }

    /**
     * Rajoute une ligne à la fin du contenu existant d'un fichier texte.
     *
     * @param filepath le chemin complet du fichier texte à la fin duquel il
     * faut écrire
     * @param newLineContent la ligne à rajouter en fin de fichier
     *
     * @return vrai si et seulement si l'ajout a pu s'effectuer correctement
     */
    public boolean appendToTextFile(String filepath, String newLineContent) {

        boolean resultat = false;

        if (newLineContent != null) {    // S'il n'y a rien à faire on ne fait rien !

            BufferedWriter bw = null;

            try {
                // L'ancien fichier ne sera PAS remplacé par le nouveau contenu, il sera rajouté à la fin de ce fichier.
                bw = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(filepath, true), TEXT_FILE_CHARSET));

                bw.write(newLineContent);
                bw.newLine();

                bw.flush();
                bw.close();
                bw = null;

                resultat = true;    // Si on est ici c'est que tout roule !
            } catch (Exception e) {
            } finally {
                // Toujours fermer le fichier si pas déjà fait !
                if (bw != null) {
                    try {
                        bw.close();
                        bw = null;
                    } catch (IOException ioe2) {
                        // On peut l'ignorer, le cas est déjà traité
                    }
                }
            }
        }

        return resultat;
    }
}
