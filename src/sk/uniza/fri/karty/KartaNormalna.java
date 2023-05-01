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
    public boolean vykonajAkciu(Hrac hrac) {
        //Tu sa deje polymorfizmus
        //Ak máme takú istú farbu alebo znak našej karty, tak sa použije
        if (hrac.getHra().getBalicekPouzitychKariet().get(hrac.getHra().getBalicekPouzitychKariet().size() - 1).getFarba() == this.getFarba() ||
            hrac.getHra().getBalicekPouzitychKariet().get(hrac.getHra().getBalicekPouzitychKariet().size() - 1).getZnak() == this.getZnak()) {

            return true;
        } else {
            System.out.println("Kartu nie je možné položiť!!!");
            return false;
        }


    }
}
