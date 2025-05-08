public class AdversaireDetermine extends Adversaire {
    
    public AdversaireDetermine(int inertie, Joueur joueur) {
        super(inertie, joueur);
    }
    
    @Override
    protected Direction choisirDirection() {
        return plusFortQueJoueur() ? directionVersJoueur() : directionFuiteJoueur();
    }
    
    @Override
    public String toString() {
        return "♞";
    }
}