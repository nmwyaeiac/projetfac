/**
 * Représente une salle à l'intérieur du plateau, accessible aux personnages.
 * Peut contenir un personnage (occupant) et éventuellement un bidon d'énergie.
 * Gère les interactions entre personnages et avec les bidons.
 */
public class SalleDedans extends Salle {    
    /** Personnage occupant actuellement la salle (null si vide) */
    protected Personnage occupant;
    
    /** Bidon d'énergie présent dans la salle (null si pas de bidon) */
    protected Bidon bidon;
    
    /**
     * Récupère le personnage occupant la salle.
     * 
     * @return Personnage occupant ou null si la salle est vide
     */
    public Personnage getOccupant() {
        return occupant;
    }
    
    /**
     * Définit le personnage occupant la salle.
     * 
     * @param occupant Nouveau personnage occupant ou null pour vider la salle
     */
    public void setOccupant(Personnage occupant) {
        this.occupant = occupant;
    }
    
    /**
     * Récupère le bidon présent dans la salle.
     * 
     * @return Bidon présent ou null si pas de bidon
     */
    public Bidon getBidon() {
        return bidon;
    }
    
    /**
     * Définit le bidon présent dans la salle.
     * 
     * @param bidon Nouveau bidon ou null pour retirer le bidon
     */
    public void setBidon(Bidon bidon) {
        this.bidon = bidon;
    }
    
    /**
     * Vérifie si la salle est occupée par un personnage.
     * 
     * @return true si la salle est occupée, false sinon
     */
    public boolean estOccupee() {
        return occupant != null;
    }
    
    /**
     * Vérifie si la salle contient un bidon.
     * 
     * @return true si la salle contient un bidon, false sinon
     */
    public boolean contientBidon() {
        return bidon != null;
    }
    
    /**
     * Représentation textuelle de la salle.
     * Priorité d'affichage: occupant > bidon > salle vide.
     * 
     * @return Symbole représentant le contenu de la salle
     */
    @Override
    public String toString() {
        if (occupant != null) {
            return occupant.toString(); // Priorité au personnage
        } else if (bidon != null) {
            return bidon.toString(); // Ensuite le bidon
        } else {
            return "□"; // Symbole de salle vide
        }
    }

    /**
     * Récupère la salle voisine dans une direction donnée.
     * 
     * @param d Direction vers la salle voisine
     * @return Salle voisine ou null si aucune
     */
    public Salle getVoisine(Direction d) {
        // Calcule les coordonnées de la salle voisine
        int newLig = getLigne() + d.getdLig();
        int newCol = getColonne() + d.getdCol();
        // Récupère la salle à ces coordonnées
        return getPlateau().getSalle(newLig, newCol);
    }
    
    /**
     * Récupère la salle voisine dans une direction donnée par son rang.
     * 
     * @param i Rang de la direction (0 à 7)
     * @return Salle voisine ou null si aucune
     */
    public Salle getVoisine(int i) {
        return getVoisine(new Direction(i));
    }
    
    /**
     * Récupère la salle voisine dans une direction donnée par sa représentation textuelle.
     * 
     * @param directionTexte Représentation textuelle de la direction
     * @return Salle voisine ou null si aucune
     */
    public Salle getVoisine(String directionTexte) {
        return getVoisine(new Direction(directionTexte));
    }

    /**
     * Constructeur d'une salle intérieure.
     * 
     * @param lig Ligne de la salle sur le plateau
     * @param col Colonne de la salle sur le plateau
     * @param p Plateau contenant la salle
     */
    public SalleDedans(int lig, int col, Plateau p) {
        super(lig, col, p); // Appel au constructeur de la classe parente
        this.occupant = null; // Initialement vide
        this.bidon = null; // Pas de bidon par défaut
    }

    /**
     * Gère l'entrée d'un personnage dans la salle.
     * Si la salle est occupée, déclenche un combat.
     * Si la salle contient un bidon, permet au personnage d'interagir avec lui.
     * 
     * @param p Personnage qui tente d'entrer
     */
    @Override
    public void entre(Personnage p) {
        // Vérifier d'abord si le personnage entrant n'est pas neutralisé
        if (p.estNeutralise()) {
            System.out.println("Le personnage est neutralisé et ne peut pas entrer.");
            return;
        }
        
        // S'il y a déjà un occupant, on gère l'interaction
        if (estOccupee()) {
            // Sauvegarde de la salle d'origine
            SalleDedans salleOrigine = p.getSalle();
            
            // Vérifier que l'occupant n'est pas neutralisé
            if (occupant.estNeutralise()) {
                // Si l'occupant est neutralisé, on le retire simplement
                occupant = null;
                // Le personnage p peut entrer
                p.migre(this);
            } else {
                // Combat entre les personnages
                Personnage.combat(p, occupant);
                
                // Si l'occupant a été neutralisé et que p n'est pas neutralisé
                if (occupant != null && occupant.estNeutralise() && !p.estNeutralise()) {
                    occupant = null; // Retire l'occupant neutralisé
                    p.migre(this); // Le personnage p entre
                } else if (!p.estNeutralise() && !occupant.estNeutralise()) {
                    // Combat terminé sans neutralisation, le personnage est rejeté
                    System.out.println("Combat terminé sans neutralisation. Le personnage reste dans sa salle d'origine.");
                    // Le personnage p reste dans sa salle d'origine (rien à faire ici)
                }
            }
        } else {
            // La salle est libre, le personnage peut y entrer
            p.migre(this);
            
            // S'il y a un bidon, le personnage interagit avec lui
            if (contientBidon()) {
                p.interagit(bidon);
            }
        }
    }
}