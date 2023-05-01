package sk.uniza.fri.hrac;

import sk.uniza.fri.hlavnyProgram.Hra;
import sk.uniza.fri.karty.Karta;
import sk.uniza.fri.shapesge.Text;

import java.util.ArrayList;

public class Hrac {
    private final String meno;
    private final ArrayList<Karta> mojeKarty;
    private final Text menoHraca;
    private final Hra hra;

    /**
     * Parametricky konstruktor, kde zadáme meno hráča, ktoré sa potom vytvorí v triede Text a vytvorí sa ArrayList
     * s hráčovími kartami
     *
     * @param meno - meno hráča
     */
    public Hrac(String meno, Hra hra) {
        this.hra = hra;
        this.meno = meno;
        this.menoHraca = new Text(this.getMeno());
        this.getMenoHraca().makeVisible();
        this.mojeKarty = new ArrayList<>();

    }

    public Hra getHra() {
        return this.hra;
    }

    /**
     * Vráti návratovú hodnotu typu Text
     *
     * @return menoHraca - Vráti návratovú hodnotu typu Text
     */
    public Text getMenoHraca() {
        return this.menoHraca;
    }

    /**
     * Nastavi poziciu mena hráča
     *
     * @param x - pozícia X textu
     * @param y - pozícia Y textu
     */
    public void setPoziciaMenoHraca(int x, int y) {
        this.getMenoHraca().setX(x);
        this.getMenoHraca().setY(y);
        this.getMenoHraca().makeVisible();
        //novaKarta.zmenPoziciu(poslednaKarta.getVonkajsiaVrstva().getX() + 88, poslednaKarta.getVonkajsiaVrstva().getY());
        for (Karta karta : this.getMojeKarty()) {
            karta.posunZvisle(y);
        }
    }

    public void skryKarty() {
        for (Karta karta : this.getMojeKarty()) {
            karta.rub();
        }
    }

    public void zobrazKarty() {
        for (Karta karta : this.getMojeKarty()) {
            karta.vykresli();
        }
    }

    /**
     * Metóda na vrátenie mena hráča
     *
     * @return meno - vráti meno hráča
     */
    public String getMeno() {
        return this.meno;
    }

    /**
     * Vráti objekt typu ArrayList karietHraca
     *
     * @return mojeKarty
     */
    public ArrayList<Karta> getMojeKarty() {
        return this.mojeKarty;
    }

    /**
     * Metóda ktorá použije kartu z hráčovej ruky a následne ju vymaže z ArrayListu a zoradí karty
     *
     * @param karta - karta ktorú použijem z mojej ruky(ArrayListu)
     */
    //TODO zabezpečiť aby sa odstranene karty dali do ArrayListu pouziteKarty v triede hra - OK
    public void pouziKartu(Karta karta) {
        if (this.getMojeKarty().contains(karta)) {

            if (karta.vykonajAkciu(this)) {
                int indexKartyNaOdstranenie = this.getMojeKarty().indexOf(karta);
                //karta.skrySa();
                this.getMojeKarty().remove(indexKartyNaOdstranenie);

                this.getHra().pridajPouzituKartu(karta);

                // Po pouziti karty, ostatne karty zmenia pozíciu
                for (int i = indexKartyNaOdstranenie; i < this.getMojeKarty().size(); i++) {
                    Karta kartaVRuke = this.getMojeKarty().get(i);
                    int predchazdajuceX = kartaVRuke.getVonkajsiaVrstva().getX();
                    int predchazdajuceY = kartaVRuke.getVonkajsiaVrstva().getY();
                    kartaVRuke.zmenPoziciu(predchazdajuceX - 88, predchazdajuceY);
                }
                this.getHra().dalsiHrac();
            }
        }
    }

    public void zoradKartyPodlaFarby() {
        //TODO treba zoradit karty podla farby
    }


//    public void zoberKartu(Karta karta) {
//        if (this.getMojeKarty().isEmpty()) {
//            Karta prvaKarta = karta;
//            prvaKarta.zmenPoziciu(720, 320);
//            this.getMojeKarty().add(prvaKarta);
//        } else {
//            Karta novaKarta = karta;
//            novaKarta.zmenPoziciu(getMojeKarty().get(getMojeKarty().size() - 1).getVonkajsiaVrstva().getX(),getMojeKarty().get(getMojeKarty().size() - 1).getVonkajsiaVrstva().getY());
//            getMojeKarty().get(getMojeKarty().size() - 1).zmenPoziciu(getMojeKarty().get(getMojeKarty().size() - 1).getVonkajsiaVrstva().getX() - 88,getMojeKarty().get(getMojeKarty().size() - 1).getVonkajsiaVrstva().getY());
//            this.getMojeKarty().add(novaKarta);
//        }
//        System.out.println("Karta bola pridaná pre hraca menom " + getMeno() + " " + getMojeKarty().size());
//    }

    /**
     * Metoda ktorá zoberie kartu z balicka kariet (ArrayList) z triedy hra a pridá ich hráčovi do ruky pričom sa usporiadajú aby neboli všetky na tej istej
     * pozícii. Pokial ideme dat prvu kartu tak sa da vedla mena hráča
     *
     * @param karta - karta ktorú ideme vložit do hráčovej ruky(ArrayList)
     */
    //TODO Zabezpeciť aby boli karty zoradene podla farby (a možno čísla ak sa to bude dať) -
    // skôr samostatna metoda ktorú budem volať pri tejto metode a metode pouzi kartu
    public boolean zoberKartu(Karta karta) {
        var vysledok = false;
        if (this.getMojeKarty().isEmpty()) {
            Karta prvaKarta = karta;
            prvaKarta.zmenPoziciu(this.getMenoHraca().getX() + 110, this.getMenoHraca().getY());
            this.getMojeKarty().add(prvaKarta);
            vysledok = true;
        } else {
            Karta novaKarta = karta;
            Karta poslednaKarta = this.getMojeKarty().get(this.getMojeKarty().size() - 1);

            if (this.getMojeKarty().size() < 7) {
                this.getMojeKarty().add(novaKarta);
                novaKarta.zmenPoziciu(poslednaKarta.getVonkajsiaVrstva().getX() + 88, poslednaKarta.getVonkajsiaVrstva().getY());
                vysledok = true;
            } else {
                System.out.println("Hrac ma maximum kariet!");
                vysledok = false;
            }
        }
        return vysledok;
    }

    /**
     * Otočí všetky hráčove ktoré ma dostupné
     */
    public void otocMojeKarty() {
        for (Karta karta : this.getMojeKarty()) {
            karta.skrySa();

        }
    }


//    public static void main(String[] args) {
//        String meno = "Patrik";
//
//        Hrac hrac = new Hrac(meno.toUpperCase());
//        hrac.setPoziciaMenoHraca(10, 600);
//        hrac.getMenoHraca().makeVisible();
//        KartaOtocit kartaOtocit = new KartaOtocit(0, 0, Color.orange);
//        KartaPreskocit kartaPreskocit = new KartaPreskocit(0, 0, Color.MAGENTA);
//        KartaDivoka kartaDivoka = new KartaDivoka(0, 0);
//        KartaNormalna kartaNormalna = new KartaNormalna(0, 0, Color.BLUE, Znak.KARTA_JEDEN);
//        KartaDivoka kartaDivoka1 = new KartaDivoka(-10, -100);
//        hrac.zoberKartu(kartaOtocit);
//        hrac.zoberKartu(kartaPreskocit);
//        hrac.zoberKartu(kartaDivoka);
//        hrac.zoberKartu(kartaNormalna);
//        hrac.pouziKartu(kartaPreskocit);
//        hrac.pouziKartu(kartaOtocit);
//        hrac.pouziKartu(kartaDivoka1);
//        //hrac.otocMojeKarty();
//    }
}
