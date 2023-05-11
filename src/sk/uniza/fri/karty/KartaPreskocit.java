package sk.uniza.fri.karty;

import sk.uniza.fri.hrac.Hrac;

import java.awt.Color;

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
    public boolean vykonajAkciu(Hrac hrac) {
        //Polymorfizmus
        var predchazdajucaKarta = hrac.getHra().getBalicekPouzitychKariet().size() - 1;

        if (hrac.getHra().getBalicekPouzitychKariet().get(predchazdajucaKarta).getFarba() == this.getFarba()) {
            hrac.getHra().dalsiHrac();
            return true;
        }
        return false;
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }
}
