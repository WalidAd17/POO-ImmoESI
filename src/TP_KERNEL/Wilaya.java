package TP_KERNEL;
public enum Wilaya {
    Adrar(100000),
    Chlef(30000),
    Laghouat(60000),
    Oum_El_Bouaghi(4000),
    Batna(5000),
    Bejaia(6000),
    Biskra(7000),
    Bechar(8000),
    Blida(9000),
    Bouira(10000),
    Tamanghasset(1100),
    Tebessa(1200),
    Tlemcen(1300),
    Tiaret(1400),
    Tizi_Ouzou(1500),
    Alger(1600),
    Djelfa(1700),
    Jijel(1800),
    Setif(1900),
    Saida(20000),
    Skikda(2100),
    Sidi_Bel_Abbes(2200),
    Annaba(2300),
    Guelma(2400),
    Constantine(2500),
    Medea(2600),
    Mostaganem(2700),
    Msila(2800),
    Mascara(2900),
    Ouargla(30000),
    Oran(3100),
    El_bayadh(3200),
    Illizi(3300),
    Bordj_Bou_Arreridj(3400),
    Boumerdes(3500),
    El_Taref(3600),
    Tindouf(3700),
    Tissemsilt(3800),
    El_Oued(3900),
    Khenchela(40000),
    Souk_Ahras(4100),
    Tipaza(4200),
    Mila(4300),
    Ain_Defla(4400),
    Naama(4500),
    Ain_Temouchent(4600),
    Ghardaia(4700),
    Relizane(4800),
    Wilaya(0);

    private int prix;

    Wilaya(int prix) {
        this.prix = prix;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

}
