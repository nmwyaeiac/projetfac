/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente une salle du plateau.
 * Toutes les salles ont une position lig-col et appartiennent à un plateau
 * donné.
 * Elles ont toutes une méthode entre(Personnage p) qui est exécutée quand un
 * personnage se présente à l'entrée de la salle (il avance vers la salle)
 * 
 * @author jo
 */
public abstract class Salle {
  /*
   * Déclaration des attributs d'une salle et accesseurs.
   */
  private int col, lig;
  private Plateau plateau;

  public void setCol(int col) {
    if (col >= 0) {
      this.col = col;
    }
  }

  public void setLig(int lig) {
    if (lig >= 0) {
      this.lig = lig;
    }
  }

  public Plateau getPlateau() {
    return this.plateau;
  }

  private void setPlateau(Plateau p) {
    this.plateau = p;
  }

  public Salle(int lig, int col, Plateau p) {
    this.setLig(lig);
    this.setCol(col);
    this.setPlateau(p);
  }

  /**
   * Méthode lancée par un personnage qui tente d'accéder à une salle au cours de
   * son déplacement (méthode avance du personnage).
   * Elle gère l'interaction entre le personnage et son contenu, et entérine
   * éventuellement l'entrée dans la salle.
   * Elle n'a pas de corps ici car l'intéraction dépend du type de salle
   * (sous-classes)
   * 
   * @param p
   */
  public abstract void entre(Personnage p);

}
