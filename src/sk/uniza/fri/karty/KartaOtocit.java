package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import java.awt.Color;

/** Trieda KartaOtocit(potomok triedy Karta)
 *  @author Patrik Pavlík
 *  @version 1.23.15
 */
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

    /**
     * KartaOtocit – možno položiť na kartu ktorá má rovnakú farbu, otočí sa smer ktorým je ďalší hráč na rade
     * @param hrac - ktorý hráč ju ma použiť
     * @return true alebo false ak sa splní alebo nesplní podmienka
     */
    @Override
    public boolean vykonajAkciu(Hrac hrac) {
        //Polymorfizmus
        var predchazdajucaKarta = hrac.getHra().getBalikPouzitychUnoKariet().pocetKarietVBaliku() - 1;

        if (hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getFarba() == this.getFarba() ||
            hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getZnak() == this.getZnak()) {

            hrac.getHra().setPoSmereHodinovychRuciciek(!hrac.getHra().jePoSmereHodinovychRuciciek());
            return true;
        } else {
            return false;
        }
    }
}
