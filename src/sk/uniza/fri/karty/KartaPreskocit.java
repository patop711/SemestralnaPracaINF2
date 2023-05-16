package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;

import java.awt.Color;

/**
 * Trieda KartaPreskocit(potomok triedy Karta)
 *
 * @author Patrik Pavlík
 * @version 1.23.15
 */
public class KartaPreskocit extends Karta {
    public KartaPreskocit(int x, int y, Color farba) {
        super(x, y, farba, Znak.KARTA_PRESKOCIT);
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
     * KartaPreskocit – možno položiť na kartu ktorá má rovnakú farbu, hráč ktorý je na rade nebude mať možnosť ťahu
     * @param hrac - ktorý hráč ju ma použiť
     * @return true alebo false ak sa splní alebo nesplní podmienka
     */
    @Override
    public boolean vykonajAkciu(Hrac hrac) {
        //Polymorfizmus
        var predchazdajucaKarta = hrac.getHra().getBalikPouzitychUnoKariet().pocetKarietVBaliku() - 1;

        if (hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getFarba() == this.getFarba() ||
            hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getZnak() == this.getZnak()) {
            hrac.getHra().dalsiHrac();
            return true;
        }
        return false;
    }
}
