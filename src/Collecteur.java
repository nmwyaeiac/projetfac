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
    public Collecteur() {
        super(0);
    }

    @Override
    public int puiser(int quantite) {
        return 0; // Impossible de puiser dans le collecteur
    }

    @Override
    public int stocker(int quantite) {
        this.energie += quantite; // Stockage sans limite
        return quantite;
    }

    @Override
    public String toString() {
        return "C";
    }
}
