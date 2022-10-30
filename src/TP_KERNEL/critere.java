package TP_KERNEL;
public class critere {
    private Transaction trans;
    private Wilaya wil;
    private int prix_max,prix_min,sup_min,nb_piece_min;
    private Tdb type_de_bien;

    public critere(Transaction trans, Wilaya wil, int prix_max, int prix_min, int sup_min, int nb_piece_min, Tdb type_de_bien) {
        this.trans = trans;
        this.wil = wil;
        this.prix_max = prix_max;
        this.prix_min = prix_min;
        this.sup_min = sup_min;
        this.nb_piece_min = nb_piece_min;
        this.type_de_bien = type_de_bien;
    }

    public Transaction getTrans() {
        return trans;
    }

    public void setTrans(Transaction trans) {
        this.trans = trans;
    }

    public Wilaya getWil() {
        return wil;
    }

    public void setWil(Wilaya wil) {
        this.wil = wil;
    }

    public int getPrix_max() {
        return prix_max;
    }

    public void setPrix_max(int prix_max) {
        this.prix_max = prix_max;
    }

    public int getPrix_min() {
        return prix_min;
    }

    public void setPrix_min(int prix_min) {
        this.prix_min = prix_min;
    }

    public int getSup_min() {
        return sup_min;
    }

    public void setSup_min(int sup_min) {
        this.sup_min = sup_min;
    }

    public int getNb_piece_min() {
        return nb_piece_min;
    }

    public void setNb_piece_min(int nb_piece_min) {
        this.nb_piece_min = nb_piece_min;
    }

    public Tdb getType_de_bien() {
        return type_de_bien;
    }

    public void setType_de_bien(Tdb type_de_bien) {
        this.type_de_bien = type_de_bien;
    }
}
