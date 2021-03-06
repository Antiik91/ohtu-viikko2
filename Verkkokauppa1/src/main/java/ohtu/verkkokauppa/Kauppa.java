package ohtu.verkkokauppa;

public class Kauppa {

    private TuoteVarasto varasto;
    private Tilisiirto pankki;
    private Ostoskori ostoskori;
    private InstanceGeneraattori viitegeneraattori;
    private String kaupanTili;

//    public Kauppa() {
//        varasto = Varasto.getInstance();
//        pankki = Pankki.getInstance();
//        viitegeneraattori = Viitegeneraattori.getInstance();
//        kaupanTili = "33333-44455";
//    }

    public Kauppa(TuoteVarasto tv, Tilisiirto pankki, InstanceGeneraattori is) {
        varasto = tv;
        this.pankki = pankki;
        viitegeneraattori = is;
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varasto.haeTuote(id);
        varasto.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varasto.saldo(id) > 0) {
            Tuote t = varasto.haeTuote(id);
            ostoskori.lisaa(t);
            varasto.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattori.uusi();
        int summa = ostoskori.hinta();

        return pankki.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
