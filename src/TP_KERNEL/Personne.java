package TP_KERNEL;
public class Personne {

    private String nom;
    private String prenom;
    private String adresse;
    private String numero;
    private String adresse_mail ;
    private String mdp;
    private Bien[] list_bien = new Bien[1000] ;
    private int nb_bien= 0;

    public Personne(String nom, String prenom, String adresse, String numero, String adresse_mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numero = numero;
        this.adresse_mail = adresse_mail;
        this.mdp = mdp;
    }

    public void affecter_bien(Bien b){
    list_bien[nb_bien] = b ;
    nb_bien ++ ;
    }


    public void afficher_biens(){
        for(int i=0  ; i < nb_bien ; i ++){
            if (!list_bien[i].isArchived()){
                list_bien[i].afficher_bien();
            }
        }
    }


    public void afficher_personne(int h){
        System.out.println("nom : " + nom);
        System.out.println("prenom : " + prenom);
        System.out.println("adresse : " + adresse);
        System.out.println("numero de telephone : " + numero);
        System.out.println("adresse e-mal : " + adresse_mail);
        System.out.println("nombre de biens ajoutés : " + nb_bien);

        if (h==1) { afficher_biens(); }

    }


    public int getNb_bien() {
        return nb_bien;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }


    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getAdresse() {
        return adresse;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getAdresse_mail() {
        return adresse_mail;
    }


    public void setAdresse_mail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }


    public Bien[] getList_bien() {
        return list_bien;
    }


    public void setList_bien(Bien[] list_bien) {
        this.list_bien = list_bien;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
