/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente les adversaires de l'énoncé, qui doivent courser/fuir le joueur suivant leur force.
 * Cette classe peut avoir des classes dérivées correspondant à des types d'adversaires distincts.
 * @author jo
 */
public class Adversaire extends Personnage
{   
    /*
    * Déclaration des attributs spécifiques à Adversaire (Ceux qui ne sont pas déjà déclarés dans Personnage)
    */

    /**
     * Restitue la direction que l'on doit prendre pour aller vers le joueur
     * @return
     */
    public Direction getDirectionVersJoueur()
    {
        return null; // à remplacer par le calcul de la direction (Voir ajouts surlignés dans le sujet)
    }
    
    /**
     * Avancée de l'adversaire.
     * Il fuit/course le joueur suivant que sa force est plus petite/plus grande que la sienne.
     * Il est possible de faire plusieurs types d'adversaires (sous classes) avec d'autres types de comportements.
     * Avancer consiste à tenter de changer de salle dans une direction donnée, avec la possibilité d'interagir avec le contenu de la salle.
     */
    @Override
    public void avance()
    {      
        /* Code spécifique de l'avancée d'un adversaire (changement de salle) */
    }

    /**
     * Action spécifique à un adversaire pour prendre de l'énergie dans une réserve limitée.
     * Par principe, un adversaire prend toute l'énergie qu'il peut dans une réserve (bidon ou l'énergie du joueur)
     * @param r La réserve d'énergie limitée (bidon ou réserve d'énergie du joueur)
     */
    @Override
    public void prendEnergie(ReserveLimitee r)
    {
        /* Code à spécifier */
    }

    /**
     * Action à effectuer quand un adversaire rencontre un autre personnage (C'est le cas quand un des protagoniste est dans une salle et l'autre a déclenché la méthode entre de cette salle).
     * Si p est un autre adversaire, il ne se passe rien. Si c'est le joueur, il y a combat.
     * 
     * @param p Le joueur ou un autre adversaire.
     */
    @Override
    public void rencontre(Personnage p)
    {
        /* Code à spécifier */
    }
    
    /**
     * Action à effectuer après la perte d'un combat contre le joueur.
     * Si l'adversaire est neutralisé (Ce n'est pas obligatoire ; le joueur n'a pas forcément pu prendre toute son énergie), il libère la place dans la salle.
     */
    @Override
    public void perd()
    {
        /* Code à spécifier */
    }

    /**
     * Initialise un Adversaire avec une inertie aléatoire entre 2 et 9, le joueur qu'il référencera et la salle initiale dans laquelle il est placé (éventuellement)
     * @param leJoueur permet à chaque adversaire de référencer le joueur pour « savoir » où il est à chaque instant
     * @param position salle initiale de l'adversaire
     */
    public Adversaire(/*…*/)
    {
        /* Code à spécifier */
    } 
    /**
     * Restitue le symbole d'un adversaire.
     * Utile pour afficher l'état du plateau.
     * @return une chaîne de caractère qui contient le symbole d'un adversaire (Par exemple Par exemple ♟)
     */
    @Override
    public String toString(){return "Le symbole d'un adversaire";}
}
