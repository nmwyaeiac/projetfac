import java.util.Scanner;

public class Direction {
  public static final Direction DROITE = new Direction("l");
  public static final Direction GAUCHE = new Direction("h");
  public static final Direction HAUT = new Direction("k");
  public static final Direction BAS = new Direction("j");
  public static final Direction HAUT_GAUCHE = new Direction("kh");
  public static final Direction HAUT_DROITE = new Direction("kl");
  public static final Direction BAS_GAUCHE = new Direction("jh");
  public static final Direction BAS_DROITE = new Direction("jl");
  private static final Direction[] DIRECTIONS = { DROITE, HAUT_DROITE, HAUT, HAUT_GAUCHE, GAUCHE, BAS_GAUCHE, BAS,
      BAS_DROITE };

  private int dLig;

  public int getdLig() {
    return this.dLig;
  }

  private void setdLig(int dLig) {
    this.dLig = dLig;
  }

  private int dCol;

  public int getdCol() {
    return this.dCol;
  }

  private void setdCol(int dCol) {
    this.dCol = dCol;
  }

  public int getRang() {
    if (!isValide())
      throw new RuntimeException("Direction invalide");
    double dCol = this.dCol, dLig = this.dLig;
    if (Math.abs(dCol * dLig) == 1)
      dCol = dCol * Math.cos(Math.PI / 4);
    int rang = (int) Math.round(Math.acos(dCol) / Math.PI * 4);
    if (dLig > 0)
      rang = 7 - rang + 1;
    return rang;
  }
  public static Direction getDirectionQuelconque() {
    try (Scanner scanner = new Scanner(System.in)) {
      Direction d;
      do {
          System.out.print("Entrez une direction avec 'h','j','k','l' ou combinaison (ex: 'kh' pour haut gauche) : ");
          String input = scanner.nextLine().trim();
          d = new Direction(input);
      } while (!d.isValide());
      return d;
    }
}
  public static Direction getDirectionHorizontaleVerticale() {
    Direction d;
    do {
      d = new Direction(Lire.S("Entrez une direction 'h','j','k','l'"));
    } while (!d.isHorizontaleVerticale());
    return d;
  }

  public static Direction getDirection(int rang) {
    rang = rang % 8;
    return Direction.DIRECTIONS[new Direction(rang).getRang()];
  }

  public static Direction getDirection(int dLig, int dCol) {
    Direction nouvelle = new Direction(dLig, dCol);
    if (nouvelle.isValide())
      return Direction.DIRECTIONS[nouvelle.getRang()];
    else
      return nouvelle;
  }

  public static Direction getDirection(String dirTexte) {
    Direction nouvelle = new Direction(dirTexte);
    return getDirection(nouvelle.getdLig(), nouvelle.getdCol());
  }

  public boolean isValide() {
    boolean res = !(getdCol() == 0 && getdLig() == 0);
    if (res)
      res = getdCol() >= -1 && getdCol() <= 1;
    if (res)
      res = getdLig() >= -1 && getdLig() <= 1;
    return res;
  }

  public boolean isHorizontaleVerticale() {
    return this.isValide() && (this.getRang() % 2 == 0);
  }

  public Direction getSucc() {
    if (isValide())
      return Direction.getDirection(this.getRang() + 1);
    return null;
  }

  public Direction getPred() {
    if (isValide())
      return Direction.getDirection(this.getRang() - 1);
    return null;
  }

  private static String getNormalisation(String dirTexte) {
    String simplification = dirTexte.toLowerCase();
    simplification = simplification.replace("-", "");
    simplification = simplification.replaceAll(" ", "");
    simplification = simplification.replaceAll("_", "");
    return simplification;
  }

  public Direction(int dLig, int dCol) {
    this.setdLig(dLig);
    this.setdCol(dCol);
  }

  public Direction() {
    this(DROITE.getdLig(), DROITE.getdCol());
  }

  public Direction(String dirTexte) {
    dirTexte = getNormalisation(dirTexte);
    if (dirTexte != null) {
      int taille = dirTexte.length();
      if (taille > 2) {
        setdCol(0);
        setdLig(0);
      } else if (taille > 0) {
        setDxDy(dirTexte.charAt(0));
        if (taille > 1)
          setDxDy(dirTexte.charAt(1));
      }
    }
  }

  public Direction(int rang) {
    this(-(int) Math.round(Math.sin(rang * Math.PI / 4)), (int) Math.round(Math.cos(rang * Math.PI / 4)));
  }

  private void setDxDy(char dir) {
    switch (dir) {
      case 'k' -> this.dLig -= 1;
      case 'j' -> this.dLig += 1;
      case 'l' -> this.dCol += 1;
      case 'h' -> this.dCol -= 1;
    }
  }

  public Direction getCombinaison(Direction autre) {
    if (autre == null || !this.isValide() || !autre.isValide())
      return null;
    Direction rep = Direction.getDirection(this.getdLig() + autre.getdLig(), this.getdCol() + autre.getdCol());
    if (rep.isValide())
      return rep;
    return null;
  }

  public Direction getInverse() {
    return Direction.getDirection(this.getRang() + 4);
  }

  @Override
  public String toString() {
    if (isValide()) {
      String[] tous = { "droite", "haut droite", "haut", "haut gauche", "gauche", "bas gauche", "bas", "bas droite" };
      return tous[getRang()];
    }
    return "invalide";
  }

  @Override
  public boolean equals(Object autre) {
    if (this == autre)
      return true;
    if (autre == null)
      return false;
    if (getClass() != autre.getClass())
      return false;
    Direction o = (Direction) autre;
    return this.getdLig() == o.getdLig() && this.getdCol() == o.getdCol();
  }
}
