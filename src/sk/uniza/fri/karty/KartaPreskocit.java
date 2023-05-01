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
        if (hrac.getHra().getBalicekPouzitychKariet().get(hrac.getHra().getBalicekPouzitychKariet().size() - 1).getFarba() == this.getFarba()) {

            hrac.getHra().dalsiHrac();
            return true;

        }
        System.out.println("Kartu nie je mozne polozit");
        return false;
    }

    @Override
    public void zmenPoziciu(int x, int y) {
        super.zmenPoziciu(x, y);
    }
}
