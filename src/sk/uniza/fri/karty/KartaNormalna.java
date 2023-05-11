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
        var predchazdajucaKarta = hrac.getHra().getBalicekPouzitychKariet().size() - 1;

        return hrac.getHra().getBalicekPouzitychKariet().get(predchazdajucaKarta).getFarba() == this.getFarba() ||
                hrac.getHra().getBalicekPouzitychKariet().get(predchazdajucaKarta).getZnak() == this.getZnak();


    }
}
