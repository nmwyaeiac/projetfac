import java.util.Random;

/**
 * Représente un adversaire velléitaire qui se déplace avec une part d'aléatoire.
 * Il a plus de chance d'aller vers/de fuir le joueur, mais il peut ponctuellement
 * aller dans une direction différente ou rester immobile.
 */
public class AdversaireVelleitaire extends Adversaire {
    private static final Random random = new Random();
    
    public AdversaireVelleitaire(int inertie, Joueur joueur) {
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
        
        // On biaise le mouvement avec 70% de chance de prendre la direction optimale
        Direction directionChoisie;
        int choix = random.nextInt(10);
        
        if (choix < 7) {
            // 70% de chance de prendre la direction optimale
            directionChoisie = directionOptimale;
        } else if (choix < 9) {
            // 20% de chance de prendre une direction aléatoire
            directionChoisie = new Direction(random.nextInt(8));
        } else {
            // 10% de chance de ne pas bouger
            System.out.println("L'adversaire reste immobile.");
            return;
        }
        
        // Tente de se déplacer dans la direction choisie
        Salle nouvelleSalle = salle.getVoisine(directionChoisie);
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
        return "♟";
    }
}