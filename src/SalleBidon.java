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
    /*
    * Déclaration des attributs d'une salle et accesseurs.
    */

    /**
     * Création d'une salle qui contient un bidon.
     * Elle doit s'affecter un nouveau bidon plein.
     * @param lig Numéro de la ligne dans le plateau
     * @param col Numéro de la colonne dans le plateau
     * @param p Le plateau qui contient la salle
     */
    public SalleBidon(/*…*/)
    {
        /* Code à spécifier */
    }

    /**
     * La chaîne qui représente une telle salle doit restituer le symbole d'un bidon en plus de celui de leur occupant éventuel
     * @return
     */
    @Override
    public String toString()
    {
        return null; /* À remplacer par le code effectif */
    }

    /**
     * Si le personnage p est entré, il peut prendre de l'énergie dans le bidon
     * @param p
     */
    @Override
    public void entre(Personnage p)
    {
        /* Code à spécifier */
    }

}
