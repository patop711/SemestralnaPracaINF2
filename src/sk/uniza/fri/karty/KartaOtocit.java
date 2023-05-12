package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import java.awt.Color;

public class KartaOtocit extends Karta {
    public KartaOtocit(int x, int y, Color farba) {
        super(x, y, farba, Znak.KARTA_OTOCIT);
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
//            if (hrac.getHra().jePoSmereHodinovychRuciciek()) {
//                hrac.getHra().setPoSmereHodinovychRuciciek(false);
//            } else {
//                hrac.getHra().setPoSmereHodinovychRuciciek(true);
//            }
            hrac.getHra().setPoSmereHodinovychRuciciek(!hrac.getHra().jePoSmereHodinovychRuciciek());
            return true;
        } else {
            return false;
        }
    }
}
