/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Réserve d'énergie bornée positivement (Il y a un maximum à ne pas dépasser)
 * @author jo
 */
public class ReserveLimitee extends Reservoir
{
    /*
    * Déclaration des attributs d'une réserve limitee et accesseurs.
    */
    public ReserveLimitee(/*…*/)
    {
        /* Code à spécifier */
    }
    /**
     * Ajustement du montant positif que l'on veut ajouter à une réserve d'énergie.
     * Il ne faut pas dépasser le maximum d'énergie possible
     * @param montant La quantité d'énergie à ajouter à la réserve
     * @return La part du montant qu'il est possible d'ajouter à la réserve
     */
    @Override
    public int ajustementAjout(int montant)
    {
        return 0; /* À remplacer par le code effectif */
    }  
}
