package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.shapesge.Rectangle;

import java.awt.Color;

public class KartaPreskocit extends Karta {
    public KartaPreskocit(int x, int y, Color farba) {
        super(x, y, farba, Znak.KARTA_PRESKOCIT);
    }

    @Override
    public Rectangle getVonkajsiaVrstva() {
        return super.getVonkajsiaVrstva();
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

        if (hrac.getHra().getBalikPouzitychUnoKariet().dajKartuNaIndexe(predchazdajucaKarta).getFarba() == this.getFarba()) {
            hrac.getHra().dalsiHrac();
            return true;
        }
        return false;
    }
}
