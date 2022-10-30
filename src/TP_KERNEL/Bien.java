package TP_KERNEL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Bien{

    protected String adresse;
    protected Wilaya wilaya;
    protected float superficie;
    protected Personne propriétaire;
    protected Transaction transaction;
    protected String date;
    protected String description;
    protected String[] pics;
    protected boolean archived;
    protected Prix prix;
    public List<Message> Comments = new ArrayList<>();
    private Scanner scnr = new Scanner(System.in);

    public Bien(String adresse, Wilaya wilaya, float superficie, Personne propriétaire, Transaction transaction, String date, String description, String[] pics, boolean archived, Prix prix) {
        this.adresse = adresse;
        this.wilaya = wilaya;
        this.superficie = superficie;
        this.propriétaire = propriétaire;
        this.transaction = transaction;
        this.date = date;
        this.description = description;
        this.pics = pics;
        this.archived = archived;
        this.prix = prix;
    }

    public void calc_prix() {
        this.prix.setPf(this.prix.getPi());
        if (this.transaction == Transaction.vente || this.transaction == Transaction.echange ) {
            if (this.prix.getPi() < 5000000) {
                if (this.wilaya.getPrix() < 50000) {
                    this.prix.setPf(this.prix.getPi() * 1.03);
                } else {
                    this.prix.setPf(this.prix.getPi() * 1.035);
                }
            } else if (this.prix.getPi() < 15000000 && this.prix.getPi() > 5000000) {
                if (this.wilaya.getPrix() < 50000) {
                    this.prix.setPf(this.prix.getPi() * 1.02);
                } else {
                    this.prix.setPf(this.prix.getPi() * 1.025);
                }
            } else if (this.prix.getPi() > 15000000) {
                if (this.wilaya.getPrix() < 70000) {
                    this.prix.setPf(this.prix.getPi() * 1.01);
                } else {
                    this.prix.setPf(this.prix.getPi() * 1.02);
                }
            }
        } else if (this.transaction == Transaction.location) {
            if (this.superficie < 60) {
                if (this.wilaya.getPrix() < 50000) {
                    this.prix.setPf(this.prix.getPf() + this.prix.getPi() * (0.01));
                } else {
                    this.prix.setPf(this.prix.getPf() + this.prix.getPi() * (0.015));
                }
            } else if (this.superficie >= 60 && this.superficie <= 150) {
                if (this.wilaya.getPrix() < 50000) {
                    this.prix.setPf(this.prix.getPf() + this.prix.getPi() * (0.02));
                } else {
                    this.prix.setPf(this.prix.getPf() + this.prix.getPi() * (0.025));
                }
            } else {
                if (this.wilaya.getPrix() < 50000) {
                    this.prix.setPf(this.prix.getPf() + this.prix.getPi() * (0.03));
                } else {
                    this.prix.setPf(this.prix.getPf() + this.prix.getPi() * (0.035));
                }
            }
        }
    }

    public void afficher_bien(){
        calc_prix();
        System.out.println("adresse :" + this.adresse + "\nwilaya :" + this.wilaya + "\nsuperficie :" + this.superficie + "\npropriétaire :" + this.propriétaire.getNom() + " " + this.propriétaire.getPrenom() + "\ntransaction :" + this.transaction + "\ndate :" + this.date + "\ndescription :" + this.description + "\narchived :" + this.archived);
        if (this.transaction == Transaction.echange){
            System.out.printf("Prix D'échange avec un bien de la mème wilaya : %.3f", this.prix.getPe());
            System.out.printf("\nPrix D'échange avec un bien d'une autre wilaya : %.3f", this.prix.getPew());
        }else{
            System.out.printf("Prix : %.3f", this.prix.getPf());
        }

    }

    public void ModifierBien() {
        System.out.println("Quel champ vouliez vous modifier ?");
        System.out.println("1-adresse \n 2-wilaya \n 3-superficie \n 4-transaction \n 5-description \n 6-archived \n 7-prix");

        int Choice = scnr.nextInt();
        switch (Choice){
            case 1:
                String drs = scnr.nextLine();
                this.setAdresse(drs);
                break;
            case 2:
                int wily = scnr.nextInt();
                this.setWilaya(Wilaya.values()[wily]);
                break;
            case 3:
                int sprf = scnr.nextInt();
                this.setSuperficie(sprf);
                break;
            case 4:
                int trns = scnr.nextInt();
                this.setTransaction(Transaction.values()[trns]);
                break;
            case 5:
                String desc = scnr.nextLine();
                this.setDescription(desc);
                break;
            case 6:
                Boolean arhv = scnr.nextBoolean();
                this.setArchived(arhv);
                break;
            case 7:
                double prx = scnr.nextDouble();
                this.prix.setPi(prx);
                this.calc_prix();
                break;
            case 'q':
                break;
            default:
                System.out.println("You entered the wrong ids");
                break;
        }

    }

    //Setters_Getters

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Wilaya getWilaya() {
        return wilaya;
    }

    public void setWilaya(Wilaya wilaya) {
        this.wilaya = wilaya;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public Personne getPropriétaire() {
        return propriétaire;
    }

    public void setPropriétaire(Personne propriétaire) {
        this.propriétaire = propriétaire;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public  Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public void AddComment( Message msg ){
        Comments.add(msg);
    }

    public void DeleteComment ( int i ){
        Comments.remove(i);
    }

    public void ShowComments (){
        for (Message msg : this.Comments) {
            msg.AfficheMsg();
        }
    }

}

