import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Représente un adversaire intelligent qui cherche le chemin optimal
 * pour atteindre ou fuir le joueur en évaluant différentes directions possibles.
 * Il choisit la direction la plus pertinente en fonction du contexte.
 */
public class AdversaireIntelligent extends Adversaire {
    /** Générateur de nombres aléatoires partagé par tous les adversaires intelligents */
    private static final Random random = new Random();
    
    /**
     * Constructeur de l'adversaire intelligent
     * 
     * @param inertie Valeur d'inertie de l'adversaire
     * @param joueur Référence au joueur à poursuivre ou fuir
     */
    public AdversaireIntelligent(int inertie, Joueur joueur) {
        super(inertie, joueur); // Appel au constructeur de la classe parente
    }
    
    /**
     * Choisit la direction optimale de déplacement en évaluant toutes les directions possibles
     * 
     * @return La direction ayant obtenu le meilleur score d'évaluation
     */
    @Override
    protected Direction choisirDirection() {
        // Détermine la direction de base (vers ou fuyant le joueur)
        Direction directionBase = plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
        
        // Liste pour stocker toutes les directions évaluées avec leur score
        List<Map.Entry<Direction, Integer>> directionsEvaluees = new ArrayList<>();
        
        // Évaluer toutes les directions possibles (8 directions)
        for (int i = 0; i < 8; i++) {
            Direction dir = Direction.getDirection(i);
            Salle voisine = salle.getVoisine(dir);
            
            // Vérifier si la direction est viable (salle intérieure non occupée)
            if (voisine instanceof SalleDedans && !((SalleDedans) voisine).estOccupee()) {
                // Calculer le score de pertinence pour cette direction
                int score = evaluerDirection(dir, directionBase);
                // Ajouter la direction et son score à la liste
                directionsEvaluees.add(new AbstractMap.SimpleEntry<>(dir, score));
            }
        }
        
        // Trier les directions par score décroissant (meilleur score en premier)
        directionsEvaluees.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        // Retourner la meilleure direction ou la direction de base si aucune n'est valide
        return directionsEvaluees.isEmpty() ? directionBase : directionsEvaluees.get(0).getKey();
    }
    
    /**
     * Évalue la pertinence d'une direction par rapport à la direction optimale
     * Plus le score est élevé, plus la direction est proche de l'optimale
     * 
     * @param d Direction à évaluer
     * @param optimale Direction optimale (vers ou loin du joueur)
     * @return Score d'évaluation (plus élevé = meilleur)
     */
    private int evaluerDirection(Direction d, Direction optimale) {
        // Direction identique = meilleur score (100)
        if (d.equals(optimale)) {
            return 100;
        }
        
        // Direction semblable (même axe général) = bon score (70)
        if ((d.getdLig() == optimale.getdLig() && d.getdLig() != 0) || 
            (d.getdCol() == optimale.getdCol() && d.getdCol() != 0)) {
            return 70;
        }
        
        // Direction qui partage un composant (vertical ou horizontal) = score moyen (50)
        if (d.getdLig() == optimale.getdLig() || d.getdCol() == optimale.getdCol()) {
            return 50;
        }
        
        // Direction complètement différente = score faible (10)
        return 10;
    }
    
    /**
     * Représentation textuelle de l'adversaire intelligent
     * 
     * @return Symbole représentant l'adversaire intelligent
     */
    @Override
    public String toString() {
        return "♝"; // Symbole du fou des échecs
    }
}