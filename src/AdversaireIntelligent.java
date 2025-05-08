import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AdversaireIntelligent extends Adversaire {
    private static final Random random = new Random();
    
    public AdversaireIntelligent(int inertie, Joueur joueur) {
        super(inertie, joueur);
    }
    
    @Override
    protected Direction choisirDirection() {
        Direction directionBase = plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
        
        // Liste de toutes les directions évaluées par score
        List<Map.Entry<Direction, Integer>> directionsEvaluees = new ArrayList<>();
        
        // Évaluer toutes les directions
        for (int i = 0; i < 8; i++) {
            Direction dir = Direction.getDirection(i);
            Salle voisine = salle.getVoisine(dir);
            
            // Vérifier si la direction est viable
            if (voisine instanceof SalleDedans && !((SalleDedans) voisine).estOccupee()) {
                int score = evaluerDirection(dir, directionBase);
                directionsEvaluees.add(new AbstractMap.SimpleEntry<>(dir, score));
            }
        }
        
        // Trier par score décroissant
        directionsEvaluees.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        // Retourner la meilleure direction ou la direction de base si aucune n'est valide
        return directionsEvaluees.isEmpty() ? directionBase : directionsEvaluees.get(0).getKey();
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
    public String toString() {
        return "♝";
    }
}