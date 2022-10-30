package TP_KERNEL;

abstract class NonHabitable extends Bien {
    public NonHabitable(String adresse, Wilaya wilaya, float superficie, Personne propriétaire, Transaction transaction, String date, String description, String[] pics, boolean archived, Prix prix) {
        super(adresse, wilaya, superficie, propriétaire, transaction, date, description, pics, archived, prix);
    }
}
