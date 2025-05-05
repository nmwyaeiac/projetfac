import java.util.Random;

/**
 * Représente un adversaire intelligent qui tente d'avancer le plus efficacement possible 
 * en direction du joueur ou de le fuir, en tenant compte des zones de blocage.
 */
public class AdversaireIntelligent extends Adversaire {
    private static final Random random = new Random();
    
    public AdversaireIntelligent(int inertie, Joueur joueur) {
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
    
    /**
     * Évalue la pertinence d'une direction par rapport à la direction optimale
     * Plus le score est élevé, plus la direction est proche de l'optimale
     */
    private int evaluerDirection(Direction d, Direction optimale) {
        // Direction identique = meilleur score
        if (d.equals(optimale)) {
            return 100;
        }
        
        // Direction semblable (même axe général) = bon score
        if ((d.getdLig() == optimale.getdLig() && d.getdLig() != 0) || 
            (d.getdCol() == optimale.getdCol() && d.getdCol() != 0)) {
            return 70;
        }
        
        // Direction qui partage un composant (vertical ou horizontal) = score moyen
        if (d.getdLig() == optimale.getdLig() || d.getdCol() == optimale.getdCol()) {
            return 50;
        }
        
        // Direction complètement différente = score faible
        return 10;
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
                System.out.println("L'adversaire intelligent a pris " + energiePrise + " unités d'énergie du bidon.");
            }
        }
    }
    
    @Override
    public String toString() {
        return "♝";
    }
    @Override
protected Direction choisirDirection() {
    Direction optimale = plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
    
    // Trouver la meilleure direction en fonction des obstacles
    Direction meilleure = optimale;
    int meilleurScore = -1;
    
    for (int i = 0; i < 8; i++) {
        Direction direction = Direction.getDirection(i);
        Salle voisine = salle.getVoisine(direction);
        
        if (voisine instanceof SalleDedans && !((SalleDedans) voisine).estOccupee()) {
            int score = evaluerDirection(direction, optimale);
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleure = direction;
            }
        }
    }
    
    return meilleure;
}
    
}