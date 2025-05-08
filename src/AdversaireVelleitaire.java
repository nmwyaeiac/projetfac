import java.util.Random;

public class AdversaireVelleitaire extends Adversaire {
    private static final Random random = new Random();
    
    public AdversaireVelleitaire(int inertie, Joueur joueur) {
        super(inertie, joueur);
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
    
    @Override
    public String toString() {
        return "♟";
    }
}
