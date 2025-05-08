public class SalleDedans extends Salle {    
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
    
    @Override
    public String toString() {
        if (occupant != null) {
            return occupant.toString();
        } else if (bidon != null) {
            return bidon.toString();
        } else {
            return "□";
        }
    }

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

    public SalleDedans(int lig, int col, Plateau p) {
        super(lig, col, p);
        this.occupant = null;
        this.bidon = null;
    }

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
                    occupant = null;
                    p.migre(this);
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