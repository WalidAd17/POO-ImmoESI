package TP_KERNEL;
import java.util.Scanner;

class Maison extends Habitable {

    private int Nb_Garage;
    private boolean jardin, piscine;

    private Scanner scnr = new Scanner(System.in);

    public Maison(String adresse, Wilaya wilaya, float superficie, Personne propriétaire, Transaction transaction, String date, String description, String[] pics, boolean archived, Prix prix, int nb_pieces, boolean meublé, int etage, int nb_Garage, boolean jardin, boolean piscine) {
        super(adresse, wilaya, superficie, propriétaire, transaction, date, description, pics, archived, prix, nb_pieces, meublé, etage);
        Nb_Garage = nb_Garage;
        this.jardin = jardin;
        this.piscine = piscine;
    }

    @Override
    public void calc_prix() {
        super.calc_prix();
        if (this.transaction == Transaction.vente || this.transaction == Transaction.echange) {
            if (this.jardin || this.piscine || this.Nb_Garage > 0) {
                this.prix.setPf(this.prix.getPf() + this.prix.getPi() * 0.05);
            }
        } else if (this.transaction == Transaction.location) {
            if (this.piscine) this.prix.setPf(this.prix.getPf() + 50000);
        }

        if (this.transaction == Transaction.echange) {
            this.prix.setPe(this.prix.getPf());
            this.prix.setPew(this.prix.getPf() + this.prix.getPi() * 0.025);
        }
    }

    ;

    @Override
    public void ModifierBien() {
        super.ModifierBien();

        System.out.println("Et finalement, Quels sont vos derniers champs que vous vouliez modifier ?");
        System.out.println("1-Nombre de garages \n 2-Jardin \n 3-Piscine");

        int Choice = scnr.nextInt();
        switch (Choice) {
            case 1:
                int nb_grg = scnr.nextInt();
                this.setNb_Garage(nb_grg);
                break;
            case 2:
                Boolean jrd = scnr.nextBoolean();
                this.setJardin(jrd);
                break;
            case 3:
                Boolean psn = scnr.nextBoolean();
                this.setPiscine(psn);
                break;
            default:
                System.out.println("An error Occurred");


        }

    }

    @Override
    public void afficher_bien(){
        super.afficher_bien();
        System.out.println("Nombre Garage :" + this.getNb_Garage() + "\nJardin :" + this.isJardin() + "\nPiscine :" + this.isPiscine());
        System.out.println("\n Type de bien : Maison ");
        System.out.println("o------------");
    }

    public int getNb_Garage() {
        return Nb_Garage;
    }

    public void setNb_Garage(int nb_Garage) {
        Nb_Garage = nb_Garage;
    }

    public boolean isJardin() {
        return jardin;
    }

    public void setJardin(boolean jardin) {
        this.jardin = jardin;
    }

    public boolean isPiscine() {
        return piscine;
    }

    public void setPiscine(boolean piscine) {
        this.piscine = piscine;
    }
}
