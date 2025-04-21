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
        
    /**
     * Restitue la chaîne qui représente le contenu de la salle
     * @return
     */
    @Override
    public  String toString()
    {
        return null; /* À remplacer par le code effectif */
    }    

    /**
     * Restitue la salle voisine dans une direction donnée
     * @param d Une direction
     * @return Une nouvelle salle, qui peut être au bord du plateau
     */
    public Salle getVoisine(Direction d)
    {
        return null; /* À remplacer par le code effectif */
    }
    public Salle getVoisine(int i)
    {
        return getVoisine(new Direction(i));
    }
    public Salle getVoisine(String directionTexte)
    {
        return getVoisine(new Direction(directionTexte));
    }

    /**
     * Crée une salle interne au plateau initialement sans occupant
     * @param lig Numéro de ligne de la salle
     * @param col Numéro de colonne de la salle
     * @param p Plateau auquel appartient la salle
     */
    public SalleDedans(/*…*/)
    {
        /* Code à spécifier */
    }

    /**
     * Action à effectuer quand un personnage se présente pour entrer dans la salle
     * C'est le personnage entrant qui exécute cette méthode quand il avance vers cette salle
     * Si la salle a un occupant, il faut gérer l'interaction avec lui
     * Puis, si la salle est inocuppée, p peut y entrer effectivement (migre(…)).
     * @param p référence un personnage, joueur ou adversaire
     */
    @Override
    public void entre(Personnage p)
    {
        /* Code à spécifier */
    }
}
