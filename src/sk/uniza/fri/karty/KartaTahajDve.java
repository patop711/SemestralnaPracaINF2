package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.shapesge.Rectangle;

import java.awt.Color;

public class KartaTahajDve extends Karta {
    public KartaTahajDve(int x, int y, Color farba) {
        super(x, y, farba, Znak.KARTA_TAHAJ_DVE);
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
        var predchazdajucaKarta = hrac.getHra().getBalicekPouzitychKariet().size() - 1;

        if (hrac.getHra().getBalicekPouzitychKariet().get(predchazdajucaKarta).getFarba() == this.getFarba() ||
            hrac.getHra().getBalicekPouzitychKariet().get(predchazdajucaKarta).getZnak() == this.getZnak()) {
            hrac.getHra().dajHracoviKartyPodlaPoctu(2, true);
            return true;
        }
        return false;
    }

}
