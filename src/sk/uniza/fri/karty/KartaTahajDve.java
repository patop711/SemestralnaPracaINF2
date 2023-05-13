package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import java.awt.Color;

/** Trieda KartaTahajDve(potomok triedy Karta)
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
public class KartaTahajDve extends Karta {
    public KartaTahajDve(int x, int y, Color farba) {
        super(x, y, farba, Znak.KARTA_TAHAJ_DVE);
    }

    @Override
    public Color getFarba() {
        return super.getFarba();
    }

    @Override
    public Znak getZnak() {
        return super.getZnak();
    }

    @Override
    public void vykresli() {
        super.vykresli();
    }

    @Override
    public void vyberSiFarbu() {
        super.vyberSiFarbu();
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }

    @Override
    public boolean vykonajAkciu(Hrac hrac) {
        //Polymorfizmus
        var predchazdajucaKarta = hrac.getHra().getBalikPouzitychUnoKariet().pocetKarietVBaliku() - 1;

        if (hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getFarba() == this.getFarba() ||
            hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getZnak() == this.getZnak()) {
            hrac.getHra().dajHracoviKartyPodlaPoctu(2, true);
            return true;
        }
        return false;
    }

}
