/**
 * Représente une salle vide du plateau, sans bidon ni fonction spéciale.
 * Les personnages peuvent y entrer librement s'il n'y a pas déjà un occupant.
 */
public class SalleVide extends SalleDedans {
  
  /**
   * Constructeur d'une salle vide.
   * 
   * @param lig Ligne de la salle sur le plateau
   * @param col Colonne de la salle sur le plateau
   * @param p Plateau contenant la salle
   */
  public SalleVide(int lig, int col, Plateau p) {
    super(lig, col, p); // Appel au constructeur de la classe parente
  }

  /**
   * Représentation textuelle de la salle vide.
   * Affiche le symbole de l'occupant s'il y en a un, sinon le symbole de salle vide.
   * 
   * @return Symbole représentant le contenu de la salle
   */
  @Override
  public String toString() {
      if (occupant != null) {
          return occupant.toString(); // Affiche le symbole de l'occupant
      } else {
          return "□"; // Symbole pour une salle vide
      }
  }
  
  /**
   * Gère l'entrée d'un personnage dans la salle.
   * Utilise le comportement standard d'une SalleDedans.
   * 
   * @param p Personnage qui tente d'entrer
   */
  @Override
  public void entre(Personnage p) {
    // Comportement normal d'une salle
    super.entre(p);
  }
}