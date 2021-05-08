package app.helpers.file;

/**
 * Interface qui spécifie la forme que doit prendre une méthode d'extraction
 * de données lors de la lecture d'un fichier au format texte. S'utilise en
 * conjonction avec la classe "TextFileReader". Voir les exemples d'utilisation
 * dans les classes de test.
 *
 * @author Jean-Claude Stritt
 */
public interface BeanExtracter<E> {

  public E textToBean (String text);

}
