/**
 * Représente la salle où le joueur commence la partie
 */
public class SalleJoueur extends SalleDedans {
  
  public SalleJoueur(int lig, int col, Plateau p) {
    super(lig, col, p);
  }

  @Override
  public String toString() {
    return "♜.";
  }
  
  @Override
  public void entre(Personnage p) {
    // Comportement similaire à une salle normale
    super.entre(p);
  }
}