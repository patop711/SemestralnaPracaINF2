package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;

import java.awt.Color;

public class KartaNormalna extends Karta {

    public KartaNormalna(int x, int y, Color farba, Znak znak) {
        super(x, y, farba, znak);
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
        //Tu sa deje polymorfizmus
        //Ak máme takú istú farbu alebo znak našej karty, tak sa použije
        var predchazdajucaKarta = hrac.getHra().getBalikPouzitychUnoKariet().pocetKarietVBaliku() - 1;

        return hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getFarba() == this.getFarba() ||
               hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getZnak() == this.getZnak();
    }
}
