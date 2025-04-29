public class SalleVide extends Salle {
  public SalleVide(int lig, int col, Plateau p) {
    super(lig, col, p);
  }

  @Override
  public String toString() {
    return Plateau.FOND; // Affiche un fond vide pour les salles vides
  }
}
