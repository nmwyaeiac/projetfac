/**
 * Représente un adversaire déterminé qui avance systématiquement 
 * dans la direction du joueur ou s'en éloigne, selon sa force.
 */
public class AdversaireDetermine extends Adversaire {
    
    public AdversaireDetermine(int inertie, Joueur joueur) {
        super(inertie, joueur);
    }
    
    @Override
    public void deplacer() {
        if (estNeutralise() || salle == null) return;
        
        // Vérifier que le joueur n'a pas été neutralisé
        if (joueur.estNeutralise()) {
            System.out.println("Le joueur est neutralisé, l'adversaire ne se déplace plus.");
            return;
        }
        
        // Direction optimale (vers ou loin du joueur selon la force)
        Direction directionOptimale = plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
        
        // Tente de se déplacer dans la direction optimale
        Salle nouvelleSalle = salle.getVoisine(directionOptimale);
        if (nouvelleSalle != null && nouvelleSalle instanceof SalleDedans) {
            SalleDedans destination = (SalleDedans) nouvelleSalle;
            if (!destination.estOccupee() || (destination.getOccupant() == joueur && !joueur.estNeutralise())) {
                // La salle est libre ou contient le joueur (non neutralisé)
                nouvelleSalle.entre(this);
            } else {
                System.out.println("L'adversaire ne peut pas entrer dans la salle (déjà occupée).");
            }
        } else {
            System.out.println("L'adversaire ne peut pas se déplacer dans cette direction.");
        }
    }
    
    @Override
    public void interagit(Reservoir r) {
        if (r instanceof Bidon) {
            // L'adversaire prend toute l'énergie possible
            int energieDisponible = r.getEnergie();
            int energieMax = ParametresJeu.MAX_ENERGIE - getEnergie();
            int quantite = Math.min(energieDisponible, energieMax);
            
            if (quantite > 0) {
                int energiePrise = ((Bidon) r).puiser(quantite);
                recevoirEnergie(energiePrise);
                System.out.println("L'adversaire a pris " + energiePrise + " unités d'énergie du bidon.");
            }
        }
    }
    
    @Override
    public String toString() {
        return "♞";
    }
}