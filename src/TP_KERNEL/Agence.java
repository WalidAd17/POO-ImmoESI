package TP_KERNEL;

import TP_GUI.Main;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class  Agence {

    public static void setNb_proprios(int nb_proprios) {
        Agence.nb_proprios = nb_proprios;
    }

    private static final Personne admin = new Personne("admin","admin","l'agence","0000000000","tt","tt");
    private static Bien[] liste_biens = new Bien[1000000];
    private static Bien[] liste_archives = new Bien[1000000];
    private static Personne[] liste_proprios = new Personne[1000];
    private static int nb_biens=0,nb_archives=0,nb_proprios=0;
    private static Personne connecte = null;

    public static void setListe_biens(Bien[] liste_biens) {
        Agence.liste_biens = liste_biens;
    }

    public static void setNb_biens(int nb_biens) {
        Agence.nb_biens = nb_biens;
    }

    public static Bien[] getListe_biens() {
        return liste_biens;
    }

    public static Bien[] getListe_archives() {
        return liste_archives;
    }

    public static Personne[] getListe_proprios() {
        return liste_proprios;
    }

    public static int getNb_biens() {
        return nb_biens;
    }

    public static int getNb_archives() {
        return nb_archives;
    }

    public static int getNb_proprios() {
        return nb_proprios;
    }

    public static Bien[] recherche(critere c, IntContainer fix){

        Bien[] result = new Bien[1000000];
        int nb_trouve=0;

        for(int i=0; i<nb_biens; i++){
            if (!liste_biens[i].isArchived()){
                nope : {
                if (c.getSup_min()!=-1){
                    if (liste_biens[i].getSuperficie() < c.getSup_min()){break nope ;}
                }

                if (c.getTrans()!=Transaction.Transaction){
                    if (liste_biens[i].getTransaction() != c.getTrans()){break nope ;
                    }
                    else{

                        if (liste_biens[i].getTransaction() == Transaction.echange ){
                            if (c.getPrix_max()!=-1){
                                if (liste_biens[i].getPrix().getPe() > c.getPrix_max()){break nope ;}
                            }

                            if (c.getPrix_min()!=-1){
                                if (liste_biens[i].getPrix().getPe() < c.getPrix_min()){break nope ;}
                            }
                        }
                        else {
                            if (c.getPrix_max()!=-1){
                                if (liste_biens[i].getPrix().getPf() > c.getPrix_max()){break nope ;}
                            }

                            if (c.getPrix_min()!=-1){
                                if (liste_biens[i].getPrix().getPf() < c.getPrix_min()){break nope ;}
                            }
                        }
                    }
                }

                if (c.getWil() != Wilaya.Wilaya){
                    if (liste_biens[i].getWilaya() != c.getWil()){break nope ;}
                }


                if (c.getType_de_bien()!=Tdb.Le_Type){

                    if (c.getType_de_bien()==Tdb.habitable){
                        if (!(liste_biens[i] instanceof Habitable)){break nope ;}
                        else{
                            if (c.getNb_piece_min()!=-1){
                                if (((Habitable)liste_biens[i]).getNb_pieces() < c.getNb_piece_min()){break nope ;}
                            }
                        }
                    }
                    else{


                        if (c.getType_de_bien()==Tdb.appart){
                            if (!(liste_biens[i] instanceof Appartement)){break nope ;}
                            else{
                                if (c.getNb_piece_min()!=-1){
                                    if (((Habitable)liste_biens[i]).getNb_pieces() < c.getNb_piece_min()){break nope ;}
                                }
                            }
                        }
                        else{

                            if (c.getType_de_bien()==Tdb.maison){
                                if (!(liste_biens[i] instanceof Maison)){break nope ;}
                                else{
                                    if (c.getNb_piece_min()!=-1){
                                        if (((Habitable)liste_biens[i]).getNb_pieces() < c.getNb_piece_min()){break nope ;}
                                    }
                                }
                            }
                            else{

                                if (c.getType_de_bien()==Tdb.non_habitable){
                                    if (!(liste_biens[i] instanceof NonHabitable)){break nope ;}
                                }
                                else{

                                    if (c.getType_de_bien()==Tdb.terrain){
                                        if (!(liste_biens[i] instanceof Terrain)){break nope ;}
                                    }
                                }
                            }
                        }
                    }
                }

                result[nb_trouve]=liste_biens[i];
                nb_trouve ++;

                }
            }
        }

        fix.setI(nb_trouve);
        return result ;
    }

    public static void archiver(Bien b){
        boolean exist=false;
        for(int i=0;i<nb_archives;i++){
            if (b==liste_archives[i]){
                exist=true;
            }
        }

        if (!exist){
            liste_archives[nb_archives]=b;
            nb_archives++;
            b.setArchived(true);
        }
    }

    public static void afficher(Bien[] show,int taille){
        for (int i=1 ; i<=taille ; i++){
            System.out.println(i + "----------------");
            show[i-1].afficher_bien();
        }
    }

    public static void supprimer(Bien good){
        good.setArchived(true);
    }

    //account

    public static boolean connect(String email, String mdp, Reason reason){
        reason.setReason(0);
        Personne found = null;
        int id = 0;
        for (int i=0; i<nb_proprios; i++){
            if (liste_proprios[i].getAdresse_mail().equals(email)){
                found = liste_proprios[i];
                id = i;
            }
        }
        if (found != null){
            if (mdp.equals(found.getMdp())){
                connecte= found;
                Main.setConnectedUser(liste_proprios[id]);
                return true;
            }
            else{
                reason.setReason(2);
            }
        }
        else{
            if ( reason.getReason() == 2 )
                reason.setReason(3);
            else
                reason.setReason(1);
        }
        return false;
    }

    public static void deconnect(){connecte=null;}

    // screens

    public static void main_screen(){

        Scanner scn = new Scanner(System.in);
        clearScreen();

        if (connecte == null){

            System.out.println("1-se connecter\n2-afficher tous les biens\n3-rechercher");
            int i = 0;
            while ((i<1)||(i>3)){
                i= scn.nextInt();
            }
            switch (i){
                case 1 :
                    connection_screen();
                    break;

                case 2 :
                    show_all_screen();
                    break;

                case 3 :
                    search_sceen();
                    break;
            }
        }
        else{
            if (connecte != admin) {

                System.out.println("1-afficher tous les biens\n2-rechercher\n3-afficher mes biens\n4-se deconnecter");
                int i = 0;
                while ((i < 1) || (i > 4)) {
                    i = scn.nextInt();
                }
                switch (i) {
                    case 1:
                        show_all_screen();
                        break;

                    case 2:
                        search_sceen();
                        break;

                    case 3:
                        show_mine_screen();
                        break;

                    case 4:
                        deconnect();
                        main_screen();
                        break;
                }

            }
            else{

                System.out.println("1-afficher tous les biens\n2-rechercher\n3-afficher les utilisateurs\n4-afficher les archives\n5-se deconnecter");
                int i = 0;
                while ((i < 1) || (i > 5)) {
                    i = scn.nextInt();
                }
                switch (i) {
                    case 1:
                        show_all_screen();
                        break;

                    case 2:
                        search_sceen();
                        break;

                    case 3:
                        show_all_users();
                        break;

                    case 4:
                        show_archived();
                        break;

                    case 5:
                        deconnect();
                        main_screen();
                        break;
                }


            }
        }

        main_screen();
    }

    public static void connection_screen(){
        Scanner scn = new Scanner(System.in);
        clearScreen();
        System.out.println("1-compte existant\n2-nouveau compte\n3-sortir");

        int i = 0;
        while ((i<1)||(i>3)){
            i= scn.nextInt();
        }
        switch (i){

            case 1 :

                clearScreen();
                //connect();
                break;


            case 2 :

                System.out.println("entrez votre adresse nom");
                String nom = scn.nextLine();
                System.out.println("entrez votre adresse prenom");
                String prenom = scn.nextLine();
                System.out.println("entrez votre adresse adresse");
                String adresse = scn.nextLine();
                System.out.println("entrez votre adresse numero de telephone");
                String num = scn.nextLine();
                System.out.println("entrez votre adresse email");
                String mail = scn.nextLine();
                System.out.println("entrez votre adresse mot de passe");
                String mdp = scn.nextLine();

                Personne neww = new Personne(nom,prenom,adresse,num,mail,mdp);
                liste_proprios[nb_proprios]= neww;
                nb_proprios++;
                connecte=neww;

                break;

            case 3 :
                main_screen();
                break;
        }

    main_screen();
    };

    public static void show_all_screen(){
        Scanner scn = new Scanner(System.in);
        clearScreen();

        Bien[] everything = new Bien[1000000];
        int taille = 0;
        for (int i =0; i< nb_biens; i++){
            if (!liste_biens[i].isArchived()){
                everything[taille]=liste_biens[i];
                taille ++;
            }
        }
        afficher(everything,taille);


        if ((connecte == null)||(connecte == admin)){

            System.out.println("1-selectionner un bien\n2-retour");
            int i = 0;
            while ((i<1)||(i>2)){
                i= scn.nextInt();
            }

            switch (i){
                case 1 :

                    System.out.println("entrez le numero du bien que vous voulez selectionner (entre 1 et "+(taille+1)+" )");
                    int c=0;
                    while ((c<1)||(c>taille+1)){
                        c= scn.nextInt();
                    }
                    select_screen(everything[c-1]);


                    break;

                case 2 :
                    main_screen();
                    break;
            }
        }
        else{

            System.out.println("1-ajouter un bien\n2-selectionner un bien\n3-retour");
            int i = 0;
            while ((i < 1) || (i > 3)) {
                i = scn.nextInt();
            }
            switch (i) {
                case 1:
                   // add_good();
                    break;

                case 2:

                    System.out.println("entrez le numero du bien que vous voulez selectionner (entre 1 et "+(taille+1)+" )");
                    int c=0;
                    while ((c<1)||(c>taille+1)){
                        c= scn.nextInt();
                    }
                    select_screen(everything[c]);

                    break;

                case 3:
                    main_screen();
                    break;

            }



        }
    }

    public static void select_screen(Bien selected){

        Scanner scn = new Scanner(System.in);
        clearScreen();

        if (connecte == admin){

            System.out.println("1-archiver\n2-supprimer\n3-afficher proprio\n4-retour");
            int i = 0;
            while ((i<1)||(i>4)){
                i= scn.nextInt();
            }
            switch (i){
                case 1 :
                    archiver(selected);
                    break;

                case 2 :
                    supprimer(selected);
                    break;

                case 3 :
                    selected.getPropriétaire().afficher_personne(1);
                    break;

                case 4 :
                    main_screen();
                    break;

            }
        }
        else{
            if (connecte == null) {

                System.out.println("1-afficher proprio\n2-retour");
                int i = 0;
                while ((i < 1) || (i > 2)) {
                    i = scn.nextInt();
                }
                switch (i) {
                    case 1:
                        selected.getPropriétaire().afficher_personne(1);
                        break;

                    case 2:
                        main_screen();
                        break;
                }
            }
            else{

                System.out.println("1-envoyer un message\n2-afficher proprio\n3-retour");
                int i = 0;
                while ((i < 1) || (i > 3)) {
                    i = scn.nextInt();
                }
                switch (i) {
                    case 1:
                        System.out.println("écrivez votre message :");
                        String msg = scn.nextLine();
                        selected.AddComment(new Message(msg,selected,connecte));
                        break;

                    case 2:
                        selected.getPropriétaire().afficher_personne(1);
                        break;

                    case 3:
                        main_screen();
                        break;

                }
            }
        }
        main_screen();
    }

    public static void show_mine_screen(){
        clearScreen();
        Scanner scn = new Scanner(System.in);
        connecte.afficher_biens();

        System.out.println("1-sortir");
        int b=0;
        while (b!=1){
            b= scn.nextInt();
        }
    }

    public static void show_all_users(){
        clearScreen();
        Scanner scn = new Scanner(System.in);

        for (int i=0; i<nb_proprios; i++){
            liste_proprios[i].afficher_personne(0);
        }

        System.out.println("1-sortir");
        int b=0;
        while (b!=1){
            b= scn.nextInt();
        }
    }

    public static void show_archived(){
        clearScreen();
        Scanner scn = new Scanner(System.in);

        for (int i=0; i<nb_archives; i++){
            liste_archives[i].afficher_bien();
        }

        System.out.println("1-sortir");
        int b=0;
        while (b!=1){
            b= scn.nextInt();
        }
        main_screen();
    }

    public static void search_sceen(){
        clearScreen();
        Scanner scn = new Scanner(System.in);

        Transaction trans=Transaction.Transaction;
        Wilaya wil;
        int prix_max,prix_min,sup_min,nb_piece_min;
        Tdb type_de_bien=Tdb.Le_Type;

        System.out.println("selectionnez les critéres importants :");
        System.out.println("type de bien ?\n1-appart\n2-maison\n3-terrain\n4-habitable\n5-non-habitable\n6-ce n'est pas important");
        int i = 0;
        while ((i<1)||(i>6)){
            i= scn.nextInt();
        }
        switch (i) {
            case 1: type_de_bien= Tdb.appart; break;
            case 2: type_de_bien= Tdb.maison; break;
            case 3: type_de_bien= Tdb.terrain; break;
            case 4: type_de_bien= Tdb.habitable; break;
            case 5: type_de_bien= Tdb.non_habitable; break;
            case 6: type_de_bien= Tdb.Le_Type; break;
        }

        System.out.println("wilaya ?\n1~48 pour les wilayas\n49 si ce n'est pas important");
        int j = 50;
        while ((j<1)||(j>49)){
            j= scn.nextInt();
        }
        wil=Wilaya.values()[j-1];

        System.out.println("transaction voulue?\n1-vente\n2-location\n3-echange\n4-ce n'est pas important");
        int Z = 0;
        while ((Z<1)||(Z>4)){
            Z= scn.nextInt();
        }
        switch (Z) {
            case 1: trans= Transaction.vente; break;
            case 2: trans= Transaction.location; break;
            case 3: trans= Transaction.echange; break;
            case 4: trans= Transaction.Transaction; break;
        }


        System.out.println("prix maximal ?\nn'importe quel nombre pour le prix\n(-1) si ce n'est pas important");
        prix_max= scn.nextInt();

        System.out.println("prix minimal ?\nn'importe quel nombre pour le prix\n(-1) si ce n'est pas important");
        prix_min= scn.nextInt();

        System.out.println("superficie minimale ?\nn'importe quel nombre pour le prix\n(-1) si ce n'est pas important");
        sup_min= scn.nextInt();

        System.out.println("nombre minimal de pieces ?\nn'importe quel nombre pour le prix\n(-1) si ce n'est pas important");
        nb_piece_min= scn.nextInt();

        //Start of work
        critere cc = new critere(trans,wil,prix_max,prix_min,sup_min,nb_piece_min,type_de_bien);
        IntContainer n_resultttt = new IntContainer(0);
        Bien[] result = recherche(cc,n_resultttt);
        afficher(result,n_resultttt.getI());

        int n_result = n_resultttt.getI();

        System.out.println("1-selectionner un bien\n2-retour");
        int w = 0;
        while ((w < 1) || (w > 2)) {
            w = scn.nextInt();
        }
        switch (w) {

            case 1:

                System.out.println("entrez le numero du bien que vous voulez selectionner (entre 1 et "+(n_result)+" )");
                int c=0;
                while ((c<1)||(c>n_result+1)){
                    c= scn.nextInt();
                }
                select_screen(result[c-1]);

                break;

            case 2:
                main_screen();
                break;

        }


    }

    public static int add_good(int tdb, String adresse, String desc, int nb_pieces, int etage, Wilaya wil, Transaction trans, String date, boolean meublé, Prix prix, Type sidu, float sup, String stat, int nb_façades, int nb_garage, boolean jardin, boolean piscine){

        switch (tdb+1) {
            case 1:

                Appartement neww = new Appartement(adresse,wil,sup,connecte,trans,date,desc,null,false,prix,nb_pieces,meublé,etage,sidu);
                connecte.affecter_bien(neww);
                liste_biens[nb_biens]=neww;
                nb_biens++;
                break;

            case 2:

                Maison newww = new Maison(adresse,wil,sup,connecte,trans,date,desc,null,false,prix,nb_pieces,meublé,etage,nb_garage,jardin,piscine);
                connecte.affecter_bien(newww);
                liste_biens[nb_biens]=newww;
                nb_biens++;
                break;


            case 3:

                Terrain newwww = new Terrain(adresse,wil,sup,connecte,trans,date,desc,null,false,prix,stat,nb_façades);
                break;

        }

        return nb_biens-1;
    }



    public static void main(String[] args) {

        try {

            LocalDate hi = LocalDate.now();
            String date = String.valueOf(new Date(hi.getDayOfMonth(), hi.getMonthValue(), hi.getYear()));

            liste_proprios[0] = admin;
            liste_proprios[1] = new Personne("prop", "1", "adresse1", "0550528764", "email1@email.dz", "prop1");
            liste_proprios[2] = new Personne("prop", "2", "adresse2", "0550528764", "email2@email.dz", "prop2");
            liste_proprios[3] = new Personne("prop", "3", "adresse3", "0550528764", "email3@email.dz", "prop3");
            liste_proprios[4] = new Personne("prop", "4", "adresse4", "0550528764", "email4@email.dz", "prop4");
            nb_proprios = 5;

            liste_biens[0] = new Appartement("somewhere", Wilaya.Chlef, 120, liste_proprios[2], Transaction.vente, date, "", null, false, new Prix(4000000, true), 4, false, 1, Type.simplexe);
            liste_proprios[2].affecter_bien(liste_biens[0]);
            liste_biens[1] = new Appartement("somewhere", Wilaya.Laghouat, 100, liste_proprios[2], Transaction.location, date, "", null, false, new Prix(40000, true), 3, false, 1, Type.simplexe);
            liste_proprios[2].affecter_bien(liste_biens[1]);
            liste_biens[2] = new Appartement("somewhere", Wilaya.Laghouat, 50, liste_proprios[2], Transaction.location, date, "", null, false, new Prix(60000, true), 1, false, 6, Type.simplexe);
            liste_proprios[2].affecter_bien(liste_biens[2]);

            liste_biens[3] = new Maison("somewhere", Wilaya.Laghouat, 200, liste_proprios[1], Transaction.vente, date, "", null, false, new Prix(10000000, true), 18, false, 3, 0, false, false);
            liste_proprios[1].affecter_bien(liste_biens[3]);
            liste_biens[4] = new Maison("somewhere", Wilaya.Chlef, 160, liste_proprios[3], Transaction.location, date, "", null, false, new Prix(150000, true), 18, false, 3, 0, false, true);
            liste_proprios[3].affecter_bien(liste_biens[4]);
            liste_biens[5] = new Maison("somewhere", Wilaya.Chlef, 200, liste_proprios[2], Transaction.echange, date, "", null, false, new Prix(14000000, true), 18, false, 3, 1, false, false);
            liste_proprios[2].affecter_bien(liste_biens[5]);

            liste_biens[6] = new Terrain("somewhere", Wilaya.Adrar, 500, liste_proprios[1], Transaction.vente, date, "", null, false, new Prix(20000000, true), "stat", 3);
            liste_proprios[1].affecter_bien(liste_biens[6]);
            liste_biens[7] = new Terrain("somewhere", Wilaya.Adrar, 650, liste_proprios[1], Transaction.vente, date, "", null, false, new Prix(18000000, true), "stat", 1);
            liste_proprios[1].affecter_bien(liste_biens[7]);
            nb_biens = 8;
            for ( int i = 0; i < nb_biens ; i++){
                liste_biens[i].calc_prix();
            }
            //main_screen();


        }catch(InputMismatchException test){
            System.out.println("entrée non valide\n\n");
            main(null);
        }
    }

    public static void clearScreen() {
        System. out. print("\033[H\033[2J");
        System. out. flush();
    }
}



