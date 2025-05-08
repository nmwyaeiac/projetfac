public class ReserveLimitee extends Reservoir {
    public ReserveLimitee() {
        super(ParametresJeu.getMaxEnergie());
    }

    @Override
    public int puiser(int quantite) {
        int prelevement = Math.min(quantite, this.energie);
        this.energie -= prelevement;
        return prelevement;
    }

    @Override
    public int stocker(int quantite) {
        int espace = ParametresJeu.getMaxEnergie() - this.energie;
        int ajoute = Math.min(quantite, espace);
        this.energie += ajoute;
        return ajoute;
    }
}