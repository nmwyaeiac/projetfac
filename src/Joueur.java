/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente le joueur de l'énoncé.
 * Dans cette version du jeu, cette classe a une seule instance, dont la position doit être connue de ses adversaires.
 * Il doit pouvoir accéder au collecteur
 * @author jo
 */
public class Joueur extends Personnage
{
    /*
    * Déclaration des attributs spécifiques à Joueur et accesseurs (Ceux qui ne sont pas déjà déclarés dans Personnage)
    */
    
    /**
     * Action à effectuer quand un joueur (le joueur) rencontre un autre personnage.
     * p peut uniquement être un adversaire dans cette version du jeu, mais la possibilité est ouverte pour une autre version du jeu où un joueur rencontre un autre joueur ou, pourquoi pas, d'autres types de personnages.
     * Si p est un autre adversaire (Ce sera toujours le cas), il y a combat.
     * @param p Un personnage quelconque (En fait, un adversaire).
     */
     @Override
    public void rencontre(Personnage p)
    {
        /* Code à spécifier */
    }
    
    /**
     * Action à effectuer après la perte d'un combat contre un adversaire.
     * Si le joueur est neutralisé, il a perdu. Le jeu est terminé.
     */
    @Override
    public void perd()
    {
        /* Code à spécifier */
    }
    
    /**
     * Avancée du joueur dans une direction demandée à l'utilisateur humain.
     * L'avancée induit l'exécution de la méthode entre(…) dans la salle de destination.
     */
    @Override
    public void avance()
    {
        /* Code à spécifier */
    }

    /**
     * Action spécifique au joueur pour prendre de l'énergie dans une réserve limitée.
     * La quantité à prendre est demandée à l'utilisateur, puis ajustée aux possibilités de la réserve.
     * L'utilsateur choisit ensuite s'il reconstitue sa propre énergie ou s'il ajoute l'énergie prise au collecteur.
     * 
     * @param r La réserve d'énergie limitée (bidon ou réserve d'énergie d'un adversaire)
     */
    @Override
    public void prendEnergie(ReserveLimitee r)
    {
        /* Code à spécifier */

    }
    public Joueur(/*…*/)
    {
        /* Code à spécifier */
    }
    
        /**
     * Restitue le symbole du joueur.
     * Utile pour afficher l'état du plateau.
     * @return une chaîne de caractère qui contient le symbole du joueur (Par exemple ♜)
     */
    @Override
    public String toString(){return "Le symbole du joueur";}    

}
