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
    @Override
    public void deplacer() {
        if (estNeutralise() || salle == null) return;
        
        // Vérifier que le joueur n'a pas été neutralisé
        if (joueur.estNeutralise()) {
            System.out.println("Le joueur est neutralisé, l'adversaire ne se déplace plus.");
            return;
        }
        
        // Obtenir la direction optimale (méthode à implémenter par les sous-classes)
        Direction directionOptimale = choisirDirection();
        
        // Tente de se déplacer dans la direction optimale
        Salle nouvelleSalle = salle.getVoisine(directionOptimale);
        if (nouvelleSalle != null && nouvelleSalle instanceof SalleDedans) {
            SalleDedans destination = (SalleDedans) nouvelleSalle;
            if (!destination.estOccupee() || (destination.getOccupant() == joueur && !joueur.estNeutralise())) {
                // La salle est libre ou contient le joueur (non neutralisé)
                nouvelleSalle.entre(this);
                // Afficher le plateau après chaque déplacement d'adversaire
                System.out.println("Un adversaire s'est déplacé.");
                salle.getPlateau().afficherPlateau();
            } else {
                System.out.println("L'adversaire ne peut pas entrer dans la salle (déjà occupée).");
            }
        } else {
            System.out.println("L'adversaire ne peut pas se déplacer dans cette direction.");
        }
    }
    
    // Méthode abstraite que chaque type d'adversaire doit implémenter
    protected abstract Direction choisirDirection();
}