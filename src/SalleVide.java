/**
 * Représente une salle vide du plateau
 */
public class SalleVide extends SalleDedans {
  
  public SalleVide(int lig, int col, Plateau p) {
    super(lig, col, p);
  }

  @Override
  public String toString() {
      if (occupant != null) {
          return occupant.toString();
      } else {
          return "□"; // Un seul caractère pour une salle vide
      }
  }
  
  @Override
  public void entre(Personnage p) {
    // Comportement normal d'une salle
    super.entre(p);
  }
}