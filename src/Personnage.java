/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente les personnages du jeu qui, dans cette version, peuvent être un Joueur ou un Adversaire.
 * Les personnages ont tous une inertie, une réserve d'énergie initialisée à 10 et ont une position qui est une salle interne au plateau (SalleDedans)
 * @author jo
 */
public abstract class Personnage
{
    /*
    * Déclaration des attributs communs à tous les personnages et accesseurs.
    */
    
    /**
     * Restitue la quantité d'énergie restante pour le personnage.
     * C'est la quantité d'énergie de la réserve d'énergie (ReserveLimitee) associée au personnage
     * @return un entier positif ou nul
     */
    public int getEnergie()
    {
        return 0;  /* À remplacer par le code effectif */
    }

    /**
     * Diminue l'énergie du personnage d'une unité (exécuté à chaque changement de salle effectif)
     */
    public void decEnergie()
    {
        /* Code à spécifier */
    }
    
    /**
     * Restitue la force du personnage (sont inertie fois son énergie).
     * @return un entier positif ou nul
     */
    public int getForce()
    {
        return 0;  /* À remplacer par le code effectif */
    }

    /**
     * Restitue true si la réserve d'énergie du personnage est vide
     * @return
     */
    public boolean estNeutralise()
    {
        return false;  /* À remplacer par le code effectif */
    }
    
    /**
     * Migration du personnage de sa salle courante à une salle de destination.
     * Elle est possible si la salle de destination n'est pas occupée.
     * Cela consiste à libérer la salle d'origine (la salle couramment occupée), à affecter la salle de destination au personnage et à faire décroître son énergie 
     * @param destination`
     */
    public void migre(SalleDedans destination)
    {
        /* Code à spécifier */
    }
    
    public void avance()
    {
    }
    
    /**
     * Action d'un personnage pour prendre de l'énergie à un autre personnage.
     * Il prend de l'énergie dans la réserve d'énergie de l'autre personnage.
     * @param autre Le personnage à qui l'énergie est prise
     */
    public void prendEnergie(Personnage autre)
    {
        /* Code à spécifier */
    }

    /**
     * Action d'un personnage pour prendre de l'énergie dans une réserve limitée.
     * Le code dépend du type de personnage (Adversaire ou Joueur).
     * @param r La réserve d'énergie limitée (bidon ou réserve d'énergie du personnage)
     */
    public abstract void prendEnergie(ReserveLimitee r);
    
    /**
     * Action à effectuer après la perte d'un combat.
     * Le code dépend du type de Personnage.
     */
    public abstract void perd();
    
    /**
     *
     * @param p
     */
    public void rencontre(Personnage p){}
        
    /**
     * Action de combat entre deux personnages.
     * En fait, ce sont un Joueur et un Adversaire, mais il n'est pas besoin de le spécifier.
     * Le gagnant est celui qui a la plus grande force. Il prend de l'énergie au perdant (utilisation de prendEnergie(Personnage autre)) et la méthode perd() est lancée pour le perdant
     * Le code ne dépend pas de qui est le joueur et qui est l'adversaire parce c'est prendEnergie(…) et perd(…) qui seront différent suivant qui l'exécute.
     * @param p1 un Personnage
     * @param p2 un autre Personnage
     */
    public static void combat(Personnage p1,Personnage p2)
    {
        /* Code à spécifier */
    }
    
    /**
     * Initialise l'inertie, la réserve d'énergie et la position du personnage
     */
    public Personnage(/*…*/)
    {
        /* Code à spécifier */
    }       

}
