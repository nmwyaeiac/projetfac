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
        
        // Tente de se déplacer dans la direction optimale
        Salle nouvelleSalle = salle.getVoisine(directionOptimale);
        if (nouvelleSalle != null && nouvelleSalle instanceof SalleDedans) {
            SalleDedans destination = (SalleDedans) nouvelleSalle;
            if (!destination.estOccupee() || (destination.getOccupant() == joueur && !joueur.estNeutralise())) {
                // La salle est libre ou contient le joueur (non neutralisé)
                nouvelleSalle.entre(this);
                // Afficher le plateau après chaque déplacement d'adversaire
                System.out.println("Un adversaire s'est déplacé.");
                salle.getPlateau().afficherPlateau();
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
    @Override
protected Direction choisirDirection() {
    Direction directionBase = plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
    
    // 30% de chance de choisir une direction aléatoire
    if (random.nextDouble() < 0.3) {
        // Choisir une direction aléatoire parmi les 8 possibles
        Direction[] directions = {
            Direction.HAUT, Direction.BAS, Direction.GAUCHE, Direction.DROITE,
            Direction.HAUT_GAUCHE, Direction.HAUT_DROITE, Direction.BAS_GAUCHE, Direction.BAS_DROITE
        };
        return directions[random.nextInt(directions.length)];
    }
    
    return directionBase;
}
}