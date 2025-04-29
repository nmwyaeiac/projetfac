import java.util.ArrayList;
import java.util.List;
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
        
        // Détermine si l'adversaire doit s'approcher ou fuir
        boolean approcher = plusFortQueJoueur();
        
        // Cherche les directions possibles (non bloquées)
        List<Direction> directionsLibres = new ArrayList<>();
        
        // Teste les 8 directions possibles
        for (int i = 0; i < 8; i++) {
            Direction d = new Direction(i);
            Salle voisine = salle.getVoisine(d);
            
            if (voisine != null && voisine instanceof SalleDedans && 
                    (!((SalleDedans) voisine).estOccupee() || 
                    (((SalleDedans) voisine).getOccupant() == joueur && !joueur.estNeutralise()))) {
                directionsLibres.add(d);
            }
        }
        
        if (directionsLibres.isEmpty()) {
            System.out.println("L'adversaire intelligent est bloqué et ne peut pas se déplacer.");
            return;
        }
        
        // Direction optimale (vers ou loin du joueur)
        Direction directionOptimale = approcher ? directionVersJoueur() : directionFuiteJoueur();
        
        // Cherche la meilleure direction parmi celles disponibles
        Direction meilleureDirection = directionsLibres.get(0);
        int meilleurScore = evaluerDirection(meilleureDirection, directionOptimale);
        
        for (Direction d : directionsLibres) {
            int score = evaluerDirection(d, directionOptimale);
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleureDirection = d;
            }
        }
        
        // Déplacement avec vérification supplémentaire
        Salle nouvelleSalle = salle.getVoisine(meilleureDirection);
        if (nouvelleSalle != null && !estNeutralise()) {
            nouvelleSalle.entre(this);
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
}