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
    public boolean vykonajAkciu(Hrac hrac) {
        //Tu sa deje polymorfizmus
        if (hrac.getHra().getBalicekPouzitychKariet().get(hrac.getHra().getBalicekPouzitychKariet().size() - 1).getFarba() == this.getFarba() ||
            hrac.getHra().getBalicekPouzitychKariet().get(hrac.getHra().getBalicekPouzitychKariet().size() - 1).getZnak() == this.getZnak()) {

            if (hrac.getHra().getPoSmereHodinovychRuciciek()) {
                hrac.getHra().setPoSmereHodinovychRuciciek(false);
                return true;
            } else {
                hrac.getHra().setPoSmereHodinovychRuciciek(true);
                return true;
            }
        } else {
            System.out.println("Kartu nie je mozne polozit");
            return false;
        }
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }
}
