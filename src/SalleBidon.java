/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Salle interne au plateau qui contient un bidon.
 * En plus de ce que font les autres instances de SalleDedans, elles doivent gérer l'interaction entre le personnage entrant et le bidon qu'elles contiennent — pour y prendre de l'énergie
 * @author jo
 */
public class SalleBidon extends SalleDedans
{
    /**
     * Création d'une salle qui contient un bidon.
     * Elle doit s'affecter un nouveau bidon plein.
     * @param lig Numéro de la ligne dans le plateau
     * @param col Numéro de la colonne dans le plateau
     * @param p Le plateau qui contient la salle
     */
    public SalleBidon(int lig, int col, Plateau p)
    {
        super(lig, col, p);
        this.bidon = new Bidon(); // Crée un nouveau bidon plein
    }

    /**
     * La chaîne qui représente une telle salle doit restituer le symbole d'un bidon en plus de celui de leur occupant éventuel
     * @return
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
     * Si le personnage p est entré, il peut prendre de l'énergie dans le bidon
     * @param p
     */
    @Override
    public void entre(Personnage p)
    {
        // Si la salle est occupée, on gère l'interaction comme une SalleDedans normale
        if (estOccupee()) {
            super.entre(p);
        } else {
            // La salle est libre, le personnage entre
            p.migre(this);
            
            // Le personnage interagit avec le bidon
            p.interagit(bidon);
        }
    }
}