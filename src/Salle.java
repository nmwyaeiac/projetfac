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
  private int colonne, ligne;
  private Plateau plateau;

  public int getColonne() {
    return colonne;
  }
  
  public void setColonne(int colonne) {
    if (colonne >= 0) {
      this.colonne = colonne;
    }
  }

  public int getLigne() {
    return ligne;
  }
  
  public void setLigne(int ligne) {
    if (ligne >= 0) {
      this.ligne = ligne;
    }
  }

  public Plateau getPlateau() {
    return this.plateau;
  }

  private void setPlateau(Plateau p) {
    this.plateau = p;
  }

  public Salle(int ligne, int colonne, Plateau p) {
    this.setLigne(ligne);
    this.setColonne(colonne);
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