package TP_KERNEL;
import java.util.*;

public class Date {
    public int jour,mois,an;

    public Date(int jour, int mois, int an) {
        this.jour = jour;
        this.mois = mois;
        this.an = an;
    }

    public Date() {
        java.util.Date d1 = new java.util.Date();
        this.jour = d1.getDay();
        this.mois = d1.getMonth();
        this.an = d1.getYear();
    }

    public void changer_date(int jour, int mois, int an) {
        this.jour = jour;
        this.mois = mois;
        this.an = an;
    }

    public String afficher_date(){
        return jour+":"+mois+":"+an;
    }

}
