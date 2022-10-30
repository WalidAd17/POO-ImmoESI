package TP_KERNEL;
import java.util.Scanner;

class Appartement extends Habitable {
    private Type sidu;

    private Scanner scnr = new Scanner(System.in);

    public Appartement(String adresse, Wilaya wilaya, float superficie, Personne propriétaire, Transaction transaction, String date, String description, String[] pics, boolean archived, Prix prix, int nb_pieces, boolean meublé, int etage, Type sidu) {
        super(adresse, wilaya, superficie, propriétaire, transaction, date, description, pics, archived, prix, nb_pieces, meublé, etage);
        this.sidu = sidu;
    }

    @Override
    public void calc_prix() {
        super.calc_prix();
        if(this.transaction == Transaction.vente || this.transaction == Transaction.echange) {
            if (this.etage <= 2) {
                this.prix.setPf(this.prix.getPf() + 50000);
            }
        }else if (this.transaction == Transaction.location){
            if(this.etage <= 2){
                this.prix.setPf(this.prix.getPf() + 5000);
            }else if (this.etage >= 600000){
                this.prix.setPf(this.prix.getPf() - 500 * this.getSuperficie());
            }
        }

        if( this.transaction == Transaction.echange ){
            this.prix.setPe(this.prix.getPf());
            this.prix.setPew(this.prix.getPf() + this.prix.getPi()*0.025);
        }
    }

    @Override
    public void ModifierBien(){
        super.ModifierBien();
        System.out.println("Type(Simplex/Duplex) : ");
        int sd = scnr.nextInt();
        this.sidu = Type.values()[sd];
    }

    @Override
    public void afficher_bien(){
        super.afficher_bien();
        System.out.println("\nType(Simplex/Duplex) :" + this.sidu);
        System.out.println("\n Type de bien : Appartement ");
        System.out.println("o------------");
    }

}
