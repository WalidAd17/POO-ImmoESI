package TP_KERNEL;
abstract class Habitable extends Bien {
    protected int Nb_pieces;
    protected boolean meublé;
    protected int etage;
    protected int sup_hab;

    public Habitable(String adresse, Wilaya wilaya, float superficie, Personne propriétaire, Transaction transaction, String date, String description, String[] pics, boolean archived, Prix prix, int nb_pieces, boolean meublé, int etage) {
        super(adresse, wilaya, superficie, propriétaire, transaction, date, description, pics, archived, prix);
        Nb_pieces = nb_pieces;
        this.meublé = meublé;
        this.etage = etage;
    }

    public void afficher_bien(){
        super.afficher_bien();
        System.out.println("\nNombre de pièces : " + Nb_pieces);
        System.out.println("\nMeublé : " + meublé);
        System.out.println("\nEtage : " + etage);
    }

    //Getters_Setters


    public int getNb_pieces() {
        return Nb_pieces;
    }

    public void setNb_pieces(int nb_pieces) {
        Nb_pieces = nb_pieces;
    }

    public boolean isMeublé() {
        return meublé;
    }

    public void setMeublé(boolean meublé) {
        this.meublé = meublé;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }
}
