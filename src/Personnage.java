import java.util.Random;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente les personnages du jeu qui, dans cette version, peuvent être un
 * Joueur ou un Adversaire.
 * Les personnages ont tous une inertie, une réserve d'énergie initialisée à 10
 * et ont une position qui est une salle interne au plateau (SalleDedans)
 * 
 * @author jo
 */
public abstract class Personnage {
  /*
   * Déclaration des attributs communs à tous les personnages et accesseurs.
   */
  protected int inertie;
  protected SalleDedans position;
  protected ReserveLimitee reserve;

  /**
   * Restitue la quantité d'énergie restante pour le personnage.
   * C'est la quantité d'énergie de la réserve d'énergie (ReserveLimitee) associée
   * au personnage
   * 
   * @return un entier positif ou nul
   */
  public int getEnergie() {
    return this.getReserve().getContenu();
  }

  public int getInertie() {
    return this.inertie;
  }

  public void setEnergie(int energie) {
    if (energie > 0)
      this.getReserve().SetContenu(energie);
    else
      throw new IllegalArgumentException("impossible d'assigner une valeur inferieur a 0 ");
  }

  public void setInertie(int inertie) {
    this.inertie = inertie;
  }

  private ReserveLimitee getReserve() {
    return this.reserve;
  }

  private void setReserve(ReserveLimitee reserve) {
    this.reserve = reserve;
  }

  /**
   * Diminue l'énergie du personnage d'une unité (exécuté à chaque changement de
   * salle effectif)
   */
  public void decEnergie() {
    this.getReserve().modifEnergie(-1);
  }

  /**
   * Restitue la force du personnage (sont inertie fois son énergie).
   * 
   * @return un entier positif ou nul
   */
  public int getForce() {
    return this.getEnergie() * this.getInertie();
  }

  /**
   * Restitue true si la réserve d'énergie du personnage est vide
   * 
   * @return
   */
  public boolean estNeutralise() {
    return this.getReserve().estVide();
  }

  /**
   * Migration du personnage de sa salle courante à une salle de destination.
   * Elle est possible si la salle de destination n'est pas occupée.
   * Cela consiste à libérer la salle d'origine (la salle couramment occupée), à
   * affecter la salle de destination au personnage et à faire décroître son
   * énergie
   * 
   * @param destination`
   */
  public void migre(SalleDedans destination) {
  }

  public void avance() {
  }

  /**
   * Action d'un personnage pour prendre de l'énergie à un autre personnage.
   * Il prend de l'énergie dans la réserve d'énergie de l'autre personnage.
   * 
   * @param autre Le personnage à qui l'énergie est prise
   */
  public void prendEnergie(Personnage autre) {
    if (!autre.estNeutralise()) {
      this.prendEnergie(autre.getReserve());
    }
  }

  /**
   * Action d'un personnage pour prendre de l'énergie dans une réserve limitée.
   * Le code dépend du type de personnage (Adversaire ou Joueur).
   * 
   * @param r La réserve d'énergie limitée (bidon ou réserve d'énergie du
   *          personnage)
   */
  public abstract void prendEnergie(ReserveLimitee r);

  /**
   * Action à effectuer après la perte d'un combat.
   * Le code dépend du type de Personnage.
   */
  public abstract void perd();

  /**
   *
   * @param p
   */
  public void rencontre(Personnage p) {
  }

  /**
   * Action de combat entre deux personnages.
   * En fait, ce sont un Joueur et un Adversaire, mais il n'est pas besoin de le
   * spécifier.
   * Le gagnant est celui qui a la plus grande force. Il prend de l'énergie au
   * perdant (utilisation de prendEnergie(Personnage autre)) et la méthode perd()
   * est lancée pour le perdant
   * Le code ne dépend pas de qui est le joueur et qui est l'adversaire parce
   * c'est prendEnergie(…) et perd(…) qui seront différent suivant qui l'exécute.
   * 
   * @param p1 un Personnage
   * @param p2 un autre Personnage
   */
  public static void combat(Personnage p1, Personnage p2) {
    int force1 = p1.getForce();
    int force2 = p2.getForce();

    System.out.println("Combat !");
    System.out.println("Force du premier personnage : " + force1);
    System.out.println("Force du second personnage : " + force2);

    if (force1 != force2) {
      if (force1 > force2) {
        System.out.println("Le premier personnage gagne !");
        p1.prendEnergie(p2);
        p2.perd();
      } else {
        System.out.println("Le second personnage gagne !");
        p2.prendEnergie(p1);
        p1.perd();
      }
    } else {
      System.out.println("Égalité , pas de gagnant !");
    }
  }

  /**
   * Initialise l'inertie, la réserve d'énergie et la position du personnage
   */
  public Personnage(int inertie) {
    this.setInertie(inertie);
    this.setReserve(new ReserveLimitee());
  }

}
