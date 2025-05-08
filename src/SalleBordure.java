/**
 * Représente une salle de bordure du plateau de jeu.
 * Ces salles sont inaccessibles aux personnages et servent de limite au plateau.
 */
public class SalleBordure extends Salle {
  
  /**
   * Constructeur d'une salle de bordure.
   * 
   * @param lig Ligne de la salle sur le plateau
   * @param col Colonne de la salle sur le plateau
   * @param p Plateau contenant la salle
   */
  public SalleBordure(int lig, int col, Plateau p) {
    super(lig, col, p); // Appel au constructeur de la classe parente
  }

  /**
   * Représentation textuelle de la bordure.
   * 
   * @return Symbole représentant une bordure (■)
   */
  @Override
  public String toString() {
      return "■"; // Symbole pour une bordure
  }
  
  /**
   * Gère la tentative d'entrée d'un personnage dans une bordure.
   * L'entrée est toujours refusée avec un message d'erreur.
   * 
   * @param p Le personnage qui tente d'entrer dans la bordure
   */
  @Override
  public void entre(Personnage p) {
    // Un personnage ne peut pas entrer dans une bordure
    System.out.println("Déplacement impossible : bordure du plateau atteinte.");
  }
}