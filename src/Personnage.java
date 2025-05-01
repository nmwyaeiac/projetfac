public abstract class Personnage {
  protected int inertie;
  protected SalleDedans salle;
  protected ReserveLimitee reserve;

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

  public void perdreEnergie(int quantite) {
    this.reserve.puiser(quantite);
  }

  public void recevoirEnergie(int quantite) {
    this.reserve.stocker(quantite);
  }

  public int getForce() {
    return this.getEnergie() * this.getInertie();
  }

  public boolean estNeutralise() {
    return this.getEnergie() <= 0;
  }

  public void migre(SalleDedans destination) {
    // Libérer la salle d'origine
    if (this.salle != null) {
        this.salle.setOccupant(null);
        
        // Convertir la salle du joueur en salle vide quand il part
        // IMPORTANT: ne pas créer de nouvelle SalleJoueur, mais remplacer par SalleVide
        if (this instanceof Joueur && this.salle.getOccupant() == null) {
            int lig = this.salle.getLigne();
            int col = this.salle.getColonne();
            Plateau p = this.salle.getPlateau();
            
            // Remplacer la SalleJoueur par une SalleVide
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

  public abstract void deplacer();

  public abstract void interagit(Reservoir r);

  public void prendEnergie(Personnage autre) {
    if (!autre.estNeutralise()) {
      int energiePrise = autre.getEnergie();
      autre.perdreEnergie(energiePrise);
      this.recevoirEnergie(energiePrise);
      System.out.println(this + " prend " + energiePrise + " unités d'énergie à " + autre);
    }
  }

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

  public Personnage(int inertie) {
    this.inertie = inertie;
    this.reserve = new ReserveLimitee();
    this.salle = null;
  }
}