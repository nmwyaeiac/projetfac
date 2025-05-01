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
  protected SalleDedans salle;
  protected ReserveLimitee reserve;

  /**
   * Restitue la quantité d'énergie restante pour le personnage.
   * C'est la quantité d'énergie de la réserve d'énergie (ReserveLimitee) associée
   * au personnage
   * 
   * @return un entier positif ou nul
   */
  public int getEnergie() {
    return this.reserve.getEnergie();
  }

  public int getInertie() {
    return this.inertie;
  }

  public SalleDedans getSalle() {
    return this.salle;
  }

  public void setSalle(SalleDedans salle) {
    this.salle = salle;
  }

  /**
   * Diminue l'énergie du personnage d'une unité (exécuté à chaque changement de
   * salle effectif)
   */
  public void perdreEnergie(int quantite) {
    this.reserve.puiser(quantite);
  }

  /**
   * Augmente l'énergie du personnage
   */
  public void recevoirEnergie(int quantite) {
    this.reserve.stocker(quantite);
  }

  /**
   * Restitue la force du personnage (son inertie fois son énergie).
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
    return this.getEnergie() <= 0;
  }

  /**
   * Migration du personnage de sa salle courante à une salle de destination.
   * Elle est possible si la salle de destination n'est pas occupée.
   * Cela consiste à libérer la salle d'origine (la salle couramment occupée), à
   * affecter la salle de destination au personnage et à faire décroître son
   * énergie
   * 
   * @param destination
   */
  public void migre(SalleDedans destination) {
    // Libérer la salle d'origine
    if (this.salle != null) {
        this.salle.setOccupant(null);
        
        // Si c'était une SalleJoueur, la transformer en SalleVide
        if (this instanceof Joueur && this.salle instanceof SalleJoueur) {
            int lig = this.salle.getLigne();
            int col = this.salle.getColonne();
            Plateau p = this.salle.getPlateau();
            SalleVide nouvelleSalle = new SalleVide(lig, col, p);
            p.setSalle(nouvelleSalle, lig, col);
        }
    }
    
    // Affecter la nouvelle salle au personnage
    this.setSalle(destination);
    
    // Affecter le personnage à la nouvelle salle
    destination.setOccupant(this);
    
    // Faire décroître l'énergie
    this.perdreEnergie(1);
}

  /**
   * Déplacement du personnage
   */
  public abstract void deplacer();

  /**
   * Interaction avec un réservoir (bidon ou collecteur)
   */
  public abstract void interagit(Reservoir r);

  /**
   * Action d'un personnage pour prendre de l'énergie à un autre personnage.
   * Il prend de l'énergie dans la réserve d'énergie de l'autre personnage.
   * 
   * @param autre Le personnage à qui l'énergie est prise
   */
  public void prendEnergie(Personnage autre) {
    if (!autre.estNeutralise()) {
      int energiePrise = autre.getEnergie();
      autre.perdreEnergie(energiePrise);
      this.recevoirEnergie(energiePrise);
      System.out.println(this + " prend " + energiePrise + " unités d'énergie à " + autre);
    }
  }

  /**
   * Action à effectuer après la perte d'un combat.
   */
  public void perd() {
    System.out.println(this + " a perdu le combat.");
    if (this.estNeutralise()) {
      System.out.println(this + " a été neutralisé !");
      // Si dans une salle, on libère la salle de manière sécurisée
      if (this.salle != null) {
        SalleDedans salleTemp = this.salle;
        this.salle = null; // Détacher d'abord le personnage de la salle
        salleTemp.setOccupant(null); // Puis détacher la salle du personnage
      }
    }
  }

  /**
   * Action de combat entre deux personnages.
   * Le gagnant est celui qui a la plus grande force. Il prend de l'énergie au
   * perdant (utilisation de prendEnergie(Personnage autre)) et la méthode perd()
   * est lancée pour le perdant
   * 
   * @param p1 un Personnage
   * @param p2 un autre Personnage
   */
  public static void combat(Personnage p1, Personnage p2) {
    int force1 = p1.getForce();
    int force2 = p2.getForce();

    System.out.println("Combat !");
    System.out.println("Force de " + p1 + " : " + force1);
    System.out.println("Force de " + p2 + " : " + force2);

    if (force1 != force2) {
      if (force1 > force2) {
        System.out.println(p1 + " gagne !");
        p1.prendEnergie(p2);
        p2.perd();
      } else {
        System.out.println(p2 + " gagne !");
        p2.prendEnergie(p1);
        p1.perd();
      }
    } else {
      System.out.println("Égalité, pas de gagnant !");
    }
  }

  /**
   * Initialise l'inertie, la réserve d'énergie et la position du personnage
   */
  public Personnage(int inertie) {
    this.inertie = inertie;
    this.reserve = new ReserveLimitee();
    this.salle = null;
  }
}