/**
 * Classe abstraite représentant un personnage du jeu.
 * Chaque personnage possède une réserve d'énergie, une inertie et occupe une salle.
 * Les personnages peuvent se déplacer, combattre et interagir avec leur environnement.
 */
public abstract class Personnage {
  /** Inertie du personnage (coefficient multiplicateur de force) */
  protected int inertie;
  /** Salle occupée par le personnage */
  protected SalleDedans salle;
  /** Réserve d'énergie du personnage */
  protected ReserveLimitee reserve;

  /**
   * Récupère l'énergie actuelle du personnage.
   * 
   * @return Quantité d'énergie du personnage
   */
  public int getEnergie() {
    return this.reserve.getEnergie();
  }

  /**
   * Récupère l'inertie du personnage.
   * 
   * @return Valeur d'inertie du personnage
   */
  public int getInertie() {
    return this.inertie;
  }

  /**
   * Récupère la salle occupée par le personnage.
   * 
   * @return Salle occupée par le personnage
   */
  public SalleDedans getSalle() {
    return this.salle;
  }

  /**
   * Définit la salle occupée par le personnage.
   * 
   * @param salle Nouvelle salle à occuper
   */
  public void setSalle(SalleDedans salle) {
    this.salle = salle;
  }

  /**
   * Fait perdre de l'énergie au personnage.
   * 
   * @param quantite Quantité d'énergie à perdre
   */
  public void perdreEnergie(int quantite) {
    this.reserve.puiser(quantite);
  }

  /**
   * Fait gagner de l'énergie au personnage.
   * 
   * @param quantite Quantité d'énergie à recevoir
   */
  public void recevoirEnergie(int quantite) {
    this.reserve.stocker(quantite);
  }

  /**
   * Calcule la force du personnage (énergie * inertie).
   * 
   * @return Force du personnage
   */
  public int getForce() {
    return this.getEnergie() * this.getInertie();
  }

  /**
   * Vérifie si le personnage est neutralisé (énergie ≤ 0).
   * 
   * @return true si le personnage est neutralisé, false sinon
   */
  public boolean estNeutralise() {
    return this.getEnergie() <= 0;
  }

  /**
   * Déplace le personnage vers une nouvelle salle.
   * Libère la salle d'origine et occupe la destination.
   * 
   * @param destination Salle de destination
   */
  public void migre(SalleDedans destination) {
    // Libérer la salle d'origine
    if (this.salle != null) {
        this.salle.setOccupant(null);
        
        // Convertir la salle du joueur en salle vide quand il part
        if (this instanceof Joueur && this.salle.getOccupant() == null) {
            // Récupère les coordonnées de la salle
            int lig = this.salle.getLigne();
            int col = this.salle.getColonne();
            Plateau p = this.salle.getPlateau();
            
            // Remplace la salle actuelle par une SalleVide
            SalleVide nouvelleSalle = new SalleVide(lig, col, p);
            p.setSalle(nouvelleSalle, lig, col);
        }
    }
    
    // Affecter la nouvelle salle au personnage
    this.setSalle(destination);
    
    // Affecter le personnage à la nouvelle salle
    destination.setOccupant(this);
    
    // Faire décroître l'énergie (coût du déplacement)
    this.perdreEnergie(1);
  }

  /**
   * Méthode abstraite pour gérer le déplacement du personnage.
   * Doit être implémentée par les sous-classes.
   */
  public abstract void deplacer();

  /**
   * Méthode abstraite pour gérer l'interaction avec un réservoir.
   * Doit être implémentée par les sous-classes.
   * 
   * @param r Réservoir avec lequel interagir
   */
  public abstract void interagit(Reservoir r);

  /**
   * Prend l'énergie d'un autre personnage après un combat victorieux.
   * 
   * @param autre Personnage vaincu
   */
  public void prendEnergie(Personnage autre) {
    // Vérifie que l'autre personnage n'est pas déjà neutralisé
    if (!autre.estNeutralise()) {
      // Prend toute l'énergie de l'autre personnage
      int energiePrise = autre.getEnergie();
      autre.perdreEnergie(energiePrise);
      this.recevoirEnergie(energiePrise);
      System.out.println(this + " prend " + energiePrise + " unités d'énergie à " + autre);
    }
  }

  /**
   * Gère la défaite du personnage après un combat.
   * Si le personnage est neutralisé, il est retiré de sa salle.
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
   * Gère un combat entre deux personnages.
   * Le personnage avec la plus grande force gagne et prend l'énergie du perdant.
   * 
   * @param p1 Premier personnage
   * @param p2 Second personnage
   */
  public static void combat(Personnage p1, Personnage p2) {
    // Calcule la force de chaque personnage
    int force1 = p1.getForce();
    int force2 = p2.getForce();

    System.out.println("Combat !");
    System.out.println("Force de " + p1 + " : " + force1);
    System.out.println("Force de " + p2 + " : " + force2);

    // Détermine le vainqueur ou déclare l'égalité
    if (force1 != force2) {
      if (force1 > force2) {
        // p1 gagne
        System.out.println(p1 + " gagne !");
        p1.prendEnergie(p2);
        p2.perd();
      } else {
        // p2 gagne
        System.out.println(p2 + " gagne !");
        p2.prendEnergie(p1);
        p1.perd();
      }
    } else {
      System.out.println("Égalité, pas de gagnant !");
    }
  }

  /**
   * Constructeur d'un personnage.
   * 
   * @param inertie Inertie du personnage
   */
  public Personnage(int inertie) {
    this.inertie = inertie;
    this.reserve = new ReserveLimitee(); // Crée une réserve d'énergie limitée
    this.salle = null; // Initialement, pas dans une salle
  }
}