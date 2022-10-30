package TP_KERNEL;
class Terrain extends NonHabitable {

    private String stat_juridique;
    private int NbFaçades;

    public Terrain(String adresse, Wilaya wilaya, float superficie, Personne propriétaire, Transaction transaction, String date, String description, String[] pics, boolean archived, Prix prix, String stat_juridique, int nbFaçades) {
        super(adresse, wilaya, superficie, propriétaire, transaction, date, description, pics, archived, prix);
        this.stat_juridique = stat_juridique;
        this.NbFaçades = nbFaçades;
    }


    @Override
    public void calc_prix(){
        super.calc_prix();
        if(this.transaction == Transaction.vente || this.transaction == Transaction.echange){
            this.prix.setPf(this.prix.getPf() + this.prix.getPi()*(0.05*this.NbFaçades));
        }

        if( this.transaction == Transaction.echange ){
            this.prix.setPe(this.prix.getPf());
            this.prix.setPew(this.prix.getPf() + this.prix.getPi()*0.025);
        }
    }

    @Override
    public void afficher_bien(){
        super.afficher_bien();
        System.out.println(" \nStatus juridique : " + this.stat_juridique + " \nNombre de façades : " + this.NbFaçades );
        System.out.println("\n Type de bien : Terrain ");
        System.out.println("o------------");
    }
}
