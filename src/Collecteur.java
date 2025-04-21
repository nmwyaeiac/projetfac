/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Il n'y a qu'un seul collecteur, qui doit être accessible uniquement du joueur.
 * L'instance de la classe Joueur peut le référencer.
 * @author jo
 */
public class Collecteur extends Reservoir
{

    /**
     * Ajustement du montant positif que l'on veut ajouter à un Collecteur.
     * Il n'y a aucun ajustement à faire car la quantité d'énergie  que peut contenir un collecteur n'est pas limitée.
     * @param montant La quantité d'énergie à ajouter au collecteur
     * @return la quantité d'énergie que l'on peut ajouter en fonction du montant spécifié… c'est-à-dire ce montant
     */
    @Override
    public int ajustementAjout(int montant){return 0; /* 0 à remplacer par la part du montant que peut recevoir le collecteur */}

    /**
     * Ajustement du montant positif que l'on veut retirer au collecteur.
     * Le montant est ajusté à 0 parce qu'on ne peut pas retirer d'énergie à un collecteur.
     * Il est possible d'afficher aussi une erreur si cette méthode est lancée.
     * @param montant La quantité d'énergie à retirer au collecteur (Aucune quantité au dessus de 0 n'est valide)
     * @return La part du montant qu'il est possible de retirer au collecteur (0)
     */
    @Override
    public int ajustementRetrait(int montant)
    {
        // affichage d'erreur éventuel
        return 0; // part du montant spécifié que l'on peut retirer dans le collecteur
    }
    public Collecteur()
    {
        /* Code à spécifier */
    }
}
