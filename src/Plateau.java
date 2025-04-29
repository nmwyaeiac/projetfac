/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Random;
 
 /**
  * Représente le plateau du jeu défini par un tableau de salles.
  * 
  * @author jo
  */
 public class Plateau {
   /*
    * Déclaration des attributs d'un plateau et accesseurs.
    */
   public static final String BORD = "█";
   public static final String FOND = "░";
   
   private final Salle[][] grille;
   private Joueur joueur;
   private List<Adversaire> adversaires;
   private Collecteur collecteur;
 
   /**
    * Restitue le nombre effectif de lignes occupables (sans compter les bords).
    * 
    * @return un nombre entier
    */
   public int getNbLig() {
     return grille.length;
   }
 
   /**
    * Restitue le nombre effectif de colonnes occupables (sans compter les bords).
    * 
    * @return un nombre entier
    */
   public int getNbCol() {
     return grille[0].length;
   }
   
   /**
    * Renvoie le joueur de la partie
    */
   public Joueur getJoueur() {
     return joueur;
   }
   
   /**
    * Renvoie la liste des adversaires
    */
   public List<Adversaire> getAdversaires() {
     return adversaires;
   }
   
   /**
    * Renvoie le collecteur d'énergie du jeu
    */
   public Collecteur getCollecteur() {
     return collecteur;
   }
 
   /**
    * Restitue la salle d'indices lig et col.
    * Si lig et col ne sont pas valide, la méthode restitue null
    * 
    * @param lig numéro de ligne
    * @param col numéro de colonne
    * @return la salle de numéro de ligne lig et de numéro de colonne col, et null
    *         si lig ou col n'est pas valide
    */
   public Salle getSalle(int lig, int col) {
     if (isValide(lig, col)) {
       return grille[lig][col];
     } else {
       return null;
     }
   }
 
   /**
    * Affecte une salle dans le plateau à la position lig-col si cette position est
    * valide
    * 
    * @param s   La salle à affecter
    * @param lig numéro de ligne valide
    * @param col numéro de colonne valide
    */
   public void setSalle(Salle s, int lig, int col) {
     if (isValide(lig, col)) {
       grille[lig][col] = s;
     }
   }
 
   /**
    * Restitue true si et seulement si les numéros de ligne lig et de colonne col
    * sont valides
    * 
    * @param lig numéro de ligne à vérifier
    * @param col numéro de colonne à vérifier
    * @return un booléen qui vaut true en cas de validité des paramètres
    */
   private boolean isValide(int lig, int col) {
     return lig >= 0 && lig < getNbLig() && col >= 0 && col < getNbCol();
   }
 
   /**
    * Restitue la salle immédiatement voisine d'une salle interne dans la direction
    * donnée.
    * Le numéro de ligne de la nouvelle salle est obtenu en faisant la somme du
    * numéro de ligne lig de la salle en paramètre et du dLig de la direction.
    * Idem pour les colonnes.
    * 
    * @param s Une salle interne au plateau (SalleDedans)
    * @param d Une direction quelconque, y compris en diagonales.
    * @return Une salle quelconque, y compris une salle de type Bordure
    */
   public Salle getVoisine(SalleDedans s, Direction d) {
     int newLig = s.getLigne() + d.getdLig();
     int newCol = s.getColonne() + d.getdCol();
     return getSalle(newLig, newCol);
   }
 
   /**
    * Initialisation du contenu du plateau.
    * Placement de salles de type SalleBordure tout autour.
    * Placement aléatoire de salles de type SalleBidon.
    * Placement aléatoire d'adversaires.
    * Placement du joueur au centre.
    */
   private void initContenu(int nbAdversaires, int nbBidons) {
     Random random = new Random();
     
     // Création du collecteur
     this.collecteur = new Collecteur();
     
     // Initialisation de toutes les salles comme vides
     for (int i = 0; i < getNbLig(); i++) {
       for (int j = 0; j < getNbCol(); j++) {
         if (i == 0 || i == getNbLig() - 1 || j == 0 || j == getNbCol() - 1) {
           // Bordures autour du plateau
           grille[i][j] = new SalleBordure(i, j, this);
         } else {
           // Salles vides à l'intérieur
           grille[i][j] = new SalleVide(i, j, this);
         }
       }
     }
     
     // Position du centre du plateau
     int centreI = getNbLig() / 2;
     int centreJ = getNbCol() / 2;
     
     // Création du joueur au centre
     this.joueur = new Joueur(centreI, centreJ, this, collecteur);
     grille[centreI][centreJ] = new SalleJoueur(centreI, centreJ, this);
     ((SalleDedans)grille[centreI][centreJ]).setOccupant(joueur);
     
     // Liste pour garder trace des positions utilisées
     List<int[]> positionsUtilisees = new ArrayList<>();
     positionsUtilisees.add(new int[]{centreI, centreJ});
     
     // Placement des bidons d'énergie
     this.adversaires = new ArrayList<>();
     int bidonsPlaces = 0;
     while (bidonsPlaces < nbBidons) {
       int i = 1 + random.nextInt(getNbLig() - 2);
       int j = 1 + random.nextInt(getNbCol() - 2);
       
       // Vérifier si la position est déjà utilisée
       boolean positionLibre = true;
       for (int[] pos : positionsUtilisees) {
         if (pos[0] == i && pos[1] == j) {
           positionLibre = false;
           break;
         }
       }
       
       if (positionLibre) {
         grille[i][j] = new SalleBidon(i, j, this);
         bidonsPlaces++;
         positionsUtilisees.add(new int[]{i, j});
       }
     }
     
     // Placement des adversaires
     int adversairesPlaces = 0;
     while (adversairesPlaces < nbAdversaires) {
       int i = 1 + random.nextInt(getNbLig() - 2);
       int j = 1 + random.nextInt(getNbCol() - 2);
       
       // Vérifier si la position est déjà utilisée
       boolean positionLibre = true;
       for (int[] pos : positionsUtilisees) {
         if (pos[0] == i && pos[1] == j) {
           positionLibre = false;
           break;
         }
       }
       
       if (positionLibre) {
         // Choisir aléatoirement un type d'adversaire
         int typeAdversaire = random.nextInt(3); // 0, 1 ou 2
         
         // Inertie aléatoire entre MIN et MAX
         int inertie = ParametresJeu.MIN_INERTIE_ADVERSAIRE + 
             random.nextInt(ParametresJeu.MAX_INERTIE_ADVERSAIRE - ParametresJeu.MIN_INERTIE_ADVERSAIRE + 1);
         
         Adversaire nouvelAdversaire;
         nouvelAdversaire = switch (typeAdversaire) {
               case 0 -> new AdversaireVelleitaire(inertie, joueur);
               case 1 -> new AdversaireDetermine(inertie, joueur);
               default -> new AdversaireIntelligent(inertie, joueur);
           };
         
         // Placer l'adversaire
         nouvelAdversaire.setSalle((SalleDedans)grille[i][j]);
         ((SalleDedans)grille[i][j]).setOccupant(nouvelAdversaire);
         adversaires.add(nouvelAdversaire);
         
         adversairesPlaces++;
         positionsUtilisees.add(new int[]{i, j});
       }
     }
   }
 
   /**
    * Affiche le plateau de jeu et les informations sur le joueur
    */
   public void afficherPlateau() {
     System.out.println("\n=== ÉTAT DU PLATEAU ===");
     System.out.println(this.toString());
     System.out.println("Adversaires restants: " + adversaires.stream().filter(a -> !a.estNeutralise()).count());
     System.out.println("Collecteur: " + collecteur.getEnergie() + " unités d'énergie");
     System.out.println("Énergie du joueur: " + joueur.getEnergie() + " / " + ParametresJeu.MAX_ENERGIE);
   }
 
   /**
    * Restitue l'état du plateau (C'est cette chaîne qu'il faut afficher à chaque
    * tour).
    * 
    * @return La chaîne de caractères qui représente le plateau
    */
    
   @Override
   public String toString() {
       StringBuilder sb = new StringBuilder();
       
       // Bordure supérieure
       for (int j = 0; j < getNbCol(); j++) {
           sb.append(BORD).append(BORD);
       }
       sb.append("\n");
       
       // Contenu du plateau
       for (int i = 0; i < getNbLig(); i++) {
           // Bordure gauche
           sb.append(BORD);
           
           // Contenu de la ligne
           for (int j = 0; j < getNbCol(); j++) {
               sb.append(grille[i][j].toString());
           }
           
           // Bordure droite
           sb.append(BORD);
           sb.append("\n");
       }
       
       // Bordure inférieure
       for (int j = 0; j < getNbCol(); j++) {
           sb.append(BORD).append(BORD);
       }
       
       return sb.toString();
   }
 
   /**
    * Création d'une instance de type Plateau avec le nombre de lignes et colonnes spécifiés,
    * ainsi que le nombre d'adversaires et de bidons
    */
   public Plateau(int nbLig, int nbCol, int nbAdversaires, int nbBidons) {
     this.grille = new Salle[nbLig][nbCol];
     initContenu(nbAdversaires, nbBidons);
   }
 }