public class SalleBordure extends Salle {
  
  public SalleBordure(int lig, int col, Plateau p) {
    super(lig, col, p);
  }

  @Override
  public String toString() {
      return "■";
  }
  
  @Override
  public void entre(Personnage p) {
    // Un personnage ne peut pas entrer dans une bordure
    System.out.println("Déplacement impossible : bordure du plateau atteinte.");
  }
}