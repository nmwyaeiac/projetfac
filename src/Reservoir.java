/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Réserve d'énergie qui peut être vide.
 * Elle est représentée par une quantité d'énergie entière positive ou nulle.
 * @author jo
 */
public abstract class Reservoir
{
    protected int energie;

    public Reservoir(int energie) {
        this.energie = energie;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = Math.max(0, energie);
    }

    public abstract int puiser(int quantite);
    public abstract int stocker(int quantite);
}
