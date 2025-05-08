/**
 * Représente une salle contenant un bidon d'énergie.
 * Les personnages qui entrent dans cette salle peuvent interagir avec le bidon
 * pour récupérer de l'énergie.
 */
public class SalleBidon extends SalleDedans
{
    /**
     * Constructeur d'une salle contenant un bidon.
     * Crée automatiquement un bidon plein dans la salle.
     * 
     * @param lig Ligne de la salle sur le plateau
     * @param col Colonne de la salle sur le plateau
     * @param p Plateau contenant la salle
     */
    public SalleBidon(int lig, int col, Plateau p)
    {
        super(lig, col, p); // Appel au constructeur de la classe parente
        this.bidon = new Bidon(); // Crée un nouveau bidon plein
    }

    /**
     * Représentation textuelle de la salle.
     * Priorité d'affichage: occupant > bidon.
     * 
     * @return Symbole représentant le contenu de la salle
     */
    @Override
    public String toString() {
        if (occupant != null) {
            return occupant.toString(); // Priorité au personnage
        } else {
            return bidon.toString(); // Sinon afficher le bidon
        }
    }

    /**
     * Gère l'entrée d'un personnage dans la salle.
     * Si la salle est occupée, déclenche un combat.
     * Sinon, permet au personnage d'interagir avec le bidon.
     * 
     * @param p Personnage qui tente d'entrer
     */
    @Override
    public void entre(Personnage p) {
        // Si la salle est occupée, on gère l'interaction comme une SalleDedans normale
        if (estOccupee()) {
            super.entre(p);
        } else {
            // La salle est libre, le personnage entre
            p.migre(this);
            
            // Le personnage interagit avec le bidon (peu importe que ce soit joueur ou adversaire)
            p.interagit(bidon);
        }
    }
}