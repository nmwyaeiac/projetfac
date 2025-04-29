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

    public Bidon() {
        super();
    }

    @Override
    public int puiser(int quantite) {
        int prelevement = Math.min(quantite, this.energie);
        this.energie -= prelevement;
        return prelevement;
    }

    @Override
    public int stocker(int quantite) {
        return 0; // Un bidon ne stocke pas
    }

    @Override
    public String toString() {
        return energie > 0 ? "☼" : "○";
    }
    
}