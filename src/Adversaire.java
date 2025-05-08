public abstract class Adversaire extends Personnage {
    protected Joueur joueur;

    public Adversaire(int inertie, Joueur joueur) {
        super(inertie);
        this.joueur = joueur;
    }

    protected boolean plusFortQueJoueur() {
        return getForce() >= joueur.getForce();
    }

    protected Direction directionVersJoueur() {
        int dLig = Integer.signum(joueur.getSalle().getLigne() - salle.getLigne());
        int dCol = Integer.signum(joueur.getSalle().getColonne() - salle.getColonne());
        return new Direction(dLig, dCol);
    }

    protected Direction directionFuiteJoueur() {
        return directionVersJoueur().getInverse();
    }
    
    @Override
    public void deplacer() {
        if (estNeutralise() || salle == null) return;
        
        // Vérifier que le joueur n'a pas été neutralisé
        if (joueur.estNeutralise()) {
            System.out.println("Le joueur est neutralisé, l'adversaire ne se déplace plus.");
            return;
        }
        
        // Obtenir la direction optimale (méthode à implémenter par les sous-classes)
        Direction directionOptimale = choisirDirection();
        
        // Essayer jusqu'à 8 directions différentes si nécessaire (en commençant par l'optimale)
        for (int tentative = 0; tentative < 8; tentative++) {
            Direction directionEssai = tentative == 0 ? 
                                    directionOptimale : 
                                    Direction.getDirection((directionOptimale.getRang() + tentative) % 8);
            
            Salle nouvelleSalle = salle.getVoisine(directionEssai);
            if (nouvelleSalle != null && nouvelleSalle instanceof SalleDedans) {
                SalleDedans destination = (SalleDedans) nouvelleSalle;
                if (!destination.estOccupee() || (destination.getOccupant() == joueur && !joueur.estNeutralise())) {
                    // La salle est libre ou contient le joueur (non neutralisé)
                    nouvelleSalle.entre(this);
                    System.out.println("Un adversaire s'est déplacé.");
                    salle.getPlateau().afficherPlateau();
                    return; // Sortir après un déplacement réussi
                }
            }
        }
        
        System.out.println("L'adversaire est bloqué dans toutes les directions.");
    }
    
    // Méthode abstraite que chaque type d'adversaire doit implémenter
    protected abstract Direction choisirDirection();
    
    public void interagit(Reservoir r) {
        if (r instanceof Bidon) {
            // L'adversaire prend toute l'énergie possible
            int energieDisponible = r.getEnergie();
            int energieMax = ParametresJeu.getMaxEnergie() - getEnergie();
            int quantite = Math.min(energieDisponible, energieMax);
            
            if (quantite > 0) {
                int energiePrise = ((Bidon) r).puiser(quantite);
                recevoirEnergie(energiePrise);
                System.out.println("L'adversaire a pris " + energiePrise + " unités d'énergie du bidon.");
            }
        }
    }
}
