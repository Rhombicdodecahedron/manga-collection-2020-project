package app.helpers.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Permet de lire un fichier texte pour en extraire une liste de beans.
 *
 * @author Jean-Claude Stritt
 * @param <E> le type de classe-entité à extraire
 */
public class TextFileReader<E> {
  private BeanExtracter<E> extracter;

  /**
   * Constructeur.
   *
   * @param extracter l'extracteur de bean à appliquer à chaque ligne de données
   */
  public TextFileReader(BeanExtracter<E> extracter) {
    this.extracter = extracter;
  }

  /**
   * Méthode privée pour lire les lignes de texte du fichier avec extraction
   * dans une liste de beans.
   *
   * @param br un reader (canal de lecture) ouvert sur un fichier texte
   * @return une liste de bean "E" extraits avec l'objet "extracter"
   * @throws IOException ce type d'erreur est remonté un niveau en dessus
   */
  private List<E> readData(BufferedReader br) throws IOException {
    List<E> ar = new ArrayList<>();
    String line;
    if (br != null) {
      ar = new ArrayList<>();

      // boucle sur toutes les lignes du fichier
      while ((line = br.readLine()) != null) {
        E e = extracter.textToBean(line);
        if (e != null) {
          ar.add(e);
        }
      }
    }
    return ar;
  }

  /**
   * Lecture du fichier avec extraction des données.
   *
   * @param fileName un nom de fichier avec son chemin
   * @param csName   un nom de "charset" (exemple: "Windows-1250")
   * @return une liste de bean "E" extraits avec l'objet "extracter"
   */
  public List<E> read(String fileName, String csName) {
    List<E> ar = new ArrayList<>();
    Charset cs = Charset.forName(csName);
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), cs))) {

      // lecture des données
      ar = readData(br);

    } catch (IOException ex) {
    }
    return ar;
  }

  /**
   * Version simplifiée de la méthode précédente. La lecture se fait automatiquement en "UTF-8".
   *
   * @param fileName un nom de fichier avec son chemin
   * @return une liste de bean "E" extraits avec l'objet "extracter"
   */
  public List<E> read(String fileName) {
    return TextFileReader.this.read(fileName, "UTF-8");
  }

  /**
   * Cette implémentation pemet en entrée un objet de type "InputStream".
   *
   * @param inputStream un objet "fichier" de type "InputStream"
   * @return une liste de bean "E" extraits avec l'objet "extracter"
   */
  public List<E> read(InputStream inputStream) {
    List<E> ar = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      // lecture des données
      ar = readData(br);

    } catch (IOException ex) {
    }
    return ar;
  }

}
