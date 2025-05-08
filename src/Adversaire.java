/**
 * Classe abstraite représentant un adversaire du jeu.
 * Un adversaire est un personnage qui poursuit ou fuit le joueur selon sa force.
 * Le comportement de déplacement spécifique est défini par les sous-classes.
 */
public abstract class Adversaire extends Personnage {
    /** Référence au joueur que l'adversaire poursuit ou fuit */
    protected Joueur joueur;

    /**
     * Constructeur d'un adversaire
     * 
     * @param inertie Valeur d'inertie de l'adversaire qui influe sur sa force
     * @param joueur Référence au joueur pour pouvoir le poursuivre ou le fuir
     */
    public Adversaire(int inertie, Joueur joueur) {
        super(inertie); // Appel au constructeur de la classe parente
        this.joueur = joueur; // Stockage de la référence au joueur
    }

    /**
     * Détermine si l'adversaire est plus fort que le joueur
     * 
     * @return true si la force de l'adversaire est supérieure ou égale à celle du joueur
     */
    protected boolean plusFortQueJoueur() {
        return getForce() >= joueur.getForce(); // Compare les forces (énergie × inertie)
    }

    /**
     * Calcule la direction vers le joueur
     * 
     * @return Direction pointant vers le joueur
     */
    protected Direction directionVersJoueur() {
        // Calcule le déplacement en ligne pour aller vers le joueur (-1, 0 ou 1)
        int dLig = Integer.signum(joueur.getSalle().getLigne() - salle.getLigne());
        // Calcule le déplacement en colonne pour aller vers le joueur (-1, 0 ou 1)
        int dCol = Integer.signum(joueur.getSalle().getColonne() - salle.getColonne());
        // Crée et retourne une direction avec ces valeurs
        return new Direction(dLig, dCol);
    }

    /**
     * Calcule la direction pour fuir le joueur
     * 
     * @return Direction opposée à celle pointant vers le joueur
     */
    protected Direction directionFuiteJoueur() {
        return directionVersJoueur().getInverse(); // Obtient la direction inverse
    }
    
    /**
     * Gère le déplacement de l'adversaire 
     * Essaie de se déplacer dans la direction optimale déterminée par la sous-classe,
     * ou dans une autre direction si celle-ci est bloquée
     */
    @Override
    public void deplacer() {
        // Ne fait rien si l'adversaire est neutralisé ou n'est pas dans une salle
        if (estNeutralise() || salle == null) return;
        
        // Vérifie que le joueur n'est pas neutralisé
        if (joueur.estNeutralise()) {
            System.out.println("Le joueur est neutralisé, l'adversaire ne se déplace plus.");
            return;
        }
        
        // Obtient la direction optimale selon le type d'adversaire
        Direction directionOptimale = choisirDirection();
        
        // Essaie jusqu'à 8 directions différentes si nécessaire
        for (int tentative = 0; tentative < 8; tentative++) {
            // Prend la direction optimale au premier essai, puis essaie les suivantes
            Direction directionEssai = tentative == 0 ? 
                                    directionOptimale : 
                                    Direction.getDirection((directionOptimale.getRang() + tentative) % 8);
            
            // Récupère la salle voisine dans cette direction
            Salle nouvelleSalle = salle.getVoisine(directionEssai);
            
            // Vérifie si le déplacement est possible
            if (nouvelleSalle != null && nouvelleSalle instanceof SalleDedans) {
                SalleDedans destination = (SalleDedans) nouvelleSalle;
                // La salle est libre ou contient le joueur non neutralisé
                if (!destination.estOccupee() || (destination.getOccupant() == joueur && !joueur.estNeutralise())) {
                    // Déplace l'adversaire vers cette salle
                    nouvelleSalle.entre(this);
                    System.out.println("Un adversaire s'est déplacé.");
                    // Affiche le plateau après déplacement
                    salle.getPlateau().afficherPlateau();
                    return; // Sort après un déplacement réussi
                }
            }
        }
        
        System.out.println("L'adversaire est bloqué dans toutes les directions.");
    }
    
    /**
     * Méthode abstraite que chaque type d'adversaire doit implémenter
     * pour définir son comportement de déplacement
     * 
     * @return La direction optimale à suivre selon le type d'adversaire
     */
    protected abstract Direction choisirDirection();
    
    /**
     * Gère l'interaction avec un réservoir d'énergie
     * 
     * @param r Le réservoir avec lequel interagir
     */
    public void interagit(Reservoir r) {
        // Si le réservoir est un bidon, l'adversaire prend de l'énergie
        if (r instanceof Bidon) {
            // L'adversaire prend toute l'énergie possible
            int energieDisponible = r.getEnergie();
            int energieMax = ParametresJeu.getMaxEnergie() - getEnergie();
            int quantite = Math.min(energieDisponible, energieMax);
            
            if (quantite > 0) {
                // Puise l'énergie du bidon
                int energiePrise = ((Bidon) r).puiser(quantite);
                // Augmente l'énergie de l'adversaire
                recevoirEnergie(energiePrise);
                System.out.println("L'adversaire a pris " + energiePrise + " unités d'énergie du bidon.");
            }
        }
    }
}