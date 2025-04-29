/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente les adversaires de l'énoncé, qui doivent courser/fuir le joueur suivant leur force.
 * Cette classe peut avoir des classes dérivées correspondant à des types d'adversaires distincts.
 * @author jo
 */
public abstract class Adversaire extends Personnage {
    protected Joueur joueur;

    public Adversaire(int inertie, Joueur joueur) {
        super(inertie);
        this.joueur = joueur;
    }

    protected boolean plusFortQueJoueur() {
        return getForce() >= joueur.getForce();
    }

    protected Direction directionVersJoueur() {
        int dLig = Integer.signum(joueur.getSalle().getLigne() - salle.getLigne());
        int dCol = Integer.signum(joueur.getSalle().getColonne() - salle.getColonne());
        return new Direction(dLig, dCol);
    }

    protected Direction directionFuiteJoueur() {
        return directionVersJoueur().getInverse();
    }
   
}