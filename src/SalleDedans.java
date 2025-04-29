/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente une salle interne au plateau, qui contient potentiellement un personnage
 * @author jo
 */
public class SalleDedans extends Salle
{    
    /*
    * Déclaration des attributs d'une salle et accesseurs.
    */
    protected Personnage occupant;
    protected Bidon bidon;
    
    public Personnage getOccupant() {
        return occupant;
    }
    
    public void setOccupant(Personnage occupant) {
        this.occupant = occupant;
    }
    
    public Bidon getBidon() {
        return bidon;
    }
    
    public void setBidon(Bidon bidon) {
        this.bidon = bidon;
    }
    
    public boolean estOccupee() {
        return occupant != null;
    }
    
    public boolean contientBidon() {
        return bidon != null;
    }
    
    /**
     * Restitue la chaîne qui représente le contenu de la salle
     * @return
     */
    @Override
    public String toString() {
        if (occupant != null && bidon != null) {
            return bidon.toString() + occupant.toString();
        } else if (occupant != null) {
            return "." + occupant.toString();
        } else if (bidon != null) {
            return bidon.toString() + ".";
        } else {
            return "..";
        }
    }    

    /**
     * Restitue la salle voisine dans une direction donnée
     * @param d Une direction
     * @return Une nouvelle salle, qui peut être au bord du plateau
     */
    public Salle getVoisine(Direction d) {
        int newLig = getLigne() + d.getdLig();
        int newCol = getColonne() + d.getdCol();
        return getPlateau().getSalle(newLig, newCol);
    }
    
    public Salle getVoisine(int i) {
        return getVoisine(new Direction(i));
    }
    
    public Salle getVoisine(String directionTexte) {
        return getVoisine(new Direction(directionTexte));
    }

    /**
     * Crée une salle interne au plateau initialement sans occupant
     * @param lig Numéro de ligne de la salle
     * @param col Numéro de colonne de la salle
     * @param p Plateau auquel appartient la salle
     */
    public SalleDedans(int lig, int col, Plateau p) {
        super(lig, col, p);
        this.occupant = null;
        this.bidon = null;
    }

    /**
     * Action à effectuer quand un personnage se présente pour entrer dans la salle
     * C'est le personnage entrant qui exécute cette méthode quand il avance vers cette salle
     * Si la salle a un occupant, il faut gérer l'interaction avec lui
     * Puis, si la salle est inoccupée, p peut y entrer effectivement (migre(…)).
     * @param p référence un personnage, joueur ou adversaire
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
            // Vérifier que l'occupant n'est pas neutralisé
            if (occupant.estNeutralise()) {
                // Si l'occupant est neutralisé, on le retire simplement
                occupant = null;
                // Le personnage p peut entrer
                p.migre(this);
            } else {
                // Combat entre les personnages
                Personnage.combat(p, occupant);
                
                // Si l'occupant a été neutralisé, il disparaît
                if (occupant != null && occupant.estNeutralise()) {
                    occupant = null;
                    // Le personnage p peut entrer s'il n'est pas neutralisé
                    if (!p.estNeutralise()) {
                        p.migre(this);
                    }
                } else if (!p.estNeutralise()) {
                    // Sinon, le personnage p reste dans sa salle d'origine
                    System.out.println("La salle est occupée, impossible d'y entrer.");
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