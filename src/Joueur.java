/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Représente le joueur de l'énoncé.
 * Dans cette version du jeu, cette classe a une seule instance, dont la position doit être connue de ses adversaires.
 * Il doit pouvoir accéder au collecteur
 * @author jo
 */
public class Joueur extends Personnage {
    private Collecteur collecteur;

    public Joueur(int ligne, int colonne, Plateau plateau, Collecteur collecteur) {
        super(8); // inertie fixée à 5
        this.collecteur = collecteur;
        this.salle = plateau.getSalle(ligne, colonne);
    }

    @Override
  
public void deplacer() {
    for (int i = 0; i < 2; i++) {
        if (estNeutralise()) return;

        Direction d = Direction.demanderDirection();
        Salle nouvelleSalle = salle.getPlateau().getSalle(
            salle.getLigne() + d.getDLig(),
            salle.getColonne() + d.getDCol()
        );

        if (nouvelleSalle != null && !(nouvelleSalle instanceof SalleBord)) {
            perdreEnergie(1);
            nouvelleSalle.entrer(this);
            // ➕ Ajout de l'affichage du plateau après chaque déplacement
            salle.getPlateau().afficherPlateau();
        } else {
            System.out.println("Déplacement impossible.");
        }
    }
}


    @Override
    public void interagit(Reservoir r) {
        if (r instanceof Bidon) {
            System.out.println("Énergie du bidon : " + r.getEnergie());
            int choix = Direction.demanderQuantite(r.getEnergie());
            recevoirEnergie(r.puiser(choix));
        } else if (r instanceof Collecteur) {
            int choix = Direction.demanderQuantite(this.getEnergie());
            r.stocker(this.reserve.puiser(choix));
        }

