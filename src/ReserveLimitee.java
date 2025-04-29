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
    public ReserveLimitee() {
        super(ParametresJeu.MAX_ENERGIE);
    }

    @Override
    public int puiser(int quantite) {
        int prelevement = Math.min(quantite, this.energie);
        this.energie -= prelevement;
        return prelevement;
    }

    @Override
    public int stocker(int quantite) {
        int espace = ParametresJeu.MAX_ENERGIE - this.energie;
        int ajoute = Math.min(quantite, espace);
        this.energie += ajoute;
        return ajoute;
    }
}
