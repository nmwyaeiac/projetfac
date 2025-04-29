public class SalleBordure extends Salle {
  public SalleBordure(int lig, int cpl, Plateau p) {
    super(lig, col, p);
  }

  @Override
  public String toString() {
    return Plateau.BORD;
  }
}
