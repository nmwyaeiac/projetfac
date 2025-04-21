/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représentent les bidons de l'énoncé.
 * Ce sont des réserves limitées d'énergie (avec un maximum d'énergie) auxquelles on ne peut pas ajouter d'énergie ; peut seulement en prendre.
 * Une instance de bidon est destinée à être référencée par une « SalleBidon ».
 * @author jo
 */
public class Bidon extends ReserveLimitee
{

    /**
     * Ajustement du montant positif que l'on veut ajouter à un bidon.
     * Le montant est ajusté à 0 parce qu'on ne peut pas ajouter d'énergie à un bidon.
     * Il est possible d'afficher aussi une erreur si cette méthode est lancée.
     * @param montant La quantité d'énergie à ajouter au bidon (Aucune quantité au dessus de 0 n'est valable)
     * @return La part du montant qu'il est possible d'ajouter au bidon (0)
     */
    @Override
    public int ajustementAjout(int montant)
    {
        // affichage d'erreur éventuel
        return 0;  // part du montant spécifié que l'on peut ajouter au bidon
    }

    /**
     * Restitue le symbole d'un bidon.
     * Utile pour afficher l'état du plateau.
     * @return une chaîne de caractère qui contient le symbole d'un bidon d'énergie (Par exemple ☼)
     */
    @Override
    public String toString(){return "Le symbole d'un bidon d'énergie";}
    
}